package fis.ra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootJDBCTemplateApp {
    private static final Logger log = LoggerFactory.getLogger(SpringBootJDBCTemplateApp.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext appContext = SpringApplication.run(SpringBootJDBCTemplateApp.class, args);

//            BeanUtil.getAllBeans(appContext);

//        PersonService service = appContext.getBean(PersonService.class);
//        service.testPersonRepo();
    }
}