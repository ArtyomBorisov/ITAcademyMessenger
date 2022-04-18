package by.it.academy.homework_1.dao.hibernate.api.mapper;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.dao.hibernate.api.IMapper;
import by.it.academy.homework_1.dao.hibernate.api.entity.AuditUserEntity;

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
                this.userMapper.toDto(auditUserEntity.getUser()),
                this.userMapper.toDto(auditUserEntity.getAuthor()),
                auditUserEntity.getLastUpdate());
    }

    @Override
    public AuditUserEntity toEntity (AuditUser auditUser) {
        if (auditUser == null) {
            return null;
        }
        AuditUserEntity auditUserEntity = new AuditUserEntity();

        auditUserEntity.setUser(userMapper.toEntity(auditUser.getUser()));
        auditUserEntity.setText(auditUser.getText());
        auditUserEntity.setAuthor(userMapper.toEntity(auditUser.getAuthor()));
        auditUserEntity.setDtCreate(auditUser.getDtCreate());
        auditUserEntity.setLastUpdate(auditUser.getLastUpdate());
        auditUserEntity.setId(auditUser.getId());

        return auditUserEntity;
    }
}
