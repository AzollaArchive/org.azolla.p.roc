/*
 * @(#)TagServiceImpl.java		Created at 15/4/22
 *
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.azolla.p.roc.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.azolla.l.ling.collect.Tuple;
import org.azolla.l.ling.lang.Integer0;
import org.azolla.l.ling.lang.String0;
import org.azolla.l.ling.util.Date0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.IMapperDao;
import org.azolla.p.roc.dao.ITagDao;
import org.azolla.p.roc.mapper.TagMapper;
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
  private CacheAware        cacheAware;
  @Autowired
  private IMapperDao<TagVo> iTagMapperDao;
  @Autowired
  private ITagDao           iTagDao;

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
        TagVo tagVo = iTagMapperDao.selectOne(TagMapper.class, new TagVo().setUrlName(urlName).setVisible(null).setDeleted(null));
        if (tagVo == null)
        {
          newTagUrlNameList.add(urlName);
          return new TagVo().setDisplayName(displayName).setUrlName(urlName);
        }
        else
        {
          rtnTagVoList.add(tagVo);
          return null;
        }
      }
    });
    if (needAddTagVoList.size() > 0)
    {
      iTagDao.btAdd(needAddTagVoList);
      cacheAware.reload(CacheAware.TAG_CACHE);
    }
    if (newTagUrlNameList.size() > 0)
    {
      rtnTagVoList.addAll(iTagDao.lstByUrlNameList(newTagUrlNameList));
    }
    return rtnTagVoList;
  }

  public Tuple.Triple<Boolean, String, TagVo> opt(Integer id, String displayName, Integer visible, Integer operable, Integer deleted)
  {
    TagVo tagVo = new TagVo();
    tagVo.setDisplayName(displayName);
    tagVo.setUrlName(String0.pinyin(displayName));
    tagVo.setVisible(Integer0.nullToZero(visible));
    tagVo.setOperable(Integer0.nullToZero(operable));
    tagVo.setDeleted(Integer0.nullToZero(deleted));

    Tuple.Triple<Boolean, String, TagVo> rtnResult = Tuple.of(true, null, tagVo);
    try
    {
      if (Integer0.isNullOrZero(id))
      {
        iTagMapperDao.add(TagMapper.class, tagVo);
      }
      else
      {
        tagVo.setId(id);
        iTagMapperDao.mod(TagMapper.class, tagVo.setModDate(Date0.now()));
      }
      cacheAware.reload(CacheAware.TAG_CACHE);
    }
    catch (Exception e)
    {
      rtnResult = Tuple.of(false, e.toString(), tagVo);
    }
    return rtnResult;
  }
}
