package com.gproject.favorite.controller;

import com.gproject.category.pojo.vo.CategoryExample;
import com.gproject.favorite.facade.FavoriteFacade;
import com.gproject.favorite.pojo.FavoriteCustom;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/4/6.
 */
@Controller
@RequestMapping("favorite")
public class FavoriteController {

    @Autowired
    private FavoriteFacade favoriteFacade;

    @ResponseBody
    @RequestMapping(value = "insertFavorite", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object insertFavorite(@RequestBody RequestMessage<FavoriteCustom> context) {
        FavoriteCustom example=context.getRequestContext();
        return favoriteFacade.insertFavorite(example);
    }

    @ResponseBody
    @RequestMapping(value = "queryFavoriteExist", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryFavoriteExist(@RequestBody RequestMessage<FavoriteCustom> context) {
        FavoriteCustom example=context.getRequestContext();
        return favoriteFacade.queryFavoriteByProductIdAndUserId(example);
    }

    @ResponseBody
    @RequestMapping(value = "queryUserFavorite", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryUserFavorite(@RequestBody RequestMessage<FavoriteCustom> context) {
        FavoriteCustom example=context.getRequestContext();
        return favoriteFacade.queryFavoriteByUserId(example.getUserId());
    }
}
