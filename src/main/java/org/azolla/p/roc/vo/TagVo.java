/*
 * @(#)TagVo.java		Created at 15/4/22
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
@Table(name = "roc_t_tag")
@Component
public class TagVo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    private Integer id;
    private String  displayName;
    private String  urlName;
    @OrderBy
    private Date    addDate;
    private Date    modDate;
    private Date    rmvDate;
    private Integer visible;
    private Integer operable;
    private Integer deleted;

    public Integer getId()
    {
        return id;
    }

    public TagVo setId(Integer id)
    {
        this.id = id;
        return this;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public TagVo setDisplayName(String displayName)
    {
        this.displayName = displayName;
        return this;
    }

    public String getUrlName()
    {
        return urlName;
    }

    public TagVo setUrlName(String urlName)
    {
        this.urlName = urlName;
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

    public TagVo setModDate(Date modDate)
    {
        this.modDate = modDate;
        return this;
    }

    public Date getRmvDate()
    {
        return rmvDate;
    }

    public TagVo setRmvDate(Date rmvDate)
    {
        this.rmvDate = rmvDate;
        return this;
    }

    public Integer getVisible()
    {
        return visible;
    }

    public TagVo setVisible(Integer visible)
    {
        this.visible = visible;
        return this;
    }

    public Integer getOperable()
    {
        return operable;
    }

    public TagVo setOperable(Integer operable)
    {
        this.operable = operable;
        return this;
    }

    public Integer getDeleted()
    {
        return deleted;
    }

    public TagVo setDeleted(Integer deleted)
    {
        this.deleted = deleted;
        return this;
    }
}
