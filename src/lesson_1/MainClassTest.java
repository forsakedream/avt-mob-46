package lesson_1;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        int test_value = MainClass.getLocalNumber();
        Assert.assertTrue("MainClass.getLocalNumber() != 14", (test_value==14));
    }
}
