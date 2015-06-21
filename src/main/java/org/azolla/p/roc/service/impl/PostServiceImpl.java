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
import org.azolla.l.ling.lang.Integer0;
import org.azolla.l.ling.lang.String0;
import org.azolla.l.ling.util.List0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.ICommentDao;
import org.azolla.p.roc.dao.IPostDao;
import org.azolla.p.roc.dao.IPostRTagDao;
import org.azolla.p.roc.dao.ITagDao;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.service.ITagService;
import org.azolla.p.roc.vo.PostRTagVo;
import org.azolla.p.roc.vo.PostVo;
import org.azolla.p.roc.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Arrays;
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

    @Autowired
    private ITagService iTagService;

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

    public Tuple.Triple<Boolean,String,PostVo> opt(int id, String title, int category, String tag, String content, Integer visible, Integer operable)
    {
        Tuple.Triple<Boolean,String,PostVo> rtnResult = null;

        String urlTitle = String0.pinyin(title);
        final PostVo postVo = new PostVo();
        postVo.setTitle(title);
        postVo.setUrlTitle(urlTitle);
        postVo.setCategoryId(category);

        postVo.setContent(Strings.nullToEmpty(content));
        postVo.setVisible(Integer0.nullToZero(visible));
        postVo.setOperable(Integer0.nullToZero(operable));

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
                postVo.setId(iPostDao.getByUrlTitle(urlTitle).getId());
            }
        }
        else
        {
            //delete all exist tag
            iPostRTagDao.rmvByPostId(id);

            postVo.setId(id);
            //mod
            iPostDao.mod(postVo);
        }

        if(Tuple.getFirst(rtnResult))
        {
            List<String> cachedTagIdList = Lists.transform(CacheAware.getTagList(), new Function<TagVo, String>()
            {
                @Nullable
                @Override
                public String apply(@Nullable TagVo input)
                {
                    return String.valueOf(input.getId());
                }
            });
            List<String> optTagList = Arrays.asList(tag.split(","));
            List<Integer> tagIdList = Lists.transform(List0.listExistInOther(optTagList, cachedTagIdList), new Function<String, Integer>()
            {
                @Nullable
                @Override
                public Integer apply(@Nullable String input)
                {
                    return Integer.valueOf(input);
                }
            });

            tagIdList.addAll(Lists.transform(iTagService.btAddByTagDisplayName(List0.listNotExistInOther(optTagList, cachedTagIdList)), new Function<TagVo, Integer>()
            {
                @Nullable
                @Override
                public Integer apply(@Nullable TagVo input)
                {
                    return input.getId();
                }
            }));


            //all tag existed in db,ajax added to db
            List<PostRTagVo> postRTagVoList = Lists.transform(tagIdList, new Function<Integer, PostRTagVo>()
            {
                @Nullable
                @Override
                public PostRTagVo apply(@Nullable Integer input)
                {
                    return new PostRTagVo(postVo.getId(),input);
                }
            });
            iPostRTagDao.btAdd(postRTagVoList);
        }

        return rtnResult;
    }
}
