package io.dshuplyakov.http;

import com.google.inject.Inject;
import io.dshuplyakov.http.handler.FilterAccount;
import io.dshuplyakov.http.handler.FindAccount;
import io.dshuplyakov.http.handler.GroupByHandler;
import io.undertow.server.RoutingHandler;

public class Routing {

    @Inject
    FilterAccount filterAccount;

    @Inject
    FindAccount findAccount;

    @Inject
    GroupByHandler groupByHandler;

    public RoutingHandler routing() {
        return new RoutingHandler()
            .get("/user/{id}", findAccount)
            .get("/accounts/filter", filterAccount)
            .get("/accounts/group", groupByHandler);

    }

}
