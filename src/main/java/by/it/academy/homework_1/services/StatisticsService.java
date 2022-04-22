package by.it.academy.homework_1.services;

import by.it.academy.homework_1.services.api.IMessageService;
import by.it.academy.homework_1.services.api.IStatisticsService;
import by.it.academy.homework_1.services.api.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Transactional(readOnly = true)
public class StatisticsService implements IStatisticsService {

    private final AtomicLong sessionCounter;
    private final IUserService userService;
    private final IMessageService messageService;

    public StatisticsService(IUserService userService,
                              IMessageService messageService) {
        this.sessionCounter = new AtomicLong(0);
        this.userService = userService;
        this.messageService = messageService;
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
        stats.put("Количество зарегистрированных пользователей", this.userService.getCount());
        stats.put("Количество всех отправленных сообщений", this.messageService.getCount());
        stats.put("Количество активных сессий", this.getSessionCount());
        return stats;
    }

    private long getSessionCount() {
        return this.sessionCounter.get();
    }
}
