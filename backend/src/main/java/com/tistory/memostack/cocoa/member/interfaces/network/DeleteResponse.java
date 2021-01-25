package com.tistory.memostack.cocoa.member.interfaces.network;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteResponse {

  private boolean result;
}
