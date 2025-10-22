package com.koreii.algoduck.member.repository;

import com.koreii.algoduck.member.dto.request.LoginRequestDto;
import com.koreii.algoduck.member.dto.request.MemberSaveRequestDto;
import com.koreii.algoduck.member.dto.request.MemberUpdateRequestDto;
import com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto;
import com.koreii.algoduck.member.entity.Member;
import com.koreii.algoduck.member.enums.MemberStatus;
import com.koreii.algoduck.member.enums.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepositoryJpaImpl implements MemberRepository {
  private final EntityManager entityManager;

  @Override
  public Member save(MemberSaveRequestDto memberSaveDto, String profileImageUrl) {
    log.info("in save, profileImageUrl = {}", profileImageUrl);

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
    return member;
  }

  @Override
  public boolean isUniqueLoginId(String loginId) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.loginId = :loginId";

    Long count = entityManager.createQuery(jpql, Long.class).setParameter("loginId", loginId).getSingleResult();
    return count == 0;
  }

  @Override
  public boolean isUniqueNickname(String nickname) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.nickname = :nickname";

    Long count = entityManager.createQuery(jpql, Long.class).setParameter("nickname", nickname).getSingleResult();
    return count == 0;
  }

  @Override
  public boolean isUniqueEmail(String email) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.email = :email";

    Long count = entityManager.createQuery(jpql, Long.class).setParameter("email", email).getSingleResult();
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
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.loginId  LIKE CONCAT(:loginId, '%')";
    return entityManager.createQuery(jpql, Long.class).setParameter("loginId", "%" + loginId + "%").getSingleResult();
  }

  @Override
  public List<MemberSimpleResponseDto> findWithLoginId(String loginId, int pageNumber, int pageSize) {
    log.info("loginId = {}", loginId);

    int offset = (pageNumber - 1) * pageSize;
    String jpql = """
        SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m)
        FROM Member m
        WHERE m.loginId LIKE CONCAT(:loginId, '%')
        ORDER BY m.solved DESC, m.createdAt ASC
        """;

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class)
        .setParameter("loginId", loginId)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

  @Override
  public long countWithNickname(String nickname) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.nickname LIKE CONCAT(:nickname, '%')";
    return entityManager.createQuery(jpql, Long.class)
        .setParameter("nickname", nickname)
        .getSingleResult();
  }

  @Override
  public List<MemberSimpleResponseDto> findWithNickname(String nickname, int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;
    String jpql = """
        SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m)
        FROM Member m
        WHERE m.nickname LIKE CONCAT(:nickname, '%')
        ORDER BY m.solved DESC, m.createdAt ASC
        """;

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class)
        .setParameter("nickname", nickname)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

  @Override
  public long countMembersWithSolvedCount(long minimum, long maximum) {
    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.solved BETWEEN :min AND :max";
    return entityManager.createQuery(jpql, Long.class)
        .setParameter("min", minimum)
        .setParameter("max", maximum)
        .getSingleResult();
  }

  @Override
  public List<MemberSimpleResponseDto> findMembersWithSolvedCount(long minimum, long maximum, int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;

    String jpql = "SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m) " +
        "FROM Member m " +
        "WHERE m.solved BETWEEN :min AND :max " +
        "ORDER BY m.solved DESC, m.createdAt ASC";

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class)
        .setParameter("min", minimum)
        .setParameter("max", maximum)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

  @Override
  public long countMembersWithRank(long rank) {
    // 해당 랭크의 푼 문제 수를 가진 인원 수 반환
    Long solvedCountAtRank = getSolvedCountAtRank(rank);
    if (solvedCountAtRank == null) {
      return 0L;
    }

    String jpql = "SELECT COUNT(m) FROM Member m WHERE m.solved = :solved";
    return entityManager.createQuery(jpql, Long.class)
        .setParameter("solved", solvedCountAtRank)
        .getSingleResult();
  }

  @Override
  public List<MemberSimpleResponseDto> findMembersWithRank(long rank, int pageNumber, int pageSize) {
    // 1️rank번째 solved 수 구하기
    Long solvedCountAtRank = getSolvedCountAtRank(rank);
    if (solvedCountAtRank == null) {
      return List.of();
    }

    // 해당 solvedCount 가진 모든 회원 조회
    int offset = (pageNumber - 1) * pageSize;

    String jpql = "SELECT new com.koreii.algoduck.member.dto.response.MemberSimpleResponseDto(m) " +
        "FROM Member m " +
        "WHERE m.solved = :solved " +
        "ORDER BY m.createdAt ASC";

    return entityManager.createQuery(jpql, MemberSimpleResponseDto.class)
        .setParameter("solved", solvedCountAtRank)
        .setFirstResult(offset)
        .setMaxResults(pageSize)
        .getResultList();
  }

  private Long getSolvedCountAtRank(long rank) {
    String jpql = "SELECT DISTINCT m.solved FROM Member m " +
        "ORDER BY m.solved DESC, m.createdAt ASC";

    List<Long> solvedDistinctList = entityManager.createQuery(jpql, Long.class)
        .setFirstResult((int) (rank - 1))
        .setMaxResults(1)
        .getResultList();

    return solvedDistinctList.isEmpty() ? null : solvedDistinctList.get(0);
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
  public Member findByMemberId(Long memberId) {
    Member member = entityManager.find(Member.class, memberId);

    return member;
  }

  @Override
  public Member login(LoginRequestDto loginRequestDto) {
    throw new UnsupportedOperationException("미구현된 메서드");
  }

  @Override
  @Transactional
  public Member update(Long memberId, MemberUpdateRequestDto updateRequestDto) {
    log.info("password = {}", updateRequestDto.getPassword());

    Member member = entityManager.find(Member.class, memberId);

    if (updateRequestDto.getPassword() != null && !updateRequestDto.getPassword().isEmpty()) {
      member.setPassword(updateRequestDto.getPassword());
    }
    member.setStatusMessage(updateRequestDto.getStatusMessage());
    return member;
  }

  @Override
  public void updateProfileImageUrl(Long memberId, String profileImageUrl) {
    entityManager.createQuery("UPDATE Member m SET m.profileImageUrl = :url WHERE m.memberId = :id")
        .setParameter("url", profileImageUrl)
        .setParameter("id", memberId)
        .executeUpdate();
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

  @Override
  public Optional<Member> findByLoginId(String loginId) {
    String jpql = "SELECT m FROM Member m WHERE m.loginId = :loginId";

    try {
      Member member = entityManager.createQuery(jpql, Member.class)
          .setParameter("loginId", loginId)
          .getSingleResult();

      return Optional.of(member);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}
