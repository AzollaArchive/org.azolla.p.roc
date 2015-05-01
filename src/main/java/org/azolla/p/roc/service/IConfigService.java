/*
 * @(#)IConfigService.java		Created at 15/4/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service;

import org.azolla.p.roc.vo.ConfigVo;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface IConfigService
{
    public List<ConfigVo> lst();

    public ConfigVo lst(String rocKey);

    public ConcurrentMap<String,String> map();

    public String value(String rocKey);
}
