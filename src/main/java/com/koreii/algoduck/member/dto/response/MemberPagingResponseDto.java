package com.koreii.algoduck.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberPagingResponseDto {
  private long totalCount;
  private List<MemberSimpleResponseDto> members;
}
