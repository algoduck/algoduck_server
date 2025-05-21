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
  public SubmissionResponseDto saveSubmission(SubmissionSaveRequestDto submissionSaveRequestDto) {
    Submission submission = Submission.builder()
        .member(memberRepository.findByMemberId(submissionSaveRequestDto.getMemberId()))
        .status(Status.JUDGING)
        .message("채점 중입니다.")
        .problem(problemRepository.findByProblemId(submissionSaveRequestDto.getProblemId()))
        .version(versionRepository.findByVersionId(submissionSaveRequestDto.getVersionId()))
        .build();

    entityManager.persist(submission);
    return new SubmissionResponseDto(submission);
  }

  @Override
  public Submission findBySubmissionId(Long submissionId) {
    Submission submission = entityManager.find(Submission.class, submissionId);
    return submission;
  }

  @Override
  @Transactional
  public SubmissionResponseDto updateSubmission(SubmissionUpdateRequestDto submissionUpdateRequestDto) {
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
    if (submissionUpdateRequestDto.getMemoryUsage() != null) {
      submission.setMessage(submissionUpdateRequestDto.getMessage());
    }
    if (submissionUpdateRequestDto.getExecutionTime() != null) {
      submission.setExecutionTime(submissionUpdateRequestDto.getExecutionTime());
    }
    if (submissionUpdateRequestDto.getMemoryUsage() != null) {
      submission.setMemoryUsage(submissionUpdateRequestDto.getMemoryUsage());
    }
    return new SubmissionResponseDto(submission);
  }

  @Override
  public PageResponse<SubmissionResponseDto> findNextPage(Long lastSeenId, int pageSize) {
    String jpql = "SELECT new com.koreii.algoduck.submission.dto.response.SubmissionResponseDto(s) " +
        "FROM Submission s " +
        "WHERE (:lastId IS NULL OR s.id < :lastId) " +
        "ORDER BY s.id DESC";

    List<SubmissionResponseDto> result = entityManager.createQuery(jpql, SubmissionResponseDto.class)
        .setParameter("lastId", lastSeenId)
        .setMaxResults(pageSize)
        .getResultList();

    boolean hasNext = result.size() > pageSize;
    if (hasNext) {
      result.remove(pageSize);
    }

    return PageResponse.of(result, hasNext, lastSeenId != null);
  }

  @Override
  public PageResponse<SubmissionResponseDto> findPrevPage(Long firstSeenId, int pageSize) {
    String jpql = "SELECT new com.koreii.algoduck.submission.dto.response.SubmissionResponseDto(s) " +
        "FROM Submission s " +
        "WHERE s.id > :firstId " +
        "ORDER BY s.id ASC";

    List<SubmissionResponseDto> result = entityManager.createQuery(jpql, SubmissionResponseDto.class)
        .setParameter("firstId", firstSeenId)
        .setMaxResults(pageSize + 1)
        .getResultList();

    boolean hasPrev = result.size() > pageSize;
    if (hasPrev) {
      result.remove(pageSize);
    }

    Collections.reverse(result); // 최신순 유지
    return PageResponse.of(result, true, hasPrev);
  }
}
