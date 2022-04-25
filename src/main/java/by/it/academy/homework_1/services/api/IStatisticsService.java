package by.it.academy.homework_1.services.api;

import java.util.Map;

public interface IStatisticsService {
    /**
     * Увеличение счетчика сессий (+1)
     * @return количество текущих открытых сессий
     */
    long incSessionCount();

    /**
     * Уменьшение счетчика сессий (-1)
     * @return количество текущих открытых сессий
     */
    long decSessionCount();

    /**
     * Получение статистики
     * @return статистика
     */
    Map<String, Object> getStats();
}
