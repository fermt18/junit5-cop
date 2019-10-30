package Junit5.TestInterfaces;

import Junit5.Extensions.TimingExtension;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("timed")
@ExtendWith(TimingExtension.class)
public interface TimeExecutionLogger {
}
