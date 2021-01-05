package com.tistory.memostack.cocoa.member.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {

  @Test
  void creationTest() {
    Member member =
        Member.builder()
            .email("test@naver.com")
            .password("1234")
            .name("테스트 유저")
            .registerAt(LocalDateTime.now())
            .build();

    assertEquals("test@naver.com", member.getEmail());
  }
}
