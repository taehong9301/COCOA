package com.tistory.memostack.cocoa.member.interfaces;

import com.tistory.memostack.cocoa.member.application.MemberService;
import com.tistory.memostack.cocoa.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MemberController.class)
@DisplayName("MemberController 테스트")
class MemberControllerTest {

  private MockMvc mvc;

  @MockBean private MemberService memberService;

  @BeforeEach
  public void setUp() {
    mvc =
        MockMvcBuilders.standaloneSetup(new MemberController(memberService))
            .addFilters(new CharacterEncodingFilter("UTF-8", true)) // utf-8 필터 추가
            .build();
  }

  @Test
  @DisplayName("회원가입 테스트")
  void insertMemberTest() throws Exception {
    // given
    final LocalDateTime now = LocalDateTime.now();
    given(memberService.insertMember(any()))
        .willReturn(
            Member.builder()
                .id(1L)
                .email("test@gmail.com")
                .password("1234")
                .name("Test User")
                .phone("010-1234-5678")
                .address("서울")
                .isActive(true)
                .registerAt(now)
                .passwordUpdatedAt(now)
                .build());

    // when
    final ResultActions actions =
        mvc.perform(
            post("/rest/api/v1/member")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(
                    "{"
                        + "  \"email\" : \"test@gmail.com\", "
                        + "  \"password\" : \"1234\", "
                        + "  \"name\": \"Test User\", "
                        + "  \"phone\": \"010-1234-5678\", "
                        + "  \"address\": \"서울\" "
                        + "}"));

    // then
    actions
        .andExpect(status().isCreated())
        .andExpect(jsonPath("email").value("test@gmail.com"))
        .andExpect(jsonPath("name").value("Test User"))
        .andExpect(jsonPath("phone").value("010-1234-5678"))
        .andExpect(jsonPath("address").value("서울"));
  }
}
