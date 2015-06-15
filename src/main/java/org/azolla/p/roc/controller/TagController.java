/*
 * @(#)TagController.java		Created at 15/5/1
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.service.ITagService;
import org.azolla.p.roc.vo.TagVo;
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
public class TagController
{
    @Autowired
    private IPostService iPostService;

    @Autowired
    private ITagService iTagService;

    @RequestMapping("/tag/{tag}")
    public String tag(@PathVariable String tag, Model model)
    {
        model.addAttribute("postLst", iPostService.lstByTagUrlName(tag, 1));
        model.addAttribute("current_page",1);

        setting(tag, model);

        return "lst";
    }

    @RequestMapping("/tag/{tag}/{page}")
    public String tag(@PathVariable String tag, @PathVariable String page, Model model)
    {
        int requestPage = Integer.parseInt(page);

        model.addAttribute("postLst",iPostService.lstByTagUrlName(tag,requestPage));
        model.addAttribute("current_page", requestPage);

        setting(tag, model);

        return "lst";
    }

    private void setting(String tag, Model model)
    {
        TagVo tagVo = iTagService.getByUrlName(tag);

        model.addAttribute("sidebar_title",tagVo.getDisplayName());
        model.addAttribute("current_request","tag/"+tag);
    }
}
