/*
 * @(#)TagDaoImpl.java		Created at 15/4/22
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.dao.impl;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.ITagDao;
import org.azolla.p.roc.vo.TagVo;
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
public class TagDaoImpl implements ITagDao
{
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private CacheAware cacheAware;

    public List<TagVo> lstWithoutVOD(RowBounds rowBounds)
    {
        return sqlSession.selectList("mapper.tag.lstWithoutVOD", null, rowBounds);
    }

    @Override
    public List<TagVo> lst()
    {
        return sqlSession.selectList("mapper.tag.lst");
    }

    @Override
    public List<TagVo> lstByPostUrlTitle(String postUrlTitle)
    {
        return sqlSession.selectList("mapper.tag.lstByPostUrlTitle", postUrlTitle);
    }

    @Override
    public TagVo getByUrlName(String urlName)
    {
        return sqlSession.selectOne("mapper.tag.getByUrlName", urlName);
    }

    @Override
    public int add(TagVo tagVo)
    {
        int rtn = sqlSession.insert("mapper.tag.add", tagVo);
        if(rtn > 0)
        {
            cacheAware.reload(CacheAware.TAG_CACHE);
        }
        return rtn;
    }

    public int btAdd(List<TagVo> tagVoList)
    {
        return sqlSession.insert("mapper.tag.btAdd",tagVoList);
    }

    public List<TagVo> btLstByUrlNameList(List<String> urlNameList)
    {
        return sqlSession.selectList("mapper.tag.btLstByUrlNameList",urlNameList);
    }

    public int mod(TagVo tagVo)
    {
        int rtn = sqlSession.update("mapper.tag.mod",tagVo);
        if(rtn > 0)
        {
            cacheAware.reload(CacheAware.TAG_CACHE);
        }
        return rtn;
    }

    public int rmvById(int id)
    {
        int rtn = sqlSession.update("mapper.tag.rmvById",id);
        if(rtn > 0)
        {
            cacheAware.reload(CacheAware.TAG_CACHE);
        }
        return rtn;
    }
}
