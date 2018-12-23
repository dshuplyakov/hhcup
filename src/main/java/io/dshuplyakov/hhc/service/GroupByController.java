package io.dshuplyakov.hhc.service;

import com.google.common.base.Joiner;
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

    List<String> allowedFields = Arrays.asList("sex","status","interests","country","city");

    //select country, count() as c from dima.account where birth = 800000000 group by country ORDER BY c
    ///accounts/group/?birth=1998&limit=4&order=-1&keys=country
    public List<Account> filter (Map<String, Deque<String>> query) {
        String queryString = buildQueryString(query);
        try {
            return accountDao.groupBy(queryString);
        } catch (SQLException e) {
            new IllegalArgumentException(e);
        }
        return null;
    }

    private String buildQueryString(Map<String, Deque<String>> query) {

        String where = buildWhere(query);
        String groupBy = buildGroupBy(query);
        String limit = buildLimit(query);
        String order = buildOrder(query);

        return null;
    }

    private String buildOrder(Map<String, Deque<String>> query) {
        if (query.containsKey(ORDER)) {
            String orderValue = query.get(LIMIT).getFirst();
            if (orderValue.equals("-1")) {
                return "country ORDER BY c";
            }

            if (orderValue.equals("1")) {
                return "country ORDER BY c";
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
        return " group by " + query.get("keys").getFirst();
    }

    private String buildWhere(Map<String, Deque<String>> query) {
        List<String> result = new ArrayList<>(query.size());
        for (Map.Entry<String, Deque<String>> entry: query.entrySet()) {
            String s = processWhere(entry.getKey(), entry.getValue().getFirst());
            result.add(s);
        }

        return andJoiner.join(result);
    }

    private String processWhere(String key, String value) {
        if (allowedFields.contains(key)) {
            return key+"="+value;
        }
        return null;
    }
}
