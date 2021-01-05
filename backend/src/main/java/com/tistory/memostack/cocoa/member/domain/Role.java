package com.tistory.memostack.cocoa.member.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Role {

  // PK
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  // 권한 이름
  @Column(nullable = false, length = 50)
  private String roleName;
}
