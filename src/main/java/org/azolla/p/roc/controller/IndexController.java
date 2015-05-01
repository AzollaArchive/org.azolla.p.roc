/*
 * @(#)CommonController.java		Created at 15/4/18
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.azolla.l.ling.util.Date0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.service.ICategoryService;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Controller
public class IndexController
{
    @Autowired
    private IPostService iPostService;

    @Autowired
    private CacheAware cacheAware;

    @RequestMapping({"/", "/index"})
    public String index(Model model)
    {
        model.addAttribute("postLst",iPostService.lst(1));
        model.addAttribute("current_page",1);

        indexSetting(model);

        return "lst";
    }

    @RequestMapping("/index/{page}")
    public String index(@PathVariable String page,Model model)
    {
        int requestPage = Integer.parseInt(page);

        model.addAttribute("postLst",iPostService.lst(requestPage));
        model.addAttribute("current_page", requestPage);

        indexSetting(model);

        return "lst";
    }

    private void indexSetting(Model model)
    {
        model.addAttribute("sidebar_title","Index");
        model.addAttribute("current_request","index");
    }


}
