package com.gproject.paypal.service;

import com.gproject.address.facade.AddressFacade;
import com.gproject.address.mapper.AddressCustomMapper;
import com.gproject.address.pojo.Address;
import com.gproject.address.pojo.AddressCustom;
import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.order.pojo.vo.OrderInsertVo;
import com.gproject.order.pojo.vo.OrderProduct;
import com.gproject.paypal.facade.PayPalFacade;
import com.gproject.paypal.util.PayPalConfig;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service
public class PayPalService extends BaseService<Object,Integer> implements PayPalFacade {

    @Autowired
    private PayPalConfig payPalConfig;
    @Autowired
    private AddressCustomMapper addressCustomMapper;

    @Override
    public Object setExpressCheckout(OrderInsertVo vo) throws Exception {
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
        BigDecimal sum=new BigDecimal(0);
        //设置商品情况
        List<OrderProduct> products=vo.getProds();
        for (OrderProduct product: products) {
            prod.setName(product.getProductName());// 设置产品名称
            prod.setQuantity(product.getNum());// 设置数量
            BasicAmountType amount = new BasicAmountType(CurrencyCodeType.USD,product.getProductPrice()+"");
            prod.setAmount(amount);// 设置价格
            sum=sum.add(product.getProductPrice().multiply(new BigDecimal(product.getNum())));
            prods.add(prod);
        }
        // 设置订单税收
        order.setHandlingTotal(new BasicAmountType(CurrencyCodeType.USD, "0.00"));// 设置手续费
        order.setShippingTotal(new BasicAmountType(CurrencyCodeType.USD,vo.getExpressFee()+""));// 设置运费
        order.setItemTotal(new BasicAmountType(CurrencyCodeType.USD,sum+""));// 消费产品的总价
        order.setOrderTotal(new BasicAmountType(CurrencyCodeType.USD, vo.getTotalFee()+""));// 订单的总价
        order.setTaxTotal(new BasicAmountType(CurrencyCodeType.USD, "0.00"));// 设置税收
        order.setInsuranceTotal(new BasicAmountType(CurrencyCodeType.USD, "0.00"));// 设置保险金

        AddressCustom address=addressCustomMapper.queryAddressByKey(vo.getAddressId());
        if(null!=address){
            // 设置订单支付地址
            AddressType shipToAddress = new AddressType();
            shipToAddress.setName(address.getName());
            shipToAddress.setStreet1(address.getAddress());
            shipToAddress.setPhone(address.getTelphone());
            order.setShipToAddress(shipToAddress);
            shipToAddress.setStreet1("天河区揽月路8号");
            shipToAddress.setCityName("广州市");
            shipToAddress.setStateOrProvince("广东省");
            shipToAddress.setPostalCode("510000");
            shipToAddress.setCountry(CountryCodeType.BJ);
        }
        //设置订单操作
        order.setPaymentAction(PaymentActionCodeType.SALE);
        orders.add(order);
        details.setPaymentDetails(orders);
        details.setAllowNote("1");
        //设置成功返回的路径和取消的路径
        String returnURL = "http://10.10.28.242:9000/pic/image/html/success.html";
        String cancelURL = "http://10.10.28.242:9000/pic/image/html/cancel.html";
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
            System.out.println(setExpressCheckoutResponse);
            return SUCCESS(setExpressCheckoutResponse);
        } else {
            throw new  Exception();
        }
    }

    @Override
    protected BaseMapper<Object, Integer> getMapper() {
        return null;
    }

    @Override
    protected String getMapperNameSpace() {
        return null;
    }

    @Override
    protected String getDefalultDatabase() {
        return null;
    }


}
