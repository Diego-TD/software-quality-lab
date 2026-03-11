package mx.edu.cetys.softwarequalitylab.poc;
//import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;

import java.util.logging.Logger;


@DisplayName("Lifecycle and display name annotation ")
public class LifecycleTest {
    private static final Logger LOGGER = Logger.getLogger(LifecycleTest.class.getName());

    @BeforeAll
    static void beforeAll(){
        LOGGER.info("Before all test");
    }

    @BeforeEach
    void beforeEach(){
        LOGGER.info("Before each test");
    }

    @AfterEach
    void afterEach(){
        LOGGER.info("after each test");
    }
    @Test
    @DisplayName("test 1")
    void test1(){
        LOGGER.info("This is a test 1");
    }

    @Test
    @DisplayName("test 2")
    void test2(){
        LOGGER.info("This is a test 2");
    }

    @AfterAll
    static void afterAll(){
        LOGGER.info("After all tests");
    }
}