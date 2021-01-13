package com.tistory.memostack.cocoa.member.interfaces;

import com.tistory.memostack.cocoa.member.application.MemberService;
import com.tistory.memostack.cocoa.member.interfaces.network.MemberRequest;
import com.tistory.memostack.cocoa.member.interfaces.network.MemberResponse;
import com.tistory.memostack.cocoa.member.interfaces.network.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("rest/api/v1")
public class MemberController {

  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  /**
   * 회원 가입
   *
   * @param memberRequest 사용자 정보를 담고 있는 객체
   * @return
   */
  @PostMapping("member")
  public @ResponseBody MemberResponse join(@RequestBody MemberRequest memberRequest) {
    log.info("Sign up. " + memberRequest.toString());
    return memberService.insertMember(memberRequest);
  }

  /**
   * @param memberRequest
   * @return
   */
  @PostMapping("authentication")
  public @ResponseBody TokenResponse authentication(@RequestBody MemberRequest memberRequest) {
    log.info("Try auth. " + memberRequest.toString());

    // TODO 개발 필요
    return TokenResponse.builder().token("daksdjoaj").build();
  }
}
