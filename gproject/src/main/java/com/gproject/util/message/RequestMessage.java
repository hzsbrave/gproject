package com.gproject.util.message;

import java.io.Serializable;

/**
 * Created by qiong on 2016/3/9.
 */
public class RequestMessage<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 请求上下文 */
	//@NotNull
	private T requestContext;

	



	public T getRequestContext() {
		return requestContext;
	}

	public void setRequestContext(T requestContext) {
		this.requestContext = requestContext;
	}

	/**
	 * 请求合法性校验
	 */
	public void check() {
		// gatewayRequest.check();
	}

	@Override
	public String toString() {
		return "RequestMessage [ requestContext=" + requestContext + "]";
	}

	
}
