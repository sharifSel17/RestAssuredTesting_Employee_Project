package testScripts;

import basePacakge.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RestUtilityClass;

public class TC004_Update_Record extends TestBase {
    String empNam = RestUtilityClass.empName();
    String empSa = RestUtilityClass.empSal();
    String empAg = RestUtilityClass.empAge();
    @BeforeClass
    void getAllEmployees() throws InterruptedException {
        //base uri
        logger.info("Started TC002_GET_SINGLE_Employee_Details");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        JSONObject requestPara = new JSONObject();
        requestPara.put("employee_name",empNam);
        requestPara.put("employee_salary",empSa);
        requestPara.put("employee_age",empAg);

        //add header stating the request body is a JSON
        httpRequest.header("Content-Type","application/json");

        //add the Json to the body
        httpRequest.body(requestPara.toJSONString());
        response = httpRequest.request(Method.PUT,"/update/"+empID);
        Thread.sleep(8000);
    }
    @Test
    public void checkingBody(){
        logger.info("Checking Response Body");
        String responseBody = response.getBody().asString();
        logger.info("Response body ==>"+responseBody);
        Assert.assertEquals(responseBody.contains(empNam),true);
        Assert.assertEquals(responseBody.contains(empSa),true);
        Assert.assertEquals(responseBody.contains(empAg),true);

    }

    @Test
    public void checkStatusCode(){
        logger.info("checking status code");
        int statusCode = response.getStatusCode();
        logger.info("Status Code Is ==>"+statusCode);
        Assert.assertEquals(statusCode,200);
    }
    @Test
    public void checkGetTime(){
        logger.info("checking execution time");
        long responseTime = response.getTime();
        logger.info("Response time is ==>"+responseTime);
        if (responseTime>2000)
            logger.warn("Response time is greater than 2000");
        Assert.assertTrue(responseTime<2000);
    }
    @Test
    public void checkStatusLine(){
        logger.info("checking status line");
        String statusLine = response.getStatusLine();
        logger.info("Status Line is =>"+statusLine);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
    }
    @Test
    public void checkingContentType(){
        logger.info("checking content type");
        String  contentType = response.header("Content-Type");
        logger.info("Content-Type is=>"+contentType);
        Assert.assertEquals(contentType,"application/json;charset=utf-8");
    }
    @AfterClass
    public void tearDown(){
        logger.info("Finished TC002_GET_SINGLE_Employee_Details");
    }
}
