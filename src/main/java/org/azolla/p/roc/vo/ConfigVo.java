/*
 * @(#)ConfigVo.java		Created at 15/4/21
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
public class ConfigVo
{
    private int    id;
    private String rocKey;
    private String rocValue;
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

    public String getRocKey()
    {
        return rocKey;
    }

    public void setRocKey(String rocKey)
    {
        this.rocKey = rocKey;
    }

    public String getRocValue()
    {
        return rocValue;
    }

    public void setRocValue(String rocValue)
    {
        this.rocValue = rocValue;
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
