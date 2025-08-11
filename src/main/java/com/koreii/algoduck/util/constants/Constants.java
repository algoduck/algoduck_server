package com.koreii.algoduck.util.constants;

import com.koreii.algoduck.exceptions.LoginIdPolicyViolationException;
import com.koreii.algoduck.exceptions.NicknamePolicyViolationException;
import com.koreii.algoduck.exceptions.PasswordPolicyViolationException;
import com.koreii.algoduck.util.validator.PolicyValidator;

public abstract class Constants {
  public static final int BATCH_SIZE = 50;
  public static final int JOIN_GRACE_PERIOD = 7;       //  회원 가입 유예기간
  public static final int QUIT_GRACE_PERIOD = 7;       //  회원 탈퇴 유예기간
  public static final String LOGIN_ID_POLICY = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$"; //  로그인 아이디 정책
  public static final String PASSWORD_POLICY = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,20}$"; //  비밀번호 정책
  public static final String NICKNAME_POLICY = "^[A-Za-z가-힣\\d]{3,10}$"; //  닉네임 정책
  public static final String LOGIN_ID_POLICY_VIOLATION = "로그인 아이디 정책에 맞지 않습니다.";
  public static final String PASSWORD_POLICY_VIOLATION = "비밀번호 정책에 맞지 않습니다.";
  public static final String NICKNAME_POLICY_VIOLATION = "닉네임 정책에 맞지 않습니다.";
  public static final String LOGIN_MEMBER = "loginMember";
  public static final int SHARD_COUNT = 4;  //  샤드 수

  public static void validateLoginId(String loginId) {
    if (!PolicyValidator.isValid(loginId, LOGIN_ID_POLICY)) {
      throw new LoginIdPolicyViolationException(LOGIN_ID_POLICY_VIOLATION);
    }
  }

  public static void validatePassword(String password) {
    if (!PolicyValidator.isValid(password, PASSWORD_POLICY)) {
      throw new PasswordPolicyViolationException(PASSWORD_POLICY_VIOLATION);
    }
  }

  public static void validateNickname(String nickname) {
    if (!PolicyValidator.isValid(nickname, NICKNAME_POLICY)) {
      throw new NicknamePolicyViolationException(NICKNAME_POLICY_VIOLATION);
    }
  }

  public static void validatePolicies(String loginId, String password, String nickname) {
    validateLoginId(loginId);
    validatePassword(password);
    validateNickname(nickname);
  }

}
