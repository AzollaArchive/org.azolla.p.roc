/*
 * @(#)CategoryDaoImpl.java		Created at 15/4/19
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.dao.impl;

import org.apache.ibatis.session.RowBounds;
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

    public List<CategoryVo> fullLstWithoutVOD(RowBounds rowBounds)
    {
        return sqlSession.selectList("mapper.category.fullLstWithoutVOD",null,rowBounds);
    }

    public List<CategoryVo> fullLstByIdWithoutVOD(int id, RowBounds rowBounds)
    {
        return sqlSession.selectList("mapper.category.fullLstByIdWithoutVOD",id,rowBounds);
    }

    @Override
    public List<CategoryVo> lstByParentId(int parentId)
    {
        return sqlSession.selectList("mapper.category.lstByParentId",parentId);
    }

    @Override
    public List<CategoryVo> lstByParentUrlName(String parentUrlName)
    {
        return sqlSession.selectList("mapper.category.lstByParentUrlName",parentUrlName);
    }

    @Override
    public List<CategoryVo> lst()
    {
        return sqlSession.selectList("mapper.category.lst");
    }

    @Override
    public CategoryVo getByUrlName(String urlName)
    {
        return sqlSession.selectOne("mapper.category.getByUrlName",urlName);
    }

    @Override
    public CategoryVo getById(int id)
    {
        return sqlSession.selectOne("mapper.category.getById",id);
    }
}
