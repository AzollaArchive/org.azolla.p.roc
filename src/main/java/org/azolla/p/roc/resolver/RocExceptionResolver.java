/*
 * @(#)RocExceptionResolver.java		Created at 15/7/25
 *
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.azolla.p.roc.resolver;

import org.azolla.l.ling.json.Json0;
import org.azolla.l.ling.util.Log0;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class RocExceptionResolver implements HandlerExceptionResolver
{
  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
  {
    ModelAndView rtn = new ModelAndView("500");
    if (ex instanceof NullPointerException)
    {
      rtn = new ModelAndView("404");
    }
    Log0.error(this.getClass(), request.getRequestURL().append(Json0.toJSONString(request.getParameterMap())).toString(), ex);
    request.setAttribute("exception", ex);
    return rtn;
  }
}
