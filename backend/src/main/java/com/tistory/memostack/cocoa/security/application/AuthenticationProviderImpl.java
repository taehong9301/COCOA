package com.tistory.memostack.cocoa.security.application;

import com.tistory.memostack.cocoa.security.domain.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

  @Autowired private PrincipalDetailsService principalDetailsService;
  @Autowired private BCryptPasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    // authentication 으로 부터 username 과 password 를 가지고 옴
    String username = (String) authentication.getPrincipal();
    String password = (String) authentication.getCredentials();

    // 인증 시도를 위해 저장소로부터 user 정보를 가지고 옴
    final PrincipalDetails principalDetails =
        (PrincipalDetails) principalDetailsService.loadUserByUsername(username);

    // 인증 비밀번호, 사용자 활성화/비활성화 확인 후 토큰 생성하여 로그인
    if (passwordEncoder.matches(password, principalDetails.getPassword())
        && principalDetails.isEnabled()) {
      return new UsernamePasswordAuthenticationToken(
          username, password, principalDetails.getAuthorities());
    }

    // 로그인 실패
    return null;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
