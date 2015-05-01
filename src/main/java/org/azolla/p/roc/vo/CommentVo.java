/*
 * @(#)CommentVo.java		Created at 15/5/1
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.vo;

import java.util.Date;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class CommentVo
{
    private int    id;
    private String username;
    private String email;
    private String content;
    private String postUrlTitle;
    private Date   addDate;
    private Date   modDate;
    private Date   rmvDate;
    private int    visible;
    private int    valid;
    private int    deleted;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getPostUrlTitle()
    {
        return postUrlTitle;
    }

    public void setPostUrlTitle(String postUrlTitle)
    {
        this.postUrlTitle = postUrlTitle;
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

    public int getValid()
    {
        return valid;
    }

    public void setValid(int valid)
    {
        this.valid = valid;
    }

    public int getDeleted()
    {
        return deleted;
    }

    public void setDeleted(int deleted)
    {
        this.deleted = deleted;
    }
}
