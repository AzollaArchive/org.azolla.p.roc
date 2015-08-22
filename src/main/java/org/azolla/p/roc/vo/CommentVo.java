/*
 * @(#)CommentVo.java		Created at 15/5/1
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.vo;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Table(name = "roc_t_comment")
@Component
public class CommentVo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    private Integer id;
    private String  username;
    private String  email;
    private String  photoUrl;
    private String  content;
    private Integer postId;
    private String  ip;
    @OrderBy
    private Date    addDate;
    private Date    modDate;
    private Date    rmvDate;
    private Integer visible;
    private Integer operable;
    private Integer deleted;

    @Transient
    private PostVo postVo;

    public Integer getId()
    {
        return id;
    }

    public CommentVo setId(Integer id)
    {
        this.id = id;
        return this;
    }

    public String getUsername()
    {
        return username;
    }

    public CommentVo setUsername(String username)
    {
        this.username = username;
        return this;
    }

    public String getEmail()
    {
        return email;
    }

    public CommentVo setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public String getPhotoUrl()
    {
        return photoUrl;
    }

    public CommentVo setPhotoUrl(String photoUrl)
    {
        this.photoUrl = photoUrl;
        return this;
    }

    public String getContent()
    {
        return content;
    }

    public CommentVo setContent(String content)
    {
        this.content = content;
        return this;
    }

    public Integer getPostId()
    {
        return postId;
    }

    public CommentVo setPostId(Integer postId)
    {
        this.postId = postId;
        return this;
    }

    public String getIp()
    {
        return ip;
    }

    public CommentVo setIp(String ip)
    {
        this.ip = ip;
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

    public CommentVo setModDate(Date modDate)
    {
        this.modDate = modDate;
        return this;
    }

    public Date getRmvDate()
    {
        return rmvDate;
    }

    public CommentVo setRmvDate(Date rmvDate)
    {
        this.rmvDate = rmvDate;
        return this;
    }

    public Integer getVisible()
    {
        return visible;
    }

    public CommentVo setVisible(Integer visible)
    {
        this.visible = visible;
        return this;
    }

    public Integer getOperable()
    {
        return operable;
    }

    public CommentVo setOperable(Integer operable)
    {
        this.operable = operable;
        return this;
    }

    public Integer getDeleted()
    {
        return deleted;
    }

    public CommentVo setDeleted(Integer deleted)
    {
        this.deleted = deleted;
        return this;
    }

    public PostVo getPostVo()
    {
        return postVo;
    }

    public CommentVo setPostVo(PostVo postVo)
    {
        this.postVo = postVo;
        return this;
    }
}
