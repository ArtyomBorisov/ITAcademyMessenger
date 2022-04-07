package by.it.academy.homework_1.controller.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mainPage")
public class MainPageController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "mainPage";
    }
}
