package com.koreii.algoduck.member.repository;

import com.koreii.algoduck.member.dto.request.LoginRequestDto;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.request.MemberUpdateRequestDto;
import com.koreii.algoduck.member.dto.response.MemberResponseDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.member.enums.MemberStatus;
import com.koreii.algoduck.member.enums.Role;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositoryJPAImpl implements MemberRepository {
  private EntityManager entityManager;

  @Override
  public Long save(MemberSaveRequestDto memberSaveDto) {
    Member member = Member.builder()
        .loginId(memberSaveDto.getLoginId())
        .password(memberSaveDto.getPassword())
        .email(memberSaveDto.getEmail())
        .nickname(memberSaveDto.getNickname())
        .solved(0)
        .role(memberSaveDto.getRole())
        .profileImageUrl(null)
        .statusMessage(memberSaveDto.getStatusMessage())
        .memberStatus(MemberStatus.ACTIVE)
        .quitRequestTime(null)
        .build();

    entityManager.persist(member);
    return member.getMemberId();
  }

  @Override
  public boolean isUniqueLoginId(String loginId) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.loginId = :loginId";

    Long count = entityManager.createQuery(jpql, Long.class).getSingleResult();
    return count == 0;
  }

  @Override
  public boolean isUniqueNickname(String nickname) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.nickname = :nickname";

    Long count = entityManager.createQuery(jpql, Long.class).getSingleResult();
    return count == 0;
  }

  @Override
  public boolean isUniqueEmail(String email) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.email = :email";

    Long count = entityManager.createQuery(jpql, Long.class).getSingleResult();
    return count == 0;
  }

  @Override
  public List<MemberSimpleResponseDto> findAll() {
    String jpql = "SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m) "
        + "FROM Member m "
        + "ORDER BY m.solved DESC, m.createdAt ASC";

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class).getResultList();
  }

  @Override
  public List<MemberSimpleResponseDto> findByLoginId(String loginId) {
    String jpql = "SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m) "
        + "FROM Member m "
        + "WHERE m.loginId = loginId "
        + "ORDER BY m.solved DESC, m.createdAt ASC";

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class).getResultList();
  }

  @Override
  public List<MemberSimpleResponseDto> findByNickname(String nickname) {
    String jpql = "SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m) "
        + "FROM Member m "
        + "WHERE m.nickname = nickname "
        + "ORDER BY m.solved DESC, m.createdAt ASC";

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class).getResultList();
  }

  @Override
  public List<MemberSimpleResponseDto> findByRole(Role role) {
    String jpql = "SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m) "
        + "FROM Member m "
        + "WHERE m.role = role "
        + "ORDER BY m.solved DESC, m.createdAt ASC";

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class).getResultList();
  }

  @Override
  public MemberResponseDto findByMemberId(Long memberId) {
    Member member = entityManager.find(Member.class, memberId);

    return new MemberResponseDto(member);
  }

  @Override
  public MemberResponseDto login(LoginRequestDto loginRequestDto) {
    throw new UnsupportedOperationException("미구현된 메서드");
  }

  @Override
  public MemberResponseDto update(MemberUpdateRequestDto updateRequestDto) {
    Member member = entityManager.find(Member.class, updateRequestDto);
    member.setPassword(updateRequestDto.getPassword());
    member.setStatusMessage(updateRequestDto.getStatusMessage());
    return new MemberResponseDto(member);
  }

  @Override
  public void quit(Long memberId) {
    throw new UnsupportedOperationException("미구현된 메서드");
  }

  @Override
  public void physicalDeleteWaitingMembers() {
    throw new UnsupportedOperationException("미구현된 메서드");
  }

  @Override
  public void logicalDeleteQuitMembers() {
    throw new UnsupportedOperationException("미구현된 메서드");
  }
}
