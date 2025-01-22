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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryJpaImpl implements MemberRepository {
  private final EntityManager entityManager;

  @Override
  public MemberResponseDto save(MemberSaveRequestDto memberSaveDto, String profileImageUrl) {
    Member member = Member.builder()
        .loginId(memberSaveDto.getLoginId())
        .password(memberSaveDto.getPassword())
        .email(memberSaveDto.getEmail())
        .nickname(memberSaveDto.getNickname())
        .solved(0)
        .role(memberSaveDto.getRole())
        .profileImageUrl(profileImageUrl)
        .statusMessage(memberSaveDto.getStatusMessage())
        .memberStatus(MemberStatus.ACTIVE)
        .quitRequestTime(null)
        .build();

    entityManager.persist(member);
    return new MemberResponseDto(member);
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

    int count = entityManager.createQuery(jpql, Integer.class).getSingleResult();
    return count == 0;
  }

  @Override
  public boolean isUniqueEmail(String email) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.email = :email";

    int count = entityManager.createQuery(jpql, Integer.class).getSingleResult();
    return count == 0;
  }

  @Override
  public long countAll() {
    String jpql = "SELECT COUNT(m) FROM Member m";
    return entityManager.createQuery(jpql, Long.class).getSingleResult();
  }

  @Override
  public List<MemberSimpleResponseDto> findAll(int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;

    String jpql = "SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m) "
        + "FROM Member m "
        + "ORDER BY m.solved DESC, m.createdAt ASC";

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

  @Override
  public long countWithLoginId(String loginId) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.loginId LIKE :loginId";
    return entityManager.createQuery(jpql, Long.class).setParameter("loginId", "%" + loginId + "%").getSingleResult();
  }

  @Override
  public List<MemberSimpleResponseDto> findWithLoginId(String loginId, int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;
    String jpql = "SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m) "
        + "FROM Member m "
        + "WHERE m.loginId = :loginId "
        + "ORDER BY m.solved DESC, m.createdAt ASC";

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class)
        .setParameter("loginId", loginId)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

  @Override
  public long countWithNickname(String nickname) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.nickname LIKE :nickname";
    return entityManager.createQuery(jpql, Long.class).setParameter("nickname", "%" + nickname + "%").getSingleResult();
  }

  @Override
  public List<MemberSimpleResponseDto> findWithNickname(String nickname, int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;
    String jpql = "SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m) "
        + "FROM Member m "
        + "WHERE m.nickname = :nickname "
        + "ORDER BY m.solved DESC, m.createdAt ASC";

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class)
        .setParameter("nickname", nickname)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

  @Override
  public long countWithRole(Role role) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.role = :role";
    return entityManager.createQuery(jpql, Long.class).setParameter("role", role).getSingleResult();
  }

  @Override
  public List<MemberSimpleResponseDto> findByRole(Role role, int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;
    String jpql = "SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m) "
        + "FROM Member m "
        + "WHERE m.role = :role "
        + "ORDER BY m.solved DESC, m.createdAt ASC";

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class)
        .setParameter("role", role)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
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
  public MemberResponseDto update(MemberUpdateRequestDto updateRequestDto, String profileImageUrl) {
    Member member = entityManager.find(Member.class, updateRequestDto);
    member.setPassword(updateRequestDto.getPassword());
    member.setStatusMessage(updateRequestDto.getStatusMessage());
    member.setPassword(profileImageUrl);
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
