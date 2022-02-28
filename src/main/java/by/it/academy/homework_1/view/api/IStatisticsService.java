package by.it.academy.homework_1.view.api;

import java.util.Map;

public interface IStatisticsService {
    /**
     * метод увеличивает счетчик сессий (+1)
     * @return количество текущих сессий
     */
    long incSessionCount();

    /**
     * метод уменьшает счетчик сессий (-1)
     * @return количество текущих сессий
     */
    long decSessionCount();

    /**
     * метод возвращает статистику
     * @return статистика
     */
    Map<String, Object> getStats();
}
