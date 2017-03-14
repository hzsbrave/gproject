/**<p>项目名：</p>
 * <p>包名：	com.zttx.redis.core</p>
 * <p>文件名：RedisNoResultCall.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015年1月14日-下午2:36:11</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package com.gproject.util.redis.callback;

import redis.clients.jedis.ShardedJedis;

/**
 * <p>
 * 名称：RedisNoResultCall.java
 * </p>
 */
public abstract class RedisNoResultCall implements RedisCallback<Object> {

	public Object doInRedis(ShardedJedis shardedJedis) throws Exception {
		action(shardedJedis);
		return null;
	}

	public abstract void action(ShardedJedis shardedJedis);

}
