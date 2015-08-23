/*
 * @(#)Init.java		Created at 15/4/20
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.aware;

import org.azolla.l.ling.lang.String0;
import org.azolla.w.leon.oss.Oss;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Service
@Scope("singleton")
public class ServletAware implements InitializingBean, ServletContextAware
{
    public static final String KEYWORDS   = "KEYWORDS";
    public static final String OSS_DOMAIN = "OSS_DOMAIN";
    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        servletContext.setAttribute(CacheAware.LEFT_CATEGORY_LST, CacheAware.getLeftCategoryList());
        servletContext.setAttribute(CacheAware.RIGHT_CATEGORY_LST, CacheAware.getRightCategoryList());
        servletContext.setAttribute(CacheAware.CONFIG_CACHE, CacheAware.getConfigMap());
        servletContext.setAttribute(CacheAware.TAG_CACHE, CacheAware.getTagList());
        servletContext.setAttribute(KEYWORDS, CacheAware.getKeywordsString());
        servletContext.setAttribute(OSS_DOMAIN, Oss.Ali.getOssDomain());
//        servletContext.setAttribute("LINE_SEPARATOR", Char0.SECTION);
    }

    public void refreshAttribute(String key, Object value)
    {
        servletContext.setAttribute(key, value);
    }

    public String getRealPath()
    {
        return servletContext.getRealPath(String0.SLASH);
    }
}
