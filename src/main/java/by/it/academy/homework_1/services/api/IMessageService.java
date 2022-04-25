package by.it.academy.homework_1.services.api;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface IMessageService {
    /**
     * Получение списка сообщений по переданному пользователю
     * @param currentUser пользователь
     * @return список сообщений
     */
    List<Message> get(User currentUser);

    /**
     * Получение списка сообщений пользователю по его логину
     * @param currentLoginUser логин
     * @return список сообщений
     */
    List<Message> get(String currentLoginUser);

    /**
     * Сохранение сообщения
     * @param message сообщение
     */
    void add(Message message);

    /**
     * Получение количества всех отправленных сообщений
     * @return количество сообщений
     */
    long getCount();

    /**
     * Обновление сообщения
     * @param message сообщение с обновленными полями
     * @param id идентификационный номер сообщения
     * @param lastUpdate дата/время последнего обновления
     * @return
     */
    Message update(Message message, Long id, LocalDateTime lastUpdate);

    /**
     * Удаление сообщения по координатам
     * @param id идентификационный номер сообщения
     * @param lastUpdate дата/время последнего обновления
     */
    void delete(Long id, LocalDateTime lastUpdate);
}
