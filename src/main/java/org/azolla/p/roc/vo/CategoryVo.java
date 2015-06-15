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
    public static final int LEFT_ROOT_ID  = 1;
    public static final int RIGHT_ROOT_ID = 2;

    public static final String LEFT_ROOT_URL  = "left";
    public static final String RIGHT_ROOT_URL = "right";

    //db column
    private int        id;
    private String     displayName;
    private String     urlName;
    private int        parentId;
    private String     controllerName;
    private int        group;
    private int        sequence;
    private Date       addDate;
    private Date       modDate;
    private Date       rmvDate;
    private int        visible;
    private int        operable;
    private int        deleted;

    private CategoryVo parentCategoryVo;
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

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
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

    public CategoryVo getParentCategoryVo()
    {
        return parentCategoryVo;
    }

    public void setParentCategoryVo(CategoryVo parentCategoryVo)
    {
        this.parentCategoryVo = parentCategoryVo;
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
