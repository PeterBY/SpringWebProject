package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
//@RunWith(Suite.class)
//@Suite.SuiteClasses({UserServiceImpl.class, MealServiceTest.class})
@RunWith(SpringRunner.class)

@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class ServiceTest {
    static final Logger LOG = LoggerFactory.getLogger(MealServiceTest.class);
    static StringBuilder results = new StringBuilder();

    static {
        // needed only for java.util.logging (postgres driver)
        SLF4JBridgeHandler.install();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            LOG.info(result + " ms\n");
        }
    };

    @AfterClass
    public static void printResult() {
        LOG.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------\n" +
                results +
                "---------------------------------\n");
    }
}
