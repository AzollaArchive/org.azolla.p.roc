/*
 * @(#)TagController.java		Created at 15/5/1
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.apache.ibatis.session.RowBounds;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.ITagDao;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.service.ITagService;
import org.azolla.p.roc.vo.TagVo;
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
public class TagController
{
    @Autowired
    private IPostService iPostService;

    @Autowired
    private ITagService iTagService;

    @Autowired
    private ITagDao iTagDao;

    @RequestMapping(value="/admin/tag/lst",method= RequestMethod.GET)
    public String lst(Model model)
    {
        return lst(1, model);
    }

    @RequestMapping(value="/admin/tag/lst/{page}",method= RequestMethod.GET)
    public String lst(@PathVariable String page,Model model)
    {
        int requestPage = Integer.valueOf(page);

        return lst(requestPage, model);
    }

    private String lst(int page, Model model)
    {
        model.addAttribute("tagVoList", iTagDao.lstWithoutVOD(new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_CONFIG_KEY_POSTSIZE)))));
        model.addAttribute("current_page", page);
        model.addAttribute("current_request", "admin/tag/lst");
        model.addAttribute("jsp_title","Tag List");

        return "admin/tag/lst";
    }

    @RequestMapping("/tag/{tag}")
    public String tag(@PathVariable String tag, Model model)
    {
        model.addAttribute("postList", iPostService.lstByTagUrlName(tag, 1));
        model.addAttribute("current_page",1);

        setting(tag, model);

        return "lst";
    }

    @RequestMapping("/tag/{tag}/{page}")
    public String tag(@PathVariable String tag, @PathVariable String page, Model model)
    {
        int requestPage = Integer.parseInt(page);

        model.addAttribute("postList",iPostService.lstByTagUrlName(tag,requestPage));
        model.addAttribute("current_page", requestPage);

        setting(tag, model);

        return "lst";
    }

    private void setting(String tag, Model model)
    {
        TagVo tagVo = iTagDao.getByUrlName(tag);

        model.addAttribute("jsp_title",tagVo.getDisplayName());
        model.addAttribute("current_request","tag/"+tag);
    }
}
