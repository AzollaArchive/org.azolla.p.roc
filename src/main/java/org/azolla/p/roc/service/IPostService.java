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
    public static final String MORE = "<!--MORE-->";
    
    public List<PostVo> lst(int page);

    public List<PostVo> lstByCategoryUrlName(String categoryUrlName, int page);

    public List<PostVo> lstByTagUrlName(String tagUrlName, int page);

    public PostVo getByUrlTitle(String urlTitle);

    public Tuple.Triple<Boolean,String,PostVo> opt(int id, String title, String content, int category, int visible, int operable, String tag, int[] tags);
}
