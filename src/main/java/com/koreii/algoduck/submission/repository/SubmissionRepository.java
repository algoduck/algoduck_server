package com.koreii.algoduck.submission.repository;

import com.koreii.algoduck.base.dto.page.PageResponse;
import com.koreii.algoduck.submission.dto.request.SubmissionSaveRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionUpdateRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import com.koreii.algoduck.submission.entity.Submission;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository {
  Submission saveSubmission(SubmissionSaveRequestDto submissionSaveRequestDto);

  Submission findBySubmissionId(Long submissionId);

  Submission updateSubmission(SubmissionUpdateRequestDto submissionUpdateRequestDto);

  long countAll();

  PageResponse<SubmissionResponseDto> findNextPage(Long lastSeenId, int pageSize);

  PageResponse<SubmissionResponseDto> findPrevPage(Long firstSeenId, int pageSize);

  PageResponse<SubmissionResponseDto> searchSubmissions(String loginId, Long problemNumber, String status, String language, Long lastSeenId, Long firstSeenId, int pageSize);

  long countByMemberId(Long memberId);

  PageResponse<SubmissionResponseDto> findNextPageByMemberId(Long memberId, Long lastSeenId, int pageSize);

  PageResponse<SubmissionResponseDto> findPrevPageByMemberId(Long memberId, Long firstSeenId, int pageSize);

}
