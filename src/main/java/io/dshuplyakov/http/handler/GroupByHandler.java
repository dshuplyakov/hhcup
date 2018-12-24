package io.dshuplyakov.http.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import io.dshuplyakov.hhc.dto.Account;
import io.dshuplyakov.hhc.service.GroupByController;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.util.Deque;
import java.util.List;
import java.util.Map;

public class GroupByHandler implements HttpHandler {

    @Inject
    GroupByController groupByController;

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {

        exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, "text/plain");
        Map<String, Deque<String>> queryParameters = exchange.getQueryParameters();

        Map<String, Integer> result = groupByController.group(queryParameters);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(result);

        exchange.getResponseSender().send(s);
    }
}
