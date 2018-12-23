package io.dshuplyakov.hhc.http;

import com.google.inject.Inject;
import io.dshuplyakov.http.Routing;
import io.undertow.Undertow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FastHttpServer {

    @Inject
    Routing routing;
    
    public void start() {
        Undertow undertow = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(routing.routing())
                .build();

        undertow.start();


       log.info("Undertow http server started");
    }
}
