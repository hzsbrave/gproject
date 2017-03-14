package com.gproject.base.mapper;

import java.io.Serializable;

/**
 * 
 * Sql映射处理基接口
 * 
 * <p>
 * Sql映射处理基接口：实现单表CURD基本操作,继承此类的子类的sql映射文件必须定义相对应的sql语句
 * </p>
 */
public interface BaseMapper<T, PK extends Serializable> {

	/**
	 * 根据主键删除记录
	 * @param userId
	 * @return
     */
	int deleteByPrimaryKey(PK userId);

	/**
	 * 插入一条数据
	 * @param record
	 * @return
     */
	int insert(T record);

	/**
	 * 插入非空字段的记录
	 * @param record
	 * @return
     */
	int insertSelective(T record);

	/**
	 *根据主键查询记录
	 * @param userId
	 * @return
     */
	T selectByPrimaryKey(PK userId);

	/**
	 *根据主键更新非空记录
	 * @param record
	 * @return
     */
	int updateByPrimaryKeySelective(T record);

	/**
	 *根据主键更新记录
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T record);

}
