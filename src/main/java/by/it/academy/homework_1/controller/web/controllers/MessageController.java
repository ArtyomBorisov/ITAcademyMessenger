package by.it.academy.homework_1.controller.web.controllers;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.view.api.IMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final IMessageService messageService;
    private final String INFORM_KEY = "inf";
    private final String URL_FORWARD_KEY = "message";

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return URL_FORWARD_KEY;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@SessionAttribute(name = "user", required = false) String loginFrom,
                         @RequestParam(name = "loginTo") String loginTo,
                         @RequestParam(name = "messageText") String messageText,
                         Model model) {

        if (loginTo == null) {
            throw new SecurityException("Ошибка безопасности");
        }

        try {
            messageService.add(new Message(loginFrom, loginTo, messageText, LocalDateTime.now()));
            model.addAttribute(INFORM_KEY, "Сообщение отправлено");
            return "mainPage";
        } catch (IllegalArgumentException e) {
            model.addAttribute(INFORM_KEY, e.getMessage());
            return URL_FORWARD_KEY;
        }
    }
}
