package programmers.coffee.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import programmers.coffee.domain.user.domain.CustomUserDetails;
import programmers.coffee.domain.user.dto.request.UserSignUpRequest;
import programmers.coffee.domain.user.service.UserService;

@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping
    public String userHome(@AuthenticationPrincipal CustomUserDetails userDetails, Model model){
        model.addAttribute("username", userDetails.getUsername());
        return "user/userHome";
    }

    @GetMapping("/signup")
    public String signUpForm(){
        return "user/signup-Form";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute UserSignUpRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/signUp-Form";
        }

        userService.signup(request);
        return "home";
    }

}