package org.azolla.p.roc.service;

import org.azolla.p.roc.vo.CategoryVo;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface ICategoryService
{
    public List<CategoryVo> lst(String parentUrlName);

    public List<CategoryVo> lst();

    public CategoryVo getByUrlName(String urlName);
}
