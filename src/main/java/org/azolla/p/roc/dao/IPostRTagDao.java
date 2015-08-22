package org.azolla.p.roc.dao;

import org.azolla.p.roc.vo.PostRTagVo;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface IPostRTagDao
{
    public int btAdd(@Nonnull List<PostRTagVo> list);
}
