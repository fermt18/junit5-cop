package Junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Junit5.TestInterfaces.TestInterfaceDynamicTestsDemo;
import Junit5.TestInterfaces.TestLifeCycleLogger;
import Junit5.TestInterfaces.TimeExecutionLogger;
import org.junit.jupiter.api.Test;

class InterfacesTest implements TestLifeCycleLogger,
        TimeExecutionLogger,
        TestInterfaceDynamicTestsDemo {

    @Test
    void isEqualValue() {
        assertEquals(1, 1);
    }

}
