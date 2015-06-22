/*
 * @(#)CommentController.java		Created at 15/6/22
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.apache.ibatis.session.RowBounds;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.ICommentDao;
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
public class CommentController
{
    @Autowired
    private ICommentDao iCommentDao;

    @RequestMapping(value="/admin/comment/lst",method= RequestMethod.GET)
    public String lst(Model model)
    {
        return lst(1, model);
    }

    @RequestMapping(value="/admin/comment/lst/{page}",method= RequestMethod.GET)
    public String lst(@PathVariable String page,Model model)
    {
        int requestPage = Integer.valueOf(page);

        return lst(requestPage, model);
    }

    private String lst(int page, Model model)
    {
        model.addAttribute("commentVoList",iCommentDao.fullLstWithoutVOD(new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_CONFIG_KEY_POSTSIZE)))));
        model.addAttribute("current_page", page);
        model.addAttribute("current_request", "admin/comment/lst");
        model.addAttribute("jsp_title","Comment List");

        return "admin/comment/lst";
    }
}
