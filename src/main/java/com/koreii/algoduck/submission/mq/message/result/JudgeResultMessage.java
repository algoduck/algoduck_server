package com.koreii.algoduck.submission.mq.message.result;

import com.koreii.algoduck.submission.enums.Status;

public record JudgeResultMessage(
    Long problemId,
    Long submissionId,
    Status result,
    String message,
    String stdout,
    String stderr,
    Integer executionTime,
    Integer memoryUsage
) {
}
