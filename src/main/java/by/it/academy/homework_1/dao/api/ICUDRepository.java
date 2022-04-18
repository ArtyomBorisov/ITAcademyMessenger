package by.it.academy.homework_1.dao.api;

import java.time.LocalDateTime;

public interface ICUDRepository<T, ID> {
    /**
     * Создание сущности в хранилище
     * @param item создаваемая сущность
     * @return созданную сущность
     */
    T create(T item);

    /**
     * Обновление сущности по координатам
     * @param item обновленная сущность
     * @param id координата сущности,  однозначно идентифицирующая обновляемую сущность
     * @param lastUpdate координата сущности, когда её последний раз обновляли
     * @throws EssenceNotFound если не удалось найти обновляемую сущность
     * @return обновленная сущность
     */
    T update(T item, ID id, LocalDateTime lastUpdate) throws EssenceNotFound;

    /**
     * Удаление сущности по координатам
     * @param id координата сущности,  однозначно идентифицирующая обновляемую сущность
     * @param lastUpdate координата сущности, когда её последний раз обновляли
     * @throws EssenceNotFound если не удалось найти удаляемую сущность
     */
    void delete(ID id, LocalDateTime lastUpdate) throws EssenceNotFound;
}
