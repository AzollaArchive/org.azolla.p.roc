/*
 * @(#)PostDaoImpl.java		Created at 15/4/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.dao.impl;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.azolla.p.roc.dao.IPostDao;
import org.azolla.p.roc.vo.PostVo;
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
public class PostDaoImpl implements IPostDao
{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<PostVo> lstWithoutVOD(RowBounds rowBounds)
    {
        return sqlSession.selectList("mapper.post.lstWithoutVOD",null,rowBounds);
    }

    @Override
    public List<PostVo> lst(RowBounds rowBounds)
    {
        return sqlSession.selectList("mapper.post.lst",null,rowBounds);
    }

    @Override
    public List<PostVo> lstByCategoryUrlName(String categoryUrlName, RowBounds rowBounds)
    {
        return sqlSession.selectList("mapper.post.lstByCategoryUrlName",categoryUrlName,rowBounds);
    }

    @Override
    public List<PostVo> lstByTagUrlName(String tagUrlName, RowBounds rowBounds)
    {
        return sqlSession.selectList("mapper.post.lstByTagUrlName",tagUrlName,rowBounds);
    }

    public PostVo getByUrlTitle(String urlTitle)
    {
        return  sqlSession.selectOne("mapper.post.getByUrlTitle",urlTitle);
    }

    public int add(PostVo postVo)
    {
        return sqlSession.insert("mapper.post.add",postVo);
    }

    public int mod(PostVo postVo)
    {
        return sqlSession.update("mapper.post.mod",postVo);
    }

    public int rmvById(int id)
    {
        return sqlSession.update("mapper.post.rmvById",id);
    }
}
