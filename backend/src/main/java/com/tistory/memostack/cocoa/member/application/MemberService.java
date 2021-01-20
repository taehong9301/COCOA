package com.tistory.memostack.cocoa.member.application;

import com.tistory.memostack.cocoa.member.domain.Member;
import com.tistory.memostack.cocoa.member.exception.NotfoundMemberException;
import com.tistory.memostack.cocoa.member.infrastructure.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

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
    log.info("Insert member. username: {}", member.getUsername());

    final LocalDateTime now = LocalDateTime.now();
    member
        .setActive(true) // 유저 활성화
        .setRegisterAt(now) // 회원가입일
        .setPasswordUpdatedAt(now) // 비밀번호 갱신일
        .setPassword(passwordEncoder.encode(member.getPassword())); // 비밀번호 암호화

    return memberRepository.save(member);
  }

  /**
   * Member 를 update 하는 메소드
   *
   * @param request 사용자 정보를 가지고 있는 Member 객체
   * @return Member 수정 완료 된 사용자의 정보를 반환
   */
  @Transactional
  public Member updateMember(Member request) {
    // id 값을 통해 Member 를 가지고 옴
    final Optional<Member> optionalMember = memberRepository.findById(request.getId());

    // Member 가 없는 경우, 에러 발생
    final Member findMember =
        optionalMember.orElseThrow(() -> new NotfoundMemberException("id: " + request.getId()));

    // 가지고 온 Member 의 정보를 Update 함
    log.info("Update member. username {}", findMember.getUsername());
    final LocalDateTime now = LocalDateTime.now();
    findMember
        .setAddress(request.getAddress()) // 주소
        .setPhone(request.getPhone()) // 전화번호
        .setActive(request.isActive()) // 유저 활성화
        .setUpdatedAt(now); // 회원 정보 갱신일

    // 비밀번호가 바뀌었다면, 날짜랑 비밀번호 갱신
    if (!passwordEncoder.matches(request.getPassword(), findMember.getPassword())) {
      findMember
          .setPassword(passwordEncoder.encode(request.getPassword())) // 신규 비밀번호
          .setPasswordUpdatedAt(now); // 비밀번호 갱신일
    }

    return memberRepository.save(findMember);
  }
}
