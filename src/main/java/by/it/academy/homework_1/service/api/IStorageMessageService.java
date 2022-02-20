package by.it.academy.homework_1.service.api;

import by.it.academy.homework_1.service.dto.Message;
import by.it.academy.homework_1.service.dto.SavedMessage;

import java.util.List;

public interface IStorageMessageService {

    /**
     * метод возращает список сообщений пользователю по его логину
     * @param loginUser - логин
     * @return List<SavedMessage> - список сообщений
     *         null - если сообщений пользователю нет
     */
    List<SavedMessage> getMessagesToUser(String loginUser);

    /**
     * метод сохраняет сообщение в хранилище
     * @param message сообщение для сохранения
     */
    void addToStorage(Message message);

    /**
     * метод возращает количество всех отправленных сообщений
     * @return количество отправленных сообщений
     */
    int getCountMessages();
}
