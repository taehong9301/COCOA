package com.tistory.memostack.cocoa.member.exception;

public class NotfoundMemberException extends RuntimeException {
  private static final String PREFIX_ERR_MSG = "Not found member.";

  public NotfoundMemberException(String msg) {
    super(PREFIX_ERR_MSG + " " + msg);
  }
}
