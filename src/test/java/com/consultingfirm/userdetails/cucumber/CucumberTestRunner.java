package com.consultingfirm.userdetails.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        publish = true,
        glue = "com.consultingfirm.userdetails.cucumber",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class CucumberTestRunner {
}
