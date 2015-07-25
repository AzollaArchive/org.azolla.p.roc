package org.azolla.p.roc.service;

import org.azolla.l.ling.collect.Tuple;
import org.azolla.p.roc.vo.CategoryVo;
import org.azolla.p.roc.vo.PostVo;

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

    public Tuple.Triple<Boolean,String,CategoryVo> opt(int id, String displayName, int parentId, String controllerName, Integer group, int sequence, Integer visible, Integer operable);
}
