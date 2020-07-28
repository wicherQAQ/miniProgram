package com.example.demo.service;

/**
 * 调取汇率数据接口
 */
public interface ExchangeRateService {

    /**
     *
     * @param scur
     * @param tcur
     */
    String getExchangeData(String scur, String tcur);
}
