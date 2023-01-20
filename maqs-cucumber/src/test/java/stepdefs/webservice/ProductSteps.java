package stepdefs.webservice;

import com.cognizantsoftvision.maqs.cucumber.ScenarioContext;
import com.cognizantsoftvision.maqs.cucumber.steps.BaseWebServiceStep;
import com.cognizantsoftvision.maqs.webservices.MediaType;
import com.cognizantsoftvision.maqs.cucumber.datamodels.Product;
import com.cognizantsoftvision.maqs.webservices.WebServiceConfig;
import org.testng.Assert;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
 
public class ProductSteps extends BaseWebServiceStep {
    private static final String baseUrl = WebServiceConfig.getWebServiceUri();

//    @Given("I Get Product Information for Product ID {int}")
//    public void i_get_product_information_for_product_id(Integer int1) throws IOException, InterruptedException {
//        Product product = this.getDriver().get(
//            baseUrl + "/api/XML_JSON/GetProduct/"+int1, MediaType.APP_XML, true, Product.class);
//        ScenarioContext.put("product", product);
//
//    }
//    @Then("The Product Name is {string}")
//    public void the_product_name_is(String pname) {
//        Product p = (Product) ScenarioContext.get("product");
//        Assert.assertEquals(p.getName(), pname, "Expected Yo-yo to be returned");
//    }

    @Given("^I Get Product Information for Product ID (\\d+)$")
    public void iGetProductInformationForProductID(int arg0) throws IOException, InterruptedException {
        Product product = this.getDriver().get(
                        baseUrl + "/api/XML_JSON/GetProduct/"+arg0, MediaType.APP_XML, true, Product.class);
                    ScenarioContext.put("product", product);
    }

    @Then("^The Product Name is \"([^\"]*)\"$")
    public void theProductNameIs(String pname) throws Throwable {
                Product p = (Product) ScenarioContext.get("product");
                Assert.assertEquals(p.getName(), pname, "Expected Yo-yo to be returned");
    }
}
