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
import org.azolla.l.ling.util.Date0;
import org.azolla.l.ling.util.List0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.IMapperDao;
import org.azolla.p.roc.dao.IPostDao;
import org.azolla.p.roc.dao.IPostRTagDao;
import org.azolla.p.roc.dao.ITagDao;
import org.azolla.p.roc.mapper.CommentMapper;
import org.azolla.p.roc.mapper.PostMapper;
import org.azolla.p.roc.mapper.PostRTagMapper;
import org.azolla.p.roc.service.IPostService;
import org.azolla.p.roc.service.ITagService;
import org.azolla.p.roc.simditor.Simditor;
import org.azolla.p.roc.vo.CommentVo;
import org.azolla.p.roc.vo.PostRTagVo;
import org.azolla.p.roc.vo.PostVo;
import org.azolla.p.roc.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
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
    private IMapperDao<CommentVo>  iCommentMapperDao;
    @Autowired
    private IMapperDao<PostRTagVo> iPostRTagMapperDao;
    @Autowired
    private IMapperDao<PostVo>     iPostMapperDao;
    @Autowired
    private IPostDao               iPostDao;
    @Autowired
    private IPostRTagDao           iPostRTagDao;
    @Autowired
    private ITagDao                iTagDao;
    @Autowired
    private ITagService            iTagService;

    @Override
    public PostVo getByUrlTitle(String urlTitle)
    {
        PostVo postVo = iPostMapperDao.selectOne(PostMapper.class, new PostVo().setUrlTitle(urlTitle).setVisible(null).setDeleted(null));
        postVo.getTagVoList().addAll(iTagDao.lstByPostUrlTitle(urlTitle));
        postVo.getCommentVoList().addAll(iCommentMapperDao.lst(CommentMapper.class, new CommentVo().setPostId(postVo.getId())));
        return postVo;
    }

    @Override
    public List<PostVo> lstByTagUrlName(@Nonnull String tagUrlName, Integer page)
    {
        return Simditor.more(iPostDao.lstByTagUrlName(tagUrlName, new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE)))));
    }

    public List<PostVo> search(String search, Integer page)
    {
        return Simditor.more(iPostDao.search(search, new RowBounds(page, Integer.parseInt(CacheAware.getConfigValue(CacheAware.ROC_POST_SIZE)))));
    }

    public Tuple.Triple<Boolean, String, PostVo> opt(Integer id, String title, Integer category, String tag, String content, Integer visible, Integer operable, Integer deleted)
    {
        Tuple.Triple<Boolean, String, PostVo> rtnResult = null;

        String urlTitle = String0.pinyin(title);
        final PostVo postVo = new PostVo();
        postVo.setTitle(title);
        postVo.setUrlTitle(urlTitle);
        postVo.setCategoryId(category);

        postVo.setContent(Strings.nullToEmpty(content));
        postVo.setVisible(Integer0.nullToZero(visible));
        postVo.setOperable(Integer0.nullToZero(operable));
        postVo.setDeleted(Integer0.nullToZero(deleted));

        rtnResult = Tuple.of(true, null, postVo);

        if (Integer0.isNullOrZero(id))
        {
            //add
            PostVo existPostVo = iPostMapperDao.selectOne(PostMapper.class, new PostVo().setUrlTitle(urlTitle).setVisible(null).setDeleted(null));
            if (existPostVo != null)
            {
                rtnResult = Tuple.of(false, "Title exist!", postVo);
            }
            else
            {
                iPostMapperDao.add(PostMapper.class, postVo);
                postVo.setId(iPostMapperDao.selectOne(PostMapper.class, new PostVo().setUrlTitle(urlTitle).setVisible(null).setDeleted(null)).getId());
            }
        }
        else
        {
            //delete all exist tag
            iPostRTagMapperDao.rmv(PostRTagMapper.class, new PostRTagVo().setPostId(id).setVisible(null));

            postVo.setId(id);
            //mod
            iPostMapperDao.mod(PostMapper.class, postVo.setModDate(Date0.now()));
        }

        if (Tuple.getFirst(rtnResult))
        {
            List<String> allCachedTagIdList = Lists.transform(CacheAware.getTagList(), new Function<TagVo, String>()
            {
                @Nullable
                @Override
                public String apply(@Nullable TagVo input)
                {
                    return String.valueOf(input.getId());
                }
            });

            List<Integer> optExistTagIdList = Lists.newArrayList();
            List<String> allOptTagIdList = Lists.newArrayList();
            if (!Strings.isNullOrEmpty(tag))
            {
                allOptTagIdList.addAll(Arrays.asList(tag.split(",")));
                optExistTagIdList.addAll(Lists.transform(List0.listExistInOther(allOptTagIdList, allCachedTagIdList), new Function<String, Integer>()
                {
                    @Nullable
                    @Override
                    public Integer apply(@Nullable String input)
                    {
                        return Integer.valueOf(input);
                    }
                }));
            }
            optExistTagIdList.addAll(Lists.transform(iTagService.btAddByTagDisplayName(List0.listNotExistInOther(allOptTagIdList, allCachedTagIdList)), new Function<TagVo, Integer>()
            {
                @Nullable
                @Override
                public Integer apply(@Nullable TagVo input)
                {
                    return input.getId();
                }
            }));

            List<PostRTagVo> postRTagVoList = Lists.transform(optExistTagIdList, new Function<Integer, PostRTagVo>()
            {
                @Nullable
                @Override
                public PostRTagVo apply(@Nullable Integer input)
                {
                    return new PostRTagVo().setPostId(postVo.getId()).setTagId(input);
                }
            });
            if (postRTagVoList.size() > 0)
            {
                iPostRTagDao.btAdd(postRTagVoList);
            }
        }

        return rtnResult;
    }
}
