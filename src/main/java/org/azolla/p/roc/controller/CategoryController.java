/*
 * @(#)CategoryController.java		Created at 15/4/30
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.apache.ibatis.session.RowBounds;
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
        model.addAttribute("categoryVoList", iCategoryDao.fullLstWithoutVOD(new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_CONFIG_KEY_POSTSIZE)))));
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

        model.addAttribute("sidebar_title",categoryVo.getDisplayName());
        model.addAttribute("current_request","category/"+categoryUrlName);
    }
}
