package by.it.academy.homework_1.controller.web.controllers;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.view.api.IMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/chats")
public class ChatsController {

    private final IMessageService messageService;

    public ChatsController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(@SessionAttribute(name = "user", required = false) String loginTo,
                        HttpSession session) {

        if (loginTo == null) {
            throw new SecurityException("Ошибка безопасности");
        }
        List<Message> messagesToUser = this.messageService.get(loginTo);
        session.setAttribute("messages", messagesToUser);

        return "chats";
    }
}
