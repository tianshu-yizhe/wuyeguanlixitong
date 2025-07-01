package com.property.controller;

import com.property.dto.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(LoginRequest loginRequest) {
        // 实际登录逻辑由Spring Security处理
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        // 实际登出逻辑由Spring Security处理
        return "redirect:/login?logout=true";
    }
}