package com.koreii.algoduck.submission.service;

import com.koreii.algoduck.language.service.LanguageService;
import com.koreii.algoduck.member.service.MemberService;
import com.koreii.algoduck.problem.service.ProblemService;
import com.koreii.algoduck.submission.dto.request.JudgeRequestDto;
import com.koreii.algoduck.submission.dto.request.SubmissionRequestDto;
import com.koreii.algoduck.submission.dto.response.SubmissionResponseDto;
import com.koreii.algoduck.submission.enums.Status;
import com.koreii.algoduck.version.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
  private final LanguageService languageService;
  private final VersionService versionService;
  private final ProblemService problemService;
  private final MemberService memberService;
  private final JudgeService judgeService;

  @Override
  public SubmissionResponseDto submission(SubmissionRequestDto submissionRequestDto) {
    JudgeRequestDto judgeRequestDto = JudgeRequestDto.builder()
        .problemId(submissionRequestDto.getProblemId())
        .language(languageService.findByLanguageId(submissionRequestDto.getLanguageId()).getName())
        .version(versionService.findByVersionId(submissionRequestDto.getVersionId()).getVersionName())
        .timeLimitation(submissionRequestDto.getTimeLimitation())
        .memoryLimitation(submissionRequestDto.getMemoryLimitation())
        .sourceCode(submissionRequestDto.getSourceCode())
        .build();

    judgeService.requestJudge(judgeRequestDto);

    // 즉시 사용자에게 "채점 중" 메시지 반환
    return SubmissionResponseDto.builder()
        .status(Status.JUDGING)
        .message("채점 중입니다.")
        .build();
  }
}
