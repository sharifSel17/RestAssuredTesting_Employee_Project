package testScripts;

import basePacakge.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC005_Delete_Record extends TestBase {
    @BeforeClass
    void getAllEmployees() throws InterruptedException {
        //base uri
        logger.info("Started TC002_GET_SINGLE_Employee_Details");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        response = httpRequest.request(Method.GET,"/employees");

        //first get the JsonPath object instance from the response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        //capture id
        String empId = jsonPathEvaluator.get("[0].id");
        response = httpRequest.request(Method.DELETE,"/delete/"+empId);
        Thread.sleep(8000);
    }
    @Test
    public void checkingBody(){
        logger.info("Checking Response Body");
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("successfully! deleted Records"),true);

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
