package library;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyLibraryAutoConfig {

    @Bean
    MyLibraryClass myLibraryClass() {
        return new MyLibraryClass();
    }

    // This bean will be created only if "my.property" property exists
    // in the application.properties and has value other than false.
    @Bean
    @ConditionalOnProperty(value = "my.property")
    MyOtherLibraryClass myOtherLibraryClass() {
        return new MyOtherLibraryClass();
    }
}
