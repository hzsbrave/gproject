package com.gproject.address.mapper;

import com.gproject.address.pojo.Address;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer addrId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addrId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
}