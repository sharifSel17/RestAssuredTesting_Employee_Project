package utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;



public class Listeners extends TestListenerAdapter {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest test;

    public void onStart(ITestContext iTestContext){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/myReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");//title of the report
        htmlReporter.config().setReportName("Rest API Testing Report");//name of the report
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        extentReports.setSystemInfo("Project Name :","Employee Database API");
        extentReports.setSystemInfo("Host Name","LocalHost");
        extentReports.setSystemInfo("OS","WINDOWS 10");
        extentReports.setSystemInfo("Tester Name", "Sharif");
        extentReports.setSystemInfo("Browser", "Chrome");

    }
    public void onTestSuccess(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.log(Status.PASS,"Test case is passed :"+result.getName());
    }
    public void onTestFailure(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.log(Status.FAIL,"Test is failed :"+result.getName());
        test.log(Status.FAIL,"Test is failed :"+result.getThrowable());
    }
    public void onTestSkipped(ITestResult result){
        test = extentReports.createTest(result.getName());
        test.log(Status.SKIP,"Test is skipped :"+result.getName());
    }


    public void onFinish(ITestContext iTestContext){
        extentReports.flush();
    }
}
