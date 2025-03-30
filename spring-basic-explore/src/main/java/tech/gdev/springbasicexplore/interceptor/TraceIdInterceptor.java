package tech.gdev.springbasicexplore.interceptor;

import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author gdev
 * @date 2025/3/30 16:56
 */
@Log4j2
public class TraceIdInterceptor implements HandlerInterceptor {
    private static final String TRACE_ID = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取traceId，如果没有则生成一个
        String traceId = request.getHeader(TRACE_ID);
        if (!StringUtils.hasLength(traceId)) {
            traceId = String.valueOf(UUID.randomUUID());
        }
        MDC.put(TRACE_ID, traceId);
        log.info("traceId: {}", traceId);
        response.addHeader(TRACE_ID, traceId); // 将traceId添加到响应头中
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear(); // 请求处理完成后清除MDC中的traceId
    }
}