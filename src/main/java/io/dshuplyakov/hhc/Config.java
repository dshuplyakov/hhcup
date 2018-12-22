package io.dshuplyakov.hhc;

import lombok.Getter;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
public class Config {

    private ClickHouseDataSource dataSource;
    private Connection connection;
    private static volatile Config config;

    private Config() {
        ClickHouseProperties properties = new ClickHouseProperties();
        dataSource = new ClickHouseDataSource("jdbc:clickhouse://srv7-phumkaok:8123", properties);
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Config getInstance(){
        if (config == null) {
            synchronized (Config.class) {
                config = new Config();
            }
        }
        return config;
    }

}
