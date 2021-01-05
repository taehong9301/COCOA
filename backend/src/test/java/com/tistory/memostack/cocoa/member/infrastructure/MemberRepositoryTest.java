package com.tistory.memostack.cocoa.member.infrastructure;

import com.tistory.memostack.cocoa.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest // junit5 에서 JPA Repository 테스트를 위해 필요한 어노테이션
class MemberRepositoryTest {

  @Autowired private MemberRepository memberRepository;

  @Test
  void findMemberByEmail() {
    // given
    final String email = "test@naver.com";
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

    // when
    final Member savedMember = memberRepository.save(member);

    // then
    assertEquals(email, savedMember.getEmail());
  }
}
