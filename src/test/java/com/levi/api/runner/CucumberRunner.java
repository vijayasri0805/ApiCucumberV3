package com.levi.api.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resource/features",
        glue={"com.levi.api.aoslse.steps"},
        plugin = {"pretty","html:target/cucumber/report.html"},
        monochrome = true
        )


public class CucumberRunner extends AbstractTestNGCucumberTests{

}

