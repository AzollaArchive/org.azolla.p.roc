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

    @Override
    public int add(PostRTagVo postRTagVo)
    {
        return sqlSession.insert("mapper.postRTag.add", postRTagVo);
    }

    @Override
    public int rmv(PostRTagVo postRTagVo)
    {
        return sqlSession.delete("mapper.postRTag.rmv", postRTagVo);
    }

    public List<PostRTagVo> lstByPostId(int postId)
    {
        return sqlSession.selectList("mapper.postRTag.lstByPostId",postId);
    }

    public int rmvByPostId(int postId)
    {
        return sqlSession.delete("mapper.postRTag.rmvByPostId",postId);
    }

    public int btAdd(List<PostRTagVo> list)
    {
        return sqlSession.insert("mapper.postRTag.btAdd",list);
    }
}
