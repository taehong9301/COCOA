package com.tistory.memostack.cocoa.member.infrastructure;

import com.tistory.memostack.cocoa.member.domain.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("RoleRepository 테스트")
class RoleRepositoryTest {

  @Autowired private RoleRepository roleRepository;

  @Test
  @DisplayName("save() 테스트")
  void saveTest() {
    // given
    String roleName = "ADMIN";
    final Role role = Role.builder().roleName(roleName).build();

    // when
    final Role savedRole = roleRepository.save(role);

    // then
    assertEquals(roleName, savedRole.getRoleName());
  }
}
