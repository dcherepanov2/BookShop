package org.example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/404")
    public String get404(){
        return "404";
    }

    @ExceptionHandler(BookShelfControllerException.class)
    public String handleError(Model model, BookShelfControllerException e){
        model.addAttribute("loginFormException", e.getMessage());
        return "404";
    }
}
