package com.tistory.memostack.cocoa.member.interfaces.network;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

  private String email;

  private String name;

  private String phone;

  private String address;
}
