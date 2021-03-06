package io.dshuplyakov.hhc.model;

import com.google.common.base.Stopwatch;
import com.google.inject.Inject;
import io.dshuplyakov.hhc.config.Config;
import io.dshuplyakov.hhc.dto.Account;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AccountDao {

    @Inject
    Config config;

    public void insertAccounts(List<Account> accounts) throws SQLException {
        PreparedStatement pstmt = config.getConnection().prepareStatement("INSERT INTO dima.account VALUES(?, ?,?, ?,?, ?,?, ?,?, ?,?)");

        for (Account account : accounts) {
            pstmt.setInt(1, account.getId());
            pstmt.setString(2, account.getFname());
            pstmt.setString(3, account.getSname());
            pstmt.setString(4,  account.getEmail());
            pstmt.setString(5,  account.getPhone());
            pstmt.setString(6, account.getSex());
            pstmt.setInt(7, account.getBirth());
            pstmt.setString(8, account.getCountry());
            pstmt.setString(9, account.getCity());
            pstmt.setInt(10, account.getJoined());
            pstmt.setString(11, account.getStatus());
            pstmt.addBatch();
        }
        log.info("start insert");
        pstmt.executeBatch();
        log.info("finished");
    }


    public Account get(Long id) throws SQLException {
        Stopwatch start = Stopwatch.createStarted();
        Statement stmt = config.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("select * from dima.account where id = " + id);
        Account result = null;
        if (rs.next()) {
            result = rsMapToAccount(rs);
        }

        log.debug("DB request filter {}", start);
        return result;
    }

    private Account rsMapToAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt(1));
        account.setSname(rs.getString(2));
        account.setFname(rs.getString(3));
        account.setEmail(rs.getString(4));
        account.setPhone(rs.getString(5));
        account.setSex(rs.getString(6));
        account.setBirth(rs.getInt(7));
        account.setCountry(rs.getString(8));
        account.setCity(rs.getString(9));
        account.setJoined(rs.getInt(10));
        account.setStatus(rs.getString(11));
        return account;
    }

    public List<Account> filter(String query) throws SQLException {
        Stopwatch start = Stopwatch.createStarted();
        Statement stmt = config.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("select * from dima.account where " + query);
        ArrayList<Account> accounts = new ArrayList<>();
        while (rs.next()) {
            accounts.add(rsMapToAccount(rs));
        }
        start.stop();
        log.debug("DB request filter {}", start);
        return accounts;
    }

    public Map<String, Integer> groupBy(String query) throws SQLException {
        Stopwatch start = Stopwatch.createStarted();
        Statement stmt = config.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        Map<String, Integer> result = new HashMap<>();
        while (rs.next()) {
            String key = rs.getString(1);
            Integer value = rs.getInt(2);
            result.put(key, value);
        }
        start.stop();
        log.debug("DB request groupBy {}", start);
        return result;
    }
}
