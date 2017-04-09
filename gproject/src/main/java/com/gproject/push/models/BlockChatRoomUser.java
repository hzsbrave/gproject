package com.gproject.push.models;


import com.gproject.push.util.GsonUtil;

/**
 * 聊天室被封禁用户信息。
 */
public class BlockChatRoomUser {
	// 聊天室用户Id。
	String id;
	// 加入聊天室时间。
	String time;
	
	public BlockChatRoomUser(String id, String time) {
		this.id = id;
		this.time = time;
	}
	
	/**
	 * 设置id
	 *
	 */	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取id
	 *
	 * @return String
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 设置time
	 *
	 */	
	public void setTime(String time) {
		this.time = time;
	}
	
	/**
	 * 获取time
	 *
	 * @return String
	 */
	public String getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, BlockChatRoomUser.class);
	}
}
