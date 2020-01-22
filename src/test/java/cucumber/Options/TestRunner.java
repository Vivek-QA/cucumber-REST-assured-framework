package cucumber.Options;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@CucumberOptions(features = "src/test/java/features", glue = {"stepDefinitions"},tags = {"@AddPlace"})
@RunWith(Cucumber.class)

public class TestRunner {
}
