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
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.vo.PostVo;
import org.azolla.p.roc.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Nullable;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Controller
@RequestMapping("/post")
public class PostController
{
    @Autowired
    private IPostService iPostService;

    @RequestMapping("/{post}")
    public String post(@PathVariable String post,Model model)
    {
        PostVo postVo = iPostService.get(post);
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
