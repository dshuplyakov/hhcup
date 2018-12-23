package io.dshuplyakov.hhc.config;

import com.google.inject.AbstractModule;
import io.dshuplyakov.hhc.http.FastHttpServer;
import io.dshuplyakov.hhc.model.AccountDao;
import io.dshuplyakov.http.handler.FilterAccount;
import io.dshuplyakov.http.handler.FindAccount;

/**
 * Created by Dmitry on 23.12.2018.
 */
public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Config.class).asEagerSingleton();
        bind(AccountDao.class).asEagerSingleton();
        bind(FastHttpServer.class).asEagerSingleton();
        bind(FilterAccount.class).asEagerSingleton();
        bind(FindAccount.class).asEagerSingleton();
    }
}
