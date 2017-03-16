package com.gproject.paypal.service;

import com.gproject.address.facade.AddressFacade;
import com.gproject.address.mapper.AddressCustomMapper;
import com.gproject.address.pojo.Address;
import com.gproject.address.pojo.AddressCustom;
import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.order.pojo.vo.OrderDetailAll;
import com.gproject.order.pojo.vo.OrderInsertVo;
import com.gproject.order.pojo.vo.OrderProduct;
import com.gproject.orderdetail.pojo.OrderDetailVo;
import com.gproject.paypal.facade.PayPalFacade;
import com.gproject.paypal.util.PayPalConfig;
import com.gproject.util.message.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import urn.ebay.api.PayPalAPI.*;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service
public class PayPalService  implements PayPalFacade {

    private static final Logger log = LoggerFactory.getLogger(PayPalService.class);

    @Autowired
    private PayPalConfig payPalConfig;
    @Autowired
    private AddressCustomMapper addressCustomMapper;
    @Value("${paypal.success.url}")
    private String SUCCESS_URL;
    @Value("${paypal.cancel.url}")
    private String CANCEL_URL;


    @Override
    public SetExpressCheckoutResponseType setExpressCheckout(OrderDetailAll all) throws Exception {
        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(payPalConfig.getConfig());
        // 快速支付请求
        SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();
        // 快速支付请求
        SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
        // 快速支付请求细节
        SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();
        // 订单
        List<PaymentDetailsType> orders = new ArrayList<PaymentDetailsType>();
        PaymentDetailsType order = new PaymentDetailsType();
        // 消费产品
        List<PaymentDetailsItemType> prods = new ArrayList<PaymentDetailsItemType>();
        PaymentDetailsItemType prod = new PaymentDetailsItemType();
        //商品总价
       // BigDecimal sum = new BigDecimal("0");
        //设置商品情况
        List<OrderDetailVo> detailVos = all.getOrderDetailVos();
        for (OrderDetailVo det : detailVos) {
            prod.setName(det.getProduct().getProductName());// 设置产品名称
            prod.setQuantity(det.getNum());// 设置数量
            BasicAmountType amount = new BasicAmountType(CurrencyCodeType.USD, det.getProduct().getProductPrice() + "");
            prod.setAmount(amount);// 设置价格
           // sum = sum.add(det.getProduct().getProductPrice().multiply(new BigDecimal(det.getNum())));
            prods.add(prod);
        }
        // 设置订单税收
        order.setHandlingTotal(new BasicAmountType(CurrencyCodeType.USD, "0.00"));// 设置手续费
        order.setShippingTotal(new BasicAmountType(CurrencyCodeType.USD, all.getExpressFee() + ""));// 设置运费
        order.setItemTotal(new BasicAmountType(CurrencyCodeType.USD, (all.getTotalFee()-all.getExpressFee()) + ""));// 消费产品的总价
        order.setOrderTotal(new BasicAmountType(CurrencyCodeType.USD, all.getTotalFee() + ""));// 订单的总价
        order.setTaxTotal(new BasicAmountType(CurrencyCodeType.USD, "0.00"));// 设置税收
        order.setInsuranceTotal(new BasicAmountType(CurrencyCodeType.USD, "0.00"));// 设置保险金

        AddressCustom address = addressCustomMapper.queryAddressByKey(all.getAddressId());
        if (null != address) {
            // 设置订单支付地址
            AddressType shipToAddress = new AddressType();
            shipToAddress.setName(address.getName());
            shipToAddress.setStreet1(address.getAddress());
            shipToAddress.setPhone(address.getTelphone());
            order.setShipToAddress(shipToAddress);
            shipToAddress.setPostalCode("510000");
        }
        //设置订单操作
        order.setPaymentAction(PaymentActionCodeType.SALE);
        orders.add(order);
        details.setPaymentDetails(orders);
        details.setAllowNote("1");
        //设置成功返回的路径和取消的路径
        String returnURL = SUCCESS_URL;
        String cancelURL = CANCEL_URL;
        details.setReturnURL(returnURL);
        details.setCancelURL(cancelURL);

        BillingAgreementDetailsType billingAgreement = new BillingAgreementDetailsType(BillingCodeType.NONE);
        billingAgreement.setBillingAgreementDescription("billingAgreement...");
        List<BillingAgreementDetailsType> billList = new ArrayList<BillingAgreementDetailsType>();
        billList.add(billingAgreement);
        details.setBillingAgreementDetails(billList);

        setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(details);
        expressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutReq);
        SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(expressCheckoutReq);
        System.out.println(setExpressCheckoutResponse);
        // 第二步：用token跳转到paypal登录页面----------------------------------
        if (setExpressCheckoutResponse.getAck().toString().equalsIgnoreCase("SUCCESS")) {
            log.info("[setExpressCheckOut] success");

        } else {
            log.info("[setExpressCheckOut] fail");
            throw new Exception();
        }
        return setExpressCheckoutResponse;
    }

    public String doCheckOutExpress(String token) throws Exception {

        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(payPalConfig.getConfig());
        String transactionId="";
        ///////////////////////////////////////////getCheckOutExpress/////////////////////////////////////////////////
        GetExpressCheckoutDetailsReq checkoutDetailsReq = new GetExpressCheckoutDetailsReq();
        // 获取详情请求  // 设置token required
        GetExpressCheckoutDetailsRequestType checkoutDetailsRequestType = new GetExpressCheckoutDetailsRequestType(token);
        checkoutDetailsReq.setGetExpressCheckoutDetailsRequest(checkoutDetailsRequestType);
        //获取详情
        GetExpressCheckoutDetailsResponseType resp = service.getExpressCheckoutDetails(checkoutDetailsReq);
        System.out.println("请求返回的响应：" + resp);
        if (resp != null) {
            if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
            } else {
                String errorMessage = getErrorMessage(resp);
                log.error("GetExpressCheckoutDetails error, token: " + token + ", message: " + errorMessage);
                return transactionId;
            }
        }

        // 付款请求参数
        DoExpressCheckoutPaymentReq checkoutPaymentReq = new DoExpressCheckoutPaymentReq();
        // 付款请求参数
        DoExpressCheckoutPaymentRequestType checkoutPaymentRequestType = new DoExpressCheckoutPaymentRequestType();
        // 付款请求详细参数
        DoExpressCheckoutPaymentRequestDetailsType detailsType = new DoExpressCheckoutPaymentRequestDetailsType();
        // 设置token required
        detailsType.setToken(token);
        // 设置paymentAction 支付方式 required
        detailsType.setPaymentAction(
                resp.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails().get(0).getPaymentAction());
        // 设置payer id Unique PayPal Customer Account required
        detailsType.setPayerID(resp.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID());
        // 将获取的订单信息设置为交易信息  // detailsType.setam // 设置AMT 订单合计金额 required
        detailsType.setPaymentDetails(resp.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails());
        checkoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(detailsType);
        checkoutPaymentReq.setDoExpressCheckoutPaymentRequest(checkoutPaymentRequestType);
        DoExpressCheckoutPaymentResponseType res = service.doExpressCheckoutPayment(checkoutPaymentReq);
        if(res!=null){
            if(res.getAck().toString().equalsIgnoreCase("SUCCESS")){
                transactionId=getTransactionId(res);
                return transactionId;
            }else{
                log.error("doExpressCheckoutDetails error, token: " + token  );
                return transactionId;
            }
        }

        return transactionId;
    }

    /**
     * 获取transactionId
     * @param res DoExpressCheckoutPaymentResponseType结果集
     * @return
     */
    private static String getTransactionId(DoExpressCheckoutPaymentResponseType res){
            String transactionId="";
            if(res!=null){
                DoExpressCheckoutPaymentResponseDetailsType  doExpressCheckoutPaymentResponseDetailsType=res.getDoExpressCheckoutPaymentResponseDetails();
                if(doExpressCheckoutPaymentResponseDetailsType!=null&&doExpressCheckoutPaymentResponseDetailsType.getPaymentInfo()!=null&& doExpressCheckoutPaymentResponseDetailsType.getPaymentInfo().size()!=0){
                    PaymentInfoType paymentInfoType=doExpressCheckoutPaymentResponseDetailsType.getPaymentInfo().get(0);
                    transactionId=paymentInfoType.getTransactionID();
                }
            }
            return transactionId;
    }

    private static String getErrorMessage(GetExpressCheckoutDetailsResponseType resp) {
        if (resp != null && resp.getErrors() != null) {
            StringBuilder sb = new StringBuilder();
            Iterator<?> errors = resp.getErrors().iterator();
            while (errors.hasNext()) {
                ErrorType error = (ErrorType) errors.next();
                sb.append("#").append(error.getErrorCode()).append(" ").append(error.getLongMessage());
            }
            return sb.toString();
        }
        return "";
    }


}
