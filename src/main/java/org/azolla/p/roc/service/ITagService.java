package org.azolla.p.roc.service;

import org.azolla.l.ling.collect.Tuple;
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
  public List<TagVo> btAddByTagDisplayName(List<String> tagDisplayNameList);

  public Tuple.Triple<Boolean, String, TagVo> opt(Integer id, String displayName, Integer visible, Integer operable, Integer deleted);
}
