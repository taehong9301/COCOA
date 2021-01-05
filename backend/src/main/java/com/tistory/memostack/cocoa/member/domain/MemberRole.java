package com.tistory.memostack.cocoa.member.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "member_role")
@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRole {
  // PK
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 외래키를 가지므로 MemberRole 이 연관 관계의 주인
  // MemberRole N : 1 Member
  @ManyToOne
  //  @JoinColumn(name = "member_id")
  private Member member;

  // 외래키를 가지므로 MemberRole 이 연관 관계의 주인
  // MemberRole N : 1 Role
  @ManyToOne
  //  @JoinColumn(name = "role_id")
  private Role role;
}
