package com.koreii.algoduck.member.service;

import com.koreii.algoduck.exceptions.member.LoginFailureException;
import com.koreii.algoduck.exceptions.member.MemberJoinException;
import com.koreii.algoduck.exceptions.member.MemberUpdateException;
import com.koreii.algoduck.file.FileStorageService;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.request.MemberUpdateRequestDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.member.enums.Role;
import com.koreii.algoduck.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.koreii.algoduck.util.constants.Constants.validatePassword;
import static com.koreii.algoduck.util.constants.Constants.validatePolicies;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final LoginService loginService;
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

    String submitProfileImageUrl = defaultProfileImageUrl;  //  DB에 저장할 url
    MemberResponseDto memberResponseDto = null;

    try {
      memberResponseDto = memberRepository.save(memberSaveRequestDto, submitProfileImageUrl);
    } catch (Exception e) {
      throw new MemberJoinException(memberSaveRequestDto.getLoginId() + " 회원가입 실패, " + e);
    }

    Long memberId = memberResponseDto.getMemberId();

    CompletableFuture<String> upload = fileStorageService.uploadFile(bucketName, "profile/" + memberSaveRequestDto.getLoginId(), file);
    upload.thenAccept(profileImageUrl -> {
      log.info("S3 업로드 완료: {}", profileImageUrl);
      memberRepository.updateProfileImageUrl(memberId, profileImageUrl);
    }).exceptionally(ex -> {
      log.error("S3 이미지 업로드 실패", ex);
      return null;
    });

    return memberResponseDto;
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
  public MemberResponseDto findDtoByMemberId(Long memberId) {
    return new MemberResponseDto(memberRepository.findByMemberId(memberId));
  }

  @Override
  public MemberSimpleResponseDto findSimpleDtoByMemberId(Long memberId) {
    return new MemberSimpleResponseDto(memberRepository.findByMemberId(memberId));
  }

  @Override
  @Transactional
  public MemberResponseDto update(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto, MultipartFile file) {
    log.info("update");
    log.info("memberUpdateRequestDto.beforeUrl = {}", memberUpdateRequestDto.getBeforeProfileImageUrl());

    if (!(memberUpdateRequestDto.getPassword() == null) && !memberUpdateRequestDto.getPassword().equals("")) {
      validatePassword(memberUpdateRequestDto.getPassword());

      log.info("after validatePassword");

      //  비밀번호 해싱
      String hashedPassword = passwordEncoder.encode(memberUpdateRequestDto.getPassword());
      memberUpdateRequestDto.setPassword(hashedPassword);
    }

    String beforeProfileUrl = memberUpdateRequestDto.getBeforeProfileImageUrl();  //  기존 프로필 이미지 url
    MemberResponseDto memberResponseDto = null;

    try {
      memberResponseDto = memberRepository.update(memberId, memberUpdateRequestDto);
    } catch (Exception e) {
      throw new MemberUpdateException(memberUpdateRequestDto.getLoginId() + " 회원 업데이트 실패", e);
    }

    log.info("file = {}", file);

    CompletableFuture<String> upload = fileStorageService.uploadFile(bucketName, "profile/" + memberUpdateRequestDto.getLoginId(), file);
    upload.thenAccept(profileImageUrl -> {
      log.info("S3 업로드 완료: {}", profileImageUrl);
      memberRepository.updateProfileImageUrl(memberId, profileImageUrl);
    }).exceptionally(ex -> {
      log.error("S3 이미지 업데이트 실패", ex);
      memberRepository.updateProfileImageUrl(memberId, beforeProfileUrl); //  기존 프로필 이미지로 대체
      return null;
    });

    //  기본 프로필 이미지 URL의 경우 제거하지 않음
    if (!beforeProfileUrl.equals(defaultProfileImageUrl)) {
      CompletableFuture<Void> delete = fileStorageService.deleteFile(bucketName, beforeProfileUrl);
      delete.thenRun(() -> {
        log.info("S3 기존 이미지 제거 완료: {}", beforeProfileUrl);
      }).exceptionally(ex -> {
        log.error("S3 기존 이미지 제거 실패", ex);
        return null;
      });
    }

    return memberResponseDto;
  }

  @Override
  public MemberResponseDto login(String loginId, String password, HttpServletRequest request) {
    return loginService.login(loginId, password, request);
  }

  @Override
  public void logout(HttpServletRequest request) {
    loginService.logout(request);
  }
}
