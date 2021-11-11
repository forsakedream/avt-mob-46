package lesson_1;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    @Test
    public void testGetLocalNumber(){
        int test_value = MainClass.getLocalNumber();
        Assert.assertTrue("MainClass.getLocalNumber() != 14", (test_value==14));
    }
    @Test
    public void testGetClassNumber(){
        int test_value = MainClass.getClassNumber();
        Assert.assertTrue("MainClass.getClassNumber() <= 45", (test_value > 45));
    }
    @Test
    public void testGetClassString(){
        String test_string = MainClass.getClassString();
        boolean isContainsHello = test_string.contains("hello") || test_string.contains("Hello");
        Assert.assertTrue("MainClass.getClassString() is not contains \"Hello\" or \"hello\"", isContainsHello);
    }
}
