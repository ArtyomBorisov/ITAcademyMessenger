package by.it.academy.homework_1.service.api;

public interface ICounter {
    /**
     * метод увеличивает значение счётчика
     */
    void increment();

    /**
     * метод уменьшает значение счётчика
     */
    void decrement();

    /**
     * метод возвращает значение счётчика
     * @return число (значение)
     */
    int getCount();
}
