package project.ppaya.square.yhutil;

import org.springframework.context.ApplicationContext;

public class YHBeanUtil
{
    public static Object getBean(String beanName)
    {
        ApplicationContext applicationContext = YHApplicationContextProviderUtil.getApplicationContext();
        return applicationContext.getBean(beanName);
    }
}