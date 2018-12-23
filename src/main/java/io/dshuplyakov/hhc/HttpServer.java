package io.dshuplyakov.hhc;

import io.dshuplyakov.http.Routing;
import io.undertow.Undertow;

public class HttpServer {

    public static void main(String[] args) {
        Undertow undertow = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(Routing.ROUTES)
                .build();

        undertow.start();
    }
}
