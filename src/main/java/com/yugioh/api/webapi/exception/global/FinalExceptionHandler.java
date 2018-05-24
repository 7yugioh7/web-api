package com.yugioh.api.webapi.exception.global;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Create By lieber
 * @Description 全局异常处理之 -- 在进入Controller之前，譬如请求一个不存在的地址，404错误。
 * @Date Create in 2018/5/16 17:35
 * @Modify By
 */
@RestController
public class FinalExceptionHandler extends BasicErrorController {

    /**
     * 错误页面路由
     */
    private static final String PATH = "/error";

    public FinalExceptionHandler() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    /**
     * JSON数据访问失败
     *
     * @param request 请求
     * @return 响应
     */
    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", status.value());
        map.put("time", System.currentTimeMillis());
        map.put("message", "你访问的资源找不到了");
        return new ResponseEntity<>(map, status);
    }

    /**
     * 访问资源url错误
     *
     * @return 错误页面
     */
    @Override
    public String getErrorPath() {
        return PATH;
    }
}
