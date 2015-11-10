/*
 * @(#)TagController.java		Created at 15/5/1
 *
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.azolla.p.roc.controller;

import org.apache.ibatis.session.RowBounds;
import org.azolla.l.ling.collect.Tuple;
import org.azolla.l.ling.lang.Integer0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.mapper.TagMapper;
import org.azolla.p.roc.service.IMapperService;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.service.ITagService;
import org.azolla.p.roc.vo.TagVo;
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
public class TagController
{
  @Autowired
  private CacheAware            cacheAware;
  @Autowired
  private IMapperService<TagVo> iTagMapperService;
  @Autowired
  private IPostService          iPostService;
  @Autowired
  private ITagService           iTagService;

  @RequestMapping(value = "/a/t/m", method = RequestMethod.GET)
  public String m(Model model)
  {
    model.addAttribute("jsp_title", "New Tag");
    model.addAttribute("tagVo", new TagVo());
    return "/a/t/m";
  }

  @RequestMapping(value = "/a/t/m/{urlName}", method = RequestMethod.GET)
  public String m(@PathVariable String urlName, Model model)
  {
    model.addAttribute("jsp_title", "Mod Tag");
    model.addAttribute("tagVo", CacheAware.getTagVoByUrl(urlName));
    return "/a/t/m";
  }

  @RequestMapping(value = "/a/t/m", method = RequestMethod.POST)
  public String m(Integer id, String displayName, Integer visible, Integer operable, Integer deleted, Model model)
  {
    String rtnString = "redirect:/a/t/l";
    Tuple.Triple<Boolean, String, TagVo> serviceResult = iTagService.opt(id, displayName, visible, operable, deleted);
    if (!Tuple.getFirst(serviceResult))
    {
      rtnString = "a/t/m";

      model.addAttribute("jsp_title", Integer0.isNullOrZero(id) ? "New Tag" : "Mod Tag");
      model.addAttribute("ctrl_result", Tuple.getSecond(serviceResult));
      model.addAttribute("tagVo", Tuple.getThird(serviceResult));
    }
    return rtnString;
  }

  @RequestMapping(value = "/a/t/l", method = RequestMethod.GET)
  public String l(Model model)
  {
    return l(1, model);
  }

  @RequestMapping(value = "/a/t/l/{page}", method = RequestMethod.GET)
  public String l(@PathVariable String page, Model model)
  {
    Integer requestPage = Integer.valueOf(page);

    return l(requestPage, model);
  }

  private String l(Integer page, Model model)
  {
    model.addAttribute("tagVoList", iTagMapperService.lst(TagMapper.class, new TagVo().setVisible(null).setDeleted(null), new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE)))));
    model.addAttribute("current_page", page);
    model.addAttribute("current_request", "a/t/l");
    model.addAttribute("jsp_title", "Tag List");

    return "a/t/l";
  }

  @RequestMapping("/t/{urlName}")
  public String t(@PathVariable String urlName, Model model)
  {
    model.addAttribute("postList", iPostService.lstByTagUrlName(urlName, 1));
    model.addAttribute("current_page", 1);

    setting(urlName, model);

    return "l";
  }

  @RequestMapping("/t/{urlName}/{page}")
  public String t(@PathVariable String urlName, @PathVariable String page, Model model)
  {
    Integer requestPage = Integer.parseInt(page);

    model.addAttribute("postList", iPostService.lstByTagUrlName(urlName, requestPage));
    model.addAttribute("current_page", requestPage);

    setting(urlName, model);

    return "l";
  }

  private void setting(String urlName, Model model)
  {
    TagVo tagVo = CacheAware.getTagVoByUrl(urlName);

    model.addAttribute("jsp_title", tagVo.getDisplayName());
    model.addAttribute("current_request", "t/" + urlName);
  }
}
