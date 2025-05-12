package com.koreii.algoduck.submission.repository;

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
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
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
  public SubmissionResponseDto updateSubmission(SubmissionUpdateRequestDto submissionUpdateRequestDto) {
    Submission submission = entityManager.find(Submission.class, submissionUpdateRequestDto.getSubmissionId());
    submission.setCodeName(submissionUpdateRequestDto.getCodeName());
    submission.setCodeUrl(submissionUpdateRequestDto.getCodeUrl());
    submission.setStatus(submissionUpdateRequestDto.getStatus());
    submission.setMessage(submissionUpdateRequestDto.getMessage());
    submission.setExecutionTime(submissionUpdateRequestDto.getExecutionTime());
    submission.setMemoryUsage(submissionUpdateRequestDto.getMemoryUsage());
    return new SubmissionResponseDto(submission);
  }
}
