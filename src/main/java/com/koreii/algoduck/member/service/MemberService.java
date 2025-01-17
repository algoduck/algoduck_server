package com.koreii.algoduck.member.service;

import com.koreii.algoduck.exceptions.LoginIdPolicyViolationException;
import com.koreii.algoduck.exceptions.NicknamePolicyViolationException;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.repository.MemberRepository;
import com.koreii.algoduck.util.constants.Constants;
import com.koreii.algoduck.util.validator.PolicyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

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
  public Long join(MemberSaveRequestDto memberSaveRequestDto) {
    if (!PolicyValidator.isValid(memberSaveRequestDto.getLoginId(), Constants.LOGIN_ID_POLICY)) {
      throw new LoginIdPolicyViolationException("로그인 아이디 정책에 맞지 않습니다.");
    }

    if (!PolicyValidator.isValid(memberSaveRequestDto.getPassword(), Constants.PASSWORD_POLICY)) {
      throw new NicknamePolicyViolationException("비밀번호 정책에 맞지 않습니다.");
    }

    if (!PolicyValidator.isValid(memberSaveRequestDto.getNickname(), Constants.NICKNAME_POLICY)) {
      throw new NicknamePolicyViolationException("닉네임 정책에 맞지 않습니다.");
    }

    return memberRepository.save(memberSaveRequestDto);
  }

  public List<MemberSimpleResponseDto> findAllMembers() {
    return memberRepository.findAll();
  }

  public List<MemberSimpleResponseDto> findMemberByLoginId(String loginId) {
    return memberRepository.findByLoginId(loginId);
  }
}
