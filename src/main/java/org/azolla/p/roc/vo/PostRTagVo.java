/*
 * @(#)PostRTagVO.java		Created at 15/5/5
 *
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.azolla.p.roc.vo;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Component
public class PostRTagVo
{
    //db column
    private int    id;
    private int    postId;
    private int    tagId;
    private Date   addDate;
    private Date   modDate;
    private Date   rmvDate;
    private int    visible;
    private int    operable;
    private int    deleted;

    private PostVo postVo;
    private TagVo  tagVo;

    public PostRTagVo(){}

    public PostRTagVo(int postId,int tagId)
    {
        this.postId = postId;
        this.tagId = tagId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPostId()
    {
        return postId;
    }

    public void setPostId(int postId)
    {
        this.postId = postId;
    }

    public int getTagId()
    {
        return tagId;
    }

    public void setTagId(int tagId)
    {
        this.tagId = tagId;
    }

    public Date getAddDate()
    {
        return addDate;
    }

    public void setAddDate(Date addDate)
    {
        this.addDate = addDate;
    }

    public Date getModDate()
    {
        return modDate;
    }

    public void setModDate(Date modDate)
    {
        this.modDate = modDate;
    }

    public Date getRmvDate()
    {
        return rmvDate;
    }

    public void setRmvDate(Date rmvDate)
    {
        this.rmvDate = rmvDate;
    }

    public int getVisible()
    {
        return visible;
    }

    public void setVisible(int visible)
    {
        this.visible = visible;
    }

    public int getOperable()
    {
        return operable;
    }

    public void setOperable(int operable)
    {
        this.operable = operable;
    }

    public int getDeleted()
    {
        return deleted;
    }

    public void setDeleted(int deleted)
    {
        this.deleted = deleted;
    }

    public PostVo getPostVo()
    {
        return postVo;
    }

    public void setPostVo(PostVo postVo)
    {
        this.postVo = postVo;
    }

    public TagVo getTagVo()
    {
        return tagVo;
    }

    public void setTagVo(TagVo tagVo)
    {
        this.tagVo = tagVo;
    }
}