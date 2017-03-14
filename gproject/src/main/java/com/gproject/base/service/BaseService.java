package com.gproject.base.service;


import com.gproject.base.mapper.BaseMapper;
import com.gproject.util.message.ResponseMessage;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class BaseService<T, PK extends Serializable> {

	private Integer maxCount = 200; // 分页每页查询的最大条数

	/**
	 * 获取 泛型实际类型
	 * 
	 * @param index
	 * @return
	 */
	public Class getGenericType(int index) {
		Type genType = getClass().getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index outof bounds");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	protected ResponseMessage SUCCESS() {
		return new ResponseMessage();
	}

	protected ResponseMessage SUCCESS(Object result) {
		return new ResponseMessage( result);
	}
	
    protected ResponseMessage FAIL(int code,String message){
        return new ResponseMessage(code,message,null,false);
    }

	/**
	 * 子类实现此抽象方法，获得业务处理子类的sql映射接口
	 * 
	 * @return
	 */
	protected abstract BaseMapper<T, PK> getMapper();

	/**
	 * 子类实现批抽象方法，获得业务处理子类的sql映射文件namespace
	 * 
	 * @return
	 */
	protected abstract String getMapperNameSpace();

	/**
	 * 子类实现批抽象方法，获得业务层默认操作数据库名称，主要是针对于insert,update,delete，主从同步问题
	 * 
	 * @return
	 */
	protected abstract String getDefalultDatabase();

	@Autowired
	private SqlSessionFactory sqlSessionFactory;


	/**
	 * 根据主键删除记录
	 * @param userId
	 * @return
	 */
	int deleteByPrimaryKey(PK userId){
		return getMapper().deleteByPrimaryKey(userId);
	};

	/**
	 * 插入一条数据
	 * @param record
	 * @return
	 */
	int insert(T record){
		return getMapper().insert(record);
	}

	/**
	 * 插入非空字段的记录
	 * @param record
	 * @return
	 */
	int insertSelective(T record){
		return getMapper().insertSelective(record);
	}

	/**
	 *根据主键查询记录
	 * @param userId
	 * @return
	 */
	T selectByPrimaryKey(PK userId){
		return getMapper().selectByPrimaryKey(userId);
	}

	/**
	 *根据主键更新非空记录
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record){
		return getMapper().updateByPrimaryKeySelective(record);
	}

	/**
	 *根据主键更新记录
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T record){
		return  getMapper().updateByPrimaryKey(record);
	}
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

}
