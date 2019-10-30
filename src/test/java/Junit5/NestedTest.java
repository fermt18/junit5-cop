package Junit5;

import org.junit.jupiter.api.*;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("A Stack object")
class NestedTest {

    private Stack<String> stack;

    // Nested stack is LIFO
    // produces a nicely readable test output -> test as documentation
    @Test
    @DisplayName("is instantiated with new")
    void isInstantiated() {
        new Stack<>();
    }

    @Nested
    @DisplayName("when empty")
    class WhenNew {

        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
        }

        @Test
        @DisplayName("empty check returns true")
        void isEmpty() {
            assertThat(stack.isEmpty(), is(Boolean.TRUE));
        }

        @Test
        @DisplayName("throws Exception when popped") // retrieves from top and delete
        void throwsExceptionWhenPopped() {
            Assertions.assertThrows(EmptyStackException.class, () -> stack.pop());
        }

        @Test
        @DisplayName("throws Exception when peeked") // retrieves from top
        void throwsExceptionWhenPeeked() {
            Assertions.assertThrows(EmptyStackException.class, () -> stack.peek());
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {

            String anElement = "an element";

            @BeforeEach
            void pushAnElement() {
                stack.push(anElement);
            }

            @Test
            @DisplayName("empty check returns false")
            void isNotEmpty() {
                assertThat(stack.isEmpty(), is(Boolean.FALSE));
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                assertThat(stack.pop(), equalTo(anElement));
                assertThat(stack.isEmpty(), is(Boolean.TRUE));
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                assertThat(stack.peek(), equalTo(anElement));
                assertThat(stack.isEmpty(), is(Boolean.FALSE));
            }
        }
    }
}
