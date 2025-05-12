package com.koreii.algoduck.submission.service;

import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionUpdateRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface SubmissionService {
  SubmissionResponseDto submission(SubmissionRequestDto submissionRequestDto);

  SubmissionResponseDto findBySubmissionId(Long submissionId);

  SubmissionResponseDto updateSubmission(SubmissionUpdateRequestDto submissionUpdateRequestDto);
}
