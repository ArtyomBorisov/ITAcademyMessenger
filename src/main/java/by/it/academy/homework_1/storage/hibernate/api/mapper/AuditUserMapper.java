package by.it.academy.homework_1.storage.hibernate.api.mapper;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageUser;
import by.it.academy.homework_1.storage.hibernate.api.entity.AuditUserEntity;

public class AuditUserMapper {
    private HibernateStorageUser storageUser;

    public AuditUserMapper() {
        this.storageUser = HibernateStorageUser.getInstance();
    }

    public AuditUser toDto (AuditUserEntity auditUserEntity) {
        return new AuditUser(
                auditUserEntity.getId(),
                auditUserEntity.getDtCreate(),
                auditUserEntity.getText(),
                this.storageUser.get(auditUserEntity.getUser()),
                this.storageUser.get(auditUserEntity.getAuthor()));
    }

    public AuditUserEntity toEntity (AuditUser auditUser) {
        AuditUserEntity auditUserEntity = new AuditUserEntity();
        auditUserEntity.setId(auditUser.getId());
        auditUserEntity.setUser(auditUser.getUser() == null ? null : auditUser.getUser().getLogin());
        auditUserEntity.setText(auditUser.getText());
        auditUserEntity.setAuthor(auditUserEntity.getAuthor() == null ? null : auditUser.getAuthor().getLogin());
        auditUserEntity.setDtCreate(auditUser.getDtCreate());

        return auditUserEntity;
    }
}
