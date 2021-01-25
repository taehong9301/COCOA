package com.tistory.memostack.cocoa.member.interfaces.network;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequest {

  private String username;

  private String email;

  private String password;

  private String name;

  private String phone;

  private String address;
}
