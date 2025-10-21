package com.koreii.algoduck.submission.repository;

import com.koreii.algoduck.base.dto.page.PageResponse;
import com.koreii.algoduck.member.repository.MemberRepository;
import com.koreii.algoduck.problem.repository.ProblemRepository;
import com.koreii.algoduck.submission.dto.request.SubmissionSaveRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionUpdateRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import com.koreii.algoduck.submission.entity.Submission;
import com.koreii.algoduck.submission.enums.Status;
import com.koreii.algoduck.version.repository.VersionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SubmissionRepositoryJpaImpl implements SubmissionRepository {
  private final EntityManager entityManager;
  private final MemberRepository memberRepository;
  private final ProblemRepository problemRepository;
  private final VersionRepository versionRepository;

  @Override
  public Submission saveSubmission(SubmissionSaveRequestDto submissionSaveRequestDto) {
    Submission submission = Submission.builder()
        .member(memberRepository.findByMemberId(submissionSaveRequestDto.getMemberId()))
        .status(Status.JUDGING)
        .message("채점 중입니다.")
        .problem(problemRepository.findByProblemId(submissionSaveRequestDto.getProblemId()))
        .version(versionRepository.findByVersionId(submissionSaveRequestDto.getVersionId()))
        .build();

    entityManager.persist(submission);
    return submission;
  }

  @Override
  public Submission findBySubmissionId(Long submissionId) {
    return entityManager.find(Submission.class, submissionId);
  }

  @Override
  @Transactional
  public Submission updateSubmission(SubmissionUpdateRequestDto submissionUpdateRequestDto) {
    log.info("submissionUpdateRequestDto = {}", submissionUpdateRequestDto);

    Submission submission = entityManager.find(Submission.class, submissionUpdateRequestDto.getSubmissionId());
    if (submissionUpdateRequestDto.getCodeName() != null) {
      submission.setCodeName(submissionUpdateRequestDto.getCodeName());
    }
    if (submissionUpdateRequestDto.getCodeUrl() != null) {
      submission.setCodeUrl(submissionUpdateRequestDto.getCodeUrl());
    }
    if (submissionUpdateRequestDto.getStatus() != null) {
      submission.setStatus(submissionUpdateRequestDto.getStatus());
    }
    if (submissionUpdateRequestDto.getMessage() != null) {
      submission.setMessage(submissionUpdateRequestDto.getMessage());
    }
    // 아래 두 줄 중복/오타가 있었음. message를 두 번 set 하고 있었음.
    if (submissionUpdateRequestDto.getExecutionTime() != null) {
      submission.setExecutionTime(submissionUpdateRequestDto.getExecutionTime());
    }
    if (submissionUpdateRequestDto.getMemoryUsage() != null) {
      submission.setMemoryUsage(submissionUpdateRequestDto.getMemoryUsage());
    }
    return submission;
  }

  @Override
  public long countAll() {
    String jpql = "SELECT COUNT(s) FROM Submission s";
    return entityManager.createQuery(jpql, Long.class).getSingleResult();
  }

  // 최신 페이지(다음 페이지) : OK (이미 필드 프로젝션 + JOIN 적용됨)
  @Override
  public PageResponse<SubmissionResponseDto> findNextPage(Long lastSeenId, int pageSize) {
    String jpql = """
        SELECT new com.koreii.algoduck.submission.dto.response.SubmissionResponseDto(
            s.submissionId,
            m.memberId,
            m.loginId,
            m.nickname,
            p.problemId,
            p.title,
            s.status,
            s.message,
            s.executionTime,
            s.memoryUsage,
            s.codeName,
            s.codeUrl
        )
        FROM Submission s
        JOIN s.member m
        JOIN s.problem p
        WHERE (:lastId IS NULL OR s.submissionId < :lastId)
        ORDER BY s.submissionId DESC
        """;

    List<SubmissionResponseDto> result = entityManager.createQuery(jpql, SubmissionResponseDto.class)
        .setParameter("lastId", lastSeenId)
        .setMaxResults(pageSize + 1)
        .getResultList();

    boolean hasNext = result.size() > pageSize;
    if (hasNext) {
      result.remove(pageSize);
    }

    return PageResponse.of(result, hasNext, lastSeenId != null);
  }

  // 이전 페이지: N+1 제거 (필드 프로젝션 + JOIN, ASC 조회 후 reverse)
  @Override
  public PageResponse<SubmissionResponseDto> findPrevPage(Long firstSeenId, int pageSize) {
    String jpql = """
        SELECT new com.koreii.algoduck.submission.dto.response.SubmissionResponseDto(
            s.submissionId,
            m.memberId,
            m.loginId,
            m.nickname,
            p.problemId,
            p.title,
            s.status,
            s.message,
            s.executionTime,
            s.memoryUsage,
            s.codeName,
            s.codeUrl
        )
        FROM Submission s
        JOIN s.member m
        JOIN s.problem p
        WHERE s.submissionId > :firstId
        ORDER BY s.submissionId ASC
        """;

    List<SubmissionResponseDto> result = entityManager.createQuery(jpql, SubmissionResponseDto.class)
        .setParameter("firstId", firstSeenId)
        .setMaxResults(pageSize + 1)
        .getResultList();

    boolean hasPrev = result.size() > pageSize;
    if (hasPrev) {
      result.remove(pageSize);
    }

    // 최신순으로 보여주기 위해 reverse
    Collections.reverse(result);
    return PageResponse.of(result, firstSeenId != null, hasPrev);
  }

  // 검색: 이미 필드 프로젝션 + JOIN 적용
  @Override
  public PageResponse<SubmissionResponseDto> searchSubmissions(
      String nickname,
      Long problemNumber,
      String status,
      List<Long> languageVersionIds,
      Long lastSeenId,
      Long firstSeenId,
      int pageSize
  ) {
    log.info("searchSubmissions ");
    log.info("nickname = {}", nickname);
    log.info("problemNumber = {}", problemNumber);
    log.info("status = {}", status);
    log.info("languageVersionIds = {}", languageVersionIds);

    StringBuilder jpql = new StringBuilder("""
        SELECT new com.koreii.algoduck.submission.dto.response.SubmissionResponseDto(
            s.submissionId, m.memberId, m.loginId, m.nickname,
            p.problemId, p.title, s.status, s.message,
            s.executionTime, s.memoryUsage, s.codeName, s.codeUrl
        )
        FROM Submission s
        JOIN s.member m
        JOIN s.problem p
        WHERE 1=1
        """);

    StringBuilder countJpql = new StringBuilder("SELECT COUNT(s) FROM Submission s WHERE 1=1");

    if (nickname != null) {
      jpql.append(" AND s.member.nickname = :nickname");
      countJpql.append(" AND s.member.nickname = :nickname");
    }
    if (problemNumber != null) {
      jpql.append(" AND s.problem.problemNumber = :problemNumber");
      countJpql.append(" AND s.problem.problemNumber = :problemNumber");
    }
    if (status != null) {
      jpql.append(" AND s.status = :status");
      countJpql.append(" AND s.status = :status");
    }
    if (languageVersionIds != null && !languageVersionIds.isEmpty()) {
      jpql.append(" AND s.version.versionId IN :languageVersionIds");
      countJpql.append(" AND s.version.versionId IN :languageVersionIds");
    }

    if (lastSeenId != null) {
      jpql.append(" AND s.submissionId < :lastSeenId");
      countJpql.append(" AND s.submissionId < :lastSeenId");
    }
    if (firstSeenId != null) {
      jpql.append(" AND s.submissionId > :firstSeenId");
      countJpql.append(" AND s.submissionId > :firstSeenId");
    }

    jpql.append(" ORDER BY s.submissionId DESC");

    TypedQuery<SubmissionResponseDto> query = entityManager.createQuery(jpql.toString(), SubmissionResponseDto.class);
    TypedQuery<Long> countQuery = entityManager.createQuery(countJpql.toString(), Long.class);

    if (nickname != null) {
      query.setParameter("nickname", nickname);
      countQuery.setParameter("nickname", nickname);
    }
    if (problemNumber != null) {
      query.setParameter("problemNumber", problemNumber);
      countQuery.setParameter("problemNumber", problemNumber);
    }
    if (status != null) {
      query.setParameter("status", Status.valueOf(status));
      countQuery.setParameter("status", Status.valueOf(status));
    }
    if (languageVersionIds != null && !languageVersionIds.isEmpty()) {
      query.setParameter("languageVersionIds", languageVersionIds);
      countQuery.setParameter("languageVersionIds", languageVersionIds);
    }
    if (lastSeenId != null) {
      query.setParameter("lastSeenId", lastSeenId);
      countQuery.setParameter("lastSeenId", lastSeenId);
    }
    if (firstSeenId != null) {
      query.setParameter("firstSeenId", firstSeenId);
      countQuery.setParameter("firstSeenId", firstSeenId);
    }

    query.setMaxResults(pageSize + 1);

    List<SubmissionResponseDto> result = query.getResultList();

    boolean hasNext = result.size() > pageSize;
    boolean hasPrev = (lastSeenId != null || firstSeenId != null);

    if (hasNext) {
      result = result.subList(0, pageSize);
    }

    long totalCount = countQuery.getSingleResult();

    if (firstSeenId != null) {
      Collections.reverse(result);
    }

    return PageResponse.of(result, hasNext, hasPrev, totalCount);
  }

  @Override
  public long countByMemberId(Long memberId) {
    // 경로 수정: s.member.id -> s.member.memberId
    String jpql = "SELECT COUNT(s) FROM Submission s WHERE s.member.memberId = :memberId";
    return entityManager.createQuery(jpql, Long.class)
        .setParameter("memberId", memberId)
        .getSingleResult();
  }

  // 특정 회원의 최신 페이지: N+1 제거 (필드 프로젝션 + JOIN)
  @Override
  public PageResponse<SubmissionResponseDto> findNextPageByMemberId(Long memberId, Long lastSeenId, int pageSize) {
    String jpql = """
        SELECT new com.koreii.algoduck.submission.dto.response.SubmissionResponseDto(
            s.submissionId,
            m.memberId,
            m.loginId,
            m.nickname,
            p.problemId,
            p.title,
            s.status,
            s.message,
            s.executionTime,
            s.memoryUsage,
            s.codeName,
            s.codeUrl
        )
        FROM Submission s
        JOIN s.member m
        JOIN s.problem p
        WHERE m.memberId = :memberId
          AND (:lastId IS NULL OR s.submissionId < :lastId)
        ORDER BY s.submissionId DESC
        """;

    List<SubmissionResponseDto> result = entityManager.createQuery(jpql, SubmissionResponseDto.class)
        .setParameter("memberId", memberId)
        .setParameter("lastId", lastSeenId)
        .setMaxResults(pageSize + 1)
        .getResultList();

    boolean hasNext = result.size() > pageSize;
    if (hasNext) {
      result.remove(pageSize);
    }

    long totalCount = countByMemberId(memberId);

    return PageResponse.of(result, hasNext, lastSeenId != null, totalCount);
  }

  // 특정 회원의 이전 페이지: N+1 제거 (필드 프로젝션 + JOIN, ASC 조회 후 reverse)
  @Override
  public PageResponse<SubmissionResponseDto> findPrevPageByMemberId(Long memberId, Long firstSeenId, int pageSize) {
    String jpql = """
        SELECT new com.koreii.algoduck.submission.dto.response.SubmissionResponseDto(
            s.submissionId,
            m.memberId,
            m.loginId,
            m.nickname,
            p.problemId,
            p.title,
            s.status,
            s.message,
            s.executionTime,
            s.memoryUsage,
            s.codeName,
            s.codeUrl
        )
        FROM Submission s
        JOIN s.member m
        JOIN s.problem p
        WHERE m.memberId = :memberId
          AND s.submissionId > :firstId
        ORDER BY s.submissionId ASC
        """;

    List<SubmissionResponseDto> result = entityManager.createQuery(jpql, SubmissionResponseDto.class)
        .setParameter("memberId", memberId)
        .setParameter("firstId", firstSeenId)
        .setMaxResults(pageSize + 1)
        .getResultList();

    boolean hasPrev = result.size() > pageSize;
    if (hasPrev) {
      result.remove(pageSize);
    }

    Collections.reverse(result); // 최신순으로 정렬
    return PageResponse.of(result, firstSeenId != null, hasPrev);
  }
}
