package com.tistory.memostack.cocoa.member.application;

import com.tistory.memostack.cocoa.member.domain.Member;
import com.tistory.memostack.cocoa.member.infrastructure.MemberRepository;
import com.tistory.memostack.cocoa.member.interfaces.network.MemberRequest;
import com.tistory.memostack.cocoa.member.interfaces.network.MemberResponse;
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
    final LocalDateTime now = LocalDateTime.now();
    member.setActive(true).setRegisterAt(now).setPasswordUpdatedAt(now);
    return memberRepository.save(member);
  }

  /**
   * API 를 통해 넘겨받은 MemberRequest 객체를 insert 하는 메소드
   *
   * @param memberRequest Member 정보를 담고 있는 MemberRequest 객체
   * @return MemberResponse 사용자 정보를 담은 MemberResponse 객체
   */
  public MemberResponse insertMember(MemberRequest memberRequest) {
    log.info("Insert member. " + memberRequest.getEmail());

    final Member member =
        insertMember(
            Member.builder()
                .email(memberRequest.getEmail())
                .password(passwordEncoder.encode(memberRequest.getPassword()))
                .name(memberRequest.getName())
                .address(memberRequest.getAddress())
                .phone(memberRequest.getPhone())
                .build());

    return MemberResponse.builder()
        .email(member.getEmail())
        .address(member.getAddress())
        .name(member.getName())
        .phone(member.getPhone())
        .build();
  }
}
