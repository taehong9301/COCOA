package com.tistory.memostack.cocoa.member.infrastructure;

import com.tistory.memostack.cocoa.member.domain.Member;
import com.tistory.memostack.cocoa.member.domain.MemberRole;
import com.tistory.memostack.cocoa.member.domain.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("MemberRoleRepository 테스트")
class MemberRoleRepositoryTest {

  @Autowired private MemberRoleRepository memberRoleRepository;

  @Autowired private MemberRepository memberRepository;

  @Autowired private RoleRepository roleRepository;

  @Test
  @DisplayName("save() 테스트")
  void saveTest() {
    // given
    String username = "tester";
    String email = "test@naver.com";
    LocalDateTime now = LocalDateTime.now();
    String roleName = "ADMIN";

    final Member savedMember =
        memberRepository.save(
            Member.builder()
                .username(username)
                .email(email)
                .password("1234")
                .name("test user")
                .isActive(true)
                .registerAt(now)
                .passwordUpdatedAt(now)
                .build());
    final Role savedRole = roleRepository.save(Role.builder().roleName(roleName).build());

    final MemberRole memberRole = MemberRole.builder().member(savedMember).role(savedRole).build();

    // when
    final MemberRole savedMemberRole = memberRoleRepository.save(memberRole);

    // then
    assertEquals(username, savedMemberRole.getMember().getUsername());
    assertEquals(email, savedMemberRole.getMember().getEmail());
    assertEquals(roleName, savedMemberRole.getRole().getRoleName());
  }
}
