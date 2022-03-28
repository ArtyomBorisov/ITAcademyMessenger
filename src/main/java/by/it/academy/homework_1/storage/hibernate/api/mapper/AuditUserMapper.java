package by.it.academy.homework_1.storage.hibernate.api.mapper;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.storage.hibernate.api.IMapper;
import by.it.academy.homework_1.storage.hibernate.api.entity.AuditUserEntity;

public class AuditUserMapper implements IMapper<AuditUser, AuditUserEntity> {
    private UserMapper userMapper = new UserMapper();

    @Override
    public AuditUser toDto (AuditUserEntity auditUserEntity) {
        if (auditUserEntity == null) {
            return null;
        }
        return new AuditUser(
                auditUserEntity.getId(),
                auditUserEntity.getDtCreate(),
                auditUserEntity.getText(),
                userMapper.toDto(auditUserEntity.getUser()),
                userMapper.toDto(auditUserEntity.getAuthor()));
    }

    @Override
    public AuditUserEntity toEntity (AuditUser auditUser) {
        if (auditUser == null) {
            return null;
        }
        AuditUserEntity auditUserEntity = new AuditUserEntity();
        auditUserEntity.setId(auditUser.getId());
        auditUserEntity.setUser(userMapper.toEntity(auditUser.getUser()));
        auditUserEntity.setText(auditUser.getText());
        auditUserEntity.setAuthor(auditUserEntity.getAuthor());
        auditUserEntity.setDtCreate(auditUser.getDtCreate());

        return auditUserEntity;
    }
}
