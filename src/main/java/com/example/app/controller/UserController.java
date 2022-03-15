package com.example.app.controller;

import com.example.app.form.UserForm;
import com.example.app.service.TodoUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TodoUserService todoUserService;

    /**
     * 登録ページの表示
     *
     * @return viewPath
     */
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user/signup";
    }

    /**
     * ユーザ登録
     * @param userForm userForm
     * @param bindingResult bindingResult
     * @param redirectAttributes redirectAttributes
     * @param request request
     * @return ModelAndView
     */
    @PostMapping("/signup")
    public ModelAndView register(
            @Validated @ModelAttribute UserForm userForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("userForm", userForm);
            return modelAndView;
        }

        String infoMessage = "ユーザを作成しました";
        try {
            todoUserService.create(userForm);
        } catch (Exception e) {
            log.error("ユーザ作成 or ログイン失敗", e);
            infoMessage = "ユーザの作成に失敗しました。";
        }
        redirectAttributes.addFlashAttribute("info_message", infoMessage);
        return new ModelAndView("redirect:/");
    }

}
