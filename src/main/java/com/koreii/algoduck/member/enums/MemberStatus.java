package com.koreii.algoduck.member.enums;

public enum MemberStatus {
  WAITING,          //  이메일 인증(강사의 경우 관리자의 수락도 포함)을 대기 중인 상태
  ACTIVE,           //  회원가입이 정상적으로 완료된 상태
  PENDING_DELETION, //  회원 탈퇴 요청 후 탈퇴 대기
  DELETED           //  논리적인 탈퇴 완료
}
