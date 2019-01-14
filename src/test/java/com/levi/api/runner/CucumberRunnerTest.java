package com.levi.api.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resource/features/CheckoutValidation.feature",
        glue={"com.levi.api.headless.steps"},
        plugin = {"pretty","html:target/cucumber/report.html"}
        )

//public class CucumberRunnerTest extends AbstractTestNGCucumberTests{
public class CucumberRunnerTest{

}

