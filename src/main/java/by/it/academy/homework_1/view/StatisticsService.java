package by.it.academy.homework_1.view;

import by.it.academy.homework_1.view.api.IMessageService;
import by.it.academy.homework_1.view.api.IStatisticsService;
import by.it.academy.homework_1.view.api.IUserService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class StatisticsService implements IStatisticsService {

    private final static StatisticsService instance = new StatisticsService();
    private final AtomicLong sessionCounter;
    private final IUserService userService;
    private final IMessageService messageService;

    private StatisticsService() {
        this.sessionCounter = new AtomicLong(0);
        this.userService = UserService.getInstance();
        this.messageService = MessageService.getInstance();
    }

    @Override
    public long incSessionCount() {
        return this.sessionCounter.incrementAndGet();
    }

    @Override
    public long decSessionCount() {
        return this.sessionCounter.decrementAndGet();
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("Количество зарегестрированных пользователей", this.userService.getCount());
        stats.put("Количество всех отправленных сообщений", this.messageService.getCount());
        stats.put("Количество активных сессий", this.getSessionCount());
        return stats;
    }

    private long getSessionCount() {
        return this.sessionCounter.get();
    }

    public static StatisticsService getInstance() {
        return instance;
    }
}
