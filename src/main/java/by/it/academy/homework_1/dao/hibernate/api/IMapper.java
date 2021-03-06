package by.it.academy.homework_1.dao.hibernate.api;

public interface IMapper<Dto, Entity> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
}
