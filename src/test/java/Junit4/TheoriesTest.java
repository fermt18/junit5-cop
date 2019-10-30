package Junit4;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class TheoriesTest {

    @DataPoints
    public static int[] positiveIntegers() {
        return new int[] { 1, 10, 100 };
    }

    @Theory
    public void testSum(int a, int b, int c) {
        System.out.println("Checking " + a + "+" + b + "+" + c );
        assertTrue(a + b > a);
        assertTrue(a + b > b);
    }
}
