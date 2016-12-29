package ru.easyjava.spring.data.declarative;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.easyjava.spring.data.declarative.dao.GreeterDao;
import ru.easyjava.spring.data.declarative.entity.Greeter;
import ru.easyjava.spring.data.declarative.service.GreeterService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Simple example of JDBC usage.
 */
public final class App {
    /**
     * Do not construct me.
     */
    private App() {
    }

    /**
     * Entry point.
     *
     * @param args Command line args. Not used.
     */
    public static void main(final String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);
        GreeterService greeterService = context.getBean(GreeterService.class);
        GreeterDao dao = context.getBean(GreeterDao.class);

        Greeter greeter = new Greeter();
        greeter.setGreeting("Hello");
        greeter.setTarget("World");

        dao.addGreet(greeter);

        System.out.println(greeterService.greet());

        try {
            dao.updateGreet(greeter, "Fail");
        } catch (NotImplementedException e) {
            System.out.println(greeterService.greet());
        }

        System.exit(0);
    }
}
