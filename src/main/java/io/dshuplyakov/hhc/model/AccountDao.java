package io.dshuplyakov.hhc.model;

import io.dshuplyakov.hhc.Config;
import io.dshuplyakov.hhc.dto.Account;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class AccountDao {

    private Connection connection;

    public AccountDao() {
        connection = Config.getInstance().getConnection();
    }


    public void insertAccounts(List<Account> accounts) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO dima.account VALUES(?, ?,?, ?,?, ?,?, ?,?, ?,?)");

        for (Account account : accounts) {
            pstmt.setInt(1, account.getId());
            pstmt.setString(2, account.getFname());
            pstmt.setString(3, account.getFname());
            pstmt.setString(4,  account.getEmail());
            pstmt.setString(5,  account.getPhone());
            pstmt.setString(6, "m");
            pstmt.setLong(7, account.getBirth());
            pstmt.setString(8, account.getCountry());
            pstmt.setString(9, account.getCity());
            pstmt.setLong(10, account.getJoined());
            pstmt.setString(11, "sd");
            pstmt.addBatch();
        }
        log.info("start insert");
        pstmt.executeBatch();
        log.info("finished");
    }
}
