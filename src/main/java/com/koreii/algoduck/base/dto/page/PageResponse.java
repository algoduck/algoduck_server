package com.koreii.algoduck.base.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
  private List<T> content;
  private boolean hasNext;
  private boolean hasPrev;
  private long totalCount;

  public static <T> PageResponse<T> of(List<T> content, boolean hasNext, boolean hasPrev, long totalCount) {
    return new PageResponse<>(content, hasNext, hasPrev, totalCount);
  }

  // 기존 메서드도 유지하되 기본 totalCount는 -1로 처리 (옵션)
  public static <T> PageResponse<T> of(List<T> content, boolean hasNext, boolean hasPrev) {
    return new PageResponse<>(content, hasNext, hasPrev, -1);
  }
}
