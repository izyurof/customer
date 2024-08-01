package com.iprody.e2e;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Cucumber test.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.iprody.e2e.steps"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class CucumberTest {

}
