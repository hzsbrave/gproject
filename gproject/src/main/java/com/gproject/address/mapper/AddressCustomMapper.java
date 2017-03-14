package com.gproject.address.mapper;

import com.gproject.address.pojo.Address;
import com.gproject.address.pojo.AddressCustom;
import com.gproject.address.pojo.vo.AddressQueryVo;

import java.util.List;

public interface AddressCustomMapper {

    public List<AddressCustom> queryAddress(int userId);

    public void updateAddressByAddrId(AddressCustom custom);

    public void insertAddress(AddressCustom custom);

    public AddressCustom queryAddressByDefault(int userId);

    public AddressCustom queryAddressByKey(int addrId);

}