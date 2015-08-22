/*
 * @(#)MapperServiceImpl.java		Created at 15/8/15
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.azolla.p.roc.dao.IMapperDao;
import org.azolla.p.roc.service.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Service
public class MapperServiceImpl<T> implements IMapperService<T>
{
    @Autowired
    private IMapperDao<T> iMapperDao;

    public int add(Class<? extends Mapper<T>> mapperClass, T t)
    {
        return iMapperDao.add(mapperClass, t);
    }

    public int rmv(Class<? extends Mapper<T>> mapperClass, T t)
    {
        return iMapperDao.rmv(mapperClass, t);
    }

    public int mod(Class<? extends Mapper<T>> mapperClass, T t)
    {
        return iMapperDao.mod(mapperClass, t);
    }

    public List<T> lst(Class<? extends Mapper<T>> mapperClass, T t)
    {
        return iMapperDao.lst(mapperClass, t);
    }

    public List<T> lst(Class<? extends Mapper<T>> mapperClass, T t, RowBounds rowBounds)
    {
        return iMapperDao.lst(mapperClass, t, rowBounds);
    }

    public T selectOne(Class<? extends Mapper<T>> mapperClass, T t)
    {
        return iMapperDao.selectOne(mapperClass, t);
    }
}
