/*
 * @(#)UserDetailService.java		Created at 15/4/18
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service.impl;

import org.azolla.p.roc.dao.IMapperDao;
import org.azolla.p.roc.mapper.UserDetailsMapper;
import org.azolla.p.roc.vo.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
//已采用配置方式。
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private IMapperDao<UserDetailsImpl> iUserDetailsMapDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return iUserDetailsMapDao.selectOne(UserDetailsMapper.class, new UserDetailsImpl().setUsername(username));
    }
}
