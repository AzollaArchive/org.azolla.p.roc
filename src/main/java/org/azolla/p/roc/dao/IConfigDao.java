package org.azolla.p.roc.dao;

import org.azolla.p.roc.vo.ConfigVo;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface IConfigDao
{
    public List<ConfigVo> lst();

    public ConfigVo get(String rocKey);
}
