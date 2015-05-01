/*
 * @(#)UserDetailsDaoImpl.java		Created at 15/4/19
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.azolla.p.roc.dao.IUserDetailsDao;
import org.azolla.p.roc.vo.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Repository
public class UserDetailsDaoImpl implements IUserDetailsDao
{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public UserDetailsImpl lst(String username)
    {
        return sqlSession.selectOne("mapper.user.lst",username);
    }
}
