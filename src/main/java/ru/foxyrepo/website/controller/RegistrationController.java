package ru.foxyrepo.website.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.foxyrepo.website.model.Profile;
import ru.foxyrepo.website.service.SaveNewUser;
import ru.foxyrepo.website.service.SecurityService;
import ru.foxyrepo.website.util.UserValidator;

@Controller
@Log4j
public class RegistrationController {

    @Autowired
    private SaveNewUser saveNewUser;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/index";
        }
        model.addAttribute("userForm", new Profile());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") Profile userForm, BindingResult bindingResult) {
        System.out.println("Отправка регистрации");
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        saveNewUser.save(userForm);
        securityService.autoLogin(userForm.getLogin(), userForm.getPassword());
        return "redirect:/index";
    }
}
