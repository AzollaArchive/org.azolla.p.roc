/*
 * @(#)CategoryController.java		Created at 15/4/30
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.azolla.p.roc.service.ICategoryService;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/category/{categoryUrlName}")
    public String category(@PathVariable String categoryUrlName, Model model)
    {
        model.addAttribute("postLst", iPostService.lstByCategoryUrlName(categoryUrlName, 1));
        model.addAttribute("current_page",1);

        setting(categoryUrlName, model);

        return "lst";
    }

    @RequestMapping("/category/{categoryUrlName}/{page}")
    public String category(@PathVariable String categoryUrlName, @PathVariable String page, Model model)
    {
        int requestPage = Integer.parseInt(page);

        model.addAttribute("postLst",iPostService.lstByCategoryUrlName(categoryUrlName, requestPage));
        model.addAttribute("current_page", requestPage);

        setting(categoryUrlName, model);

        return "lst";
    }

    private void setting(String categoryUrlName, Model model)
    {
        CategoryVo categoryVo = iCategoryService.getByUrlName(categoryUrlName);

        model.addAttribute("sidebar_title",categoryVo.getDisplayName());
        model.addAttribute("current_request","category/"+categoryUrlName);
    }
}
