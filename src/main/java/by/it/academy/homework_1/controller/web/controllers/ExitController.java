package by.it.academy.homework_1.controller.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/exit")
public class ExitController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
