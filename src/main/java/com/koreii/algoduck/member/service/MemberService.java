package com.koreii.algoduck.member.service;

import com.koreii.algoduck.exceptions.FileUploadFailException;
import com.koreii.algoduck.exceptions.LoginIdPolicyViolationException;
import com.koreii.algoduck.exceptions.NicknamePolicyViolationException;
import com.koreii.algoduck.exceptions.member.MemberJoinException;
import com.koreii.algoduck.exceptions.member.MemberUpdateException;
import com.koreii.algoduck.file.FileStorageService;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.request.MemberUpdateRequestDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.enums.Role;
import com.koreii.algoduck.member.repository.MemberRepository;
import com.koreii.algoduck.util.constants.Constants;
import com.koreii.algoduck.util.validator.PolicyValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.koreii.algoduck.util.constants.Constants.LOGIN_ID_POLICY;
import static com.koreii.algoduck.util.constants.Constants.LOGIN_ID_POLICY_VIOLATION;
import static com.koreii.algoduck.util.constants.Constants.PASSWORD_POLICY;
import static com.koreii.algoduck.util.constants.Constants.PASSWORD_POLICY_VIOLATION;
import static com.koreii.algoduck.util.constants.Constants.NICKNAME_POLICY;
import static com.koreii.algoduck.util.constants.Constants.NICKNAME_POLICY_VIOLATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
  private final MemberRepository memberRepository;
  private final FileStorageService fileStorageService;

  @Value("${spring.cloud.aws.s3.profile_bucket}")
  private String bucketName;

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
    validatePolicies(memberSaveRequestDto);

    String profileImageUrl = null;

    try {
      if (file != null) {
        try {
          profileImageUrl = fileStorageService.uploadFile(bucketName, "profile/" + memberSaveRequestDto.getLoginId(), file);
          memberSaveRequestDto.setProfileImageUrl(profileImageUrl);
        } catch (FileUploadFailException e) {
          log.warn("Failed to upload profile image for member {}'s join:", memberSaveRequestDto.getLoginId(), e);
        }
      }

      return memberRepository.save(memberSaveRequestDto);
    } catch (Exception e) { //  회원 저장이 실패한 경우
      if (profileImageUrl != null) { //  파일 업로드는 성공한 경우
        try {
          fileStorageService.deleteFile(bucketName, profileImageUrl);
        } catch (Exception de) {
          log.error("Failed to delete profile image from S3 after join failure: {}", profileImageUrl, de);
        }
      }

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

  private MemberResponseDto findMemberByLoginId(Long memberId) {
    return memberRepository.findByMemberId(memberId);
  }

  @Transactional
  public MemberResponseDto update(MemberUpdateRequestDto memberUpdateRequestDto, MultipartFile file) {
    if (!PolicyValidator.isValid(memberUpdateRequestDto.getPassword(), PASSWORD_POLICY)) {
      throw new NicknamePolicyViolationException(PASSWORD_POLICY_VIOLATION);
    }

    MemberResponseDto memberResponseDto = null;

    try {
      if (file != null) {
        String beforeProfileUrl = memberUpdateRequestDto.getProfileImageUrl();  //  기존 프로필 이미지 url

        try {
          String afterProfileUrl = fileStorageService.uploadFile(bucketName, "profile/" + memberUpdateRequestDto.getLoginId(), file);
          memberUpdateRequestDto.setProfileImageUrl(afterProfileUrl);

          if (beforeProfileUrl != null) {
            fileStorageService.deleteFile(bucketName, beforeProfileUrl);  // 업데이트가 성공할 경우 기존 파일 제거
          }
        } catch (FileUploadFailException e) {
          log.warn("Failed to upload profile image for member {}'s join:", memberUpdateRequestDto.getLoginId(), e);
        }
      }

      memberResponseDto = memberRepository.update(memberUpdateRequestDto);

    } catch (Exception e) {  //  회원 업데이트가 실패한 경우
      throw new MemberUpdateException(memberUpdateRequestDto.getLoginId() + " 업데이트 실패", e);
    }

    return memberResponseDto;
  }

  private static void validatePolicies(MemberSaveRequestDto memberSaveRequestDto) {
    if (!PolicyValidator.isValid(memberSaveRequestDto.getLoginId(), LOGIN_ID_POLICY)) {
      throw new LoginIdPolicyViolationException(LOGIN_ID_POLICY_VIOLATION);
    }

    if (!PolicyValidator.isValid(memberSaveRequestDto.getPassword(), PASSWORD_POLICY)) {
      throw new NicknamePolicyViolationException(PASSWORD_POLICY_VIOLATION);
    }

    if (!PolicyValidator.isValid(memberSaveRequestDto.getNickname(), NICKNAME_POLICY)) {
      throw new NicknamePolicyViolationException(NICKNAME_POLICY_VIOLATION);
    }
  }
}
