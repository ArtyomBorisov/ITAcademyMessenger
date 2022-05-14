package by.it.academy.homework_1.controller.web.controllers.rest;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.services.api.IMessageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class RestChatsController {

    private final IMessageService messageService;

    public RestChatsController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Message> index(@SessionAttribute(name = "user", required = false) User user) {
        if (user == null) {
            throw new SecurityException("Ошибка безопасности");
        }

        return this.messageService.get(user);
    }
}
