/*
 * @(#)CommentDaoImpl.java		Created at 15/5/1
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.azolla.p.roc.dao.ICommentDao;
import org.azolla.p.roc.vo.CommentVo;
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
public class CommentDaoImpl implements ICommentDao
{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<CommentVo> lstByPostId(int postId)
    {
        return sqlSession.selectList("mapper.comment.lstByPostId",postId);
    }

    public int add(CommentVo commentVo)
    {
        return sqlSession.insert("mapper.comment.add",commentVo);
    }
}
