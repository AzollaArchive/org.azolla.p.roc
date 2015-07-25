package org.azolla.p.roc.dao;

import org.apache.ibatis.session.RowBounds;
import org.azolla.p.roc.vo.CategoryVo;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface ICategoryDao
{
    public List<CategoryVo> fullLstWithoutVOD(RowBounds rowBounds);

    public List<CategoryVo> fullLstByIdWithoutVOD(int id, RowBounds rowBounds);

    public List<CategoryVo> lstByParentId(int parentId);

    public List<CategoryVo> lstByParentUrlName(String parentUrlName);

    public List<CategoryVo> lst();

    public CategoryVo getByUrlName(String urlName);

    public CategoryVo getById(int id);

    public int rmvById(int id);

    public int add(CategoryVo categoryVo);

    public int mod(CategoryVo categoryVo);
}
