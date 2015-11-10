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
  @OrderBy
  private Integer id;
  private Integer tagId;
  private Integer score;
  private String  ip;
  @OrderBy
  private Date    addDate;
  private Date    modDate;
  private Date    rmvDate;
  private Integer visible = 1;
  private Integer operable;
  private Integer deleted = 0;

  @Transient
  private TagVo tagVo;

  public Integer getId()
  {
    return id;
  }

  public ProfessionalVo setId(Integer id)
  {
    this.id = id;
    return this;
  }

  public Integer getTagId()
  {
    return tagId;
  }

  public ProfessionalVo setTagId(Integer tagId)
  {
    this.tagId = tagId;
    return this;
  }

  public Integer getScore()
  {
    return score;
  }

  public ProfessionalVo setScore(Integer score)
  {
    this.score = score;
    return this;
  }

  public String getIp()
  {
    return ip;
  }

  public ProfessionalVo setIp(String ip)
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

  public ProfessionalVo setModDate(Date modDate)
  {
    this.modDate = modDate;
    return this;
  }

  public Date getRmvDate()
  {
    return rmvDate;
  }

  public ProfessionalVo setRmvDate(Date rmvDate)
  {
    this.rmvDate = rmvDate;
    return this;
  }

  public Integer getVisible()
  {
    return visible;
  }

  public ProfessionalVo setVisible(Integer visible)
  {
    this.visible = visible;
    return this;
  }

  public Integer getOperable()
  {
    return operable;
  }

  public ProfessionalVo setOperable(Integer operable)
  {
    this.operable = operable;
    return this;
  }

  public Integer getDeleted()
  {
    return deleted;
  }

  public ProfessionalVo setDeleted(Integer deleted)
  {
    this.deleted = deleted;
    return this;
  }

  public TagVo getTagVo()
  {
    return tagVo;
  }

  public ProfessionalVo setTagVo(TagVo tagVo)
  {
    this.tagVo = tagVo;
    return this;
  }
}
