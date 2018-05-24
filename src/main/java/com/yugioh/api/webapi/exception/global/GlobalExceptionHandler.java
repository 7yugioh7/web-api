package com.yugioh.api.webapi.exception.global;

import com.yugioh.api.webapi.result.RestResult;
import com.yugioh.api.webapi.result.RestResultGenerator;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;


/**
 * @Author Create By lieber
 * @Description 全局异常处理
 * @Date Create in 2018/5/16 17:38
 * @Modify By
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private RestResultGenerator restResultGenerator;

    /**
     * 业务代码出错
     *
     * @param exception 异常类型
     * @return 默认返回
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResult> processRuntimeException(Exception exception) {
        ResponseEntity.BodyBuilder builder;
        // 此处可针对具体异常
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
        } else {
            builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        RestResult restResult = restResultGenerator.generateExceptionResult(exception);
        return builder.body(restResult);
    }

}
