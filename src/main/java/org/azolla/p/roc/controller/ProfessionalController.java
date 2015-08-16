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
import java.sql.Date;

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

//    @Autowired
//    private ProfessionalMapperService professionalMapperService;

    @RequestMapping(value = "/admin/professional/rmv/{id}", method = RequestMethod.GET)
    public String rmv(@PathVariable int id, Model model)
    {
        ProfessionalVo professionalVo = new ProfessionalVo();
        professionalVo.setId(id);
        professionalVo.setDeleted(1);
        professionalVo.setRmvDate(Date0.now());
        iProfessionalMapperService.rmv(ProfessionalMapper.class, professionalVo);
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
        int requestPage = Integer.valueOf(page);

        return lst(requestPage, model);
    }

    private String lst(int page, Model model)
    {

        model.addAttribute("professionalVoList", Lists.transform(iProfessionalMapperService.lst(ProfessionalMapper.class, new ProfessionalVo(), new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE)))), new Function<ProfessionalVo, ProfessionalVo>()
        {
            @Nullable
            @Override
            public ProfessionalVo apply(ProfessionalVo input)
            {
                if (input != null)
                {
                    input.setScoreName(CacheAware.getTagVoById(input.getScoreId()).getDisplayName());
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
