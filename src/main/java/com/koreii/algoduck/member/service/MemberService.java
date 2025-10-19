package com.koreii.algoduck.member.service;

import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.request.MemberUpdateRequestDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.member.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService {
  boolean isUniqueLoginId(String loginId);

  boolean isUniqueNickname(String nickname);

  boolean isUniqueEmail(String email);

  MemberResponseDto join(MemberSaveRequestDto memberSaveRequestDto, MultipartFile file);

  long countAllMembers();

  List<MemberSimpleResponseDto> findAllMembers(int pageNumber, int pageSize);

  long countMembersWithLoginId(String loginId);

  List<MemberSimpleResponseDto> findMembersWithLoginId(String loginId, int pageNumber, int pageSize);

  long countMembersWithNickname(String nickname);

  List<MemberSimpleResponseDto> findMembersWithNickname(String nickname, int pageNumber, int pageSize);

  Long countMembersWithSolvedCount(long minimum, long maximum);

  List<MemberSimpleResponseDto> findMembersWithSolvedCount(long minimum, long maximum, int pageNumber, int pageSize);

  Long countMembersWithRank(long rank);

  List<MemberSimpleResponseDto> findMembersWithRank(long rank, int pageNumber, int pageSize);

  long countMembersWithRole(Role role);

  List<MemberSimpleResponseDto> findMembersWithRole(Role role, int pageNumber, int pageSize);

  MemberResponseDto findDtoByMemberId(Long memberId);

  MemberSimpleResponseDto findSimpleDtoByMemberId(Long memberId);

  MemberResponseDto update(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto, MultipartFile file);

  Member login(String loginId, String password, HttpServletRequest request);

  void logout(HttpServletRequest request);

  MemberResponseDto getMyInfo(HttpSession session);
}
