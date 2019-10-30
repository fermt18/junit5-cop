package Junit5.Frameworks.Cucumber;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import cucumber.api.CucumberOptions;

@Disabled("Not yet supported!")
//https://github.com/cucumber/cucumber-jvm/issues/1149
@CucumberOptions(plugin = { "pretty" })
//@ExtendWith(CucumberExtension.class)
public class CucumberTest {

    @TestFactory
    public Stream<DynamicTest> runCukes(Stream<DynamicTest> scenarios) {
        List<DynamicTest> tests = scenarios.collect(Collectors.toList());
        return tests.stream();
    }

}
