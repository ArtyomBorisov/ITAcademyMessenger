package by.it.academy.homework_1.storage.api;

import by.it.academy.homework_1.model.Message;

import java.util.List;

public interface IStorageMessage {

    /**
     * метод возращает список сообщений пользователю по его логину
     * @param loginUser - логин
     * @return List<Message> - список сообщений
     *         null - если сообщений пользователю нет
     */
    List<Message> get(String loginUser);

    /**
     * метод сохраняет сообщение в хранилище
     * @param message сообщение для сохранения
     */
    void add(Message message);

    /**
     * метод возращает количество всех отправленных сообщений
     * @return количество отправленных сообщений
     */
    int getCount();
}
