/*
 * @(#)ConfigController.java		Created at 15/6/28
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.apache.ibatis.session.RowBounds;
import org.azolla.l.ling.collect.Tuple;
import org.azolla.l.ling.lang.Integer0;
import org.azolla.l.ling.util.Date0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.mapper.ConfigMapper;
import org.azolla.p.roc.service.IConfigService;
import org.azolla.p.roc.service.IMapperService;
import org.azolla.p.roc.vo.ConfigVo;
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
public class ConfigController
{
    @Autowired
    private CacheAware               cacheAware;
    @Autowired
    private IConfigService           iConfigService;
    @Autowired
    private IMapperService<ConfigVo> iConfigMapperService;

    @RequestMapping(value = "/admin/config/opt", method = RequestMethod.GET)
    public String opt(Model model)
    {
        model.addAttribute("jsp_title", "New Config");
        model.addAttribute("configVo", new ConfigVo());
        return "/admin/config/opt";
    }

    @RequestMapping(value = "/admin/config/opt/{key}", method = RequestMethod.GET)
    public String opt(@PathVariable String key, Model model)
    {
        model.addAttribute("jsp_title", "New Config");
        model.addAttribute("configVo", iConfigMapperService.selectOne(ConfigMapper.class, new ConfigVo().setRocKey(key).setVisible(null).setDeleted(null)));
        return "/admin/config/opt";
    }

    @RequestMapping(value = "/admin/config/opt", method = RequestMethod.POST)
    public String opt(Integer id, String rocKey, String rocValue, Integer visible, Integer operable, Integer deleted, Model model)
    {
        String rtnString = "redirect:/admin/config/lst";

        Tuple.Triple<Boolean, String, ConfigVo> serviceResult = iConfigService.opt(id, rocKey, rocValue, visible, operable, deleted);
        if (!Tuple.getFirst(serviceResult))
        {
            rtnString = "admin/config/opt";

            model.addAttribute("jsp_title", Integer0.isNullOrZero(id) ? "New Config" : "Mod Config");
            model.addAttribute("ctrl_result", Tuple.getSecond(serviceResult));
            model.addAttribute("configVo", Tuple.getThird(serviceResult));
        }

        return rtnString;
    }

    @RequestMapping(value = "/admin/config/lst", method = RequestMethod.GET)
    public String lst(Model model)
    {
        return lst(1, model);
    }

    @RequestMapping(value = "/admin/config/lst/{page}", method = RequestMethod.GET)
    public String lst(@PathVariable String page, Model model)
    {
        Integer requestPage = Integer.valueOf(page);

        return lst(requestPage, model);
    }

    private String lst(Integer page, Model model)
    {
        model.addAttribute("configVoList", iConfigMapperService.lst(ConfigMapper.class, new ConfigVo().setVisible(null).setDeleted(null), new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE)))));
        model.addAttribute("current_page", page);
        model.addAttribute("current_request", "admin/config/lst");
        model.addAttribute("jsp_title", "Config List");

        return "admin/config/lst";
    }
}
