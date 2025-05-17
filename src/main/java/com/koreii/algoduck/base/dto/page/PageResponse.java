package com.koreii.algoduck.base.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
  private boolean hasNext;
  private boolean hasPrev;
  private List<T> content;

  public static <T> PageResponse<T> of(List<T> content, boolean hasNext, boolean hasPrev) {
    return new PageResponse<>(hasNext, hasPrev, content);
  }
}
