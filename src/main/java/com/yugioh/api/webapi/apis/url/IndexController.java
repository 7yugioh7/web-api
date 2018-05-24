package com.yugioh.api.webapi.apis.url;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Create By lieber
 * @Description
 * @Date Create in 2018/5/16 18:30
 * @Modify By
 */
@Controller
public class IndexController {

    /**
     * 首页,默认页面
     *
     * @return 页面
     */
    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

}
