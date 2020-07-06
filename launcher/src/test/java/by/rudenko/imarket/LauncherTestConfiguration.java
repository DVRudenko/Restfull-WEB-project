package by.rudenko.imarket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan("by.rudenko.imarket")
public class LauncherTestConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {
    // класс где лежит конфиг приложения
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{IMarketConfig.class, Launcher.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    // все запосы / попадут в диспетчер сервлетов
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public MockMvc createMockMvc () {return MockMvcBuilders.webAppContextSetup(webApplicationContext).build();}


}
