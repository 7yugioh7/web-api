package com.yugioh.api.webapi.apis.ajax;

import com.yugioh.api.webapi.result.RestResult;
import com.yugioh.api.webapi.result.RestResultGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Create By lieber
 * @Description demo
 * @Date Create in 2018/5/15 17:24
 * @Modify By
 */
@RestController
@RequestMapping("demo")
public class DemoAjaxApi {

    @Resource
    private RestResultGenerator restResultGenerator;

    @RequestMapping("test")
    public RestResult test() {
        return restResultGenerator.generateSuccessResult("");
    }

}
