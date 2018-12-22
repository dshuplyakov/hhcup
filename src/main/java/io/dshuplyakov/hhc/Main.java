package io.dshuplyakov.hhc;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dshuplyakov.hhc.dto.AccountList;
import io.dshuplyakov.hhc.model.AccountDao;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Created by Dmitry on 21.12.2018.
 */
@Slf4j
public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        System.out.println(11);

        byte[] jsonData = Files.readAllBytes(Paths.get("accounts_1.json"));

        log.info("Started");
        ObjectMapper objectMapper = new ObjectMapper();
        AccountList user = objectMapper.readValue(jsonData, AccountList.class);
        log.info("Parsed {} accounts",  user.getAccounts().size());

        AccountDao accountDao = new AccountDao();
        accountDao.insertAccounts(user.getAccounts());


    }
}
