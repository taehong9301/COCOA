package com.tistory.memostack.cocoa.security.application;

import com.tistory.memostack.cocoa.member.domain.Member;
import com.tistory.memostack.cocoa.member.infrastructure.MemberRepository;
import com.tistory.memostack.cocoa.security.domain.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 사용자가 로그인을 하면, IoC 에 등록된 UserDetailsService 의 loadUserByUsername() 을 호출 함. 여기서는 UserDetailsService
 * 가 PrincipalDetailsService.
 */
@Service
public class PrincipalDetailsService implements UserDetailsService {

  @Autowired private MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Member> optionalMember = memberRepository.findMemberByUsername(username);

    return optionalMember
        .map(PrincipalDetail::new) // PrincipalDetails 객체에 담아서 반환 (SecurityContextHolder 에 담기 위함)
        .orElseThrow(() -> new UsernameNotFoundException("Invalid email. username: " + username));
  }
}
