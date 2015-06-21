package org.azolla.p.roc.dao;

import org.azolla.p.roc.vo.PostRTagVo;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface IPostRTagDao
{
    public int add(PostRTagVo postRTagVo);

    public int rmv(PostRTagVo postRTagVo);

    public List<PostRTagVo> lstByPostId(int postId);

    public int rmvByPostId(int postId);

    public int btAdd(List<PostRTagVo> list);
}
