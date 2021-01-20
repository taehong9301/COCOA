package com.tistory.memostack.cocoa.member.infrastructure;

import com.tistory.memostack.cocoa.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) // junit4의 @RunWith 역할
@DataJpaTest // junit5 에서 JPA Repository 테스트를 위해 필요한 어노테이션
@DisplayName("MemberRepository 테스트")
class MemberRepositoryTest {

  @Autowired private MemberRepository memberRepository;

  Member save(String username) {
    final LocalDateTime now = LocalDateTime.now();
    final Member member =
        Member.builder()
            .username(username)
            .email("test@gmail.com")
            .password("1234")
            .name("테스트 유저")
            .isActive(true)
            .registerAt(now)
            .passwordUpdatedAt(now)
            .build();

    return memberRepository.save(member);
  }

  @Test
  @DisplayName("save() 테스트")
  void saveTest() {
    // given
    String username = "tester";

    // when
    Member savedMember = save(username);

    // then
    assertEquals(username, savedMember.getUsername());
  }

  @Test
  @DisplayName("findAll() 테스트")
  void finaAll() {
    // given
    for (int i = 0; i < 10; i++) {
      save("tester" + i);
    }

    // when
    List<Member> members = memberRepository.findAll();

    // then
    assertFalse(members.isEmpty());
    members.forEach(
        member -> {
          assertTrue(member.getUsername().matches("tester\\d{1}"));
        });
  }

  @Test
  @DisplayName("findMemberByUsername() 테스트")
  void findMemberByUsernameTest() {
    // given
    String username = "tester";
    save(username);

    // when
    final Optional<Member> optionalMember = memberRepository.findMemberByUsername(username);

    // then
    assertTrue(optionalMember.isPresent());
    optionalMember.ifPresent(
        member -> {
          assertEquals(username, member.getUsername());
        });
  }
}
