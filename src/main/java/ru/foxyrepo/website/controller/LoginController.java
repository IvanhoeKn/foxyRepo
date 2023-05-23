package ru.foxyrepo.website.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.foxyrepo.website.service.SecurityService;

@Controller
@Log4j
public class LoginController {

    @Autowired
    private SecurityService securityService;

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (securityService.isAuthenticated()) {
            return "redirect:/index";
        }
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        return "login";
    }
}
