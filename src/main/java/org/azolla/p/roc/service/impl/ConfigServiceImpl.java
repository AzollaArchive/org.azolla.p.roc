/*
 * @(#)ConfigServiceImpl.java		Created at 15/4/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service.impl;

import org.azolla.p.roc.dao.IConfigDao;
import org.azolla.p.roc.service.IConfigService;
import org.azolla.p.roc.vo.ConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Service
public class ConfigServiceImpl implements IConfigService
{
    @Autowired
    private IConfigDao iConfigDao;

    @Override
    public ConcurrentMap<String, String> map()
    {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<String, String>();
        for(ConfigVo configVo : iConfigDao.lst())
        {
            concurrentMap.put(configVo.getRocKey(),configVo.getRocValue());
        }
        return concurrentMap;
    }
}
