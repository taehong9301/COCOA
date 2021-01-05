package com.tistory.memostack.cocoa.member.domain;

import com.tistory.memostack.cocoa.common.converter.BooleanToYNConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member {

  // PK
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  // email (Email 로 로그인)
  @Column(unique = true, nullable = false, length = 80)
  private String email;

  // 비밀번호
  @Column(nullable = false)
  private String password;

  // 이름 (또는 닉네임)
  @Column(nullable = false, length = 50)
  private String name;

  // 전화번호
  @Column(length = 50)
  private String phone;

  // 주소 (배송지)
  private String address;

  // 휴면 계정 여부
  @Convert(converter = BooleanToYNConverter.class)
  @Column(nullable = false, length = 1)
  private boolean isActive;

  // 계정 정보 등록일
  @Column(nullable = false)
  private LocalDateTime registerAt;

  // 계정 정보 수정일
  private LocalDateTime updatedAt;

  // 비밀번호 갱신일
  @Column(nullable = false)
  private LocalDateTime passwordUpdatedAt;
}
