/*
 * @(#)IPostService.java		Created at 15/4/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service;

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

    public List<PostVo> lstBcategory(String categoryUrlName, int page);

    public List<PostVo> lstBtag(String tagUrlName, int page);

    public PostVo get(String urlTitle);
}
