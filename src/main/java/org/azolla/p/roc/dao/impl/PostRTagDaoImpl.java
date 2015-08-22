/*
 * @(#)PostRTagDaoImpl.java		Created at 15/5/5
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.azolla.p.roc.dao.IPostRTagDao;
import org.azolla.p.roc.vo.PostRTagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Repository
public class PostRTagDaoImpl implements IPostRTagDao
{
    @Autowired
    private SqlSession sqlSession;

    public int btAdd(@Nonnull List<PostRTagVo> list)
    {
        return sqlSession.insert("mapper.postRTag.btAdd", list);
    }
}
