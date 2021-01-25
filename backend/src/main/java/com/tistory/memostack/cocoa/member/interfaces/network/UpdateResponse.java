package com.tistory.memostack.cocoa.member.interfaces.network;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateResponse {

  private String username;

  private String email;

  private String name;

  private String phone;

  private String address;

  private LocalDateTime updateAt;

  private LocalDateTime passwordUpdateAt;
}
