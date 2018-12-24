package io.dshuplyakov.hhc.service;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import io.dshuplyakov.hhc.dto.Account;
import io.dshuplyakov.hhc.model.AccountDao;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Dmitry on 24.12.2018.
 */
@Slf4j
public class GroupByController {

    public static final String LIMIT = "limit";
    public static final String ORDER = "order";

    @Inject
    private AccountDao accountDao;

    private Joiner andJoiner = Joiner.on(" and ").skipNulls();
    private String selectSQL = "select %s, count() as cnt from dima.account";
    List<String> allowedGroupByFields = Arrays.asList("sex","status","interests","country","city");
    List<String> allowedWhereFields = Arrays.asList("fname","sname","email","phone","sex","birth","country","city","joined","status");

    //select country, count() as c from dima.account where birth = 800000000 group by country ORDER BY c
    ///accounts/group/?birth=1998&limit=4&order=-1&keys=country
    public Map<String, Integer> group(Map<String, Deque<String>> query) {
        String queryString = buildQueryString(query);
        log.info(queryString);
        try {
            return accountDao.groupBy(queryString);
        } catch (SQLException e) {
            new IllegalArgumentException(e);
        }
        return null;
    }

    private String buildQueryString(Map<String, Deque<String>> query) {
        String select = buildSelect(query);
        String where = buildWhere(query);
        String groupBy = buildGroupBy(query);
        String limit = buildLimit(query);
        String order = buildOrder(query);

        return select + where + groupBy + order + limit;
    }

    private String buildSelect(Map<String, Deque<String>> query) {
        String selectField = extractSelectField(query);
        return String.format(selectSQL, selectField);
    }

    private String extractSelectField(Map<String, Deque<String>> query) {
        String value = query.get("keys").getFirst();
        if (!allowedGroupByFields.contains(value)) {
            throw new IllegalArgumentException("No param \"key\"");
        }
        return value;
    }

    private String buildOrder(Map<String, Deque<String>> query) {
        if (query.containsKey(ORDER)) {
            String orderValue = query.get(ORDER).getFirst();
            if (orderValue.equals("-1")) {
                return " ORDER BY cnt DESC";
            }

            if (orderValue.equals("1")) {
                return " ORDER BY cnt";
            }
        }
        return null;
    }

    private String buildLimit(Map<String, Deque<String>> query) {
        String limit="";
        if (query.containsKey(LIMIT)) {
            limit = " "+LIMIT + " " + query.get(LIMIT).getFirst();
        }
        return limit;
    }

    private String buildGroupBy(Map<String, Deque<String>> query) {
        return " group by " + extractSelectField(query);
    }

    private String buildWhere(Map<String, Deque<String>> query) {
        List<String> result = new ArrayList<>(query.size());
        for (Map.Entry<String, Deque<String>> entry: query.entrySet()) {
            String s = processWhere(entry.getKey(), entry.getValue().getFirst());
            result.add(s);
        }

        String where = andJoiner.join(result);
        if (!Strings.isNullOrEmpty(where)) {
            where = " where "+where;
        }
        return where;
    }

    private String processWhere(String key, String value) {
        if (allowedWhereFields.contains(key)) {
            return key+"='"+value+"'";
        }
        return null;
    }
}
