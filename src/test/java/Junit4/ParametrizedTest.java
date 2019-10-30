package Junit4;

import org.junit.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParametrizedTest {

    @Parameterized.Parameter(0)
    public int input1;

    @Parameterized.Parameter(1)
    public int input2;

    @Parameterized.Parameter(2)
    public int sum;

    @Parameterized.Parameters(name = "{index}: input1={0} input2={1} sum={2}?")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] { {1,1,2}, {2,2,4}, {3,3,9} });
    }

    @Ignore("Failing test")
    @Test
    public void testSum(){
        Assert.assertTrue(input1 + "+" + input2 + " is not " + sum,
                input1 + input2 == sum);
    }
}
