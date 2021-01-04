/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package helloworld.java;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import static org.junit.Assert.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class AppTest_Testcontainers {
    private App underTest;

    @Rule
    // public GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
    //                                     .withExposedPorts(6379);
    public GenericContainer redis = new GenericContainer(DockerImageName.parse("franciscocodefresh/private-redis:1.0.1"))
                                        .withExposedPorts(6379);

    @Before
    public void setUp() {
        underTest = new App();
    }
    
    @Test
    public void testGreeting() {
        String expectedGreeting = "Hello World!";
        String retrievedGreeting = underTest.getGreeting();
        assertEquals("strings should be the same", expectedGreeting, retrievedGreeting);
    }

    @Test
    public void testContainer() {
        String address = redis.getHost();
        Integer port = redis.getFirstMappedPort();
        assertEquals("host should be Valid -a change-", address, redis.getHost());
    }
}
