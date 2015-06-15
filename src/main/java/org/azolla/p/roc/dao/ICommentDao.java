package org.azolla.p.roc.dao;

import org.azolla.p.roc.vo.CommentVo;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface ICommentDao
{
    public List<CommentVo> lstByPostId(int postId);
}
