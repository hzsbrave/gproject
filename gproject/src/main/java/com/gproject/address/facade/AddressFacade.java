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

    /**
     * 为用户添加收货地址
     * 1.若用户不存在收货地址，则新增收货地址，并将地址设置为默认收货地址
     * 2.若存在收货地址，则添加收货地址
     * @param custom
     * @return
     */
    public Object insertAddress(AddressCustom custom);

    public Object deleteAddress(AddressCustom custom);

    public Object queryDefaultAddress(AddressQueryVo vo);

    public Object queryAddressByAddrId(AddressQueryVo vo);
}
