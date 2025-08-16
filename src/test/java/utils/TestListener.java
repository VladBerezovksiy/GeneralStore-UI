package utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getTestName();
        if(testName == null) testName = result.getMethod().getMethodName();
        System.out.println(Consts.FAILED + ": " + testName +  "\n" + result.getThrowable().getMessage() + "\n");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getTestName();
        if(testName == null) testName = result.getMethod().getMethodName();
        System.out.println(Consts.PASSED + ": " + testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getTestName();
        if(testName == null) testName = result.getMethod().getMethodName();
        System.out.println(Consts.SKIPPED + ": " + testName);
    }
}
