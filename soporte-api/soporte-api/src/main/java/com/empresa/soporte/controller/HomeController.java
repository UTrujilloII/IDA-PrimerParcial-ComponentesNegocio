package com.empresa.soporte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    // Página principal
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("nombre", "Luis Rodríguez 👋");
        return "index"; // busca /resources/templates/index.html
    }

    // Redirige a Swagger UI
    @RequestMapping("/swagger")
    public String swagger() {
        return "redirect:/swagger-ui/index.html";
    }
}
