/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package stepdefs.webservice;

import io.github.maqs.cucumber.ScenarioContext;
import io.github.maqs.cucumber.steps.BaseWebServiceStep;
import io.github.maqs.webservices.MediaType;
import io.github.maqs.cucumber.datamodels.Product;
import io.github.maqs.webservices.WebServiceConfig;
import org.testng.Assert;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
 
public class ProductSteps extends BaseWebServiceStep {
    private static final String baseUrl = WebServiceConfig.getWebServiceUri();

    @Given("I Get Product Information for Product ID {int}")
    public void i_get_product_information_for_product_id(Integer int1) throws IOException, InterruptedException {
        Product product = this.getDriver().get(
            baseUrl + "/api/XML_JSON/GetProduct/2", MediaType.APP_XML, true, Product.class);
        ScenarioContext.put("product", product);
        
    }
    @Then("The Product Name is {string}")
    public void the_product_name_is(String pname) {
        Product p = (Product) ScenarioContext.get("product");
        Assert.assertEquals(p.getName(), pname, "Expected Yo-yo to be returned");
    }
}
