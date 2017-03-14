package com.gproject.base.pojo;

import java.io.Serializable;

public class BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;
	public int currentPage;
	public int pageSize;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
