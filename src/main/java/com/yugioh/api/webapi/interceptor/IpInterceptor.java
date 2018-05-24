package com.yugioh.api.webapi.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Create By lieber
 * @Description
 * @Date Create in 2018/5/17 11:12
 * @Modify By
 */
public class IpInterceptor implements HandlerInterceptor {

    /**
     * 未知ip
     */
    private final static String UNKNOWN = "unknown";

    /**
     * 日志记录
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(IpInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = getIpAddress(request);
        //TODO 路由白名单,即不拦截路由

        // 获取可以访问系统的白名单
        String ipStr = "127.0.0.1|0:0:0:0:0:0:0:1";
        String[] ipArr = ipStr.split("\\|");
        List<String> ipList = Arrays.asList(ipArr);
        if (ipList.contains(ip)) {
            LOGGER.info("the request ip is : {}", ip);
            return true;
        } else {
            LOGGER.error("{} is not contains ipWhiteList .......", ip);
            //向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            String data = "您好，ip为" + ip + ",暂时没有访问权限，请联系管理员开通访问权限。";
            OutputStream stream = response.getOutputStream();
            stream.write(data.getBytes("UTF-8"));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

    /**
     * 获取访问的ip地址
     *
     * @param request 请求
     * @return ip地址
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
