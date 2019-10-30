package SpringApp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan //declare the package in which Spring will look for beans definition in the form of annotations
public class MySpringApplication {

    public static void main(String[] args) {
        // create Spring App context
        try (AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(
                MySpringApplication.class)) {
            // get the Spring component whose class is MessageComponent
            MessageComponent messageComponent = context
                    .getBean(MessageComponent.class);
            System.out.println(messageComponent.getMessage());
        }
    }
}