package com.tistory.memostack.cocoa.common.filter;

import com.tistory.memostack.cocoa.common.util.JwtManager;
import com.tistory.memostack.cocoa.security.application.PrincipalDetailsService;
import com.tistory.memostack.cocoa.security.domain.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

  @Autowired private PrincipalDetailsService principalDetailsService;
  @Autowired private JwtManager jwtManager;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      // token 으로 부터 username을 가지고 옴
      final String token = getJwtFromRequest(request);
      log.info(token);
      String username = jwtManager.getUsernameFromToken(token);

      // 인증
      final PrincipalDetails principalDetails =
          (PrincipalDetails) principalDetailsService.loadUserByUsername(username);
      final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(
              principalDetails.getUsername(), null, principalDetails.getAuthorities());
      usernamePasswordAuthenticationToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

    } catch (Exception ex) {
      log.error("Could not set user authentication in security context", ex);
    }

    filterChain.doFilter(request, response);
  }

  private boolean isTokenFormat(String token) {
    return StringUtils.hasText(token) && token.startsWith("Bearer ");
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String token = request.getHeader("Authorization");

    if (isTokenFormat(token)) {
      return token.split(" ")[1];
    }
    return null;
  }
}
