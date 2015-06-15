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
import org.azolla.l.ling.collect.Tuple;
import org.azolla.l.ling.lang.String0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.ICommentDao;
import org.azolla.p.roc.dao.IPostDao;
import org.azolla.p.roc.dao.IPostRTagDao;
import org.azolla.p.roc.dao.ITagDao;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.vo.PostRTagVo;
import org.azolla.p.roc.vo.PostVo;
import org.azolla.p.roc.vo.TagVo;
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
    private IPostRTagDao iPostRTagDao;

    @Autowired
    private CacheAware cacheAware;

    @Override
    public List<PostVo> lst(int page)
    {
        return more(iPostDao.lst(new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_CONFIG_KEY_POSTSIZE)))));
    }

    @Override
    public List<PostVo> lstByCategoryUrlName(String categoryUrlName, int page)
    {
        return more(iPostDao.lstByCategoryUrlName(categoryUrlName, new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_CONFIG_KEY_POSTSIZE)))));
    }

    @Override
    public List<PostVo> lstByTagUrlName(String tagUrlName, int page)
    {
        return more(iPostDao.lstByTagUrlName(tagUrlName, new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_CONFIG_KEY_POSTSIZE)))));
    }

    private List<PostVo> more(List<PostVo> lst)
    {
        return Lists.transform(lst, new Function<PostVo, PostVo>()
        {
            @Nullable
            @Override
            public PostVo apply(PostVo input)
            {
                input.setContent(input.getContent().split(MORE)[0]);
                return input;
            }
        });
    }

    @Override
    public PostVo getByUrlTitle(String urlTitle)
    {
        PostVo postVo = iPostDao.getByUrlTitle(urlTitle);

        postVo.getTagVoList().addAll(iTagDao.lstByPostUrlTitle(urlTitle));
        postVo.getCommentVoList().addAll(iCommentDao.lstByPostId(postVo.getId()));

        return postVo;
    }

    public Tuple.Triple<Boolean,String,PostVo> opt(int id, String title, String content, int category, int visible, int operable, String tag, int[] tags)
    {
        Tuple.Triple<Boolean,String,PostVo> rtnResult = null;

        String urlTitle = String0.pinyin(title);
        PostVo postVo = new PostVo();
        postVo.setTitle(title);
        postVo.setUrlTitle(urlTitle);
        postVo.setContent(Strings.nullToEmpty(content));
        postVo.setCategoryId(category);
        postVo.setVisible(visible);
        postVo.setOperable(visible);

        rtnResult = Tuple.of(true,null,postVo);

        if(id == 0)
        {
            //add
            if(iPostDao.getByUrlTitle(urlTitle) != null)
            {
                rtnResult = Tuple.of(false,"Title exist!",postVo);
            }
            else
            {
                iPostDao.add(postVo);
            }
        }
        else
        {
            //mod
            iPostDao.mod(postVo);
        }

        PostVo dbPostVo = iPostDao.getByUrlTitle(urlTitle);
        String tagUrlName = "";
        boolean hasNewTag = false;
        for(String t : tag.split(","))
        {
            tagUrlName = String0.pinyin(t);
            if(iTagDao.add(new TagVo(t,tagUrlName)) == 1)
            {
                hasNewTag = true;

                iPostRTagDao.add(new PostRTagVo(dbPostVo.getId(),iTagDao.getByUrlName(tagUrlName).getId()));
            }


        }

        for(int t : tags)
        {
            iPostRTagDao.add(new PostRTagVo(dbPostVo.getId(),t));
        }

        if(hasNewTag)
        {
            cacheAware.reload(CacheAware.TAG_CACHE);
        }

        return rtnResult;
    }
}
