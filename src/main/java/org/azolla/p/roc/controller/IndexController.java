/*
 * @(#)CommonController.java		Created at 15/4/18
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.azolla.p.roc.service.IPostService;
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
public class IndexController
{
    @Autowired
    private IPostService iPostService;

    @RequestMapping({"/", "/index"})
    public String index(Model model)
    {
        model.addAttribute("postList",iPostService.lst(1));
        model.addAttribute("current_page",1);

        setting(model);

        return "lst";
    }

    @RequestMapping("/index/{page}")
    public String index(@PathVariable String page,Model model)
    {
        int requestPage = Integer.parseInt(page);

        model.addAttribute("postList",iPostService.lst(requestPage));
        model.addAttribute("current_page", requestPage);

        setting(model);

        return "lst";
    }

    private void setting(Model model)
    {
        model.addAttribute("jsp_title","Index");
        model.addAttribute("current_request","index");
    }


}
