/*
 * @(#)IMapperDao.java		Created at 15/8/15
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.dao;

import org.apache.ibatis.session.RowBounds;
import org.azolla.p.roc.vo.ProfessionalVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface IMapperDao<T>
{
    public int add(Class<? extends Mapper<T>> mapperClass, T t);

    public int rmv(Class<? extends Mapper<T>> mapperClass, T t);

    public List<T> lst(Class<? extends Mapper<T>> mapperClass, T t);

    public List<T> lst(Class<? extends Mapper<T>> mapperClass, T t, RowBounds rowBounds);
}
