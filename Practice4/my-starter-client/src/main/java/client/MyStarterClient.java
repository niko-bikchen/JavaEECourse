package client;

import library.MyLibraryClass;
import library.MyOtherLibraryClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyStarterClient {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MyStarterClient.class, args);

        MyLibraryClass myLibraryClass = applicationContext.getBean(MyLibraryClass.class);
        myLibraryClass.printInfo();

        MyLibraryClass myLibraryClass1 = (MyLibraryClass) applicationContext.getBean("myLibraryClass");
        myLibraryClass1.printInfo();

        // This will work only if property "my.property" exists in the application.properties
        // and has value other than false, as this bean is annotated with @ConditionalOnProperty.
        // Otherwise, this bean will not be created and the lines below will cause an error.
        MyOtherLibraryClass myOtherLibraryClass = applicationContext.getBean(MyOtherLibraryClass.class);
        myOtherLibraryClass.printInfo();
    }
}
