package ru.vanjo.qa.uiautotestsstarter.common;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Base class for all web pages.
 */
public class BaseWebPage {

    protected void waitForElementReload(By locator, Condition existOrVisible) {
        for(int i = 0; i < 10; i++) {
            if ($(locator).exists() || $(locator).isDisplayed()) {
                sleep(50);
            } else {
                $(locator).should(existOrVisible, Constants.Timeouts.NORMAL);
                break;
            }
        }
    }
}
