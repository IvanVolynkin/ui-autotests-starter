package ru.vanjo.qa.uiautotestsstarter.helpers.testrail;

public enum Status {
    PASSED(1), BLOCKED(2), UNTESTED(3), RETEST(4), FAILED(5);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
