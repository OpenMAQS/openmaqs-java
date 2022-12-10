package com.maqs.base;

import com.maqs.utilities.helper.Config;
import com.maqs.utilities.helper.TestCategories;
import com.maqs.utilities.logging.ConsoleLogger;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Base Extendable Test unit test class.
 */
public class BaseExtendableTestUnitTest extends BaseExtendableTest<ITestObject> {

  @Test(groups = TestCategories.FRAMEWORK)
  public void testCreateNewTestObject() {
    this.createNewTestObject();
    Assert.assertNotNull(this.getTestObject());
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testCreateConsole() {
    HashMap<String, String> newValueMap = new HashMap<>();
    newValueMap.put("Log", "NO");
    Config.addGeneralTestSettingValues(newValueMap, true);
    this.createNewTestObject();
    Assert.assertTrue(this.getTestObject().getLogger() instanceof ConsoleLogger);
  }
}
