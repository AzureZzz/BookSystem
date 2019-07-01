package com.guet.annotation;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthAdminInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod ();

        if (method.getAnnotation (AuthAdmin.class) != null) {
            if(request.getSession().getAttribute("admin")==null) {
                response.sendRedirect("/admin/login");
            }else{
                return true;
            }
            return false;
        }
        return true;
    }
}