/*
 * @(#)CategoryController.java		Created at 15/4/30
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.apache.ibatis.session.RowBounds;
import org.azolla.l.ling.collect.Tuple;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.ICategoryDao;
import org.azolla.p.roc.service.ICategoryService;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Controller
public class CategoryController
{
    @Autowired
    private IPostService iPostService;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private ICategoryDao iCategoryDao;

    @RequestMapping(value="/admin/category/opt",method= RequestMethod.GET)
    public String opt(Model model)
    {
        model.addAttribute("jsp_title","New Category");
        model.addAttribute("categoryVo", new CategoryVo());

        return "admin/category/opt";
    }

    @RequestMapping(value="/admin/category/opt/{id}",method= RequestMethod.GET)
    public String opt(@PathVariable int id, Model model)
    {
        model.addAttribute("jsp_title","Mod Category");
        model.addAttribute("categoryVo", iCategoryDao.getById(id));

        return "admin/category/opt";
    }

    @RequestMapping(value="/admin/category/opt",method= RequestMethod.POST)
    public String opt(int id, String displayName, int parentId, String controllerName, Integer group, int sequence, Integer visible, Integer operable, Model model)
    {
        String rtnString = "redirect:/admin/category/lst";
        Tuple.Triple<Boolean,String,CategoryVo> serviceResult = iCategoryService.opt(id,displayName,parentId,controllerName,group,sequence,visible,operable);
        if(!Tuple.getFirst(serviceResult))
        {
            rtnString = "admin/category/opt";

            model.addAttribute("jsp_title",id == 0 ? "New Category" : "Mod Category");
            model.addAttribute("ctrl_result",Tuple.getSecond(serviceResult));
            model.addAttribute("categoryVo",Tuple.getThird(serviceResult));
        }
        return rtnString;
    }

    @RequestMapping(value="/admin/category/rmv/{id}",method= RequestMethod.GET)
    public String rmv(@PathVariable int id, Model model)
    {
        iCategoryDao.rmvById(id);
        return "redirect:/admin/category/lst";
    }

    @RequestMapping(value="/admin/category/lst",method= RequestMethod.GET)
    public String lst(Model model)
    {
        return lst(1, model);
    }

    @RequestMapping(value="/admin/category/lst/{page}",method= RequestMethod.GET)
    public String lst(@PathVariable String page,Model model)
    {
        int requestPage = Integer.valueOf(page);

        return lst(requestPage, model);
    }

    private String lst(int page, Model model)
    {
        model.addAttribute("categoryVoList", iCategoryDao.fullLstWithoutVOD(new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE)))));
        model.addAttribute("current_page", page);
        model.addAttribute("current_request", "admin/category/lst");
        model.addAttribute("jsp_title","Category List");

        return "admin/category/lst";
    }

    @RequestMapping("/category/{categoryUrlName}")
    public String category(@PathVariable String categoryUrlName, Model model)
    {
        model.addAttribute("postList", iPostService.lstByCategoryUrlName(categoryUrlName, 1));
        model.addAttribute("current_page",1);

        setting(categoryUrlName, model);

        return "lst";
    }

    @RequestMapping("/category/{categoryUrlName}/{page}")
    public String category(@PathVariable String categoryUrlName, @PathVariable String page, Model model)
    {
        int requestPage = Integer.parseInt(page);

        model.addAttribute("postList",iPostService.lstByCategoryUrlName(categoryUrlName, requestPage));
        model.addAttribute("current_page", requestPage);

        setting(categoryUrlName, model);

        return "lst";
    }

    private void setting(String categoryUrlName, Model model)
    {
        CategoryVo categoryVo = iCategoryDao.getByUrlName(categoryUrlName);

        model.addAttribute("jsp_title",categoryVo.getDisplayName());
        model.addAttribute("current_request","category/"+categoryUrlName);
    }
}
