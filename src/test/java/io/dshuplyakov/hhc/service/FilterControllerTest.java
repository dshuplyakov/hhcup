package io.dshuplyakov.hhc.service;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Dmitry on 23.12.2018.
 */
public class FilterControllerTest {

    @Test
    public void testFilter() throws Exception {

        Map<String, Deque<String>> params = new HashMap<>();
        params.put("status_neq", new ArrayDeque<>(Arrays.asList("всё сложно")));
        params.put("birth_lt", new ArrayDeque<>(Arrays.asList("643972596")));
        params.put("country_eq", new ArrayDeque<>(Arrays.asList("Индляндия")));
        params.put("limit", new ArrayDeque<>(Arrays.asList("5")));

        FilterController controller = new FilterController();
        controller.filter(params);


    }
}