package io.dshuplyakov.hhc.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDaoTest {

    @Test
    public void name() throws Exception {
        AccountDao accountDao = new AccountDao();
        accountDao.get(22L);
    }
}
