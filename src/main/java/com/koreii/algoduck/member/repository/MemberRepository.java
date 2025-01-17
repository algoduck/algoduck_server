package com.koreii.algoduck.member.repository;

import com.koreii.algoduck.member.dto.request.LoginRequestDto;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.request.MemberUpdateRequestDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.enums.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository {
  //  CRUD
  Long save(MemberSaveRequestDto memberSaveDto); //  회원가입, 저장 후 PK 반환

  boolean isUniqueLoginId(String loginId); //  loginId가 유일한지 확인

  boolean isUniqueNickname(String nickname); //  nickname이 유일한지 확인

  boolean isUniqueEmail(String email); //  email이 유일한지 확인

  List<MemberSimpleResponseDto> findAll();  //  전체 회원 리스트를 푼 문제 순, 가입 시간 순으로 보여주기 (100명 단위로 페이지네이션)

  List<MemberSimpleResponseDto> findByLoginId(String loginId);    //  로그인 아이디에 loginId가 포함된 회원 리스트를 보여주기 (100명 단위로 페이지네이션)

  List<MemberSimpleResponseDto> findByNickname(String nickname);  //  닉네임에 nickname이 포함돤 회원 리스트를 보여주기 (100명 단위로 페이지네이션)

  List<MemberSimpleResponseDto> findByRole(Role role);            //  역할이 role인 회원 리스트를 보여주기 (100명 단위로 페이지네이션)

  MemberResponseDto findByMemberId(Long memberId);               //  특정 회원 한 명의 상세정보를 PK로 가져오기

  MemberResponseDto login(LoginRequestDto loginRequestDto);                //  로그인 결과로 로그인한 회원 정보를 반환함

  MemberResponseDto update(MemberUpdateRequestDto updateRequestDto);       //  회원 정보를 갱신함

  void quit(Long memberId);   //  회원 탈퇴를 요청하면 해당 회원의 상태를 PENDING_DELETION으로 변경함

  void physicalDeleteWaitingMembers();  //  회원가입 요청은 했지만 가입 유예 기간 동안 이메일 인증 안한 회원들을 실제로 제거함

  void logicalDeleteQuitMembers();      //  회원 탈퇴 요청 후 탈퇴 유예 기간이 지난 회원들을 논리적으로 탈퇴처리
}
