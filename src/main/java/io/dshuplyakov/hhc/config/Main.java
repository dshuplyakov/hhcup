package io.dshuplyakov.hhc.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Dmitry on 23.12.2018.
 */
@Slf4j
public class Main {

    public static void main(String[] args){
        Injector injector = Guice.createInjector(new MainModule());
        try {
            injector.getInstance(AppRunner.class).start();
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            //вызовет все Shutdown-хуки
            System.exit(1);
        }
    }
}
