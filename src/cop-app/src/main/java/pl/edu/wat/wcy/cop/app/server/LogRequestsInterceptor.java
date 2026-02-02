/**
 *
 */
package pl.edu.wat.wcy.cop.app.server;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pl.edu.wat.wcy.cop.app.server.controllers.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
// Intercepts log requests requests.

public class LogRequestsInterceptor extends HandlerInterceptorAdapter {
    private static final String START_TIME_PARAM = "STARTTIME";
    private static final String END_TIME_PARAM = "ENDTIME";

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LogRequestsInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Method method = hm.getMethod();

            String action = request.getRequestURL().toString();
            String ipAddress = WebUtil.getClientIpAddress(request);

            Long startTime = WebUtil.getKeyFromRequestAttribute(request, START_TIME_PARAM, Long.class, 0l);

            Long endTime = WebUtil.getKeyFromRequestAttribute(request, END_TIME_PARAM, Long.class,
                    new Date().getTime());
            Date startDateTime = new Date(startTime);
            Date endDateTime = new Date(endTime);
            Long total = endTime - startTime;
            String className = method.getDeclaringClass().getSimpleName();
            String methodName = method.getName();
            logger.debug(String.format(
                    "Zadanie wywolania IP = %s path = %s ,start = %s, koniec = %s, total = %s,klasa = %s, metoda = %s, parametry  to %s",
                    ipAddress, action, startDateTime, endDateTime, total, className, methodName,
                    request.getParameterMap()));
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        addCurrentTimeRequestParam(request, handler, START_TIME_PARAM);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        addCurrentTimeRequestParam(request, handler, END_TIME_PARAM);
        super.postHandle(request, response, handler, modelAndView);

    }

    /**
     * @param request
     * @param handler
     * @param param
     */
    private void addCurrentTimeRequestParam(HttpServletRequest request, Object handler, String param) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Method method = hm.getMethod();
            if (method.getDeclaringClass().isAnnotationPresent(RestController.class)) {
                request.setAttribute(param, System.currentTimeMillis());
            }
        }

    }

}
