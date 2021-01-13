package com.tistory.memostack.cocoa.member.application;

import com.tistory.memostack.cocoa.member.domain.Member;
import com.tistory.memostack.cocoa.member.exception.NotfoundMemberException;
import com.tistory.memostack.cocoa.member.infrastructure.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("MemberService 테스트")
class MemberServiceTest {

  @Mock private MemberRepository memberRepository;
  private BCryptPasswordEncoder passwordEncoder;
  private MemberService memberService;

  @BeforeEach
  void setUp() {
    this.passwordEncoder = new BCryptPasswordEncoder(); // 비밀번호 암호화를 위해..
    this.memberService = new MemberService(memberRepository, passwordEncoder);
  }

  @Test
  @DisplayName("insert 테스트")
  void insetMemberTest() {
    // given
    final LocalDateTime now = LocalDateTime.now();
    final Member member =
        Member.builder()
            .email("test@naver.com")
            .password("1234")
            .name("테스트 유저")
            .phone("010-1234-5678")
            .isActive(true)
            .registerAt(now)
            .passwordUpdatedAt(now)
            .build();
    given(memberRepository.save(member))
        .willReturn(
            Member.builder()
                .id(1L)
                .email("test@naver.com")
                .password(passwordEncoder.encode("1234"))
                .name("테스트 유저")
                .phone("010-1234-5678")
                .isActive(true)
                .registerAt(now)
                .passwordUpdatedAt(now)
                .build());

    // when
    final Member savedMember = memberService.insertMember(member);

    // then
    assertEquals(1L, savedMember.getId());
    assertEquals("test@naver.com", savedMember.getEmail());
    assertTrue(passwordEncoder.matches("1234", savedMember.getPassword()));
  }

  @Test
  @DisplayName("update 테스트")
  void updateMemberTest() {
    // given
    final LocalDateTime now = LocalDateTime.now();
    final Member request =
        Member.builder().id(1L).email("test@naver.com").password("4321").name("아무개").build();
    given(memberRepository.findById(1L))
        .willReturn(
            Optional.of(
                Member.builder()
                    .id(1L)
                    .email("test@naver.com")
                    .password("1234")
                    .name("홍길동")
                    .phone("010-1234-5678")
                    .isActive(true)
                    .registerAt(now)
                    .passwordUpdatedAt(now)
                    .build()));
    given(memberRepository.save(any()))
        .willReturn(
            Member.builder()
                .id(1L)
                .email("test@naver.com")
                .password(passwordEncoder.encode("4321"))
                .name("홍길동")
                .phone("010-1234-5678")
                .isActive(true)
                .registerAt(now)
                .passwordUpdatedAt(now)
                .build());

    // when
    final Member updatedMember = memberService.updateMember(request);

    // then
    assertEquals(1L, updatedMember.getId());
    assertEquals("test@naver.com", updatedMember.getEmail());
    assertTrue(passwordEncoder.matches("4321", updatedMember.getPassword()));
    assertEquals("홍길동", updatedMember.getName());
  }

  @Test
  @DisplayName("update 실패 테스트")
  void updateMemberFailTest() {
    // given
    final Member request =
        Member.builder().id(1L).email("test@naver.com").password("4321").name("아무개").build();
    given(memberRepository.findById(1L)).willReturn(Optional.empty());

    assertThrows(
        NotfoundMemberException.class,
        () -> {
          // when
          memberService.updateMember(request);
        });
  }
}
