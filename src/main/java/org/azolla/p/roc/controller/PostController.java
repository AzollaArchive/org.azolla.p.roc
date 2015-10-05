/*
 * @(#)PostController.java		Created at 15/5/1
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.RowBounds;
import org.azolla.l.ling.collect.Tuple;
import org.azolla.l.ling.lang.Integer0;
import org.azolla.l.ling.util.Date0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.mapper.PostMapper;
import org.azolla.p.roc.service.IMapperService;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Nullable;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Controller
public class PostController
{
    @Autowired
    private IMapperService<PostVo> iPostMapperService;
    @Autowired
    private IPostService           iPostService;

    @RequestMapping(value = "/admin/post/opt", method = RequestMethod.GET)
    public String opt(Model model)
    {
        model.addAttribute("jsp_title", "New Post");
        model.addAttribute("postVo", new PostVo());

        return "admin/post/opt";
    }

    @RequestMapping(value = "/admin/post/opt/{urlTitle}", method = RequestMethod.GET)
    public String opt(@PathVariable String urlTitle, Model model)
    {
        model.addAttribute("jsp_title", "Mod Post");
        model.addAttribute("postVo", iPostService.getByUrlTitle(urlTitle));

        return "admin/post/opt";
    }

    @RequestMapping(value = "/admin/post/opt", method = RequestMethod.POST)
    public String opt(Integer id, String title, Integer category, String tag, String content, Integer visible, Integer operable, Integer deleted, Model model)
    {
        String rtnString = "redirect:/admin/post/lst";
        Tuple.Triple<Boolean, String, PostVo> serviceResult = iPostService.opt(id, title, category, tag, content, visible, operable, deleted);

        if (!Tuple.getFirst(serviceResult))
        {
            rtnString = "admin/post/opt";

            model.addAttribute("jsp_title", Integer0.isNullOrZero(id) ? "New Post" : "Mod Post");
            model.addAttribute("ctrl_result", Tuple.getSecond(serviceResult));
            model.addAttribute("postVo", Tuple.getThird(serviceResult));

        }
        return rtnString;
    }

    @RequestMapping(value = "/admin/post/lst", method = RequestMethod.GET)
    public String lst(Model model)
    {
        return lst(1, model);
    }

    @RequestMapping(value = "/admin/post/lst/{page}", method = RequestMethod.GET)
    public String lst(@PathVariable String page, Model model)
    {
        Integer requestPage = Integer.valueOf(page);

        return lst(requestPage, model);
    }

    private String lst(Integer page, Model model)
    {
        model.addAttribute("postVoList", Lists.transform(iPostMapperService.lst(PostMapper.class, new PostVo().setVisible(null).setDeleted(null), new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE)))), new Function<PostVo, PostVo>()
        {
            @Nullable
            @Override
            public PostVo apply(@Nullable PostVo input)
            {
                return input.setCategoryVo(CacheAware.getCategoryVoById(input.getCategoryId()));
            }
        }));
        model.addAttribute("current_page", page);
        model.addAttribute("current_request", "admin/post/lst");
        model.addAttribute("jsp_title", "Post List");

        return "admin/post/lst";
    }

    @RequestMapping("/post/{urlTitle}")
    public String post(@PathVariable String urlTitle, Model model)
    {
        PostVo postVo = iPostService.getByUrlTitle(urlTitle);
        model.addAttribute("post", postVo);
        model.addAttribute("jsp_title", postVo.getTitle());

        return "pst";
    }
}
