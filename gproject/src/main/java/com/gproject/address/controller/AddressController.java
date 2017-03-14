package com.gproject.address.controller;

import com.gproject.address.facade.AddressFacade;
import com.gproject.address.pojo.AddressCustom;
import com.gproject.address.pojo.vo.AddressQueryVo;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/3/5.
 */
@Controller
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressFacade addressFacade;

    @ResponseBody
    @RequestMapping(value = "queryAddress",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryAddress(@RequestBody RequestMessage<AddressQueryVo> context){
        AddressQueryVo vo=context.getRequestContext();
        Object obj=addressFacade.queryAddress(vo);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "queryAddressByAddrId",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryAddressByAddrId(@RequestBody RequestMessage<AddressQueryVo> context){
        AddressQueryVo vo=context.getRequestContext();
        Object obj=addressFacade.queryAddressByAddrId(vo);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "queryDefaultAddress",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryDefaultAddress(@RequestBody RequestMessage<AddressQueryVo> context){
        AddressQueryVo vo=context.getRequestContext();
        Object obj=addressFacade.queryDefaultAddress(vo);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "insertAddress",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object insertAddress(@RequestBody RequestMessage<AddressCustom> context){
        AddressCustom vo=context.getRequestContext();
        Object obj=addressFacade.insertAddress(vo);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "updateDefaultAddress",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object updateAddress(@RequestBody RequestMessage<AddressCustom> context){
        AddressCustom vo=context.getRequestContext();
        Object obj=addressFacade.updateAddressDefault(vo);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "updateAddressByAddrId",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object updateAddressByAddrId(@RequestBody RequestMessage<AddressCustom> context){
        AddressCustom vo=context.getRequestContext();
        Object obj=addressFacade.updateAddressByAddrId(vo);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "deleteAddress",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object deleteAddress(@RequestBody RequestMessage<AddressCustom> context){
        AddressCustom vo=context.getRequestContext();
        Object obj=addressFacade.deleteAddress(vo);
        return obj;
    }

}
