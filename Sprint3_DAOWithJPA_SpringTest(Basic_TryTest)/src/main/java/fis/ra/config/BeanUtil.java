package fis.ra.config;

import org.springframework.context.ApplicationContext;

import java.util.Arrays;

public class BeanUtil {
    public static void getAllBeans(ApplicationContext appContext){
        String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans)
        {
            System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
        }
    }
}
