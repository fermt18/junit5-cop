package Junit5.Extensions;

import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimingExtension
        implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    static final Logger log = LoggerFactory
            .getLogger(TimingExtension.class.getName());

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        getStore(context).put(context.getRequiredTestMethod(),
                System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        long start = getStore(context).remove(testMethod, long.class);
        long duration = System.currentTimeMillis() - start;

        //log.info("Method {} took {} ms", testMethod.getName(), duration);
        System.out.println("Method " + testMethod.getName() + " took " + duration + " [ms]");
    }

    private Store getStore(ExtensionContext context) {
        return context.getStore(Namespace.create(getClass(), context));
    }

}
