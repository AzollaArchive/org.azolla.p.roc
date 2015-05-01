package org.azolla.p.roc.service;

import org.azolla.p.roc.vo.TagVo;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface ITagService
{
    public List<TagVo> lst();

    public TagVo get(String urlName);
}
