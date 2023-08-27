package ru.vanjo.qa.uiautotestsstarter.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Retry implements IRetryAnalyzer {

    private int count = 0;

    private final static int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetryCount) {
            count++;
            System.out.println("===============================================================");
            System.out.println("TEST: " + result.getMethod().getMethodName() + " WILL BE RETRIED!");
            System.out.println("===============================================================");
            closeWebDriver();
            return true;
        }
        return false;
    }


}
