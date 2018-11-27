package com.nnniu.alipaytest;

public class AlipayTestConfig {
	/**
     * 用于支付宝支付业务的入参 app_id
     */
    public static final String APPID = "2015120900948265";
    
    /**
     * pkcs8 格式的商户私钥
     */
    public static final String APP_PRIVATE_KEY = "";
	
    public static final String CHARSET = "UTF-8";
    
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

    /**
     * 异步通知地址
     */
    public static final String ALIPAY_NOTIFY_URL = "http://39.107.64.191/sia/apiPay/alipayNotify";
}
