package io.dshuplyakov.hhc.service;

import com.google.common.base.Joiner;
import com.google.inject.Inject;
import io.dshuplyakov.hhc.dto.Account;
import io.dshuplyakov.hhc.model.AccountDao;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Dmitry on 23.12.2018.
 */
@Slf4j
public class FilterController {

    @Inject
    private AccountDao accountDao;

    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";
    private Joiner andJoiner = Joiner.on(" and ").skipNulls();

    List<String> strFields = Arrays.asList("fname","sname","email","phone","sex","country","status");
    List<String> intFields = Arrays.asList("birth","joined");

    public List<Account> filter (Map<String, Deque<String>> query) {
        String queryString = buildQueryString(query);
        try {
            return accountDao.filter(queryString);
        } catch (SQLException e) {
            new IllegalArgumentException(e);
        }
        return null;
    }

    private String buildQueryString(Map<String, Deque<String>> query) {
        List<String> ops = new ArrayList<>(query.size());
        for (Map.Entry<String, Deque<String>> entry: query.entrySet()) {
            String s = processToSql(entry.getKey(), entry.getValue().getFirst());
            ops.add(s);
        }

        String limitAndOffset = addLimitAndOffset(query);

        String join = andJoiner.join(ops) + limitAndOffset ;
        log.debug(join);
        return join;
    }

    private String processToSql(String key, String value) {
        String clearKey;
        if (key.contains("_")) {
            clearKey = key.substring(0, key.indexOf("_"));
        } else {
            clearKey = key;
        }
        if (intFields.contains(clearKey)) {
            return convertToSql(key, value);
        }
        if (strFields.contains(clearKey)) {
            return convertToSql(key, "'"+value+"'");
        }

        return null;
    }

    private String addLimitAndOffset(Map<String, Deque<String>> query) {
        String limit="";
        if (query.containsKey(LIMIT)) {
            limit = " "+LIMIT + " " + query.get(LIMIT).getFirst();
        }

        String offset="";
        if (query.containsKey(OFFSET)) {
            offset = OFFSET + " " + query.get(OFFSET).getFirst();
        }

        return offset+limit;
    }

    private String convertToSql(String key, String value) {
        if (key.contains("_")) {
            String[] parts = key.split("_");
            String op = "no?";
            if (parts[1].equals("eq")) {
                op = "=";
            } else if (parts[1].equals("neq")) {
                op = "!=";
            } else if (parts[1].equals("lt")) {
                op = "<";
            } else if (parts[1].equals("gt")) {
                op = ">";
            } else if (parts[1].equals("null")) {
                op = "=''";
            }
            return parts[0] + op + value;
        } else {
            return key + "=" + value;
        }
    }
}
