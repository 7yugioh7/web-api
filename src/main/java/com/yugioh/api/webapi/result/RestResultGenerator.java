package com.yugioh.api.webapi.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @Author Create By lieber
 * @Description 接口响应结果生成类
 * @Date Create in 2018/5/16 17:53
 * @Modify By
 */
@Component
public class RestResultGenerator {

    /**
     * 日志记录工具
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(RestResultGenerator.class);

    /**
     * 生成响应结果
     *
     * @param code    状态码
     * @param data    数据
     * @param message 消息
     * @param <T>     数据类型
     * @return 结果
     */
    public <T> RestResult<T> generateResult(int code, T data, String message) {
        RestResult<T> result = new RestResult<>();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate rest result:{}", result);
        }
        return result;
    }

    /**
     * 成功响应
     *
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 结果
     */
    public <T> RestResult<T> generateSuccessResult(T data) {
        return generateResult(HttpStatus.OK.value(), data, "成功");
    }

    /**
     * 异常响应
     *
     * @param e 异常信息
     * @return 结果
     */
    public RestResult<Object> generateExceptionResult(Exception e) {
        LOGGER.error("出现异常", e);
        return generateResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "服务器繁忙");
    }

}
