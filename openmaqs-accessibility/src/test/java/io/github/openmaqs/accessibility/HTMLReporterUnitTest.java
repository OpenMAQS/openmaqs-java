/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.accessibility;

import com.deque.html.axecore.args.AxeRunOptions;
import com.deque.html.axecore.results.Check;
import com.deque.html.axecore.results.CheckedNode;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.results.ResultType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.openmaqs.selenium.BaseSeleniumTest;
import io.github.openmaqs.selenium.factories.UIWaitFactory;
import io.github.openmaqs.utilities.helper.TestCategories;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.UUID;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * The Accessibility HTML Reporter unit tests.
 */
public class HTMLReporterUnitTest extends BaseSeleniumTest {

  /**
   * The file path to the testFiles folder.
   */
  private static final String TEST_FILES = "src/test/resources/testFiles/";

  /**
   * The file path to the html testing page.
   */
  private static final String filePath = TEST_FILES + "integration-test-target.html";

  /**
   * The file to be opened in the browser.
   */
  private static final File integrationTestTargetSimpleFile = new File(filePath);

  /**
   * The url to be opened in the browser.
   */
  private static final String integrationTestTargetSimpleUrl = integrationTestTargetSimpleFile.getAbsolutePath();

  /**
   * The file to be opened in the browser.
   */
  private static final File integrationTestTargetComplexFile = new File(
          TEST_FILES + "integration-test-target-complex.html");

  /**
   * The url to be opened in the browser.
   */
  private static final String integrationTestTargetComplexUrl = integrationTestTargetComplexFile.getAbsolutePath();

  /**
   * The file to be converted into a result type.
   */
  private static final File integrationTestJsonResultFile = new File(TEST_FILES + "sampleResults.json");

  /**
   * The path to the file converted into a result type.
   */
  private static final String integrationTestJsonResultUrl = integrationTestJsonResultFile.getAbsolutePath();

  /**
   * String value of main element selector.
   */
  private static final String mainElementSelector = "main";

  private void loadTestPage(String testPage) {
    this.getWebDriver().get("file:///" + new File(testPage).getAbsolutePath());
    UIWaitFactory.getWaitDriver(getWebDriver()).waitForPageLoad();
    UIWaitFactory.getWaitDriver(getWebDriver()).waitUntilVisibleElement(By.cssSelector(mainElementSelector));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void runScanOnPage() {
    loadTestPage(integrationTestTargetSimpleUrl);

    //var timeBeforeScan = DateTime.Now;

    AxeRunOptions axeRunOptions = new AxeRunOptions();
    axeRunOptions.setXPath(true);

    AxeBuilder builder = new AxeBuilder();
        builder.withOptions(axeRunOptions);
        builder.withTags(Arrays.asList("wcag2a", "wcag2aa"));
        builder.disableRules(Collections.singletonList("color-contrast"));
        builder.withOutputFile("./raw-axe-results.json");

    Results results = builder.analyze(this.getWebDriver());

    Assert.assertEquals(results.getViolations().size(), 2);

    for (Rule violations : results.getViolations()) {
      Assert.assertFalse(violations.getId().contains("color-contrast"));

      // results.Violations.FirstOrDefault(v => !v.Tags.Contains("wcag2a") && !v.Tags.Contains("wcag2aa")).Should().BeNull();
      Assert.assertTrue(violations.getTags().contains("wcag2a"));
      // Assert.assertTrue(violations.getTags().contains("wcag2aa"));
    }

    Assert.assertNotNull(results.getViolations().get(0).getNodes().get(0));

//    File.GetLastWriteTime(@"./raw-axe-results.json").Should().BeOnOrAfter(timeBeforeScan);
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void runScanOnGivenElement() throws IOException, ParseException {
    loadTestPage(integrationTestTargetSimpleUrl);
    String path = createReportPath();
    SeleniumReport.createHtmlReport(this.getWebDriver(),
        this.getWebDriver().findElement(By.cssSelector(mainElementSelector)), path);
    validateReport(path, 3, 14, 0, 76);

    deleteFile(new File(path));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void reportFullPage() throws IOException, ParseException {
    loadTestPage(integrationTestTargetSimpleUrl);
    String path = createReportPath();
    SeleniumReport.createHtmlReport(this.getWebDriver(), path);
    validateReport(path, 4, 26, 0, 70);

    deleteFile(new File(path));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void reportFullPageViolationsOnly() throws IOException, ParseException {
    loadTestPage(integrationTestTargetSimpleUrl);
    String path = createReportPath();
    SeleniumReport.createHtmlReport(this.getWebDriver(), path, EnumSet.of(ResultType.Violations));

    // Check violations
    validateReport(path, 4, 0, 0, 0);
    validateResultNotWritten(path,
        EnumSet.of(ResultType.Passes, ResultType.Inapplicable, ResultType.Incomplete));

    deleteFile(new File(path));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void reportFullPagePassesInapplicableViolationsOnly() throws IOException, ParseException {
    loadTestPage(integrationTestTargetSimpleUrl);
    String path = createReportPath();
    SeleniumReport.createHtmlReport(this.getWebDriver(), path,
        EnumSet.of(ResultType.Passes, ResultType.Inapplicable, ResultType.Violations));

    // Check Passes
    validateReport(path, 4, 26, 0, 70);
    validateResultNotWritten(path, EnumSet.of(ResultType.Incomplete));

    deleteFile(new File(path));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void reportOnElement() throws IOException, ParseException {
    loadTestPage(integrationTestTargetSimpleUrl);
    String path = createReportPath();

    var mainElement = this.getWebDriver().findElement(By.cssSelector(mainElementSelector));
    SeleniumReport.createHtmlReport(this.getWebDriver(), mainElement, path);

    validateReport(path, 3, 14, 0, 76);
    deleteFile(new File(path));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void reportRespectRules() throws IOException, ParseException {
    loadTestPage(integrationTestTargetSimpleUrl);
    String path = createReportPath();

    var builder = new AxeBuilder().disableRules(Collections.singletonList("color-contrast"));
    SeleniumReport.createHtmlReport(this.getWebDriver(), builder.analyze(this.getWebDriver()), path);

    validateReport(path, 3, 21, 0, 70);
    deleteFile(new File(path));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void reportSampleResults() throws IOException, ParseException {
    String path = createReportPath();
    Results results = new ObjectMapper().readValue(new File(integrationTestJsonResultUrl), Results.class);

    SeleniumReport.createHtmlReport(this.getWebDriver(), results, path);
    validateReport(path, 3, 5, 2, 4);

    String text = new String(Files.readAllBytes(Paths.get(path)));
    Document doc = Jsoup.parse(text);

    String errorMessage = Objects.requireNonNull(doc.selectFirst("#ErrorMessage")).text();
    Assert.assertEquals(errorMessage, "java.lang.Exception: AutomationError");

    String reportContext = Objects.requireNonNull(doc.selectFirst("#reportContext")).text();
    Assert.assertTrue(reportContext.contains("Url: https://www.google.com/"), "URL is not in the document");
    Assert.assertTrue(reportContext.contains("Orientation: landscape-primary"), "Orientation is not in the document");
    Assert.assertTrue(reportContext.contains("Size: 1200 x 646"), "Size is not in the document");
    Assert.assertTrue(reportContext.contains("Time: 14-Apr-20 01:33:59"), "Time is not in the document: " + reportContext);
    Assert.assertTrue(reportContext.contains("User agent: AutoAgent"), "User Agent is not in the document");
    Assert.assertTrue(reportContext.contains("Using: axe-core (3.4.1)"), "Using is not in the document");

    deleteFile(new File(path));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void reportRespectsIframeImplicitTrue() throws IOException, ParseException {
    loadTestPage(integrationTestTargetComplexUrl);
    String path = createReportPath();

    SeleniumReport.createHtmlReport(this.getWebDriver(), path);
    validateReport(path, 4, 43, 0, 65);

    deleteFile(new File(path));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void ReportRespectsIframeTrue() throws IOException, ParseException {
    loadTestPage(integrationTestTargetComplexUrl);
    String path = createReportPath();

    AxeRunOptions runOptions = new AxeRunOptions();
    runOptions.setIFrames(true);

    var builder = new AxeBuilder().withOptions(runOptions);

    SeleniumReport.createHtmlReport(this.getWebDriver(), builder.analyze(this.getWebDriver()), path);
    validateReport(path, 4, 43, 0, 65);

    deleteFile(new File(path));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void reportRespectsIframeFalse() throws IOException, ParseException {
    loadTestPage(integrationTestTargetComplexUrl);
    String path = createReportPath();

    AxeRunOptions runOptions = new AxeRunOptions();
    runOptions.setIFrames(false);

    var builder = new AxeBuilder().withOptions(runOptions);
    SeleniumReport.createHtmlReport(this.getWebDriver(), builder.analyze(this.getWebDriver()), path);
    validateReport(path, 4, 43, 0, 65);

    deleteFile(new File(path));
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void runSiteThatReturnsMultipleTargets() {
    loadTestPage(integrationTestTargetComplexUrl);

    Results axeResult = new AxeBuilder()
        .withOutputFile("./raw-axe-results.json").analyze(this.getWebDriver());

    Rule colorContrast = null;

    for (Rule rule : axeResult.getViolations()) {
      if (rule.getId().equals("color-contrast")) {
        colorContrast = rule;
        break;
      }
    }

    Assert.assertNotNull(colorContrast);

    for (CheckedNode checkedNode : colorContrast.getNodes()) {
      for (Check check : checkedNode.getAny()) {
        if (check.getId().equals("color-contrast")) {
          Assert.assertNotNull(checkedNode.getAny());
          Assert.assertEquals(checkedNode.getAny().size(), 1);
          break;
        }
      }
    }
  }

  private String createReportPath() {
    return FileSystems.getDefault().getPath("target" + File.separator + "logs")
        + File.separator + UUID.randomUUID() + ".html";
  }

  private void validateReport(String path, int violationCount, int passCount, int incompleteCount, int inapplicableCount)
      throws IOException {
    String text = String.valueOf(Files.readString(Paths.get(path)));
    Document doc = Jsoup.parse(text);

    // Check the Element count for each result type
    validateElementCount(doc, violationCount, ResultType.Violations);
    validateElementCount(doc, passCount, ResultType.Passes);
    validateElementCount(doc, inapplicableCount, ResultType.Inapplicable);
    validateElementCount(doc, incompleteCount, ResultType.Incomplete);

    // Check header data
    Assert.assertTrue(text.contains("Using: axe-core"), "Expected to find 'Using: axe-core'");

    // Check the result count for each result type
    validateResultCount(text, violationCount, ResultType.Violations);
    validateResultCount(text, incompleteCount, ResultType.Incomplete);
    validateResultCount(text, passCount, ResultType.Passes);
    validateResultCount(text, inapplicableCount, ResultType.Inapplicable);
  }

  private void validateElementCount(Document doc, int count, ResultType resultType) {
    String ending = resultType.equals(ResultType.Inapplicable) ? "div.findings" : "div > div.htmlTable";
    String xpath = "#" + resultType + "Section > " + ending;
    Elements liNodes = !doc.select(xpath).isEmpty() ? doc.select(xpath) : new Elements();
    Assert.assertEquals(liNodes.size(), count, "Expected " + count + " " + resultType);
  }

  private void validateResultCount(String text, int count, ResultType resultType) {
    if (count != 0) {
      Assert.assertTrue(text.contains(resultType + ": " + count),
          "Expected to find '" + resultType + ": " + count);
    }
  }

  private void validateResultNotWritten(String path, EnumSet<ResultType> resultTypeArray) throws IOException {
    loadTestPage(integrationTestTargetSimpleUrl);
    String text = String.valueOf(Files.readAllLines(Paths.get(path)));

    for (ResultType resultType : resultTypeArray) {
      Assert.assertFalse(text.contains(resultType + ": "),
          "Expected to not find '" + resultType  + ": '");
    }
  }

  private void deleteFile(File file) {
    if (file.exists()) {
      Assert.assertTrue(file.delete(), "File was not deleted");
      Assert.assertFalse(file.exists(), "file still exists");
    }
  }
}