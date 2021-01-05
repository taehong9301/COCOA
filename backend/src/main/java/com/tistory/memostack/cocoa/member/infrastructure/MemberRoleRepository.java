package com.tistory.memostack.cocoa.member.infrastructure;

import com.tistory.memostack.cocoa.member.domain.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {}
