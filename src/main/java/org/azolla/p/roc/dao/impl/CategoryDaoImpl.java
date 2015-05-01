/*
 * @(#)CategoryDaoImpl.java		Created at 15/4/19
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.azolla.p.roc.dao.ICategoryDao;
import org.azolla.p.roc.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Repository
public class CategoryDaoImpl implements ICategoryDao
{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<CategoryVo> lst(String parentUrlName)
    {
        return sqlSession.selectList("mapper.category.lst",parentUrlName);
    }

    @Override
    public CategoryVo get(String urlName)
    {
        return sqlSession.selectOne("mapper.category.get",urlName);
    }
}
