package io.dshuplyakov.http.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import io.dshuplyakov.hhc.dto.Account;
import io.dshuplyakov.hhc.model.AccountDao;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;


public class FindAccount implements HttpHandler {

    @Inject
    AccountDao accountDao;

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {

        exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, "text/plain");
        String test = exchange.getQueryParameters().get("id").getFirst();

        Account account = accountDao.get(Long.valueOf(test));

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(account);

        exchange.getResponseSender().send(s);

    }
}
