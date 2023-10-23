package msemail.msemail.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/test-thymeleaf")
    public String testThymeleaf(Model model) {
        model.addAttribute("title", "Titre de l'exemple");
        model.addAttribute("message", "Ceci est un message d'exemple");
        return "email-template";
    }


}
