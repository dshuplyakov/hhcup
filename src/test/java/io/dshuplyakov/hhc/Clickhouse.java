package io.dshuplyakov.hhc;


import org.junit.Test;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.Connection;

/**
 * Created by Dmitry on 21.12.2018.
 */
public class Clickhouse {

    private ClickHouseDataSource dataSource;
    private Connection connection;

    @Test
    public void testName() throws Exception {
        ClickHouseProperties properties = new ClickHouseProperties();
        dataSource = new ClickHouseDataSource("jdbc:clickhouse://srv7-phumkaok:8123", properties);
        connection = dataSource.getConnection();


        System.out.println(111);

    }
}
