/*
 * @(#)PostController.java		Created at 15/5/1
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.azolla.l.ling.collect.Tuple;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.vo.PostVo;
import org.azolla.p.roc.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletResponse;

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
    private IPostService iPostService;

    @RequestMapping(value="/admin/post/add",method= RequestMethod.GET)
    public String add(Model model)
    {
        model.addAttribute("jsp_title","New Post");
        model.addAttribute("postVo", new PostVo());

        return "admin/post/opt";
    }

    @RequestMapping(value="/admin/post/add",method= RequestMethod.POST)
    public String add(int id, String title, String content, int category, int visible, int operable, String tag, int[] tags, Model model, HttpServletResponse response)
    {
        String rtnString = "redirect:/admin/post/lst";
        Tuple.Triple<Boolean,String,PostVo> serviceResult = iPostService.opt(id,title,content,category,visible,operable,tag,tags);

        if(!Tuple.getFirst(serviceResult))
        {
            rtnString = "admin/post/add";

            model.addAttribute("jsp_title","New Post");
            model.addAttribute("ctrl_result",Tuple.getSecond(serviceResult));
            model.addAttribute("postVo",Tuple.getThird(serviceResult));

        }

        return rtnString;
    }

    @RequestMapping(value="/admin/post/lst",method= RequestMethod.GET)
    public String lst(Model model)
    {
        model.addAttribute("jsp_title","Post List");

        return "admin/post/lst";
    }

    @RequestMapping("/post/{urlTile}")
    public String post(@PathVariable String urlTile,Model model)
    {
        PostVo postVo = iPostService.getByUrlTitle(urlTile);
        model.addAttribute("post", postVo);
        model.addAttribute("post4keyword", Joiner.on(",").join(Lists.transform(postVo.getTagVoList(), new Function<TagVo, String>(){
            @Nullable
            @Override
            public String apply(TagVo input)
            {
                return input.getDisplayName();
            }
        })));

        return "pst";
    }
}
