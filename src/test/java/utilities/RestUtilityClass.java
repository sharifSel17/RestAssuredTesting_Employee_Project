package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtilityClass {
    public static String empName(){

        String generatedString = RandomStringUtils.randomAlphabetic(1);
        return ("Adam"+generatedString);
    }
    public static String empSal(){
        String generatedString = RandomStringUtils.randomAlphabetic(4);
        return (generatedString);
    }
    public static String empAge(){
        String generatedString = RandomStringUtils.randomAlphabetic(2);
        return (generatedString);
    }
}
