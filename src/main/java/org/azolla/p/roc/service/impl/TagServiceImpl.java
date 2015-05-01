/*
 * @(#)TagServiceImpl.java		Created at 15/4/22
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service.impl;

import org.azolla.p.roc.dao.ITagDao;
import org.azolla.p.roc.service.ITagService;
import org.azolla.p.roc.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Service
public class TagServiceImpl implements ITagService
{
    @Autowired
    private ITagDao iTagDao;

    @Override
    public List<TagVo> lst()
    {
        return iTagDao.lst();
    }

    @Override
    public TagVo get(String urlName)
    {
        return iTagDao.get(urlName);
    }
}
