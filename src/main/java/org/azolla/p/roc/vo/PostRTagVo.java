/*
 * @(#)PostRTagVO.java		Created at 15/5/5
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
@Table(name = "roc_t_post_r_tag")
@Component
public class PostRTagVo
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @OrderBy
  private Integer id;
  private Integer postId;
  private Integer tagId;
  @OrderBy
  private Date    addDate;
  private Date    modDate;
  private Date    rmvDate;
  private Integer visible = 1;
  private Integer operable;
  private Integer deleted = 0;

  @Transient
  private PostVo postVo;
  @Transient
  private TagVo  tagVo;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public Integer getPostId()
  {
    return postId;
  }

  public PostRTagVo setPostId(Integer postId)
  {
    this.postId = postId;
    return this;
  }

  public Integer getTagId()
  {
    return tagId;
  }

  public PostRTagVo setTagId(Integer tagId)
  {
    this.tagId = tagId;
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

  public PostRTagVo setModDate(Date modDate)
  {
    this.modDate = modDate;
    return this;
  }

  public Date getRmvDate()
  {
    return rmvDate;
  }

  public PostRTagVo setRmvDate(Date rmvDate)
  {
    this.rmvDate = rmvDate;
    return this;
  }

  public Integer getVisible()
  {
    return visible;
  }

  public PostRTagVo setVisible(Integer visible)
  {
    this.visible = visible;
    return this;
  }

  public Integer getOperable()
  {
    return operable;
  }

  public PostRTagVo setOperable(Integer operable)
  {
    this.operable = operable;
    return this;
  }

  public Integer getDeleted()
  {
    return deleted;
  }

  public PostRTagVo setDeleted(Integer deleted)
  {
    this.deleted = deleted;
    return this;
  }

  public PostVo getPostVo()
  {
    return postVo;
  }

  public PostRTagVo setPostVo(PostVo postVo)
  {
    this.postVo = postVo;
    return this;
  }

  public TagVo getTagVo()
  {
    return tagVo;
  }

  public PostRTagVo setTagVo(TagVo tagVo)
  {
    this.tagVo = tagVo;
    return this;
  }
}
