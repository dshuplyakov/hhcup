package io.dshuplyakov.hhc;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dshuplyakov.hhc.dto.AccountList;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Dmitry on 21.12.2018.
 */
@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(11);

        byte[] jsonData = Files.readAllBytes(Paths.get("accounts_1.json"));

        log.info("Started");
        ObjectMapper objectMapper = new ObjectMapper();
        AccountList user = objectMapper.readValue(jsonData, AccountList.class);
        log.info("Parsed {} accounts",  user.getAccounts().size());


    }
}
