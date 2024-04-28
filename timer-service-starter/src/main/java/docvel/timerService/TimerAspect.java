package docvel.timerService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimerAspect {

    private static final Logger log = LoggerFactory.getLogger(TimerAspect.class);

    @Pointcut("@within(docvel.timerService.Timer)")
    public void anyAnnotatedClass(){}

    @Pointcut("execution(public * *.*(..))")
    public void anyPublicMethod(){}

    @Around("anyAnnotatedClass() && anyPublicMethod()")
    public Object measuringExecutionTimeOfMethod(ProceedingJoinPoint point) throws Throwable{
        long start = System.currentTimeMillis();
        Object obj = point.proceed();
        long elapsedTime = System.currentTimeMillis() - start;

        log.info("время выполнения {}.{}: {} ms",
                point.getTarget().getClass().getName(),
                point.getSignature().getName(),
                elapsedTime);

        return obj;
    }
}
