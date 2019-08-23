package kr.co.nexsys.mcp.homemanager.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public static String home() {
        return "This is home";
    }
}
