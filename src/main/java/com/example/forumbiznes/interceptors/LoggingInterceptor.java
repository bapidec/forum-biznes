package com.example.forumbiznes.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LoggingInterceptor implements Serializable {
    private final static Logger log = Logger.getLogger(LoggingInterceptor.class.getName());

    @AroundInvoke
    Object log(InvocationContext ic) throws Exception {
        List<String> paramNames = new ArrayList<>();
        Parameter[] params = ic.getMethod().getParameters();
        for(Parameter p : params) {
            paramNames.add(p.getName());
        }
        log.info("Wywołanie metody " + ic.getMethod().getName() + "(" + paramNames.toString() + ")");
        Object res = null;
        try {
            res = ic.proceed();
        } finally {
            return res;
        }

    }

}
