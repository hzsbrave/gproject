package com.gproject.paypal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gproject.paypal.util.DateUtil;
import com.gproject.paypal.util.PayPalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.ResponseBody;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsReq;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.RefundTransactionReq;
import urn.ebay.api.PayPalAPI.RefundTransactionRequestType;
import urn.ebay.api.PayPalAPI.RefundTransactionResponseType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.api.PayPalAPI.TransactionSearchReq;
import urn.ebay.api.PayPalAPI.TransactionSearchRequestType;
import urn.ebay.api.PayPalAPI.TransactionSearchResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AddressType;
import urn.ebay.apis.eBLBaseComponents.BillingAgreementDetailsType;
import urn.ebay.apis.eBLBaseComponents.BillingCodeType;
import urn.ebay.apis.eBLBaseComponents.CountryCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.GetExpressCheckoutDetailsResponseDetailsType;
import urn.ebay.apis.eBLBaseComponents.ItemCategoryType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentInfoType;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionSearchResultType;
import urn.ebay.apis.eBLBaseComponents.RefundType;
import urn.ebay.apis.eBLBaseComponents.SetAccessPermissionsRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

@Controller
@RequestMapping("paypal")
public class PayPalSDKController {

	@Autowired
	private PayPalConfig payPalConfig;

	@RequestMapping("/setCheckOutExpress")
	public void setCheckOutExpress(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

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
		// 产品1
		prod.setName("iphone 6s");// 设置产品名称
		prod.setQuantity(Integer.parseInt("2"));// 设置数量
		BasicAmountType amount = new BasicAmountType(CurrencyCodeType.HKD, "10");
		prod.setAmount(amount);// 设置价格
		prod.setDescription("iphone 6s High");// 设置描述
		prod.setItemCategory(ItemCategoryType.fromValue("Physical"));// 设置产品类别
		prods.add(prod);
		// 产品2
		PaymentDetailsItemType prod2 = new PaymentDetailsItemType();
		prod2.setName("xiao mi mix");// 设置产品名称
		prod2.setQuantity(Integer.parseInt("1"));// 设置数量
		BasicAmountType amount2 = new BasicAmountType(CurrencyCodeType.HKD, "15");
		prod2.setAmount(amount2);// 设置价格
		prod2.setDescription("xiao mi mix High");// 设置描述
		prod2.setItemCategory(ItemCategoryType.fromValue("Physical"));// 设置产品类别
		prods.add(prod2);

		order.setPaymentDetailsItem(prods);// 设置订单产品
		// 设置订单税收
		order.setHandlingTotal(new BasicAmountType(CurrencyCodeType.HKD, "1.00"));// 设置手续费
		order.setShippingTotal(new BasicAmountType(CurrencyCodeType.HKD, "1.00"));// 设置运费
		order.setTaxTotal(new BasicAmountType(CurrencyCodeType.HKD, "1.00"));// 设置税收
		order.setInsuranceTotal(new BasicAmountType(CurrencyCodeType.HKD, "1.00"));// 设置保险金
		order.setItemTotal(new BasicAmountType(CurrencyCodeType.HKD, "35.00"));// 消费产品的总价
		order.setOrderTotal(new BasicAmountType(CurrencyCodeType.HKD, "39.00"));// 订单的总价

		// 设置订单支付地址
		AddressType shipToAddress = new AddressType();
		shipToAddress.setName("小雏菊");
		shipToAddress.setStreet1("天河区揽月路8号");
		shipToAddress.setStreet2("萝岗区开源大道企业加速器");
		shipToAddress.setCityName("广州市");
		shipToAddress.setStateOrProvince("广东省");
		shipToAddress.setPostalCode("510000");
		shipToAddress.setCountry(CountryCodeType.BJ);
		order.setShipToAddress(shipToAddress);

		order.setPaymentAction(PaymentActionCodeType.SALE);

		orders.add(order);
		details.setPaymentDetails(orders);
		details.setAllowNote("1");

		String returnURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/getCheckOutExpress.action";
		String cancelURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/cancel";
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
			session.setAttribute("lastReq", service.getLastRequest());
			session.setAttribute("lastResp", service.getLastResponse());
			Map<Object, Object> map = new LinkedHashMap<Object, Object>();
			map.put("当前操作：", "初始交易成功，是否登录paypal?");
			map.put("Ack", setExpressCheckoutResponse.getAck());
			map.put("Token", setExpressCheckoutResponse.getToken());
			map.put("Redirect URL", "<a href=https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
					+ setExpressCheckoutResponse.getToken() + ">登录paypal</a>");
			session.setAttribute("map", map);
			response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/Response.jsp");
		} else {
			session.setAttribute("Error", setExpressCheckoutResponse.getErrors());
			response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/Error.jsp");
		}

	}


	@RequestMapping("/getCheckOutExpress")
	public void getCheckOutExpress(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(payPalConfig.getConfig());
		// get请求参数
		GetExpressCheckoutDetailsReq checkoutDetailsReq = new GetExpressCheckoutDetailsReq();
		// get请求参数 // 设置token required
		GetExpressCheckoutDetailsRequestType checkoutDetailsRequestType = new GetExpressCheckoutDetailsRequestType(
				request.getParameter("token"));

		checkoutDetailsReq.setGetExpressCheckoutDetailsRequest(checkoutDetailsRequestType);

		GetExpressCheckoutDetailsResponseType resp = service.getExpressCheckoutDetails(checkoutDetailsReq);

		System.out.println("请求返回的响应：" + resp);

		if (resp != null) {
			session.setAttribute("lastReq", service.getLastRequest());
			session.setAttribute("lastResp", service.getLastResponse());
			if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
				Map map = new HashMap<Object,Object>();
				map.put("Ack", resp.getAck());
				map.put("Token", resp.getGetExpressCheckoutDetailsResponseDetails().getToken());
				// Unique PayPal Customer Account identification number.
				map.put("Payer ID", resp.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID());
				map.put("Redirect URL",
						"<a href=" + request.getScheme() + "://" + request.getServerName() + ":"
								+ request.getServerPort() + request.getContextPath()
								+ "/doCheckOutExpress.action>DoExpressCheckoutPayment确认订单</a>");
				session.setAttribute("map", map);
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath() + "/Response.jsp");
			}

		} else {
			session.setAttribute("Error", resp.getErrors());
			response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/Error.jsp");

		}

	}

	// 确认订单
	@RequestMapping("/doCheckOutExpress")
	public void doCheckOutExpress(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(payPalConfig.getConfig());
		HttpSession session = request.getSession();
		Map map = (Map) session.getAttribute("map");
		// 从session中获取token
		String token = (String) map.get("Token");

		// get请求参数
		GetExpressCheckoutDetailsReq checkoutDetailsReq = new GetExpressCheckoutDetailsReq();
		// get请求参数 // 设置token required
		GetExpressCheckoutDetailsRequestType checkoutDetailsRequestType = new GetExpressCheckoutDetailsRequestType(
				token);
		checkoutDetailsReq.setGetExpressCheckoutDetailsRequest(checkoutDetailsRequestType);
		GetExpressCheckoutDetailsResponseType resp = service.getExpressCheckoutDetails(checkoutDetailsReq);
		System.out.println("do get:" + resp.getAck().toString());

		if (resp != null && resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
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
			// 设置AMT 订单合计金额 required
			// detailsType.setam
			// 将获取的订单信息设置为交易信息
			detailsType.setPaymentDetails(resp.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails());

			checkoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(detailsType);
			checkoutPaymentReq.setDoExpressCheckoutPaymentRequest(checkoutPaymentRequestType);

			DoExpressCheckoutPaymentResponseType res = service.doExpressCheckoutPayment(checkoutPaymentReq);
			if (res != null) {
				session.setAttribute("lastReq", service.getLastRequest());
				session.setAttribute("lastResp", service.getLastResponse());
				if (res.getAck().toString().equalsIgnoreCase("SUCCESS")) {
					System.out.println("do get:" + res);
					Map resMap = new HashMap<Object,Object>();
					resMap.put("Ack", resp.getAck());
					Iterator<PaymentInfoType> iterator = res.getDoExpressCheckoutPaymentResponseDetails()
							.getPaymentInfo().iterator();
					int index = 1;
					while (iterator.hasNext()) {
						PaymentInfoType result = (PaymentInfoType) iterator.next();
						resMap.put("Transaction ID" + index, result.getTransactionID());
						index++;
					}
					resMap.put("Billing Agreement ID",
							res.getDoExpressCheckoutPaymentResponseDetails().getBillingAgreementID());
					session.setAttribute("map", resMap);
					response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + request.getContextPath() + "/Response.jsp");
				}

			} else {
				session.setAttribute("Error", resp.getErrors());
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath() + "/Error.jsp");

			}

		}

	}

	// 全额退款
	@RequestMapping("/refundTransation")
	public void refundTransation(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(payPalConfig.getConfig());

		// 退款请求参数
		RefundTransactionReq refundTransactionReq = new RefundTransactionReq();
		// 退款详情
		RefundTransactionRequestType refundTransactionRequestType = new RefundTransactionRequestType();
		// 设置transactionID required
		refundTransactionRequestType.setTransactionID("0S704319AV741031W");
		// 设置 退款模式 FULL--全额 Partical--部分 需要 设置部分退款金额 required
		refundTransactionRequestType.setRefundType(RefundType.FULL);

		refundTransactionReq.setRefundTransactionRequest(refundTransactionRequestType);
		// 申请退款
		RefundTransactionResponseType resp = service.refundTransaction(refundTransactionReq);

		if (resp != null) {
			session.setAttribute("lastReq", service.getLastRequest());
			session.setAttribute("lastResp", service.getLastResponse());
			if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
				Map<Object, Object> map = new LinkedHashMap<Object, Object>();
				map.put("当前操作：", "全额退款成功");
				map.put("Ack", resp.getAck());
				map.put("退款金额", resp.getTotalRefundedAmount());
				map.put("Transaction ID", "");

				session.setAttribute("map", map);
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath() + "/Response.jsp");
			} else {

				session.setAttribute("Error", resp.getErrors());
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath() + "/Error.jsp");
			}
		}

	}

	// 部分退款
	@RequestMapping("/refundTransationPARTIAL")
	public void refundTransationPARTIAL(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(payPalConfig.getConfig());

		// 退款请求参数
		RefundTransactionReq refundTransactionReq = new RefundTransactionReq();
		// 退款详情
		RefundTransactionRequestType refundTransactionRequestType = new RefundTransactionRequestType();
		// 设置transactionID required
		refundTransactionRequestType.setTransactionID("1SE67640UU310061B");
		// 设置 退款模式 FULL--全额 Partical--部分 需要 设置部分退款金额 required
		refundTransactionRequestType.setRefundType(RefundType.PARTIAL);

		BasicAmountType amount = new BasicAmountType(CurrencyCodeType.HKD, "10.0");
		refundTransactionRequestType.setAmount(amount);
		System.out.println("部分退款。。。。。");
		refundTransactionReq.setRefundTransactionRequest(refundTransactionRequestType);
		// 申请退款
		RefundTransactionResponseType resp = service.refundTransaction(refundTransactionReq);

		if (resp != null) {
			session.setAttribute("lastReq", service.getLastRequest());
			session.setAttribute("lastResp", service.getLastResponse());
			if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
				Map<Object, Object> map = new LinkedHashMap<Object, Object>();
				map.put("当前操作：", "部分退款成功");
				map.put("Ack", resp.getAck());
				map.put("退款金额", resp.getTotalRefundedAmount());
				map.put("Transaction ID", "");

				session.setAttribute("map", map);
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath() + "/Response.jsp");
			} else {

				session.setAttribute("Error", resp.getErrors());
				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath() + "/Error.jsp");
			}
		}

	}

	@RequestMapping("/searchTrasaction")
	public void searchTrasaction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(payPalConfig.getConfig());
		// 查询订单请求
		TransactionSearchReq transactionSearchReq = new TransactionSearchReq();
		// 查询订单详情请求
		TransactionSearchRequestType transactionSearchRequestType = new TransactionSearchRequestType();
		// 设置查询开始日期，required The value must be in UTC/GMT
		if (request.getParameter("startDate") != null && !"".equals(request.getParameter("startDate"))) {
			Date twoDaysAgo = DateUtil.getAddDay(new Date(), -30);
			// Date Simple: 2015-01-25T00:00:00+0530
			String dateStr = DateUtil.convertDateToString(twoDaysAgo, "yyyy-MM-dd") + "T00:00:00+0530";
			transactionSearchRequestType.setStartDate(dateStr);
		}
		// 设置查询结束日期，optional
		if (request.getParameter("endDate") != null && !"".equals(request.getParameter("endDate"))) {
			transactionSearchRequestType.setStartDate(request.getParameter("endDate"));
		}
		// 设置查询交易id，optional
		if (request.getParameter("TranditonId") != null && !"".equals(request.getParameter("TranditonId"))) {
			transactionSearchRequestType.setStartDate(request.getParameter("TranditonId"));
		}
		// 设置查询email，optional
		if (request.getParameter("email") != null && !"".equals(request.getParameter("email"))) {
			transactionSearchRequestType.setStartDate(request.getParameter("email"));
		}

		transactionSearchReq.setTransactionSearchRequest(transactionSearchRequestType);

		TransactionSearchResponseType transactionSearchResponseType = service.transactionSearch(transactionSearchReq);
		System.out.println("kk:"+transactionSearchResponseType.getAck().toString());
		if (transactionSearchResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) {
			session.setAttribute("lastReq", service.getLastRequest());
			session.setAttribute("lastResp", service.getLastResponse());
			if (transactionSearchResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) {
				if (transactionSearchResponseType.getPaymentTransactions().size() > 0) {
					Map<Object, Object> map = new LinkedHashMap<Object, Object>();
					map.put("Ack", transactionSearchResponseType.getAck());
					Iterator<PaymentTransactionSearchResultType> iterator = transactionSearchResponseType
							.getPaymentTransactions().iterator();
					int index = 1;
					while (iterator.hasNext()) {
						PaymentTransactionSearchResultType result = (PaymentTransactionSearchResultType) iterator
								.next();

						map.put("Transaction ID" + index, result.getTransactionID());

						if (result.getNetAmount() != null) {
							map.put("Net Amount" + index,
									result.getNetAmount().getValue() + " " + result.getNetAmount().getCurrencyID());
						}

						map.put("Payer" + index, result.getPayer());

						map.put("Status" + index, result.getStatus());
						index++;
					}

					session.setAttribute("map", map);
					response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + request.getContextPath() + "/Response.jsp");
				} else {

					session.setAttribute("Error", transactionSearchResponseType.getErrors());
					response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + request.getContextPath() + "/Error.jsp");
				}
			}

		}
	}

}
