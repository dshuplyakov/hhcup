package io.dshuplyakov.http;

import io.dshuplyakov.http.handler.MyHandler;
import io.undertow.server.RoutingHandler;

public class Routing {

    public static final RoutingHandler ROUTES = new RoutingHandler()
            .get("/user/{id}", new MyHandler());

}
