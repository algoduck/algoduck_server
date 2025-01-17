package com.koreii.algoduck.member.entity;

import com.koreii.algoduck.base.BaseTimeEntity;
import com.koreii.algoduck.member.enums.MemberStatus;
import com.koreii.algoduck.member.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@SequenceGenerator(
    name = "MEMBERS_SEQ_GENERATOR",
    sequenceName = "MEMBERS_SEQ",
    allocationSize = 1
)
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBERS_SEQ_GENERATOR")
  @Column(name = "member_id")
  private Long memberId;

  @Column(name = "login_id", unique = true, nullable = false, length = 20)
  private String loginId;

  @Column(nullable = false, length = 20)
  @Setter
  private String password;

  @Column(unique = true, nullable = false, length = 40)
  private String email;

  @Column(unique = true, nullable = false, length = 20, columnDefinition = "VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci")
  private String nickname;

  @Column(nullable = false)
  private Integer solved;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Setter
  private Role role;

  @Column(name = "profile_image_url", columnDefinition = "TEXT")
  @Setter
  private String profileImageUrl;

  @Column(name = "status_message")
  @Setter
  private String statusMessage;

  @Column(name = "member_status", nullable = false)
  @Enumerated(EnumType.STRING)
  @Setter
  private MemberStatus memberStatus;

  @Column(name = "quit_request_time")
  @Setter
  private LocalDateTime quitRequestTime;
}
