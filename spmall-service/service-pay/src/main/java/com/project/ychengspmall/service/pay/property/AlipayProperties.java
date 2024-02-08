package com.project.ychengspmall.service.pay.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 @author XxychengychengxX
 @date 2024/1/28
 */
@Data
@ConfigurationProperties(prefix = "ychengspamll.alipay")
public class AlipayProperties {
    private String alipayUrl;
    private String appPrivateKey;
    public  String alipayPublicKey;
    private String appId;
    public  String returnPaymentUrl;
    public  String notifyPaymentUrl;

    public final static String format="json";
    public final static String charset="utf-8";
    public final static String sign_type="RSA2";
}
