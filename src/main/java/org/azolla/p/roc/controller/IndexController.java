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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(String search,Model model,HttpServletRequest request)
    {
        request.getSession().setAttribute("search",search);
        model.addAttribute("postList", iPostService.search(search,1));
        model.addAttribute("current_page", 1);
        model.addAttribute("jsp_title","Search");
        model.addAttribute("current_request","search");
        return "lst";
    }

    @RequestMapping(value = "/search/{page}", method = RequestMethod.GET)
    public String search(@PathVariable int page,Model model,HttpServletRequest request)
    {
        model.addAttribute("postList", iPostService.search(request.getSession().getAttribute("search").toString(),page));
        model.addAttribute("current_page", page);
        model.addAttribute("jsp_title","Search");
        model.addAttribute("current_request","search");
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
