package com.koreii.algoduck.member.entity;

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
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBERS_SEQ_GENERATOR")
  @Column(name = "member_id")
  private Long memberId;

  @Column(name = "login_id", unique = true, nullable = false)
  private String loginId;

  @Column(nullable = false)
  @Setter
  private String password;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(unique = true, nullable = false)
  private String nickname;

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
}
