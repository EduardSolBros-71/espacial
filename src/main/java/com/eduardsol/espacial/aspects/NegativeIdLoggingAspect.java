package com.eduardsol.espacial.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NegativeIdLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(NegativeIdLoggingAspect.class);

    // Definir el Pointcut para capturar llamadas al metodo obtenerNavePorId
    @Pointcut("execution(* com.eduardsol.espacial.services.NaveEspacialServiceImpl.getById(..)) && args(id)")
    public void getByIdMethod(Long id) {}

    // Before advice que se ejecuta antes del metodo getById si el id es negativo
    @Before("getByIdMethod(id)")
    public void logIfNegativeId(Long id) {
        if (id < 0) {
            logger.warn("Se solicitó una nave con un ID negativo: " + id);
        }
    }
}