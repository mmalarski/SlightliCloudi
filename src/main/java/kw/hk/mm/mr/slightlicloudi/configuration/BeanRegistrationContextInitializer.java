package kw.hk.mm.mr.slightlicloudi.configuration;


import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import java.util.HashSet;
import java.util.Set;

public class BeanRegistrationContextInitializer implements ApplicationContextInitializer<GenericApplicationContext> {
    private final Set<Class<?>> allBeans = new HashSet<>(BeanRegistry.commonBeans);

    @Override
    public void initialize(GenericApplicationContext context) {
        allBeans.forEach(bean -> context.registerBean(bean));
    }

    protected void register(Class<?> newBean) {
        allBeans.add(newBean);
    }


}
