/*
 * @(#)CategoryVo.java		Created at 15/4/19
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
@Table(name = "roc_t_category")
@Component
public class CategoryVo
{
    @Transient
    public static final Integer LEFT_ROOT_ID   = 2;
    @Transient
    public static final Integer RIGHT_ROOT_ID  = 3;
    @Transient
    public static final String  LEFT_ROOT_URL  = "left";
    @Transient
    public static final String  RIGHT_ROOT_URL = "right";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    private Integer id;
    private String  displayName;
    private String  urlName;
    private Integer parentId;
    private String  controllerName;
    private Integer grouped;
    @OrderBy
    private Integer seq;
    private Date    addDate;
    private Date    modDate;
    private Date    rmvDate;
    private Integer visible = 1;
    private Integer operable;
    private Integer deleted = 0;

    @Transient
    private CategoryVo parentCategoryVo;
    @Transient
    private List<CategoryVo> subCategoryVoList = Lists.newArrayList();

    public Integer getId()
    {
        return id;
    }

    public CategoryVo setId(Integer id)
    {
        this.id = id;
        return this;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public CategoryVo setDisplayName(String displayName)
    {
        this.displayName = displayName;
        return this;
    }

    public String getUrlName()
    {
        return urlName;
    }

    public CategoryVo setUrlName(String urlName)
    {
        this.urlName = urlName;
        return this;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public CategoryVo setParentId(Integer parentId)
    {
        this.parentId = parentId;
        return this;
    }

    public String getControllerName()
    {
        return controllerName;
    }

    public CategoryVo setControllerName(String controllerName)
    {
        this.controllerName = controllerName;
        return this;
    }

    public Integer getGrouped()
    {
        return grouped;
    }

    public CategoryVo setGrouped(Integer grouped)
    {
        this.grouped = grouped;
        return this;
    }

    public Integer getSeq()
    {
        return seq;
    }

    public CategoryVo setSeq(Integer seq)
    {
        this.seq = seq;
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

    public CategoryVo setModDate(Date modDate)
    {
        this.modDate = modDate;
        return this;
    }

    public Date getRmvDate()
    {
        return rmvDate;
    }

    public CategoryVo setRmvDate(Date rmvDate)
    {
        this.rmvDate = rmvDate;
        return this;
    }

    public Integer getVisible()
    {
        return visible;
    }

    public CategoryVo setVisible(Integer visible)
    {
        this.visible = visible;
        return this;
    }

    public Integer getOperable()
    {
        return operable;
    }

    public CategoryVo setOperable(Integer operable)
    {
        this.operable = operable;
        return this;
    }

    public Integer getDeleted()
    {
        return deleted;
    }

    public CategoryVo setDeleted(Integer deleted)
    {
        this.deleted = deleted;
        return this;
    }

    public CategoryVo getParentCategoryVo()
    {
        return parentCategoryVo;
    }

    public CategoryVo setParentCategoryVo(CategoryVo parentCategoryVo)
    {
        this.parentCategoryVo = parentCategoryVo;
        return this;
    }

    public List<CategoryVo> getSubCategoryVoList()
    {
        return subCategoryVoList;
    }

    public CategoryVo setSubCategoryVoList(List<CategoryVo> subCategoryVoList)
    {
        this.subCategoryVoList = subCategoryVoList;
        return this;
    }
}
