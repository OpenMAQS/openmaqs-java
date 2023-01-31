import com.cognizantsoftvision.maqs.cucumber.BaseWebServiceCucumber;

import io.cucumber.testng.CucumberOptions;


/**
 * WebService glue.
 */
@CucumberOptions( glue = {"stepdefs.webservice"}, plugin = {"pretty"}, features = {"src/test/resources/webservice"})
public class TestRunnerWebService extends BaseWebServiceCucumber {

}