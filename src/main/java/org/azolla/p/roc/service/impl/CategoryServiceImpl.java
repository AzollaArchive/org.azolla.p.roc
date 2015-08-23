/*
 * @(#)CategoryServiceImpl.java		Created at 15/4/19
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service.impl;

import org.azolla.l.ling.collect.Tuple;
import org.azolla.l.ling.lang.Integer0;
import org.azolla.l.ling.lang.String0;
import org.azolla.l.ling.util.Date0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.IMapperDao;
import org.azolla.p.roc.mapper.CategoryMapper;
import org.azolla.p.roc.service.ICategoryService;
import org.azolla.p.roc.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Service
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private CacheAware             cacheAware;
    @Autowired
    private IMapperDao<CategoryVo> iCategoryMapperDao;

    public List<CategoryVo> loop(@Nonnull Integer parentId)
    {
        List<CategoryVo> rtnList = iCategoryMapperDao.lst(CategoryMapper.class, new CategoryVo().setParentId(parentId));
        for (CategoryVo categoryVo : rtnList)
        {
            categoryVo.setSubCategoryVoList(loop(categoryVo.getId()));
        }
        return rtnList;
    }

    public Tuple.Triple<Boolean, String, CategoryVo> opt(Integer id, String displayName, Integer parentId, String controllerName, Integer grouped, Integer seq, Integer visible, Integer operable, Integer deleted)
    {
        Tuple.Triple<Boolean, String, CategoryVo> rtnResult = null;
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setDisplayName(displayName);
        categoryVo.setUrlName(String0.pinyin(displayName));
        categoryVo.setParentId(parentId);
        categoryVo.setControllerName(controllerName);
        categoryVo.setGrouped(Integer0.nullToZero(grouped));
        categoryVo.setSeq(Integer0.nullToZero(seq));
        categoryVo.setVisible(Integer0.nullToZero(visible));
        categoryVo.setOperable(Integer0.nullToZero(operable));
        categoryVo.setDeleted(Integer0.nullToZero(deleted));
        rtnResult = Tuple.of(true, null, categoryVo);
        try
        {
            if (id == null || id == 0)
            {
                iCategoryMapperDao.add(CategoryMapper.class, categoryVo);
            }
            else
            {
                categoryVo.setId(id);
                iCategoryMapperDao.mod(CategoryMapper.class, categoryVo.setModDate(Date0.now()));
            }
            cacheAware.reload(CacheAware.CATEGORY_CACHE);
        }
        catch (Exception e)
        {
            rtnResult = Tuple.of(false, e.toString(), categoryVo);
        }
        return rtnResult;
    }
}
