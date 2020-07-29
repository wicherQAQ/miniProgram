package com.example.demo.service.impl;

import com.example.demo.service.LifeAppService;
import com.example.demo.utils.oConvertUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import static com.example.demo.constant.LifeAppConstant.TYPE_EXCHANGE_RATE;
import static com.example.demo.constant.LifeAppConstant.TYPE_POSTCODE;

@Service
public class LifeAppServiceImpl implements LifeAppService {

    /**
     * 请求的URL
     */
    @Value("${fly.now-api.request-url}")
    private String URL;

    /**
     * 第三方应用的请求key
     */
    @Value("${fly.now-api.appkey}")
    private String APP_KEY;

    /**
     * 请求时携带参数
     * 可能会过期需要在yml配置类中手动修改
     */
    @Value("${fly.now-api.sign}")
    private String SIGN;

    @Override
    public String getExchangeData(String scur, String tcur) {
        StringBuilder url = new StringBuilder(URL);
        url.append(TYPE_EXCHANGE_RATE)
                .append("&scur=").append(scur)
                .append("&tcur=").append(tcur)
                .append("&appkey=").append(APP_KEY)
                .append("&sign=").append(SIGN);
        String res = null;
        try {
            res = getApiResult(url.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String getPostcode(String postcode,String areaname) {
        StringBuilder url = new StringBuilder(URL);
        url.append(TYPE_POSTCODE);
        if(oConvertUtils.isNotEmpty(postcode)){
            url.append("&postcode=").append(postcode);
        }
        if(oConvertUtils.isNotEmpty(areaname)){
            url.append("&areaname=").append(areaname);
        }
        url.append("&appkey=").append(APP_KEY).append("&sign=").append(SIGN);
        String res = null;
        try {
            res = getApiResult(url.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    private String getApiResult(String url) throws Exception{
        URL u = new URL(url);
        InputStream in = u.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[] = out.toByteArray();
        return new String(b, "utf-8");
    }

}
