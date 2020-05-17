package basePacakge;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;
    public Logger logger;
    public  String empID = "1";

    @BeforeClass
    public void base(){
        logger = Logger.getLogger("TestBase");
        PropertyConfigurator.configure("log4j.properties");
        logger.info(Level.DEBUG);
    }
}
