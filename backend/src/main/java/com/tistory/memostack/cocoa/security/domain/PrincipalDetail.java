package com.tistory.memostack.cocoa.security.domain;

import com.tistory.memostack.cocoa.member.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/** SecurityContextHolder 에 담기 위한 Principal 객체 생성 */
public class PrincipalDetail implements UserDetails {

  private final Member member;

  public PrincipalDetail(Member member) {
    this.member = member;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return member.getPassword();
  }

  @Override
  public String getUsername() {
    return member.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return member.isActive();
  }
}
