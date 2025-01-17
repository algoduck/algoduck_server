package com.koreii.algoduck.util.validator;

import java.util.regex.Pattern;

public abstract class PolicyValidator {
  public static boolean isValid(String input, String regex) {
    if (input == null || regex == null) {
      return false;
    }

    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(input).matches();
  }
}