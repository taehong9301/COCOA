package com.tistory.memostack.cocoa.security.config;

import com.tistory.memostack.cocoa.common.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * 비밀번호 암호화 인코딩 방식 정의
   *
   * @return BCryptPasswordEncoder BCrypt 단방향 암호화
   */
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtFilter jwtFilter() {
    return new JwtFilter();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("resources/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        // API 를 사용하려면 인증 필요
        .antMatchers("/rest/api/v1/authentication", "/rest/api/v1/member")
        .permitAll()
        .antMatchers("/rest/api/**")
        .authenticated()
        // 기타 url 은 모두 허용
        .anyRequest()
        .permitAll()
        .and()
        // JWT 필터 적용
        .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
