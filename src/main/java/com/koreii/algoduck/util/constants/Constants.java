package com.koreii.algoduck.util.constants;

public abstract class Constants {
  public static final int JOIN_GRACE_PERIOD = 7;       //  회원 가입 유예기간
  public static final int QUIT_GRACE_PERIOD = 7;       //  회원 탈퇴 유예기간
  public static final String LOGIN_ID_POLICY = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$\n"; //  로그인 아이디 정책
  public static final String PASSWORD_POLICY = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,20}$\n"; //  비밀번호 정책
  public static final String NICKNAME_POLICY = "^[A-Za-z가-힣\\d]{3,10}$"; //  닉네임 정책
  public static final String LOGIN_ID_POLICY_VIOLATION = "로그인 아이디 정책에 맞지 않습니다.";
  public static final String PASSWORD_POLICY_VIOLATION = "비밀번호 정책에 맞지 않습니다.";
  public static final String NICKNAME_POLICY_VIOLATION = "닉네임 정책에 맞지 않습니다.";
}
