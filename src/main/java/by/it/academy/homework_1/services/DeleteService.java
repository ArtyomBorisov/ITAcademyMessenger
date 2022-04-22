package by.it.academy.homework_1.services;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.dao.api.IAuditUserStorage;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import by.it.academy.homework_1.dao.api.IStorageUser;
import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.services.api.IDeleteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeleteService implements IDeleteService {

    private final IStorageUser storageUser;
    private final IStorageMessage storageMessage;
    private final IAuditUserStorage auditUserStorage;
    private final User deletedUser;

    public DeleteService(IStorageUser storageUser,
                         IStorageMessage storageMessage,
                         IAuditUserStorage auditUserStorage) {
        this.storageUser = storageUser;
        this.storageMessage = storageMessage;
        this.auditUserStorage = auditUserStorage;
        this.deletedUser = this.storageUser.get("deleted");
    }


    @Override
    public void deleteUser(String login, LocalDateTime lastUpdate) throws EssenceNotFound {
        List<Message> messages = this.storageMessage.get(login);
        for (Message message : messages) {
            message.setUserTo(this.deletedUser);
            this.storageMessage.update(message, message.getId(), message.getLastUpdate());
        }

        List<AuditUser> auditUsers = this.auditUserStorage.read(login);

        for (AuditUser auditUser : auditUsers) {
            if (login.equals(auditUser.getUser().getLogin())) {
                auditUser.setUser(this.deletedUser);
            }
            if (login.equals(auditUser.getAuthor().getLogin())) {
                auditUser.setAuthor(this.deletedUser);
            }
            this.auditUserStorage.update(auditUser, auditUser.getId(), auditUser.getLastUpdate());
        }

        this.storageUser.delete(login, lastUpdate);
    }

    @Override
    public void deleteMessage(Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.storageMessage.delete(id, lastUpdate);
    }

    @Override
    public void deleteAuditUser(Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.auditUserStorage.delete(id, lastUpdate);
    }
}
