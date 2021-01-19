package com.tistory.memostack.cocoa.member.infrastructure;

import com.tistory.memostack.cocoa.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // JpaRepository 를 extends 하면, 생략 가능.
public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findMemberByEmail(String email);
}
