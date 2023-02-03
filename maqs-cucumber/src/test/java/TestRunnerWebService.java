/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

import io.cucumber.testng.CucumberOptions;
import io.github.maqs.cucumber.BaseWebServiceCucumber;


/**
 * WebService glue.
 */
@CucumberOptions( glue = {"stepdefs.webservice"}, plugin = {"pretty"}, features = {"src/test/resources/webservice"})
public class TestRunnerWebService extends BaseWebServiceCucumber {

}