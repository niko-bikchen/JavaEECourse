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

        MyOtherLibraryClass myOtherLibraryClass = applicationContext.getBean(MyOtherLibraryClass.class);
        myOtherLibraryClass.printInfo();
    }
}
