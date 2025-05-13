package org.example.webshop.controller;


import org.example.webshop.model.UserDto;
import org.example.webshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDto") UserDto userDto, RedirectAttributes redirectAttributes, UserService userService) {
        userService.register(userDto);
        redirectAttributes.addFlashAttribute("message", "Registreringen lyckades!");
        return "redirect:/login";
    }
}
