package com.example.PracticaSpring.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/hola")
    public String saludar(){
        return "Hola como estas?";
    }
}
