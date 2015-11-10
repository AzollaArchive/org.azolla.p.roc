package org.azolla.p.roc.dao;

import org.azolla.p.roc.vo.TagVo;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface ITagDao
{
  public int btAdd(@Nonnull List<TagVo> tagVoList);

  public List<TagVo> lstByPostUrlTitle(String postUrlTitle);

  public List<TagVo> lstByUrlNameList(@Nonnull List<String> urlNameList);
}
