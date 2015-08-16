/*
 * @(#)ConfigServiceImpl.java		Created at 15/4/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service.impl;

import org.azolla.l.ling.collect.Tuple;
import org.azolla.l.ling.lang.Integer0;
import org.azolla.p.roc.aware.CacheAware;
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

    @Autowired
    private CacheAware cacheAware;

    @Override
    public ConcurrentMap<String, String> map()
    {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<String, String>();
        for (ConfigVo configVo : iConfigDao.lst())
        {
            concurrentMap.put(configVo.getRocKey(), configVo.getRocValue());
        }
        return concurrentMap;
    }

    public Tuple.Triple<Boolean, String, ConfigVo> opt(int id, String rocKey, String rocValue, Integer visible, Integer operable)
    {
        ConfigVo configVo = new ConfigVo();
        configVo.setRocKey(rocKey);
        configVo.setRocValue(rocValue);
        configVo.setVisible(Integer0.nullToZero(visible));
        configVo.setOperable(Integer0.nullToZero(operable));

        Tuple.Triple<Boolean, String, ConfigVo> rtnResult = Tuple.of(true, null, configVo);
        try
        {
            if (id == 0)
            {
                iConfigDao.add(configVo);
            }
            else
            {
                configVo.setId(id);
                iConfigDao.mod(configVo);
            }
            cacheAware.reload(CacheAware.CONFIG_CACHE);
        }
        catch (Exception e)
        {
            rtnResult = Tuple.of(false,e.toString(),configVo);
        }
        return rtnResult;
    }
}
