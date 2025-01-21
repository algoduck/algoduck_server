package com.koreii.algoduck.member.service;

import com.koreii.algoduck.exceptions.file.FileUploadFailException;
import com.koreii.algoduck.exceptions.member.MemberJoinException;
import com.koreii.algoduck.exceptions.member.MemberUpdateException;
import com.koreii.algoduck.file.FileStorageService;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.request.MemberUpdateRequestDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.enums.Role;
import com.koreii.algoduck.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.koreii.algoduck.util.constants.Constants.validatePassword;
import static com.koreii.algoduck.util.constants.Constants.validatePolicies;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl {
  private final MemberRepository memberRepository;
  private final FileStorageService fileStorageService;

  @Value("${spring.cloud.aws.s3.profile_bucket}")
  private String bucketName;

  @Value("${app.default.profile-image-url}")
  private String defaultProfileImageUrl;

  public boolean isUniqueLoginId(String loginId) {
    return memberRepository.isUniqueLoginId(loginId);
  }

  public boolean isUniqueNickname(String nickname) {
    return memberRepository.isUniqueNickname(nickname);
  }

  public boolean isUniqueEmail(String email) {
    return memberRepository.isUniqueEmail(email);
  }

  @Transactional
  public MemberResponseDto join(MemberSaveRequestDto memberSaveRequestDto, MultipartFile file) {
    validatePolicies(memberSaveRequestDto.getLoginId(), memberSaveRequestDto.getPassword(), memberSaveRequestDto.getNickname());

    String profileImageUrl = fileUpload("profile/" + memberSaveRequestDto.getLoginId(), file);

    try {
      if (profileImageUrl == null) { //  회원 가입할 때 제출한 이미지 업로드가 실패한 경우
        log.warn("기본 이미지 사용");
        memberSaveRequestDto.setProfileImageUrl(defaultProfileImageUrl); //  기본 이미지로 설정
      } else {  //  제출한 이미지 업로드가 성공할 경우
        memberSaveRequestDto.setProfileImageUrl(profileImageUrl); //  해당 이미지로 설정
      }

      return memberRepository.save(memberSaveRequestDto);
    } catch (Exception e) { //  회원 저장이 실패한 경우
      deleteFileIfNotNull(profileImageUrl);
      throw new MemberJoinException(memberSaveRequestDto.getLoginId() + " 회원 가입 실패", e);
    }
  }

  public long countAllMembers() {
    return memberRepository.countAll();
  }

  public List<MemberSimpleResponseDto> findAllMembers(int pageNumber, int pageSize) {
    return memberRepository.findAll(pageNumber, pageSize);
  }

  public long countMembersWithLoginId(String loginId) {
    return memberRepository.countWithLoginId(loginId);
  }

  public List<MemberSimpleResponseDto> findMembersWithLoginId(String loginId, int pageNumber, int pageSize) {
    return memberRepository.findWithLoginId(loginId, pageNumber, pageSize);
  }

  public long countMembersWithNickname(String nickname) {
    return memberRepository.countWithNickname(nickname);
  }

  public List<MemberSimpleResponseDto> findMembersWithNickname(String nickname, int pageNumber, int pageSize) {
    return memberRepository.findWithNickname(nickname, pageNumber, pageSize);
  }

  public long countMembersWithRole(Role role) {
    return memberRepository.countWithRole(role);
  }

  public List<MemberSimpleResponseDto> findMembersWithRole(Role role, int pageNumber, int pageSize) {
    return memberRepository.findByRole(role, pageNumber, pageSize);
  }

  public MemberResponseDto findMemberByLoginId(Long memberId) {
    return memberRepository.findByMemberId(memberId);
  }

  @Transactional
  public MemberResponseDto update(MemberUpdateRequestDto memberUpdateRequestDto, MultipartFile file) {
    validatePassword(memberUpdateRequestDto.getPassword());

    String beforeProfileUrl = memberUpdateRequestDto.getProfileImageUrl();  //  기존 프로필 이미지 url
    String afterProfileUrl = fileUpload("profile/" + memberUpdateRequestDto.getLoginId(), file);

    MemberResponseDto memberResponseDto;

    try {
      //  업로드 실패 시 기존 이미지 사용
      memberUpdateRequestDto.setProfileImageUrl(afterProfileUrl == null ? beforeProfileUrl : afterProfileUrl);
      log.warn("기존에 사용하던 이미지 사용");
      memberResponseDto = memberRepository.update(memberUpdateRequestDto);

      if (afterProfileUrl != null) { //  업로드가 성공했을 경우 기존 파일 삭제
        deleteFileIfNotNull(beforeProfileUrl);
      }
      return memberResponseDto;
    } catch (Exception e) {  //  회원 업데이트가 실패한 경우
      throw new MemberUpdateException(memberUpdateRequestDto.getLoginId() + " 업데이트 실패", e);
    }
  }

  private String fileUpload(String folderPath, MultipartFile file) {
    if (file == null) {
      return null;
    }

    try {
      return fileStorageService.uploadFile(bucketName, folderPath, file);
    } catch (FileUploadFailException e) {
      log.warn("Failed to upload file to Folder: {}", folderPath, e);
      return null;
    }
  }

  private void deleteFileIfNotNull(String fileUrl) {
    if (fileUrl != null) {
      try {
        fileStorageService.deleteFile(bucketName, fileUrl);
      } catch (Exception e) {
        log.error("Failed to delete file from {}", fileUrl, e);
      }
    }
  }
}
