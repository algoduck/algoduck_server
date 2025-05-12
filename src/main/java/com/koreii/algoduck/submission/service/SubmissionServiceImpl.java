package com.koreii.algoduck.submission.service;

import com.koreii.algoduck.member.service.MemberService;
import com.koreii.algoduck.problem.dto.response.ProblemResponseDto;
import com.koreii.algoduck.problem.service.ProblemService;
import com.koreii.algoduck.submission.dto.request.JudgeRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionSaveRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionUpdateRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import com.koreii.algoduck.submission.repository.SubmissionRepository;
import com.koreii.algoduck.version.dto.response.VersionResponseDto;
import com.koreii.algoduck.version.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
  private final VersionService versionService;
  private final ProblemService problemService;
  private final JudgeService judgeService;
  private final SubmissionRepository submissionRepository;

  @Override
  public SubmissionResponseDto submission(SubmissionRequestDto submissionRequestDto) {
    ProblemResponseDto problemResponseDto = problemService.findDtoByProblemId(submissionRequestDto.getProblemId());
    VersionResponseDto versionResponseDto = versionService.findByVersionId(submissionRequestDto.getVersionId());

    SubmissionResponseDto submissionResponseDto = submissionRepository.saveSubmission(new SubmissionSaveRequestDto(submissionRequestDto));

    JudgeRequestDto judgeRequestDto = JudgeRequestDto.builder()
        .problemId(submissionRequestDto.getProblemId())
        .language(versionResponseDto.getLanguageName())
        .version(versionResponseDto.getVersionName())
        .timeLimitation(problemResponseDto.getTimeLimitation())
        .memoryLimitation(problemResponseDto.getMemoryLimitation())
        .sourceCode(submissionRequestDto.getSourceCode())
        .build();

    judgeService.requestJudge(judgeRequestDto);

    // 즉시 사용자에게 "채점 중" 메시지 반환
    return submissionResponseDto;
  }

  @Override
  public SubmissionResponseDto findBySubmissionId(Long submissionId) {
    return new SubmissionResponseDto(submissionRepository.findBySubmissionId(submissionId));
  }

  @Override
  public SubmissionResponseDto updateSubmission(SubmissionUpdateRequestDto submissionUpdateRequestDto) {
    return submissionRepository.updateSubmission(submissionUpdateRequestDto);
  }
}
