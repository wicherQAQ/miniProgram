package com.example.demo.service;

/**
 * 调取汇率数据接口
 */
public interface LifeAppService {

    /**
     *
     * @param scur
     * @param tcur
     */
    String getExchangeData(String scur, String tcur);

    /**
     *
     * @param postcode
     * @param areaname
     * @return
     */
    String getPostcode(String postcode,String areaname);
}
