/*
 * @(#)ConfigVo.java		Created at 15/4/21
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
@Table(name = "roc_t_config")
@Component
public class ConfigVo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    private Integer id;
    private String  rocKey;
    private String  rocValue;
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

    public ConfigVo setId(Integer id)
    {
        this.id = id;
        return this;
    }

    public String getRocKey()
    {
        return rocKey;
    }

    public ConfigVo setRocKey(String rocKey)
    {
        this.rocKey = rocKey;
        return this;
    }

    public String getRocValue()
    {
        return rocValue;
    }

    public ConfigVo setRocValue(String rocValue)
    {
        this.rocValue = rocValue;
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

    public ConfigVo setModDate(Date modDate)
    {
        this.modDate = modDate;
        return this;
    }

    public Date getRmvDate()
    {
        return rmvDate;
    }

    public ConfigVo setRmvDate(Date rmvDate)
    {
        this.rmvDate = rmvDate;
        return this;
    }

    public Integer getVisible()
    {
        return visible;
    }

    public ConfigVo setVisible(Integer visible)
    {
        this.visible = visible;
        return this;
    }

    public Integer getOperable()
    {
        return operable;
    }

    public ConfigVo setOperable(Integer operable)
    {
        this.operable = operable;
        return this;
    }

    public Integer getDeleted()
    {
        return deleted;
    }

    public ConfigVo setDeleted(Integer deleted)
    {
        this.deleted = deleted;
        return this;
    }
}