package io.dshuplyakov.hhc;


import org.junit.Test;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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


        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO dima.account VALUES(?, ?)");

        for (int i = 0; i < 200; i++) {
            pstmt.setInt(1, i);
            pstmt.setString(2, "Zhan" + i);
            pstmt.addBatch();
        }
        pstmt.executeBatch();

        System.out.println(111);

    }
}
