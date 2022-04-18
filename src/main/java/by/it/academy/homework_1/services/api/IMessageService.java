package by.it.academy.homework_1.services.api;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;

import java.util.List;

public interface IMessageService {
    /**
     * метод возвращает список сообщений по переданному пользователю
     * @param currentUser пользователь
     * @return список сообщений
     */
    List<Message> get(User currentUser);

    /**
     * метод возвращает список сообщений пользователю по его логину
     * @param currentLoginUser логин
     * @return список сообщений
     */
    List<Message> get(String currentLoginUser);

    /**
     * метод сохраняет сообщение
     * @param message сообщение
     */
    void add(Message message);

    /**
     * метод возвращает количество всех отправленных сообщений
     * @return количество сообщений
     */
    long getCount();
}
