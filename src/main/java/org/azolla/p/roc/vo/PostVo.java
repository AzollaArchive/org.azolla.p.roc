/*
 * @(#)PostVo.java		Created at 15/4/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.vo;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Component
public class PostVo
{
    //db column
    private int    id;
    private String title;
    private String urlTitle;
    private String content;
    private int    categoryId;
    private Date   addDate;
    private Date   modDate;
    private Date   rmvDate;
    private int    visible;
    private int    operable;
    private int    deleted;

    private CategoryVo categoryVo;
    private List<TagVo> tagVoList = Lists.newArrayList();
    private List<CommentVo> commentVoList = Lists.newArrayList();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getUrlTitle()
    {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle)
    {
        this.urlTitle = urlTitle;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
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

    public CategoryVo getCategoryVo()
    {
        return categoryVo;
    }

    public void setCategoryVo(CategoryVo categoryVo)
    {
        this.categoryVo = categoryVo;
    }

    public List<TagVo> getTagVoList()
    {
        return tagVoList;
    }

    public void setTagVoList(List<TagVo> tagVoList)
    {
        this.tagVoList = tagVoList;
    }

    public List<CommentVo> getCommentVoList()
    {
        return commentVoList;
    }

    public void setCommentVoList(List<CommentVo> commentVoList)
    {
        this.commentVoList = commentVoList;
    }
}
