package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private DbPopulator dbPopulator;

    @Autowired
    private MealService service;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1, USER_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(100002, LocalDateTime.now(), "TEST", 5000);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(updated.getId(), USER_ID));
    }


}