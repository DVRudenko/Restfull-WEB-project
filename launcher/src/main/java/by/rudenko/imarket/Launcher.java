package by.rudenko.imarket;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Launcher extends AbstractAnnotationConfigDispatcherServletInitializer {


    // класс где лежит конфиг приложения
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{IMarketConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    // все запросы / попадут в диспетчер сервлетов
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


}




