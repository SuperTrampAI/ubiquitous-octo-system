package com.supertrampai.myutils.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截器
 * @author Administrator
 *
 */
@Aspect
@Component
public class ControllerInterceptor {
/**
 * 定义一个日志的全局的静态
 */
private static Logger logger =LoggerFactory.getLogger(ControllerInterceptor.class);
 

    /**
     * 定义拦截规则：
     * 拦截com.zhangmen.report.endpoint包下面的所有类中，
     * 有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.xmlyart.admin..action..*(..))")
    public void controllerMethodPointcut() {
    }

    /**
     * 拦截器:
     * 记录请求日志
     * 拦截异常
     *
     * @param pjp
     * @return Object 被拦截方法的执行结果
     */
    @Around("controllerMethodPointcut()")
    public Object interceptor(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip=RequestUtil.getIp(request);
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();

        Object retObj;
        try {
            retObj = pjp.proceed();
        } catch (Throwable e) {
            if (e instanceof CusException) {
                logger.error("请求ip:"+ip+","+className + "#" + methodName + "业务异常，错误：" + e.getMessage());
                retObj = new Resp(1,e.getMessage());
            } else {
                logger.error("请求ip:"+ip+","+className + "#" + methodName + "系统异常，错误：" + e.getMessage(),e);
                retObj = new Resp(1,e.getMessage()==null?"系统异常":e.getMessage());
            }
        }

        long costMs = System.currentTimeMillis() - beginTime;
        logger.info("ip:{},{}#{}请求结束，耗时：{}ms",ip, className, methodName, costMs);

        return retObj;
    }


}