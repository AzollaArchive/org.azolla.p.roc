/*
 * @(#)TagServiceImpl.java		Created at 15/4/22
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.azolla.l.ling.lang.String0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.ITagDao;
import org.azolla.p.roc.service.ITagService;
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
public class TagServiceImpl implements ITagService
{
    @Autowired
    private ITagDao iTagDao;

    @Autowired
    private CacheAware cacheAware;

    public TagVo addByTagDisplayName(String tagDisplayName)
    {
        String urlName = String0.pinyin(tagDisplayName);
        TagVo rtnTagVo = iTagDao.getByUrlName(urlName);
        if(rtnTagVo == null)
        {
            rtnTagVo = new TagVo();
            rtnTagVo.setUrlName(urlName);
            rtnTagVo.setDisplayName(tagDisplayName);
            if(iTagDao.add(rtnTagVo) > 0)
            {
                rtnTagVo = iTagDao.getByUrlName(urlName);
            }
            else
            {
                rtnTagVo = null;
            }
        }
        return rtnTagVo;
    }

    public List<TagVo> btAddByTagDisplayName(List<String> tagDisplayNameList)
    {
        final List<TagVo> rtnTagVoList = Lists.newArrayList();

        final List<String> newTagUrlNameList = Lists.newArrayList();
        List<TagVo> needAddTagVoList = Lists.transform(tagDisplayNameList, new Function<String, TagVo>()
        {
            @Nullable
            @Override
            public TagVo apply(@Nullable String displayName)
            {
                String urlName = String0.pinyin(displayName);
                TagVo tagVo = iTagDao.getByUrlName(urlName);
                if(tagVo == null)
                {
                    newTagUrlNameList.add(urlName);
                    return new TagVo(displayName, urlName);
                }
                else
                {
                    rtnTagVoList.add(tagVo);
                    return null;
                }
            }
        });
        if(needAddTagVoList.size() > 0)
        {
            iTagDao.btAdd(needAddTagVoList);
            cacheAware.reload(CacheAware.TAG_CACHE);
        }
        if(newTagUrlNameList.size() > 0)
        {
            rtnTagVoList.addAll(iTagDao.btLstByUrlNameList(newTagUrlNameList));
        }
        return rtnTagVoList;
    }
}
