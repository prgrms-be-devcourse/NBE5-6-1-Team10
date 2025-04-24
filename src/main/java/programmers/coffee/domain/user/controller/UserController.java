package programmers.coffee.domain.user.controller;

import jakarta.validation.Valid;
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
    public String signUpForm(Model model){
        model.addAttribute("userSignUpRequest", new UserSignUpRequest());
        return "user/signup-form";
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute UserSignUpRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/signup-form";
        }

        userService.signup(request);
        return "redirect:/";
    }

}