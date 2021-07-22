package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.services.LoginService;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final Logger logger = Logger.getLogger(LoginController.class);
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        logger.info("GET /login returns login_page.html");
        model.addAttribute("loginForm", new LoginForm());
        return "login_page";
    }

    @PostMapping("/auth")
    public String authenticate(LoginForm loginFrom) throws BookShelfControllerException {
        if (loginService.authenticate(loginFrom)) {
            logger.info("login OK redirect to book shelf");
            return "redirect:/books/shelf";
        } else {
            logger.info("login FAIL redirect back to login");
            throw new BookShelfControllerException("invalid username or password");
        }
    }

    @GetMapping(value = "/reg")
    public String reg(Model model) {
        logger.info("GET /registration returns registration_page.html");
        model.addAttribute("registrationForm", new LoginForm());
        return "registration_page.html";
    }

    @PostMapping("/registration")
    public String registration(LoginForm loginFrom) {
        loginService.add(loginFrom);
        return "redirect:/login";
    }
}
