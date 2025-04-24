package programmers.coffee.domain.user.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import programmers.coffee.domain.user.domain.CustomUserDetails;
import programmers.coffee.domain.user.domain.User;
import programmers.coffee.domain.user.dto.request.UserSignUpRequest;
import programmers.coffee.domain.user.service.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static programmers.coffee.domain.user.domain.Role.*;

@WithMockUser
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @DisplayName("회원 메인 페이지를 조회 할때 view 이름은 user/userHome 이다.")
    @Test
    void userHome() throws Exception {
        //given & when & then
        String name = "테스터";
        CustomUserDetails userDetails = new CustomUserDetails(createUser(name));

        mockMvc.perform(
                get("/users")
                        .with(csrf())
                        .with(user(userDetails))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("user/userHome"))
                .andExpect(model().attribute("username", name));
    }

    @DisplayName("회원 가입폼을 요청 할때 view 이름은 user/signup-form 이다.")
    @Test
    void signUpForm() throws Exception {
        //given //when //then
        mockMvc.perform(
                get("/users/signup")
                        .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("user/signup-form"));
    }

    @DisplayName("회원 가입 요청을 할때 예외가 없다면 view 이름은 redirect:/ 이다.")
    @Test
    void signUp() throws Exception {
        //given
        UserSignUpRequest request = createSignUpRequest("테스터", "test@naver.com");

        //when //then
        mockMvc.perform(
                post("/users/signup")
                        .with(csrf())
                        .param("name", request.getName())
                        .param("email", request.getEmail())
                        .param("zipCode", request.getZipCode())
                        .param("password", request.getPassword())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @DisplayName("회원 가입 요청을 할때 이름에 공백이 포함 된다면 에러 메시지와 함께 회원가입 폼으로 돌아간다.")
    @Test
    void signUp_WhenNameContainsWhitespace_ThenReturnSignUpForm() throws Exception {
        //given
        UserSignUpRequest request = createSignUpRequest("테스터", "test@naver.com");

        //when //then
        mockMvc.perform(
                        post("/users/signup")
                                .with(csrf())
                                .param("name", "공 백")
                                .param("email", request.getEmail())
                                .param("zipCode", request.getZipCode())
                                .param("password", request.getPassword())
                )
                .andExpect(status().isOk())
                .andExpect(view().name("user/signup-form"))
                .andExpect(model().hasErrors())
                .andExpect(content().string(Matchers.containsString("이름은 공백을 포함할 수 없습니다.")));
    }

    @DisplayName("회원 가입 요청을 할때 이름이 null 이라면 에러 메시지와 함께 회원가입 폼으로 돌아간다.")
    @Test
    void signUp_WhenNameIsNull_ThenReturnSignUpForm() throws Exception {
        //given
        UserSignUpRequest request = createSignUpRequest(null, "test@naver.com");

        //when //then
        mockMvc.perform(
                        post("/users/signup")
                                .with(csrf())
                                .param("name", request.getName())
                                .param("email", request.getEmail())
                                .param("zipCode", request.getZipCode())
                                .param("password", request.getPassword())
                )
                .andExpect(status().isOk())
                .andExpect(view().name("user/signup-form"))
                .andExpect(model().hasErrors())
                .andExpect(content().string(Matchers.containsString("이름은 필수 입력값 입니다.")));
    }

    private User createUser(String name){
        return User.builder()
                .name(name)
                .email("test@naver.com")
                .password("tempPassword")
                .zipCode("1234-5678")
                .role(ROLE_USER)
                .build();
    }

    private UserSignUpRequest createSignUpRequest(String name, String email){
        return UserSignUpRequest.builder()
                .name(name)
                .email(email)
                .password("tempPassword")
                .zipCode("zipCode")
                .build();
    }
}