package com.example.app.controller;

import com.example.app.entity.TodoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(
        Model model,
        ModelAndView modelAndView
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof TodoUser) {
            model.addAttribute("isLogin", authentication.isAuthenticated());
        } else {
            model.addAttribute("isLogin", false);
        }

        String infoMessage = (String) model.getAttribute("info_message");
        model.addAttribute("infoMessage", infoMessage);
        return "home";
    }
}
