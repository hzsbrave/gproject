package com.gproject.address.service;

import com.gproject.address.facade.AddressFacade;
import com.gproject.address.mapper.AddressCustomMapper;
import com.gproject.address.pojo.AddressCustom;
import com.gproject.address.pojo.vo.AddressQueryVo;
import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/5.
 */
@Service
public class AddressService extends BaseService<AddressCustom, Integer> implements AddressFacade {

    @Autowired
    private AddressCustomMapper addressCustomMapper;

    @Override
    public Object queryAddress(AddressQueryVo vo) {
        if (null == vo)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        List<AddressCustom> lists = addressCustomMapper.queryAddress(vo.getUserId());
        return SUCCESS(lists);
    }

    @Override
    public Object updateAddressDefault(AddressCustom custom) {
        if (null == custom)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        AddressCustom cus = addressCustomMapper.queryAddressByDefault(custom.getUserId());
        if (null == cus)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        //记录为默认收货地址的地址id
        int prex = cus.getAddrId();
        if(prex==custom.getAddrId())
            return SUCCESS();
        AddressCustom prexcus = new AddressCustom();
        prexcus.setAddrId(prex);
        prexcus.setStatus(0);
        prexcus.setDeleted(0);
        //将之前的默认地址设为未用地址
        addressCustomMapper.updateAddressByAddrId(prexcus);
        //设置新的默认地址
        custom.setStatus(2);
        custom.setDeleted(0);
        addressCustomMapper.updateAddressByAddrId(custom);
        return SUCCESS();
    }

    @Override
    public Object updateAddressByAddrId(AddressCustom custom) {
        if (null == custom)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        AddressCustom  cus=addressCustomMapper.queryAddressByKey(custom.getAddrId());
        if(null==cus)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        addressCustomMapper.updateAddressByAddrId(custom);
        return SUCCESS();
    }

    @Override
    public Object insertAddress(AddressCustom custom) {
        if (null == custom)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        List<AddressCustom> lists = addressCustomMapper.queryAddress(custom.getUserId());
        //没有地址信息，設置第一條地址信息為默認收貨地址
        if (null == lists || 0 == lists.size()) {
            //status: 0--未使用 1--使用中  2--默认
            custom.setStatus(2);
        } else
            custom.setStatus(0);
        custom.setDeleted(0);
        addressCustomMapper.insertAddress(custom);
        return SUCCESS();
    }

    @Override
    public Object deleteAddress(AddressCustom custom) {
        if (null == custom)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        AddressCustom query = addressCustomMapper.queryAddressByKey(custom.getAddrId());
        if (null == query)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        //若删除的是默认地址
        if (2 == query.getStatus()) {
            query.setDeleted(1);
            query.setStatus(0);
            addressCustomMapper.updateAddressByAddrId(query);
            List<AddressCustom> lists = addressCustomMapper.queryAddress(custom.getUserId());
            //若其存在其他收货地址，设置第一条地址信息为默认地址
            if (null != lists && 0 != lists.size()) {
                AddressCustom first = lists.get(0);
                first.setStatus(2);
                addressCustomMapper.updateAddressByAddrId(first);
            }
            return SUCCESS();
        }
        //删除不是默认地址
        query.setDeleted(1);
        addressCustomMapper.updateAddressByAddrId(query);
        return SUCCESS();
    }

    @Override
    public Object queryDefaultAddress(AddressQueryVo vo) {
        if (null == vo)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        AddressCustom custom = addressCustomMapper.queryAddressByDefault(vo.getUserId());
        if (null == custom)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        return SUCCESS(custom);
    }

    @Override
    public Object queryAddressByAddrId(AddressQueryVo vo) {
        if (null == vo)
            return FAIL(ResponseType.PARAMETER_NULL, "vo is null");
        AddressCustom cus=addressCustomMapper.queryAddressByKey(vo.getAddrId());
        return SUCCESS(cus);
    }


    @Override
    protected BaseMapper<AddressCustom, Integer> getMapper() {
        return null;
    }

    @Override
    protected String getMapperNameSpace() {
        return null;
    }

    @Override
    protected String getDefalultDatabase() {
        return null;
    }
}
