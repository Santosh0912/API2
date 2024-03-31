package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		 features="src/test/java/features/",
		 //glue= {"stepDefinition"},
		 		 plugin="json:target/jsonReports/cucumber-report.json",
				 glue= {"stepDefinition"})
		//tags= "@AddPlace and @DeletePlace")

		//features = "src/test/java/features", glue = { "stepDefinition" }, tags = "@AddPlace")

//features = "src/test/java/features", glue = { "stepDefinition" }, tags = "@DeletePlace")

public class TestRunner {

}
