/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

import io.github.openmaqs.cucumber.BaseSeleniumCucumber;

import io.cucumber.testng.CucumberOptions;


/**
 * Selenium glue.
 */
@CucumberOptions( glue = {"stepdefs.selenium"}, plugin = {"pretty"}, features = {"src/test/resources/selenium"})
public class TestRunnerSelenium extends BaseSeleniumCucumber {

}