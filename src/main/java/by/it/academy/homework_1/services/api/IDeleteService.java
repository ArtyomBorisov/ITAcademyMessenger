package by.it.academy.homework_1.services.api;

import java.time.LocalDateTime;

public interface IDeleteService {
    void deleteUser(String login, LocalDateTime lastUpdate);

    void deleteMessage(Long id, LocalDateTime lastUpdate);

    void deleteAuditUser(Long id, LocalDateTime lastUpdate);
}
