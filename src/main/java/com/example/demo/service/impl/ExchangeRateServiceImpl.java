package com.example.demo.service.impl;

import com.example.demo.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

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
        StringBuilder sb = new StringBuilder(URL);
        sb.append("&scur=").append(scur)
                .append("&tcur=").append(tcur)
                .append("&appkey=").append(APP_KEY)
                .append("&sign=").append(SIGN);
        String res=null;
        try {
            res = getExchangeRate(sb.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    private String getExchangeRate(String url) throws UnsupportedEncodingException {
        java.net.URL u = null;
        ByteArrayOutputStream out = null;
        InputStream in = null;
        try {
            u = new URL(url);
            in = u.openStream();
            out = new ByteArrayOutputStream();
            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        byte b[] = out.toByteArray();
        return new String(b, "utf-8");
    }

}
