/*
 * @(#)UserDetailsImplTest.java		Created at 15/5/2
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.vo.impl;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class UserDetailsImplTest
{
    @Test
    public void testMd5PasswordEncoder()
    {
        System.out.println(new Md5PasswordEncoder().encodePassword("ShaneKing","ShaneKing"));
    }
}
