package com.tistory.memostack.cocoa.common.util;

import com.tistory.memostack.cocoa.member.domain.MemberRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("JwtManger 테스트")
class JwtManagerTest {

  private JwtManager jwtManager;

  @BeforeEach
  void setUp() {
    jwtManager = new JwtManager();
  }

  @Test
  @DisplayName("토큰 생성 및 복호화 테스트")
  void tokenTest() {
    // given
    LocalDateTime now = LocalDateTime.now();

    // when
    // 1) 토큰 생성
    final String token = jwtManager.generateJwtToken("hong01", new ArrayList<>());
    // 2) 토큰으로부터 username 가져오기
    final String usernameFromToken = jwtManager.getUsernameFromToken(token);
    // 3) 토큰으로부터 role 가져오기
    final List<MemberRole> memberRoleFromToken = jwtManager.getMemberRoleSetFromToken(token);

    // then
    assertEquals("hong01", usernameFromToken);
  }
}
