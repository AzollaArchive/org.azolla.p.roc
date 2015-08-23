/*
 * @(#)ProfessionalController.java		Created at 15/8/15
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.RowBounds;
import org.azolla.l.ling.util.Date0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.mapper.ProfessionalMapper;
import org.azolla.p.roc.service.IMapperService;
import org.azolla.p.roc.vo.ProfessionalVo;
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
public class ProfessionalController
{
    @Autowired
    private IMapperService<ProfessionalVo> iProfessionalMapperService;

    @RequestMapping(value = "/admin/professional/rmv/{id}", method = RequestMethod.GET)
    public String rmv(@PathVariable Integer id, Model model)
    {
        iProfessionalMapperService.mod(ProfessionalMapper.class, new ProfessionalVo().setId(id).setDeleted(1).setVisible(null).setRmvDate(Date0.now()));
        return "redirect:/admin/professional/lst";
    }

    @RequestMapping(value = "/admin/professional/lst", method = RequestMethod.GET)
    public String lst(Model model)
    {
        return lst(1, model);
    }

    @RequestMapping(value = "/admin/professional/lst/{page}", method = RequestMethod.GET)
    public String lst(@PathVariable String page, Model model)
    {
        Integer requestPage = Integer.valueOf(page);

        return lst(requestPage, model);
    }

    private String lst(Integer page, Model model)
    {

        model.addAttribute("professionalVoList", Lists.transform(iProfessionalMapperService.lst(ProfessionalMapper.class, new ProfessionalVo().setVisible(null).setDeleted(null), new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE)))), new Function<ProfessionalVo, ProfessionalVo>()
        {
            @Nullable
            @Override
            public ProfessionalVo apply(ProfessionalVo input)
            {
                if (input != null)
                {
                    input.setTagVo(CacheAware.getTagVoById(input.getTagId()));
                }
                return input;
            }
        }));
        model.addAttribute("current_page", page);
        model.addAttribute("current_request", "admin/professional/lst");
        model.addAttribute("jsp_title", "Professional List");

        return "admin/professional/lst";
    }
}
