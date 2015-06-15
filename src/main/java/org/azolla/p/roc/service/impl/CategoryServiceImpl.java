/*
 * @(#)CategoryServiceImpl.java		Created at 15/4/19
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service.impl;

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

    @Override
    public List<CategoryVo> lst()
    {
        return iCategoryDao.lst();
    }

    @Override
    public CategoryVo getByUrlName(String urlName)
    {
        return iCategoryDao.getByUrlName(urlName);
    }
}
