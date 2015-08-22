package org.azolla.p.roc.service;

import org.azolla.l.ling.collect.Tuple;
import org.azolla.p.roc.vo.CategoryVo;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface ICategoryService
{
    public List<CategoryVo> loop(@Nonnull Integer parentId);

    public Tuple.Triple<Boolean, String, CategoryVo> opt(Integer id, String displayName, Integer parentId, String controllerName, Integer group, Integer sequence, Integer visible, Integer operable);
}
