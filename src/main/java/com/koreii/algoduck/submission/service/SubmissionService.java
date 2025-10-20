package com.koreii.algoduck.submission.service;

import com.koreii.algoduck.base.dto.page.PageResponse;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionUpdateRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubmissionService {
  SubmissionResponseDto submit(SubmissionRequestDto submissionRequestDto);

  SubmissionResponseDto findBySubmissionId(Long submissionId);

  SubmissionResponseDto updateSubmission(SubmissionUpdateRequestDto submissionUpdateRequestDto);

  PageResponse<SubmissionResponseDto> getFirstPage(int pageSize);

  // 다음 페이지 요청 (현재 마지막 ID 기준으로 다음 데이터 조회)
  PageResponse<SubmissionResponseDto> getNextPage(Long lastSeenId, int pageSize);

  // 이전 페이지 요청 (현재 첫 ID 기준으로 더 최신 데이터 조회)
  PageResponse<SubmissionResponseDto> getPrevPage(Long firstSeenId, int pageSize);

  PageResponse<SubmissionResponseDto> getNextPageByMemberId(Long memberId, Long lastSeenId, int pageSize);

  PageResponse<SubmissionResponseDto> getPrevPageByMemberId(Long memberId, Long firstSeenId, int pageSize);

  PageResponse<SubmissionResponseDto> searchSubmissions(String nickname, Long problemNumber, String status, List<Long> languageVersionIds, Long lastSeenId, Long firstSeenId, int pageSize);

  String getCode(Long submissionId);
}
