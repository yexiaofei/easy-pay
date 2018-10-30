package com.easy.easypayservice.service;

import com.easy.easypayservice.dao.F2FPayResultVo;
import com.easy.easypayservice.dao.ScanPayResultVo;

import java.math.BigDecimal;
import java.util.Date;

public interface TradePaymentManagerService {

    /**
     * 条码支付,对应的是支付宝的条码支付
     *
     * @param payKey      商户支付key
     * @param authCode    支付授权码
     * @param productName 产品名称
     * @param orderNo     商户订单号
     * @param orderDate   下单日期
     * @param orderTime   下单时间
     * @param orderPrice  订单金额(元)
     * @param payWayCode  支付方式
     * @param orderIp     下单IP
     * @param remark      支付备注
     * @param field1      扩展字段1
     * @param field2      扩展字段2
     * @param field3      扩展字段3
     * @param field4      扩展字段4
     * @param field5      扩展字段5
     * @return
     */
    public F2FPayResultVo f2fPay(String payKey, String authCode, String productName, String orderNo, Date orderDate, Date orderTime, BigDecimal orderPrice, String payWayCode, String orderIp, String remark, String field1, String field2, String field3, String field4, String field5);



    public ScanPayResultVo initDirectScanPay(String payKey, String productName, String orderNo, Date orderDate, Date orderTime, BigDecimal orderPrice, String payWayCode, String orderIp, Integer orderPeriod, String returnUrl
            , String notifyUrl, String remark, String field1, String field2, String field3, String field4, String field5);

}
