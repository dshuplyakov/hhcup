package io.dshuplyakov.hhc.config;

import com.google.inject.Inject;
import io.dshuplyakov.hhc.http.FastHttpServer;

/**
 * Created by Dmitry on 23.12.2018.
 */
public class AppRunner {

    @Inject
    FastHttpServer httpServer;

    public void start (){
        httpServer.start();
    }
}
