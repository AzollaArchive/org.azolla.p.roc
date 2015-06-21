/*
 * @(#)AjaxController.java		Created at 15/6/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.azolla.l.ling.io.Close0;
import org.azolla.l.ling.util.Log0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.service.ITagService;
import org.azolla.p.roc.vo.TagVo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Controller
public class AjaxController
{
    @Autowired
    private ITagService iTagService;
    @Autowired
    private CacheAware cacheAware;

    @RequestMapping("/ajax/lstTag")
    public void lstTag(HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.println(JSONArray.toJSONString(CacheAware.getTagList()));
        }
        catch (Exception e)
        {
            Log0.error(this.getClass(), e.toString(), e);
        }
        finally
        {
            Close0.close(out);
        }
    }

    @RequestMapping("/ajax/addTag/{tagDisplayName}")
    public void addTag(@PathVariable String tagDisplayName,HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            JSONObject obj = new JSONObject();
            TagVo tagVo = iTagService.addByTagDisplayName(tagDisplayName);
            if(tagVo == null)
            {
                obj.put("err", 1);
                obj.put("msg", "operation failed!");
            }
            else
            {
                obj.put("err", 0);
                obj.put("rst", tagVo);

                cacheAware.reload(CacheAware.TAG_CACHE);
            }
            out.println(obj.toJSONString());
            return;
        }
        catch (Exception e)
        {
            Log0.error(this.getClass(), e.toString(), e);
        }
        finally
        {
            Close0.close(out);
        }
    }
}
