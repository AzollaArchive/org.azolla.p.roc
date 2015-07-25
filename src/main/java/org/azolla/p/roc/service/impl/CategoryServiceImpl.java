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
import org.azolla.p.roc.dao.ICategoryDao;
import org.azolla.p.roc.service.ICategoryService;
import org.azolla.p.roc.vo.CategoryVo;
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
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private ICategoryDao iCategoryDao;

    @Override
    public List<CategoryVo> lst(String parentUrlName)
    {
        List<CategoryVo> rtnList = iCategoryDao.lstByParentUrlName(parentUrlName);
        for(CategoryVo categoryVo : rtnList)
        {
            List<CategoryVo> subList = lst(categoryVo.getUrlName());
            if(subList.size() > 0)
            {
                categoryVo.setSubCategoryVoList(subList);
            }
        }
        return rtnList;
    }

    public Tuple.Triple<Boolean,String,CategoryVo> opt(int id, String displayName, int parentId, String controllerName, Integer group, int sequence, Integer visible, Integer operable)
    {
        Tuple.Triple<Boolean,String,CategoryVo> rtnResult = null;
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setDisplayName(displayName);
        categoryVo.setUrlName(String0.pinyin(displayName));
        categoryVo.setParentId(parentId);
        categoryVo.setControllerName(controllerName);
        categoryVo.setGroup(Integer0.nullToZero(group));
        categoryVo.setSequence(sequence);
        categoryVo.setVisible(Integer0.nullToZero(visible));
        categoryVo.setOperable(Integer0.nullToZero(operable));
        rtnResult = Tuple.of(true,null,categoryVo);
        try
        {
            if(id == 0)
            {
                iCategoryDao.add(categoryVo);
            }
            else
            {
                categoryVo.setId(id);
                iCategoryDao.mod(categoryVo);
            }
        }
        catch (Exception e)
        {
            rtnResult = Tuple.of(false,e.toString(),categoryVo);
        }
        return rtnResult;
    }
}
