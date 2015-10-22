/*
 * @(#)CommentController.java		Created at 15/6/22
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.apache.ibatis.session.RowBounds;
import org.azolla.l.ling.util.Date0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.mapper.CommentMapper;
import org.azolla.p.roc.service.IMapperService;
import org.azolla.p.roc.vo.CommentVo;
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
    private IMapperService<CommentVo> iCommentVoMapperService;

    @RequestMapping(value = "/a/cmt/d/{id}", method = RequestMethod.GET)
    public String d(@PathVariable Integer id, Model model)
    {
        iCommentVoMapperService.mod(CommentMapper.class, new CommentVo().setId(id).setDeleted(1).setVisible(null).setRmvDate(Date0.now()));
        return "redirect:/a/cmt/l";
    }

    @RequestMapping(value = "/a/cmt/l", method = RequestMethod.GET)
    public String l(Model model)
    {
        return l(1, model);
    }

    @RequestMapping(value = "/a/cmt/l/{page}", method = RequestMethod.GET)
    public String l(@PathVariable String page, Model model)
    {
        Integer requestPage = Integer.valueOf(page);

        return l(requestPage, model);
    }

    private String l(Integer page, Model model)
    {
        model.addAttribute("commentVoList", iCommentVoMapperService.lst(CommentMapper.class, new CommentVo().setVisible(null).setDeleted(null), new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE)))));
        model.addAttribute("current_page", page);
        model.addAttribute("current_request", "a/cmt/l");
        model.addAttribute("jsp_title", "Comment List");

        return "a/cmt/l";
    }
}
