package com.example.demo.controller;

import com.example.demo.constant.ExchangeRateConstant;
import com.example.demo.utils.RedisUtil;
import com.example.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fly")
public class ExchangeRateController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取汇率数据
     * @return
     */
    @GetMapping("/exchangeRate")
    public Result<?> getRate() {
        String res = (String)redisUtil.get(ExchangeRateConstant.EXCHANGE_RATE_KEY);
        return Result.ok(res);
    }

}
