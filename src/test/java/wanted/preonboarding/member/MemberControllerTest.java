package wanted.preonboarding.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import wanted.preonboarding.member.controller.MemberController;
import wanted.preonboarding.member.dto.MemberCreateForm;
import wanted.preonboarding.member.service.MemberService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class MemberControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    MemberService memberService;

    @Autowired
    MemberController memberController;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void memberCreate() throws Exception {
        MemberCreateForm memberCreateForm = new MemberCreateForm("test@gmail.com", "qwer1234");

        String memberCreateFormToJson = objectMapper.writeValueAsString(memberCreateForm);

        mvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(memberCreateFormToJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("이메일 형식 아닐때")
    void isEmailValid() throws Exception {
        MemberCreateForm memberCreateForm = new MemberCreateForm("asdgmail.com", "qwer1234");

        String memberCreateFormToJson = objectMapper.writeValueAsString(memberCreateForm);

        mvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(memberCreateFormToJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("비밀번호 8글자보다 짧을 때")
    void isPasswordValid() throws Exception {
        MemberCreateForm memberCreateForm = new MemberCreateForm("test@gmail.com", "qwer123");

        String memberCreateFormToJson = objectMapper.writeValueAsString(memberCreateForm);

        mvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(memberCreateFormToJson))
                .andExpect(status().isBadRequest());
    }
}
