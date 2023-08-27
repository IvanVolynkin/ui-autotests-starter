package ru.vanjo.qa.uiautotestsstarter.common;

import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.vanjo.qa.uiautotestsstarter.helpers.testrail.Status;
import ru.vanjo.qa.uiautotestsstarter.helpers.testrail.TestRailClient;
import ru.vanjo.qa.uiautotestsstarter.helpers.testrail.TestRailId;

import java.lang.reflect.Method;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        var id = getTestRailId(result);
        if(id > 0) {
            TestRailClient.addResultForCase(
                    Configuration.instance().getConfig().getInt("testrail.runId"),
                    id,
                    Status.PASSED
            );
        }
    }

    private int getTestRailId(ITestResult result) {
        var iClass = result.getTestClass();
        var newIClass = iClass.getRealClass();
        Method testMethod = null;
        try {
            testMethod = newIClass.getMethod(result.getName());
        } catch (NoSuchMethodException ignored) {}
        if (testMethod != null && testMethod.isAnnotationPresent(TestRailId.class)) {
            var useAsTestName = testMethod.getAnnotation(TestRailId.class);
            var caseId = useAsTestName.id();
            return caseId;
        }
        return 0;
    }
}
