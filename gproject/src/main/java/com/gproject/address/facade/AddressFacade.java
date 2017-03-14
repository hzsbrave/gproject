package com.gproject.address.facade;

import com.gproject.address.pojo.Address;
import com.gproject.address.pojo.AddressCustom;
import com.gproject.address.pojo.vo.AddressQueryVo;

/**
 * Created by Administrator on 2017/3/5.
 */
public interface AddressFacade {

    public Object queryAddress(AddressQueryVo vo);

    public Object updateAddressDefault(AddressCustom custom);

    public Object updateAddressByAddrId(AddressCustom custom);

    public Object insertAddress(AddressCustom custom);

    public Object deleteAddress(AddressCustom custom);

    public Object queryDefaultAddress(AddressQueryVo vo);

    public Object queryAddressByAddrId(AddressQueryVo vo);
}
