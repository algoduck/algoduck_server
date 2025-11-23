package com.koreii.algoduck.submission.mq.message.request;

public record JudgeRequestMessage(
    Long problemId,
    Long submissionId,
    String language,
    String version,
    int timeLimitation,
    int memoryLimitation,
    String sourceCode
) {
}
