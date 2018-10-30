package com.easy.easypayservice.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.easy.easypayservice.dao.F2FPayResultVo;
import com.easy.easypayservice.dao.ScanPayResultVo;
import com.easy.easypayservice.service.TradePaymentManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("rpTradePaymentManagerService")
public class TradePaymentManagerServiceImpl implements TradePaymentManagerService {



    //支付订单
    @Override
    @Transactional
    public F2FPayResultVo f2fPay(String payKey, String authCode, String productName, String orderNo, Date orderDate, Date orderTime, BigDecimal orderPrice, String payWayCode, String orderIp, String remark, String field1, String field2, String field3, String field4, String field5) {
        F2FPayResultVo f2FPayResultVo = new F2FPayResultVo();
        //获取用户支付信息
        //根据相关商户或者商品规则以及支付方式获取手续费参数

        //根据商户和订单号查询订单，订单存在则查询订单状态，订单不存在则创建订单

        //调用支付宝进行支付
        AlipayClient alipayClient = new DefaultAlipayClient("","","");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        System.out.println(JSONObject.toJSONString(paramMap));
        request.setBizContent(JSONObject.toJSONString(paramMap));
        try {
            AlipayTradePayResponse response = alipayClient.execute(request);
            JSONObject responseJSON = JSONObject.parseObject(JSONObject.toJSONString(response));

        }catch (AlipayApiException e){
            e.printStackTrace();


        }
        f2FPayResultVo.setStatus("");// 支付结果
        paramMap.put("status","");

        f2FPayResultVo.setOrderIp("");// 下单ip
        paramMap.put("orderIp","");

        f2FPayResultVo.setOrderNo("");// 商户订单号
        paramMap.put("merchantOrderNo","");

        f2FPayResultVo.setPayKey(payKey);// 支付号
        paramMap.put("payKey", payKey);

        f2FPayResultVo.setProductName("");// 产品名称
        paramMap.put("productName","");

        f2FPayResultVo.setRemark("");// 支付备注
        paramMap.put("remark","");

        f2FPayResultVo.setTrxNo("");// 交易流水号
        paramMap.put("trxNo","");

        f2FPayResultVo.setSign("");//签名

        f2FPayResultVo.setField1("");// 扩展字段1
        paramMap.put("field1","");

        f2FPayResultVo.setField2("");// 扩展字段2
        paramMap.put("field2","");

        f2FPayResultVo.setField3("");// 扩展字段3
        paramMap.put("field3","");

        f2FPayResultVo.setField4("");// 扩展字段4
        paramMap.put("field4","");

        f2FPayResultVo.setField5("");// 扩展字段5
        paramMap.put("field5","");

        //进行本地数据记录处理
        return f2FPayResultVo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScanPayResultVo initDirectScanPay(String payKey, String productName, String orderNo, Date orderDate, Date orderTime, BigDecimal orderPrice, String payWayCode, String orderIp, Integer orderPeriod, String returnUrl, String notifyUrl, String remark, String field1, String field2, String field3, String field4, String field5) {
        ScanPayResultVo scanPayResultVo = new ScanPayResultVo();

        return scanPayResultVo;
    }
}
