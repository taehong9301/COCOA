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

  Member save(String email) {
    final LocalDateTime now = LocalDateTime.now();
    final Member member =
        Member.builder()
            .email(email)
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
    String email = "test@naver.com";

    // when
    Member savedMember = save(email);

    // then
    assertEquals(email, savedMember.getEmail());
  }

  @Test
  @DisplayName("findAll() 테스트")
  void finaAll() {
    // given
    for (int i = 0; i < 10; i++) {
      save("test" + i + "@naver.com");
    }

    // when
    List<Member> members = memberRepository.findAll();

    // then
    assertFalse(members.isEmpty());
    members.forEach(
        member -> {
          assertTrue(member.getEmail().matches("test\\d{1}@naver.com"));
        });
  }

  @Test
  @DisplayName("findMemberByEmail() 테스트")
  void findMemberByEmailTest() {
    // given
    String email = "test@naver.com";
    save(email);

    // when
    final Optional<Member> optionalMember = memberRepository.findMemberByEmail(email);

    // then
    assertTrue(optionalMember.isPresent());
    optionalMember.ifPresent(
        member -> {
          assertEquals(email, member.getEmail());
        });
  }
}
