package io.dshuplyakov.http.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dshuplyakov.hhc.dto.Account;
import io.dshuplyakov.hhc.model.AccountDao;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.util.Deque;

public class MyHandler implements HttpHandler {
    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {

        exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, "text/plain");
        String test = exchange.getQueryParameters().get("id").getFirst();

        AccountDao accountDao = new AccountDao();
        Account account = accountDao.get(Long.valueOf(test));

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(account);

        exchange.getResponseSender().send(s);

        // dispatch to non-io threads
     /*   if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            return;
        }*/

        // in worker thread
        // implement here ...
    }
}
