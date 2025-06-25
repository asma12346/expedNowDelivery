package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaviconController {
    
    @RequestMapping("favicon.ico")
    public void favicon() {
        // Ne rien faire, juste renvoyer 200 OK avec rien.
    }
}
