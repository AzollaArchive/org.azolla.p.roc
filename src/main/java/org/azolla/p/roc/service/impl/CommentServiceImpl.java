/*
 * @(#)CommentServiceImpl.java		Created at 15/6/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.service.impl;

import org.azolla.p.roc.dao.ICommentDao;
import org.azolla.p.roc.service.ICommentService;
import org.azolla.p.roc.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Service
public class CommentServiceImpl implements ICommentService
{
    @Autowired
    private ICommentDao iCommentDao;

    public CommentVo add(Integer postId, String commentName, String commentEmail, String commentContent,HttpServletRequest request)
    {
        CommentVo rtnCommentVo = new CommentVo();
        rtnCommentVo.setPostId(postId);
        rtnCommentVo.setUsername(commentName);
        rtnCommentVo.setEmail(commentEmail);
        rtnCommentVo.setContent(commentContent);
        rtnCommentVo.setIp(request.getRemoteHost());

        if(iCommentDao.add(rtnCommentVo) <= 0)
        {
            rtnCommentVo = null;
        }

        return rtnCommentVo;
    }
}
