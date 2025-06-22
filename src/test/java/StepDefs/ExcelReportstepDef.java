package StepDefs;

import com.Utils.ExcelReportUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
public class ExcelReportstepDef {

    @After
    public void afterScenario(Scenario scenario) {
        String status = scenario.isFailed() ? "FAILED" : "PASSED";
        ExcelReportUtil.writeTestResult(scenario.getName(), status);
    }

    @AfterAll
    public static void afterAll() {
        ExcelReportUtil.saveReport();
    }
}