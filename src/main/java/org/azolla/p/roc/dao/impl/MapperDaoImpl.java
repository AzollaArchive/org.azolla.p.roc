/*
 * @(#)MapperDaoImpl.java		Created at 15/8/15
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.dao.impl;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.azolla.p.roc.dao.IMapperDao;
import org.azolla.p.roc.mapper.ProfessionalMapper;
import org.azolla.p.roc.vo.ProfessionalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Repository
public class MapperDaoImpl<T> implements IMapperDao<T>
{
    @Autowired
    private SqlSession sqlSession;

    public int add(Class<? extends Mapper<T>> mapperClass, T t)
    {
        return sqlSession.getMapper(mapperClass).insertSelective(t);
    }

    public int rmv(Class<? extends Mapper<T>> mapperClass, T t)
    {
        return sqlSession.getMapper(mapperClass).updateByPrimaryKeySelective(t);
    }

    public List<T> lst(Class<? extends Mapper<T>> mapperClass, T t)
    {
        return sqlSession.getMapper(mapperClass).select(t);
    }

    public List<T> lst(Class<? extends Mapper<T>> mapperClass, T t, RowBounds rowBounds)
    {
        return sqlSession.getMapper(mapperClass).selectByRowBounds(t, rowBounds);
    }
}
