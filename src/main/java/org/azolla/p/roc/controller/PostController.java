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
import org.azolla.p.roc.dao.IPostDao;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.vo.PostVo;
import org.azolla.p.roc.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private IPostDao iPostDao;

    @RequestMapping(value="/admin/post/opt",method= RequestMethod.GET)
    public String opt(Model model)
    {
        model.addAttribute("jsp_title","New Post");
        model.addAttribute("postVo", new PostVo());

        return "admin/post/opt";
    }

    @RequestMapping(value="/admin/post/opt/{urlTitle}",method= RequestMethod.GET)
    public String opt(@PathVariable String urlTitle, Model model)
    {
        model.addAttribute("jsp_title","Mod Post");
        model.addAttribute("postVo", iPostService.getByUrlTitle(urlTitle));

        return "admin/post/opt";
    }

    @RequestMapping(value="/admin/post/opt",method= RequestMethod.POST)
    public String opt(int id, String title, int category, String tag, String content, Integer visible, Integer operable, Model model)
    {
        String rtnString = "redirect:/admin/post/lst";
        Tuple.Triple<Boolean,String,PostVo> serviceResult = iPostService.opt(id,title,category,tag,content,visible,operable);

        if(!Tuple.getFirst(serviceResult))
        {
            rtnString = "admin/post/opt";

            model.addAttribute("jsp_title",id == 0 ? "New Post" : "Mod Post");
            model.addAttribute("ctrl_result",Tuple.getSecond(serviceResult));
            model.addAttribute("postVo",Tuple.getThird(serviceResult));

        }
        return rtnString;
    }

    @RequestMapping(value = "/admin/post/rmv/{id}", method = RequestMethod.GET)
    public String rmv(@PathVariable int id)
    {
        iPostDao.rmvById(id);
        return "redirect:/admin/post/lst";
    }

    @RequestMapping(value="/admin/post/lst",method= RequestMethod.GET)
    public String lst(Model model)
    {
        return lst(1, model);
    }

    @RequestMapping(value="/admin/post/lst/{page}",method= RequestMethod.GET)
    public String lst(@PathVariable String page,Model model)
    {
        int requestPage = Integer.valueOf(page);

        return lst(requestPage,model);
    }

    private String lst(int page,Model model)
    {
        model.addAttribute("postVoList",iPostService.lstWithoutVOD(page));
        model.addAttribute("current_page", page);
        model.addAttribute("current_request", "admin/post/lst");
        model.addAttribute("jsp_title","Post List");

        return "admin/post/lst";
    }

    @RequestMapping("/post/{urlTile}")
    public String post(@PathVariable String urlTile,Model model)
    {
        PostVo postVo = iPostService.getByUrlTitle(urlTile);
        model.addAttribute("post", postVo);
        model.addAttribute("jsp_title",postVo.getTitle());
//        model.addAttribute("post4keyword", Joiner.on(",").join(Lists.transform(postVo.getTagVoList(), new Function<TagVo, String>(){
//            @Nullable
//            @Override
//            public String apply(TagVo input)
//            {
//                return input.getDisplayName();
//            }
//        })));

        return "pst";
    }
}
