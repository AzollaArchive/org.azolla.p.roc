/*
 * @(#)PostDaoImpl.java		Created at 15/4/21
 *
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.azolla.p.roc.dao.impl;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.azolla.p.roc.dao.IPostDao;
import org.azolla.p.roc.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Repository
public class PostDaoImpl implements IPostDao
{
  @Autowired
  private SqlSession sqlSession;

  @Override
  public List<PostVo> lstByTagUrlName(String tagUrlName, RowBounds rowBounds)
  {
    return sqlSession.selectList("mapper.post.lstByTagUrlName", tagUrlName, rowBounds);
  }

  @Override
  public List<PostVo> search(@Nonnull String search, @Nonnull RowBounds rowBounds)
  {
    return sqlSession.selectList("mapper.post.search", search, rowBounds);
  }
}
