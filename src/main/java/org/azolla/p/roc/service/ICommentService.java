package org.azolla.p.roc.service;

import org.azolla.p.roc.vo.CommentVo;

import javax.servlet.http.HttpServletRequest;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface ICommentService
{
    public CommentVo add(Integer postId, String commentName, String commentEmail, String commentContent,HttpServletRequest request);
}
