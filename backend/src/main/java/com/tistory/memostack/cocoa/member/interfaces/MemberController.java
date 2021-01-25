package com.tistory.memostack.cocoa.member.interfaces;

import com.tistory.memostack.cocoa.member.application.MemberService;
import com.tistory.memostack.cocoa.member.domain.Member;
import com.tistory.memostack.cocoa.member.exception.NotfoundMemberException;
import com.tistory.memostack.cocoa.member.interfaces.network.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

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
  public ResponseEntity<MemberResponse> join(@RequestBody MemberRequest memberRequest) {
    log.info("Sign up. {}", memberRequest.toString());

    final Member insertedMember =
        memberService.insertMember(
            Member.builder()
                .username(memberRequest.getUsername())
                .email(memberRequest.getEmail())
                .password(memberRequest.getPassword())
                .name(memberRequest.getName())
                .address(memberRequest.getAddress())
                .phone(memberRequest.getPhone())
                .build());

    log.info("Success to join member. Member: {}", insertedMember.toString());
    return new ResponseEntity<>(
        MemberResponse.builder()
            .username(insertedMember.getUsername())
            .email(insertedMember.getEmail())
            .address(insertedMember.getAddress())
            .name(insertedMember.getName())
            .phone(insertedMember.getPhone())
            .build(),
        HttpStatus.CREATED);
  }

  /**
   * 사용자 정보 수정
   *
   * @param memberRequest 사용자 정보를 담고 있는 객체
   * @return ResponseEntity<MemberResponse> 사용자의 정보 수정 완료 여부
   */
  @PutMapping("member/{id}")
  public ResponseEntity<UpdateResponse> updateMember(
      @PathVariable("id") Long id, @RequestBody MemberRequest memberRequest) {
    log.info("Update member. id: {}, details: {}", id, memberRequest);

    final Member updatedMember =
        memberService.updateMember(
            Member.builder()
                .id(id)
                .email(memberRequest.getEmail())
                .password(memberRequest.getPassword())
                .name(memberRequest.getName())
                .address(memberRequest.getAddress())
                .phone(memberRequest.getPhone())
                .build());

    log.info("Success to update member. Member: {}", updatedMember.toString());
    return new ResponseEntity<>(
        UpdateResponse.builder()
            .username(updatedMember.getUsername())
            .email(updatedMember.getEmail())
            .address(updatedMember.getAddress())
            .name(updatedMember.getName())
            .phone(updatedMember.getPhone())
            .updateAt(updatedMember.getUpdatedAt())
            .passwordUpdateAt(updatedMember.getPasswordUpdatedAt())
            .build(),
        HttpStatus.OK);
  }

  /**
   * 회원 탈퇴
   *
   * @param id 사용자의 id 값
   * @return ResponseEntity<DeleteResponse> 사용자 정상 탈퇴 여부
   */
  @DeleteMapping("member/{id}")
  public ResponseEntity<DeleteResponse> deleteMember(@PathVariable Long id) {
    log.info("Delete member. id: {}", id);
    final boolean isDeleted = memberService.deleteMember(id);

    if (!isDeleted) {
      // 삭제 실패
      throw new NotfoundMemberException("Fail to delete member. id: " + id);
    }

    log.info("Success to delete member.");
    return new ResponseEntity<>(DeleteResponse.builder().result(isDeleted).build(), HttpStatus.OK);
  }

  /**
   * 로그인
   *
   * @param authorization username 과 password 가 담긴 header 의 authorization
   * @return String Jwt 토큰 값
   */
  @PostMapping("authentication")
  public @ResponseBody ResponseEntity<TokenResponse> authentication(
      @RequestHeader String authorization) {
    // Header 에서 계정 정보를 가지고 와서 Base64 복호화
    log.info("Authorization: {}", authorization);
    final String encodedUsernamePassword = authorization.split(" ")[1];
    final String[] usernamePassword =
        new String(Base64.getDecoder().decode(encodedUsernamePassword)).split(":");

    // 로그인 및 jwt 토큰 발급
    log.info("Try login. username: {}", usernamePassword[0]);
    final String token = memberService.login(usernamePassword[0], usernamePassword[1]);
    log.info("Token for {}: {}", usernamePassword[0], token);

    return new ResponseEntity<>(TokenResponse.builder().token(token).build(), HttpStatus.OK);
  }
}
