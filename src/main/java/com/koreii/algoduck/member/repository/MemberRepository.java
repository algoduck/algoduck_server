package com.koreii.algoduck.member.repository;

import com.koreii.algoduck.member.dto.request.LoginRequestDto;
import com.koreii.algoduck.member.dto.request.MemberUpdateRequestDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.response.MemberSimpeResponseDto;
import com.koreii.algoduck.member.enums.Role;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
  //  CRUD
  Long save(MemberSaveRequestDto memberSaveDto); //  회원가입, 저장 후 PK 반환

  boolean isValidLoginId(String loginId); //  loginId가 유일한지 않는지, 아이디 정책에 맞는지 확인

  boolean isValidNickname(String nickname); //  nickname이 유일한지, 닉네임 정책에 맞는지 확인

  boolean isValidEmail(String email); //  email이 유일한지 확인

  boolean isValidPassword(String password); //  password가 비밀번호 정책에 맞는지 확인

  List<MemberSimpeResponseDto> findAll();  //  전체 회원 리스트를 푼 문제 순, 가입 시간 순으로 보여주기 (100명 단위로 페이지네이션)

  Optional<List<MemberSimpeResponseDto>> findByLoginId(String loginId);    //  로그인 아이디에 loginId가 포함된 회원 리스트를 보여주기 (100명 단위로 페이지네이션)

  Optional<List<MemberSimpeResponseDto>> findByNickname(String nickname);  //  닉네임에 nickname이 포함돤 회원 리스트를 보여주기 (100명 단위로 페이지네이션)

  Optional<List<MemberSimpeResponseDto>> findByRole(Role role);            //  역할이 role인 회원 리스트를 보여주기 (100명 단위로 페이지네이션)

  Optional<MemberResponseDto> findByMemberId(Long memberId);               //  특정 회원 한 명의 상세정보를 PK로 가져오기

  MemberResponseDto login(LoginRequestDto loginRequestDto);                //  로그인 결과로 로그인한 회원 정보를 반환함

  MemberResponseDto update(MemberUpdateRequestDto updateRequestDto);       //  회원 정보를 갱신함

  void quit(Long memberId);   //  회원 탈퇴하면 해당 회원의 상태를 변경함
}
