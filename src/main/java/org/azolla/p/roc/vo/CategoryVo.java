/*
 * @(#)CategoryVo.java		Created at 15/4/19
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
public class CategoryVo
{
    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    private int    id;
    private String displayName;
    private String urlName;
    private String    parentUrlName;
    private String controllerName;
    private int group;
    private int sequence;
    private Date   addDate;
    private Date   modDate;
    private Date   rmvDate;
    private int    visible;
    private int    valid;
    private int    deleted;
    private List<CategoryVo> subCategoryVoList = Lists.newArrayList();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getUrlName()
    {
        return urlName;
    }

    public void setUrlName(String urlName)
    {
        this.urlName = urlName;
    }

    public String getParentUrlName()
    {
        return parentUrlName;
    }

    public void setParentUrlName(String parentUrlName)
    {
        this.parentUrlName = parentUrlName;
    }

    public String getControllerName()
    {
        return controllerName;
    }

    public void setControllerName(String controllerName)
    {
        this.controllerName = controllerName;
    }

    public int getGroup()
    {
        return group;
    }

    public void setGroup(int group)
    {
        this.group = group;
    }

    public int getSequence()
    {
        return sequence;
    }

    public void setSequence(int sequence)
    {
        this.sequence = sequence;
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

    public List<CategoryVo> getSubCategoryVoList()
    {
        return subCategoryVoList;
    }

    public void setSubCategoryVoList(List<CategoryVo> subCategoryVoList)
    {
        this.subCategoryVoList = subCategoryVoList;
    }
}
