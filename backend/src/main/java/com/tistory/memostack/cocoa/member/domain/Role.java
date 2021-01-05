package com.tistory.memostack.cocoa.member.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Role {

  // PK
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // 권한 이름
  @Column(unique = true, nullable = false, length = 50)
  private String roleName;

  // Role 1 : N MemberRole
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
  private Set<MemberRole> memberRoles = new HashSet<>();
}
