package com.easy.easypaywebgateway.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.easy.easypayservice.dao.F2FPayResultVo;
import com.easy.easypayservice.service.TradePaymentManagerService;
import com.easy.easypaywebgateway.configuration.AlipayProperties;
import com.easy.easypaywebgateway.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

/**
 * 支付宝通用接口.
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController {
    private static final Logger logger = LoggerFactory.getLogger(AlipayController.class);
    @Autowired
    private AlipayProperties aliPayProperties;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayTradeService alipayTradeService;

    @Autowired(required=false)
    private TradePaymentManagerService TradePaymentManagerService;



    /**
     * 条码支付,商户通过前置设备获取到用户支付授权码后,请求支付网关支付,商户扫用户
     * 商家使用扫码工具(扫码枪等)扫描用户支付宝的付款码
     * @return
     */
    @RequestMapping("/doPay")
    public String initPay(HttpServletRequest httpServletRequest){
        logger.info("======>进入扫码支付");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //获取商户传入参数
        String payKey = getString_UrlDecode_UTF8("payKey"); // 企业支付KEY
        paramMap.put("payKey", payKey);
        String authCode = getString_UrlDecode_UTF8("authCode"); // 企业支付KEY
        paramMap.put("authCode", authCode);
        String productName = getString_UrlDecode_UTF8("productName"); // 商品名称
        paramMap.put("productName", productName);
        String orderNo = getString_UrlDecode_UTF8("orderNo"); // 订单编号
        paramMap.put("orderNo", orderNo);
        String orderPriceStr = getString_UrlDecode_UTF8("orderPrice"); // 订单金额 , 单位:元
        paramMap.put("orderPrice", orderPriceStr);
        String payWayCode = getString_UrlDecode_UTF8("payWayCode"); // 支付方式编码 支付宝: ALIPAY  微信:WEIXIN
        paramMap.put("payWayCode", payWayCode);
        String orderIp = getString_UrlDecode_UTF8("orderIp"); // 下单IP
        paramMap.put("orderIp", orderIp);
        String orderDateStr = getString_UrlDecode_UTF8("orderDate"); // 订单日期
        paramMap.put("orderDate", orderDateStr);
        String orderTimeStr = getString_UrlDecode_UTF8("orderTime"); // 订单日期
        paramMap.put("orderTime", orderTimeStr);
        String orderPeriodStr = getString_UrlDecode_UTF8("orderPeriod"); // 订单有效期
        paramMap.put("orderPeriod", orderPeriodStr);
        String returnUrl = getString_UrlDecode_UTF8("returnUrl"); // 页面通知返回url
        paramMap.put("returnUrl", returnUrl);
        String notifyUrl = getString_UrlDecode_UTF8("notifyUrl"); // 后台消息通知Url
        paramMap.put("notifyUrl", notifyUrl);
        String remark = getString_UrlDecode_UTF8("remark"); // 支付备注
        paramMap.put("remark", remark);
        String sign = getString_UrlDecode_UTF8("sign"); // 签名

        String field1 = getString_UrlDecode_UTF8("field1"); // 扩展字段1
        paramMap.put("field1", field1);
        String field2 = getString_UrlDecode_UTF8("field2"); // 扩展字段2
        paramMap.put("field2", field2);
        String field3 = getString_UrlDecode_UTF8("field3"); // 扩展字段3
        paramMap.put("field3", field3);
        String field4 = getString_UrlDecode_UTF8("field4"); // 扩展字段4
        paramMap.put("field4", field4);
        String field5 = getString_UrlDecode_UTF8("field5"); // 扩展字段5
        paramMap.put("field5", field5);
        logger.info("扫码支付,接收参数:{}", paramMap);
//        Date orderDate = DateUtil.convertDate("yyyyMMdd",orderDateStr);
//        Date orderTime = DateUtil.convertDate("yyyyMMddHHmmss",orderTimeStr);
        Date orderDate = DateUtil.convertDate("yyyyMMdd","20181020");
        Date orderTime = DateUtil.convertDate("yyyyMMddHHmmss","20181020101010");

        //验签
        boolean check = rsaCheckV1(httpServletRequest);

        //发起支付
        Double a = Double.valueOf("1");
        BigDecimal orderPrice = BigDecimal.valueOf(a);
        F2FPayResultVo f2FPayResultVo = TradePaymentManagerService.f2fPay(payKey, authCode, productName, orderNo, orderDate, orderTime, orderPrice, payWayCode, orderIp, remark, field1, field2, field3, field4, field5);

        String payResultJson = JSONObject.toJSONString(f2FPayResultVo);
        logger.debug("条码支付--支付结果==>{}", f2FPayResultVo);
        return "paypage";
    }

    /**
     * 用户直接支付通过本系统网页调转到支付宝平台进行支付
     *
     * @return
     */
    @RequestMapping("/gotoPayPage")
    public void gotoPayPage(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException ,IOException {
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("支付测试");
        model.setTotalAmount("0.01");
        model.setBody("支付测试，共0.01元");
        model.setTimeoutExpress("5m");

        AlipayTradeWapPayRequest wapPayRequest =new AlipayTradeWapPayRequest();
        wapPayRequest.setBizModel(model);
        // 调用SDK生成表单, 并直接将完整的表单html输出到页面
        String form = alipayClient.pageExecute(wapPayRequest).getBody();
        System.out.println(form);
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     *扫码支付，指用户打开支付宝钱包中的“扫一扫”功能，扫描商户针对每个订单实时生成的订单二维码，并在手机端确认支付
     *发起预下单请求，同步返回订单二维码
     * 商家获取二维码展示在屏幕上，然后用户去扫描屏幕上的二维码
     * @return
     */


    @RequestMapping("/returnUrl")
    public String returnUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {
        //验签
        //获取支付宝的反馈信息
        //接口内处理业务逻辑
        //返回成功或者失败的界面
        return "gotoWapPay";
    }




    /**
     * 当面付-扫码付
     *
     * 扫码支付，指用户打开支付宝钱包中的“扫一扫”功能，扫描商户针对每个订单实时生成的订单二维码，并在手机端确认支付。
     *
     * 发起预下单请求，同步返回订单二维码
     *
     * 适用场景：商家获取二维码展示在屏幕上，然后用户去扫描屏幕上的二维码
     */


    /**
     * 校验签名
     * @param request
     * @return
     */
    public boolean rsaCheckV1(HttpServletRequest request){
        // 获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        try {
            boolean verifyResult = AlipaySignature.rsaCheckV1(params,
                    aliPayProperties.getAlipayPublicKey(),
                    aliPayProperties.getCharset(),
                    aliPayProperties.getSignType());

            return verifyResult;
        } catch (AlipayApiException e) {
            System.out.println("verify sigin error, exception is:{}"+ e.toString());
            return false;
        }
    }
    public String getString_UrlDecode_UTF8(String key) {
        try {
            String string = key.toString();
            if (string==null){
                return null;
            }else{
                return URLDecoder.decode(string, "utf-8");
            }
        } catch (Exception e) {
            logger.error("URL解码错误:",e);
            return "";
        }

    }
}
