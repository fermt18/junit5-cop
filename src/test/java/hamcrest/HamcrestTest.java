package hamcrest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

// declarative test specification
// focus on readability
class HamcrestTest {
    private List<String> testList = Arrays.asList("One", "Two", "Three");

    @Test
    void matchers_hasItem(){
        assertThat(testList, hasItem("One"));
        assertThat(testList, hasItems("One", "Three"));
        // JUnit equivalent
        Assertions.assertTrue(testList.contains("One"));
        Assertions.assertTrue(testList.contains("One") && testList.contains("Three"));
    }

    @Test
    void matchers_oneOf(){
        assertThat(testList, oneOf(
                Arrays.asList("One", "Two", "Three"),
                Arrays.asList("One", "Two", "Four"),
                "Three"));
    }

    @Test
    void matchers_allOf(){
        assertThat(testList, allOf(hasItem("One"), hasItem("Two"), hasItem("Three")));
        // Same as &&
        Assertions.assertTrue(testList.contains("One") && testList.contains("Two") && testList.contains("Three"));
    }

    @Test
    void matchers_anyOf(){
        assertThat(testList, anyOf(hasItem("One"), hasItem("Four"), hasItem("Five")));
        // Same as ||
        Assertions.assertTrue(testList.contains("One") || testList.contains("Four") || testList.contains("Five"));
    }

    @Test
    void matchers_not(){
        assertThat(testList, not(anyOf(hasItem("Zero"), hasItem("Four"), hasItem("Five"))));
        assertThat(testList, not(nullValue()));
    }

    @Test
    void matchers_equalsTo(){
        assertThat(testList.get(0), equalTo("One"));
        assertThat(testList, equalTo(Arrays.asList("One", "Two", "Three")));
        // JUnit equivalent
        Assertions.assertNotEquals(Arrays.asList("One", "Two", "Three"), testList);
        Assertions.assertArrayEquals(Arrays.asList("One", "Two", "Three").toArray(), testList.toArray());
    }

    @Test
    void matchers_hasToString(){
        assertThat(1, hasToString(equalTo("1")));
        assertThat(true, hasToString(equalTo("true")));
        // JUnit equivalent
        Assertions.assertEquals("true", Boolean.TRUE.toString());
    }

    @Test
    void matchers_sameInstance(){
        assertThat(testList, not(sameInstance(Arrays.asList("One", "Two", "Three"))));
        assertThat(testList, sameInstance(testList));
        assertThat(testList.hashCode(), not(equalTo(Arrays.asList("One", "Two", "Three"))));
        // JUnit equivalent
        Assertions.assertFalse(testList == Arrays.asList("One", "Two", "Three"));
    }

    @Test
    void matchers_custom() {
        assertThat(Math.sqrt(-1), is(notANumber()));
    }
}
