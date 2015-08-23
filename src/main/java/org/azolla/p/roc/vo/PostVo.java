/*
 * @(#)PostVo.java		Created at 15/4/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.vo;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Table(name = "roc_t_post")
@Component
public class PostVo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy(value = "desc")
    private Integer id;
    private String  title;
    private String  urlTitle;
    private String  content;
    private Integer categoryId;
    @OrderBy(value = "desc")
    private Date    addDate;
    private Date    modDate;
    private Date    rmvDate;
    private Integer visible = 1;
    private Integer operable;
    private Integer deleted = 0;

    @Transient
    private CategoryVo categoryVo;
    @Transient
    private List<TagVo>     tagVoList     = Lists.newArrayList();
    @Transient
    private List<CommentVo> commentVoList = Lists.newArrayList();

    public Integer getId()
    {
        return id;
    }

    public PostVo setId(Integer id)
    {
        this.id = id;
        return this;
    }

    public String getTitle()
    {
        return title;
    }

    public PostVo setTitle(String title)
    {
        this.title = title;
        return this;
    }

    public String getUrlTitle()
    {
        return urlTitle;
    }

    public PostVo setUrlTitle(String urlTitle)
    {
        this.urlTitle = urlTitle;
        return this;
    }

    public String getContent()
    {
        return content;
    }

    public PostVo setContent(String content)
    {
        this.content = content;
        return this;
    }

    public Integer getCategoryId()
    {
        return categoryId;
    }

    public PostVo setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
        return this;
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

    public PostVo setModDate(Date modDate)
    {
        this.modDate = modDate;
        return this;
    }

    public Date getRmvDate()
    {
        return rmvDate;
    }

    public PostVo setRmvDate(Date rmvDate)
    {
        this.rmvDate = rmvDate;
        return this;
    }

    public Integer getVisible()
    {
        return visible;
    }

    public PostVo setVisible(Integer visible)
    {
        this.visible = visible;
        return this;
    }

    public Integer getOperable()
    {
        return operable;
    }

    public PostVo setOperable(Integer operable)
    {
        this.operable = operable;
        return this;
    }

    public Integer getDeleted()
    {
        return deleted;
    }

    public PostVo setDeleted(Integer deleted)
    {
        this.deleted = deleted;
        return this;
    }

    public CategoryVo getCategoryVo()
    {
        return categoryVo;
    }

    public PostVo setCategoryVo(CategoryVo categoryVo)
    {
        this.categoryVo = categoryVo;
        return this;
    }

    public List<TagVo> getTagVoList()
    {
        return tagVoList;
    }

    public PostVo setTagVoList(List<TagVo> tagVoList)
    {
        this.tagVoList = tagVoList;
        return this;
    }

    public List<CommentVo> getCommentVoList()
    {
        return commentVoList;
    }

    public PostVo setCommentVoList(List<CommentVo> commentVoList)
    {
        this.commentVoList = commentVoList;
        return this;
    }
}
