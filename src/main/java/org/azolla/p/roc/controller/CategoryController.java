/*
 * @(#)CategoryController.java		Created at 15/4/30
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.apache.ibatis.session.RowBounds;
import org.azolla.l.ling.collect.Tuple;
import org.azolla.l.ling.util.Date0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.mapper.CategoryMapper;
import org.azolla.p.roc.mapper.PostMapper;
import org.azolla.p.roc.service.ICategoryService;
import org.azolla.p.roc.service.IMapperService;
import org.azolla.p.roc.simditor.Simditor;
import org.azolla.p.roc.vo.CategoryVo;
import org.azolla.p.roc.vo.PostVo;
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
    private CacheAware                 cacheAware;
    @Autowired
    private ICategoryService           iCategoryService;
    @Autowired
    private IMapperService<CategoryVo> iCategoryMapperService;
    @Autowired
    private IMapperService<PostVo>     iPostMapperService;

    @RequestMapping(value = "/admin/category/opt", method = RequestMethod.GET)
    public String opt(Model model)
    {
        model.addAttribute("jsp_title", "New Category");
        model.addAttribute("categoryVo", new CategoryVo());

        return "admin/category/opt";
    }

    @RequestMapping(value = "/admin/category/opt/{id}", method = RequestMethod.GET)
    public String opt(@PathVariable Integer id, Model model)
    {
        model.addAttribute("jsp_title", "Mod Category");
        model.addAttribute("categoryVo", CacheAware.getCategoryVoById(id));

        return "admin/category/opt";
    }

    @RequestMapping(value = "/admin/category/opt", method = RequestMethod.POST)
    public String opt(Integer id, String displayName, Integer parentId, String controllerName, Integer grouped, Integer seq, Integer visible, Integer operable, Integer deleted, Model model)
    {
        String rtnString = "redirect:/admin/category/lst";
        Tuple.Triple<Boolean, String, CategoryVo> serviceResult = iCategoryService.opt(id, displayName, parentId, controllerName, grouped, seq, visible, operable, deleted);
        if (!Tuple.getFirst(serviceResult))
        {
            rtnString = "admin/category/opt";

            model.addAttribute("jsp_title", id == 0 ? "New Category" : "Mod Category");
            model.addAttribute("ctrl_result", Tuple.getSecond(serviceResult));
            model.addAttribute("categoryVo", Tuple.getThird(serviceResult));
        }
        return rtnString;
    }

    @RequestMapping(value = "/admin/category/lst", method = RequestMethod.GET)
    public String lst(Model model)
    {
        return lst(1, model);
    }

    @RequestMapping(value = "/admin/category/lst/{page}", method = RequestMethod.GET)
    public String lst(@PathVariable String page, Model model)
    {
        Integer requestPage = Integer.valueOf(page);

        return lst(requestPage, model);
    }

    private String lst(Integer page, Model model)
    {
        model.addAttribute("categoryVoList", iCategoryMapperService.lst(CategoryMapper.class, new CategoryVo().setVisible(null).setDeleted(null), new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE)))));
        model.addAttribute("current_page", page);
        model.addAttribute("current_request", "admin/category/lst");
        model.addAttribute("jsp_title", "Category List");

        return "admin/category/lst";
    }

    @RequestMapping("/category/{urlName}")
    public String category(@PathVariable String urlName, Model model)
    {
        model.addAttribute("postList", Simditor.more(iPostMapperService.lst(PostMapper.class, new PostVo().setCategoryId(CacheAware.getCategoryVoByUrl(urlName).getId()), new RowBounds(1, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE))))));
        model.addAttribute("current_page", 1);

        setting(urlName, model);

        return "lst";
    }

    @RequestMapping("/category/{urlName}/{page}")
    public String category(@PathVariable String urlName, @PathVariable String page, Model model)
    {
        Integer requestPage = Integer.parseInt(page);

        model.addAttribute("postList", Simditor.more(iPostMapperService.lst(PostMapper.class, new PostVo().setCategoryId(CacheAware.getCategoryVoByUrl(urlName).getId()), new RowBounds(requestPage, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE))))));
        model.addAttribute("current_page", requestPage);

        setting(urlName, model);

        return "lst";
    }

    private void setting(String urlName, Model model)
    {
        CategoryVo categoryVo = CacheAware.getCategoryVoByUrl(urlName);

        model.addAttribute("jsp_title", categoryVo.getDisplayName());
        model.addAttribute("current_request", "category/" + urlName);
    }
}
