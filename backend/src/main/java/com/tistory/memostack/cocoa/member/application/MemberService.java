package com.tistory.memostack.cocoa.member.application;

import com.tistory.memostack.cocoa.common.util.JwtManager;
import com.tistory.memostack.cocoa.member.domain.Member;
import com.tistory.memostack.cocoa.member.exception.NotfoundMemberException;
import com.tistory.memostack.cocoa.member.infrastructure.MemberRepository;
import com.tistory.memostack.cocoa.security.application.AuthenticationProviderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  private final JwtManager jwtManager;

  @Autowired private AuthenticationProviderImpl authenticationProvider;

  private final BCryptPasswordEncoder passwordEncoder;

  public MemberService(
      MemberRepository memberRepository,
      JwtManager jwtManager,
      BCryptPasswordEncoder passwordEncoder) {
    this.memberRepository = memberRepository;
    this.jwtManager = jwtManager;
    this.passwordEncoder = passwordEncoder;
  }

  public String login(String username, String password) {
    final Authentication authentication =
        authenticationProvider.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));

    return jwtManager.generateJwtToken(
        authentication.getName(),
        authentication.getAuthorities().stream()
            .map(grantedAuthority -> grantedAuthority.getAuthority())
            .collect(Collectors.toList()));
  }

  /**
   * Member 를 insert 하는 메소드
   *
   * @param member 사용자 정보를 가지고 있는 Member 객체
   * @return Member 저장 완료 된 사용자의 정보를 반환
   */
  @Transactional
  public Member insertMember(Member member) {
    log.info("Insert member. username: {}", member.getUsername());

    final LocalDateTime now = LocalDateTime.now();
    member
        .setIsActive(true) // 유저 활성화
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
    log.info("Update member. username: {}", findMember.getUsername());
    final LocalDateTime now = LocalDateTime.now();
    if (StringUtils.hasText(request.getEmail())) {
      findMember.setEmail(request.getEmail()).setUpdatedAt(now); // 이메일
    }
    if (StringUtils.hasText(request.getName())) {
      findMember.setName(request.getName()).setUpdatedAt(now); // 이메일
    }
    if (StringUtils.hasText(request.getAddress())) {
      findMember.setAddress(request.getAddress()).setUpdatedAt(now); // 주소
    }
    if (StringUtils.hasText(request.getPhone())) {
      findMember.setPhone(request.getPhone()).setUpdatedAt(now); // 전화번호
    }
    if (null != request.getIsActive()) {
      findMember.setIsActive(request.getIsActive()).setUpdatedAt(now); // 회원 정보 갱신일
    }
    if (!passwordEncoder.matches(request.getPassword(), findMember.getPassword())) {
      findMember
          .setPassword(passwordEncoder.encode(request.getPassword())) // 신규 비밀번호
          .setPasswordUpdatedAt(now) // 비밀번호 갱신일
          .setUpdatedAt(now);
    }

    return memberRepository.save(findMember);
  }

  /**
   * 사용자 삭제
   *
   * @param id 삭제할 사용자 id
   * @return boolean 정상 삭제 여부
   */
  @Transactional
  public boolean deleteMember(Long id) {
    memberRepository.deleteById(id);

    final Optional<Member> optionalMember = memberRepository.findById(id);
    return !optionalMember.isPresent();
  }
}
