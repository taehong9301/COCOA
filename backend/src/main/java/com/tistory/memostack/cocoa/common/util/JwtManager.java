package com.tistory.memostack.cocoa.common.util;

import com.tistory.memostack.cocoa.member.domain.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtManager {
  private final String securityKey = "hello world"; // TODO 추후 zookeeper 로 민감정보 분리
  private final Long expiredTime = 1000 * 60L * 60L * 3L; // 유효시간 3시간

  /**
   * Member 정보를 담은 JWT 토큰을 생성한다.
   *
   * @param username Member 의 username
   * @param memberRoles Member 의 인가정보
   * @return String JWT 토큰
   */
  public String generateJwtToken(String username, List<String> memberRoles) {
    final Map<String, Object> claims = new HashMap<>();
    claims.put("username", username); // username
    claims.put("roles", memberRoles); // 인가정보

    Date now = new Date();
    return Jwts.builder()
        .setSubject(username) // 보통 username
        .setHeader(createHeader())
        .setClaims(claims) // 클레임, 토큰에 포함될 정보
        .setExpiration(new Date(now.getTime() + expiredTime)) // 만료일
        .signWith(SignatureAlgorithm.HS256, securityKey)
        .compact();
  }

  private Map<String, Object> createHeader() {
    Map<String, Object> header = new HashMap<>();
    header.put("typ", "JWT");
    header.put("alg", "HS256"); // 해시 256 사용하여 암호화
    header.put("regDate", System.currentTimeMillis());
    return header;
  }

  /**
   * Token 에서 Claim 을 가져온다.
   *
   * @param token JWT 토큰
   * @return Claims 클레임
   */
  private Claims getClaims(String token) {
    return Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token).getBody();
  }

  /**
   * 토큰으로 부터 username 을 가져온다.
   *
   * @param token JWT 토큰
   * @return String Member 의 username
   */
  public String getUsernameFromToken(String token) {
    return (String) getClaims(token).get("username");
  }

  /**
   * 토큰으로 부터 인가 정보를 가져온다.
   *
   * @param token JWT 토큰
   * @return Set<MemberRole> role 정보를 가지고 있는 Set
   */
  public List<MemberRole> getMemberRoleSetFromToken(String token) {
    return (List<MemberRole>) getClaims(token).get("roles");
  }
}
