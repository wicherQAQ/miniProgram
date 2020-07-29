package com.example.demo.controller;

import com.example.demo.constant.LifeAppConstant;
import com.example.demo.service.LifeAppService;
import com.example.demo.utils.RedisUtil;
import com.example.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fly")
public class LifeRateController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LifeAppService lifeAppService;

    /**
     * 获取汇率数据
     * @return
     */
    @GetMapping("/exchangeRate")
    public Result<?> getRate() {
        String res = (String)redisUtil.get(LifeAppConstant.EXCHANGE_RATE_KEY);
        return Result.ok(res);
    }

    /**
     * 查询邮政编码数据
     * @return
     */
    @GetMapping("/searchPostcode")
    public Result<?> searchPostcode(@RequestParam(name = "postcode") String postcode,
                                    @RequestParam(name = "areaname") String areaname) {
        String res = lifeAppService.getPostcode(postcode,areaname);
        return Result.ok(res);
    }

}
