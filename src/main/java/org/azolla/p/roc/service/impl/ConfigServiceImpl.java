/*
 * @(#)ConfigServiceImpl.java		Created at 15/4/21
 *
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.azolla.p.roc.service.impl;

import org.azolla.l.ling.collect.Tuple;
import org.azolla.l.ling.lang.Integer0;
import org.azolla.l.ling.util.Date0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.dao.IMapperDao;
import org.azolla.p.roc.mapper.ConfigMapper;
import org.azolla.p.roc.service.IConfigService;
import org.azolla.p.roc.vo.ConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  private CacheAware           cacheAware;
  @Autowired
  private IMapperDao<ConfigVo> iConfigMapperDao;

  public Tuple.Triple<Boolean, String, ConfigVo> opt(Integer id, String rocKey, String rocValue, Integer visible, Integer operable, Integer deleted)
  {
    ConfigVo configVo = new ConfigVo();
    configVo.setRocKey(rocKey);
    configVo.setRocValue(rocValue);
    configVo.setVisible(Integer0.nullToZero(visible));
    configVo.setOperable(Integer0.nullToZero(operable));
    configVo.setDeleted(Integer0.nullToZero(deleted));

    Tuple.Triple<Boolean, String, ConfigVo> rtnResult = Tuple.of(true, null, configVo);
    try
    {
      if (Integer0.isNullOrZero(id))
      {
        iConfigMapperDao.add(ConfigMapper.class, configVo);
      }
      else
      {
        configVo.setId(id);
        iConfigMapperDao.mod(ConfigMapper.class, configVo.setModDate(Date0.now()));
      }
      cacheAware.reload(CacheAware.CONFIG_CACHE);
    }
    catch (Exception e)
    {
      rtnResult = Tuple.of(false, e.toString(), configVo);
    }
    return rtnResult;
  }
}
