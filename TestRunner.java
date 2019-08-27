package myRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"features\\b_carregamento.feature"},  //the path of the feature files
		tags = {"@CD_01"},
		glue= {"step_definitions"}, //the path of the step definition files
		plugin = {"pretty", "html:target/cucumber",  "json:target/cucumber.json", "junit:target/cucumber.xml"}, //to generate different types of reporting
		monochrome = true, //display the console output in a proper readable format
		strict = true, //it will check if any step is not defined in step definition file
		dryRun = false //to check the mapping is proper between feature file and step def file
		
		)

public class TestRunner {

}
