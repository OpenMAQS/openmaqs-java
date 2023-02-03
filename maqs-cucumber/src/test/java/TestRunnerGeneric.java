/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

import io.github.maqs.cucumber.BaseGenericCucumber;
import io.cucumber.testng.CucumberOptions;


/**
 * Generic glue.
 */
@CucumberOptions( glue = {"stepdefs.generic"}, plugin = {"pretty"}, features = {"src/test/resources/generic"})
public class TestRunnerGeneric extends BaseGenericCucumber {

}