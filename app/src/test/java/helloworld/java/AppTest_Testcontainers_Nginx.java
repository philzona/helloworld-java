/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package helloworld.java;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
//import static org.junit.Assert.*;

//import org.testcontainers.containers.GenericContainer;
// import org.testcontainers.utility.DockerImageName;
// import org.testcontainers.containers.NginxContainer;
// import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static org.hamcrest.CoreMatchers.containsString;
import static org.rnorth.visibleassertions.VisibleAssertions.*;


import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.NginxContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.utility.DockerImageName;

import java.net.MalformedURLException;


public class AppTest_Testcontainers_Nginx {
    private App underTest;

    @Rule
    public NginxContainer<?> nginx = new NginxContainer<>(DockerImageName.parse("nginx:1.9.4"))
        .waitingFor(new HttpWaitStrategy());
    // public NginxContainer<?> nginx = new NginxContainer<>("nginx");
    //     // .withCustomContent(tmpDirectory)
    //     // .waitingFor(new HttpWaitStrategy());

    @Before
    public void setUp() {
        String address = nginx.getHost();
        Integer port = nginx.getFirstMappedPort();
        URL baseUrl; 
        try {
            baseUrl = nginx.getBaseUrl("http", 80);
        } catch (MalformedURLException e) {
            address = "";
        }
        
        underTest = new App();
    }

    @Test
    public void testNginx() {
        URL baseUrl = null;
        try {
            baseUrl = nginx.getBaseUrl("http", 80);
        } catch (MalformedURLException e) {

        }
        //System.out.println( baseUrl.toString() );
        try {
            assertThat("An HTTP GET from the Nginx server returns the index.html from the custom content directory",
                responseFromNginx(baseUrl),
                containsString("Welcome to nginx")
            );
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
    }

    private static String responseFromNginx(URL baseUrl) throws IOException {
        URLConnection urlConnection = baseUrl.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
           out.append(line);
           out.append(newLine);
        }
       return out.toString();
    }
}
