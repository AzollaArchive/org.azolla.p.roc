/*
 * @(#)ProfessionalVo.java		Created at 15/8/15
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
@Table(name = "roc_t_professional")
@Component
public class ProfessionalVo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer scoreId;
    private Integer scoreValue;

    private String  ip;
    private Date    addDate;
    private Date    modDate;
    private Date    rmvDate;
    private Integer visible;
    private Integer operable;
    private Integer deleted;

    @Transient
    private String scoreName;

    public ProfessionalVo(){}

    public ProfessionalVo(Integer scoreId, Integer scoreValue, String ip)
    {
        this.scoreId = scoreId;
        this.scoreValue = scoreValue;
        this.ip = ip;
    }

    public ProfessionalVo(Integer deleted)
    {
        this.deleted = deleted;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getScoreId()
    {
        return scoreId;
    }

    public void setScoreId(Integer scoreId)
    {
        this.scoreId = scoreId;
    }

    public Integer getScoreValue()
    {
        return scoreValue;
    }

    public void setScoreValue(Integer scoreValue)
    {
        this.scoreValue = scoreValue;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
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

    public Integer getVisible()
    {
        return visible;
    }

    public void setVisible(Integer visible)
    {
        this.visible = visible;
    }

    public Integer getOperable()
    {
        return operable;
    }

    public void setOperable(Integer operable)
    {
        this.operable = operable;
    }

    public Integer getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Integer deleted)
    {
        this.deleted = deleted;
    }

    public String getScoreName()
    {
        return scoreName;
    }

    public void setScoreName(String scoreName)
    {
        this.scoreName = scoreName;
    }
}
