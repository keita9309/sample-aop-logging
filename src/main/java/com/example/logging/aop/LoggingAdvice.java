package com.example.logging.aop;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
    private final Logger logger;

    public LoggingAdvice() {
        this.logger = LoggerFactory.getLogger(getClass());
    }
    
    // com.example.loggingdemoパッケージ配下の全てのクラスのメソッドがログ出力の対象となる
    // この例では、serviceパッケージ配下のみログ出力の対象とする
    @Before("execution(* com.example.logging.service..*.*(..))")
    public void logBefore(JoinPoint jp) {
        startLog(jp);
    }

    // この例では、serviceパッケージ配下のみログ出力の対象とする
    @AfterReturning(value = "execution(* com.example.logging.service..*.*(..))", returning = "retval")
    public void logAfter(JoinPoint jp, Object retval) {
        endLog(jp, retval);
    }

    private void startLog(JoinPoint jp) {
        String mess = "[START]" + getClassName(jp) + "." + getSignature(jp) + ":args=[" + getArgs(jp) + "]";
        logger.info(mess);
    }

    private void endLog(JoinPoint jp, Object retval) {
        String mess = "[END]" + getClassName(jp) + "." + getSignature(jp) + ":retval=[" + getRetVal(retval) + "]";
        logger.info(mess);
    }

    private String getClassName(JoinPoint jp) {
        return jp.getTarget().getClass().toString();
    }

    private String getSignature(JoinPoint jp) {
        return jp.getSignature().getName();
    }

    private String getArgs(JoinPoint jp) {
        if (jp.getArgs() == null) {
            return "args is null";
        }

        Object[] args = jp.getArgs();
        ArrayList<String> list = new ArrayList<>();

        int i = 0;
        for (Object arg : args) {
            i++;
            if (arg != null) {
                list.add("arg" + i + "=" + arg);
            } else {
                list.add("arg" + i + "=null");

            }
        }
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("aaa");
        list2.add("bbb");
        list2.add("ccc");
        String.join(" ", list2);
        System.out.println(String.join(" ", list2));
        return String.join(" ", list);
    }

    private String getRetVal(Object retval) {
        if (retval == null) {
            return "retval is null";
        } else {
            return retval.toString();
        }
    }
}
