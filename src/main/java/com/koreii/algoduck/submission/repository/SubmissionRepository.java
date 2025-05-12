package com.koreii.algoduck.submission.repository;

import com.koreii.algoduck.submission.dto.request.SubmissionSaveRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionUpdateRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import com.koreii.algoduck.submission.entity.Submission;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository {
  SubmissionResponseDto saveSubmission(SubmissionSaveRequestDto submissionSaveRequestDto);

  Submission findBySubmissionId(Long submissionId);

  SubmissionResponseDto updateSubmission(SubmissionUpdateRequestDto submissionUpdateRequestDto);
}
