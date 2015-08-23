/*
 * @(#)IConfigService.java		Created at 15/4/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service;

import org.azolla.l.ling.collect.Tuple;
import org.azolla.p.roc.vo.ConfigVo;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface IConfigService
{
    public Tuple.Triple<Boolean, String, ConfigVo> opt(Integer id, String rocKey, String rocValue, Integer visible, Integer operable, Integer deleted);
}
