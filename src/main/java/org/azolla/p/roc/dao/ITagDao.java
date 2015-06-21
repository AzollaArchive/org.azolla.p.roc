package org.azolla.p.roc.dao;

import org.azolla.p.roc.vo.TagVo;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface ITagDao
{
    public List<TagVo> lst();

    public List<TagVo> lstByPostUrlTitle(String postUrlTitle);

    public TagVo getByUrlName(String urlName);

    public int add(TagVo tagVo);

    public int btAdd(List<TagVo> tagVoList);

    public List<TagVo> btLstByUrlNameList(List<String> urlNameList);
}
