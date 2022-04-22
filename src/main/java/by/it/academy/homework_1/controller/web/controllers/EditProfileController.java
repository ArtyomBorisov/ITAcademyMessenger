package by.it.academy.homework_1.controller.web.controllers;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.services.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Controller
@RequestMapping("/editProfile")
public class EditProfileController {

    private final IUserService userService;

    public EditProfileController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(@SessionAttribute(name = "user", required = false) User user) {
        if (user == null) {
            throw new SecurityException("Ошибка безопасности");
        }

        return "editProfile";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String update(@SessionAttribute(name = "user", required = false) User user,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "birthday", required = false) String birthdayRaw,
                         HttpSession session,
                         Model model) {
        if (user == null) {
            throw new SecurityException("Ошибка безопасности");
        }


        LocalDate birthday = null;
        if (birthdayRaw != null) {
            try {
                birthday = LocalDate.parse(birthdayRaw);
            } catch (DateTimeParseException e) {
            }
        }

        User userUpdate = new User(user.getLogin(), password, name, birthday);

        this.userService.update(userUpdate, user.getLogin(), user.getLastUpdate());

        session.setAttribute("user", this.userService.get(user.getLogin()));
        model.addAttribute("inf", "Данные успешно обновлены");
        return "mainPage";
    }
}
