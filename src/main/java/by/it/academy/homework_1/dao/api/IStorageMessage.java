package by.it.academy.homework_1.dao.api;

import by.it.academy.homework_1.model.Message;

import java.util.List;

public interface IStorageMessage extends ICUDRepository<Message, Long> {

    /**
     * Получение списка сообщений пользователю по его логину
     * @param loginUser - логин
     * @return List<Message> - список сообщений
     *         null - если сообщений пользователю нет
     */
    List<Message> get(String loginUser);

    /**
     * Получение количества всех отправленных сообщений
     * @return количество отправленных сообщений
     */
    long getCount();
}
