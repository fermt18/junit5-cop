package Junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.*;

public class AssumptionsTest {

    @Test
    void assumeTrueTest() {
        assumeTrue(false);
        fail("Test 1 failed (skipped as assumption is not met)");
    }

    @Test
    void assumeFalseTest() {
        assumeFalse(this::getTrue);
        fail("Test 2 failed (skipped as assumption is not met)");
    }

    private boolean getTrue() {
        return true;
    }

    @Test
    void assummingThatTest() {
        assumingThat(false, () -> fail("Test 3 failed (only lambda is skipped as assumption is not met)"));
    }
}
