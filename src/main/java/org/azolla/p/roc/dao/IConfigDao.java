package org.azolla.p.roc.dao;

import org.apache.ibatis.session.RowBounds;
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
    public List<ConfigVo> lstWithoutVOD(RowBounds rowBounds);

    public List<ConfigVo> lst();

    public ConfigVo getByRocKey(String rocKey);

    public int add(ConfigVo configVo);

    public int mod(ConfigVo configVo);

    public int rmvById(int id);
}
