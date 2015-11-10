/*
 * @(#)HighChartsJsonVo.java		Created at 15/8/17
 *
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.azolla.p.roc.highcharts;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Component
public class HighChartsJsonVo
{
  private String name;

  private List<List<Object>> data = Lists.newArrayList();

  public HighChartsJsonVo()
  {
  }

  public HighChartsJsonVo(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public List<List<Object>> getData()
  {
    return data;
  }

  public void setData(List<List<Object>> data)
  {
    this.data = data;
  }
}
