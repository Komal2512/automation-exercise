package org.AutomationExercise;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/Features",
        glue = {"StepDefs"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/Cucumber.html",
                "json:target/cucumber-reports/Cucumber.json"
        },
        monochrome = true
//        publish = false,
//        tags = "@SmokeTest"  // optional: run specific tagged scenarios
)
public class TestRunner {
}
