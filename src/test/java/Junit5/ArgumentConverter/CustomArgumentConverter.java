package Junit5.ArgumentConverter;

import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class CustomArgumentConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) {
        return String.valueOf(source);
    }
}
