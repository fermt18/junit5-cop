package hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsNotANumber extends TypeSafeMatcher {

    @Override
    public boolean matchesSafely(Object number) {
        Double dNumber = (Double)number;
        return dNumber.isNaN();
    }

    public void describeTo(Description description) {
        description.appendText("not a number");
    }

    public static Matcher notANumber() {
        return new IsNotANumber();
    }
}
