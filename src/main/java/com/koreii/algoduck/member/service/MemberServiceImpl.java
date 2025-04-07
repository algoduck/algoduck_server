package com.koreii.algoduck.member.service;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.koreii.algoduck.util.constants.Constants.validatePassword;
import static com.koreii.algoduck.util.constants.Constants.validatePolicies;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final FileStorageService fileStorageService;
  private final BCryptPasswordEncoder passwordEncoder;

  @Value("${spring.cloud.aws.s3.profile_bucket}")
  private String bucketName;

  @Value("${app.default.profile-image-url}")
  private String defaultProfileImageUrl;

  @Override
  public boolean isUniqueLoginId(String loginId) {
    return memberRepository.isUniqueLoginId(loginId);
  }

  @Override
  public boolean isUniqueNickname(String nickname) {
    return memberRepository.isUniqueNickname(nickname);
  }

  @Override
  public boolean isUniqueEmail(String email) {
    return memberRepository.isUniqueEmail(email);
  }

  @Override
  @Transactional
  public MemberResponseDto join(MemberSaveRequestDto memberSaveRequestDto, MultipartFile file) {
    validatePolicies(memberSaveRequestDto.getLoginId(), memberSaveRequestDto.getPassword(), memberSaveRequestDto.getNickname());

    //  비밀번호 해싱
    String hashedPassword = passwordEncoder.encode(memberSaveRequestDto.getPassword());
    memberSaveRequestDto.setPassword(hashedPassword);

    String profileImageUrl = fileStorageService.uploadFile(bucketName, "profile/" + memberSaveRequestDto.getLoginId(), file);

    log.info("join uploaded file url = {}", profileImageUrl);
    String submitProfileImageUrl = null;  //  DB에 저장할 url

    try {
      if (profileImageUrl == null) { //  회원 가입할 때 제출한 이미지 업로드가 실패한 경우
        log.warn("기본 이미지 사용");
        submitProfileImageUrl = defaultProfileImageUrl; //  기본 이미지로 설정
      } else {  //  제출한 이미지 업로드가 성공할 경우
        submitProfileImageUrl = profileImageUrl; //  해당 이미지로 설정
      }

      return memberRepository.save(memberSaveRequestDto, submitProfileImageUrl);
    } catch (Exception e) { //  회원 저장이 실패한 경우
      fileStorageService.deleteFile(bucketName, profileImageUrl);
      throw new MemberJoinException(memberSaveRequestDto.getLoginId() + " 회원 가입 실패", e);
    }
  }

  @Override
  public long countAllMembers() {
    return memberRepository.countAll();
  }

  @Override
  public List<MemberSimpleResponseDto> findAllMembers(int pageNumber, int pageSize) {
    return memberRepository.findAll(pageNumber, pageSize);
  }

  @Override
  public long countMembersWithLoginId(String loginId) {
    return memberRepository.countWithLoginId(loginId);
  }

  @Override
  public List<MemberSimpleResponseDto> findMembersWithLoginId(String loginId, int pageNumber, int pageSize) {
    return memberRepository.findWithLoginId(loginId, pageNumber, pageSize);
  }

  @Override
  public long countMembersWithNickname(String nickname) {
    return memberRepository.countWithNickname(nickname);
  }

  @Override
  public List<MemberSimpleResponseDto> findMembersWithNickname(String nickname, int pageNumber, int pageSize) {
    return memberRepository.findWithNickname(nickname, pageNumber, pageSize);
  }

  @Override
  public long countMembersWithRole(Role role) {
    return memberRepository.countWithRole(role);
  }

  @Override
  public List<MemberSimpleResponseDto> findMembersWithRole(Role role, int pageNumber, int pageSize) {
    return memberRepository.findByRole(role, pageNumber, pageSize);
  }

  @Override
  public MemberResponseDto findMemberByMemberId(Long memberId) {
    return memberRepository.findByMemberId(memberId);
  }

  @Override
  @Transactional
  public MemberResponseDto update(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto, MultipartFile file) {
    validatePassword(memberUpdateRequestDto.getPassword());

    //  비밀번호 해싱
    String hashedPassword = passwordEncoder.encode(memberUpdateRequestDto.getPassword());
    memberUpdateRequestDto.setPassword(hashedPassword);


    log.info("file = {}", file);

    String beforeProfileUrl = memberUpdateRequestDto.getBeforeProfileImageUrl();  //  기존 프로필 이미지 url
    String afterProfileUrl = fileStorageService.uploadFile(bucketName, "profile/" + memberUpdateRequestDto.getLoginId(), file);

    log.info("In MemberService update");
    log.info("beforeProfileFileUrl = {}", beforeProfileUrl);
    log.info("afterProfileUrl = {}", afterProfileUrl);

    log.info("update uploaded file url = {}", afterProfileUrl);
    String submitProfileUrl = null;

    MemberResponseDto memberResponseDto;

    try {
      //  업로드 실패 시 기존 이미지 사용
      submitProfileUrl = afterProfileUrl == null ? beforeProfileUrl : afterProfileUrl;
      memberResponseDto = memberRepository.update(memberId, memberUpdateRequestDto, submitProfileUrl);

      if (afterProfileUrl != null) { //  업로드가 성공했을 경우 기존 파일 삭제
        log.info("beforeProfileUrl = {}", beforeProfileUrl);
        fileStorageService.deleteFile(bucketName, beforeProfileUrl);
      }
      return memberResponseDto;
    } catch (Exception e) {  //  회원 업데이트가 실패한 경우
      throw new MemberUpdateException(memberUpdateRequestDto.getLoginId() + " 업데이트 실패", e);
    }
  }
}
