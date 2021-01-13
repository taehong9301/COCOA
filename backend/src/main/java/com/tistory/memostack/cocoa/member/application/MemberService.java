package com.tistory.memostack.cocoa.member.application;

import com.tistory.memostack.cocoa.member.domain.Member;
import com.tistory.memostack.cocoa.member.infrastructure.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  private final BCryptPasswordEncoder passwordEncoder;

  public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Member 를 insert 하는 메소드
   *
   * @param member 사용자 정보를 가지고 있는 Member 객체
   * @return Member 저장 완료 된 사용자의 정보를 반환
   */
  public Member insertMember(Member member) {
    log.info("Insert member. {}", member.getEmail());

    final LocalDateTime now = LocalDateTime.now();
    member
        .setActive(true) // 유저 활성화
        .setRegisterAt(now) // 회원가입일
        .setPasswordUpdatedAt(now) // 비밀번호 갱신일
        .setPassword(passwordEncoder.encode(member.getPassword())); // 비밀번호 암호화

    return memberRepository.save(member);
  }
}
