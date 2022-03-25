package by.it.academy.homework_1.storage.sql.api;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class SQLDBInitializer implements AutoCloseable {
    private volatile static SQLDBInitializer instance;

    private ComboPooledDataSource cpds;

    private SQLDBInitializer() throws PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5433/messenger?ApplicationName=MessengerDB");
        cpds.setUser("postgres");
        cpds.setPassword("postgres");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

    public DataSource getDataSource() {
        return cpds;
    }

    public static SQLDBInitializer getInstance() {
        if(instance == null){
            synchronized (SQLDBInitializer.class){
                if(instance == null){
                    try {
                        instance = new SQLDBInitializer();
                    } catch (Exception e) {
                        throw new RuntimeException("Ошибка подключения к базе", e);
                    }
                }
            }
        }
        return instance;
    }

    @Override
    public synchronized void close() throws Exception {
        cpds.close();
    }
}
