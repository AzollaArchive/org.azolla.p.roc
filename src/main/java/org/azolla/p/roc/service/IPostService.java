/*
 * @(#)IPostService.java		Created at 15/4/21
 *
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.azolla.p.roc.service;

import org.azolla.l.ling.collect.Tuple;
import org.azolla.p.roc.vo.PostVo;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface IPostService
{
  public PostVo getByUrlTitle(String urlTitle);

  public List<PostVo> lstByTagUrlName(String tagUrlName, Integer page);

  public List<PostVo> search(String search, Integer page);

  public Tuple.Triple<Boolean, String, PostVo> opt(Integer id, String title, Integer category, String tag, String content, Integer visible, Integer operable, Integer deleted);
}
