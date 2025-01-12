package com.koreii.algoduck.member.enums;

public enum Role {
  GENERAL("GENERAL"),
  INSRTUCTOR("INSTRUCTOR"),
  ADMINISTRATOR("ADMINISTRATOR");

  private final String role;

  Role(String role) {
    this.role = role;
  }
}
