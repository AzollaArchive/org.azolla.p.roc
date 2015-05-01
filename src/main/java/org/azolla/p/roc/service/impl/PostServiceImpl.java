/*
 * @(#)PostServiceImpl.java		Created at 15/4/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service.impl;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.RowBounds;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.ICommentDao;
import org.azolla.p.roc.dao.IPostDao;
import org.azolla.p.roc.dao.ITagDao;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Service
public class PostServiceImpl implements IPostService
{
    @Autowired
    private IPostDao iPostDao;

    @Autowired
    private ITagDao iTagDao;

    @Autowired
    private ICommentDao iCommentDao;

    @Autowired
    private CacheAware cacheAware;

    @Override
    public List<PostVo> lst(int page)
    {
        return more(iPostDao.lst(new RowBounds(page, Integer.parseInt(cacheAware.getConfigValue(CacheAware.ROC_CONFIG_KEY_POSTSIZE)))));
    }

    @Override
    public List<PostVo> lstBcategory(String categoryUrlName, int page)
    {
        return more(iPostDao.lstBcategory(categoryUrlName, new RowBounds(page, Integer.parseInt(cacheAware.getConfigValue(CacheAware.ROC_CONFIG_KEY_POSTSIZE)))));
    }

    @Override
    public List<PostVo> lstBtag(String tagUrlName, int page)
    {
        return more(iPostDao.lstBtag(tagUrlName, new RowBounds(page, Integer.parseInt(cacheAware.getConfigValue(CacheAware.ROC_CONFIG_KEY_POSTSIZE)))));
    }

    private List<PostVo> more(List<PostVo> lst)
    {
        return Lists.transform(lst, new Function<PostVo, PostVo>()
        {
            @Nullable
            @Override
            public PostVo apply(PostVo input)
            {
                if(Strings.isNullOrEmpty(input.getDescription()))
                {
                    input.setDescription(input.getTitle());
                }
                input.setContent(input.getContent().split(MORE)[0]);
                return input;
            }
        });
    }

    @Override
    public PostVo get(String urlTitle)
    {
        PostVo postVo = iPostDao.get(urlTitle);

        if(Strings.isNullOrEmpty(postVo.getDescription()))
        {
            postVo.setDescription(postVo.getTitle());
        }
        postVo.getTagVoList().addAll(iTagDao.lstBpostUrlTitle(urlTitle));
        postVo.getCommentVoList().addAll(iCommentDao.lstBpostUrlTitle(urlTitle));

        return postVo;
    }
}
