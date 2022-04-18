package by.it.academy.homework_1.controller.web.controllers;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.services.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final IUserService userService;
    private final String INFORM_KEY = "inf";
    private final String URL_FORWARD_KEY = "signUp";

    public SignUpController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return URL_FORWARD_KEY;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestParam(name = "login") String login,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "birthday", required = false) String birthdayRaw,
                         HttpSession session,
                         Model model) {
        LocalDate birthday = null;
        if (birthdayRaw != null) {
            try {
                birthday = LocalDate.parse(birthdayRaw);
            } catch (DateTimeParseException e) {
            }
        }

        User user = new User(login, password, name, birthday);

        try {
            userService.signUp(user);
            session.setAttribute("user", user);
            model.addAttribute(INFORM_KEY, "Аккаунт успешно создан.");
            return "redirect:/";
        } catch (IllegalArgumentException e){
            model.addAttribute(INFORM_KEY, e.getMessage());
            return URL_FORWARD_KEY;
        }
    }
}
