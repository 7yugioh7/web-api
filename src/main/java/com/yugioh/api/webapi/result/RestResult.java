package com.yugioh.api.webapi.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Create By lieber
 * @Description API接口公用响应格式
 * @Date Create in 2018/5/15 17:28
 * @Modify By
 */
@Data
public class RestResult<T> implements Serializable {

    /**
     * 响应状态码,同http状态码
     */
    private int code;

    /**
     * 响应时间
     */
    private long time = System.currentTimeMillis();

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应信息
     */
    private String message;
}
