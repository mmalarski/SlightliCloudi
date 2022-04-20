package kw.hk.mm.mr.slightlicloudi.configuration;

import kw.hk.mm.mr.slightlicloudi.configuration.JWT.JWTHandler;

import java.util.List;

public class BeanRegistry {
    static List<Class<?>> commonBeans = List.of(
            JWTHandler.class
    );
}
