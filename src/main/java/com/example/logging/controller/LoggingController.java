package com.example.logging.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.logging.service.Greeting;

@Controller
public class LoggingController {
    private final Greeting greeting;

    public LoggingController(Greeting greeting) {
       this.greeting = greeting;
    }

    @GetMapping("/greeting")
	public String greetingForm(Model model) {
		String text = greeting.getGreeting("AOP", "テスト");
		model.addAttribute("greeting", text);
		return "greeting";
	}
}
