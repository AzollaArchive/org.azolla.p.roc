/*
 * @(#)AjaxController.java		Created at 15/6/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import org.azolla.l.ling.img.Img0;
import org.azolla.l.ling.io.Close0;
import org.azolla.l.ling.io.File0;
import org.azolla.l.ling.json.Json0;
import org.azolla.l.ling.lang.String0;
import org.azolla.l.ling.util.Log0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.aware.ServletAware;
import org.azolla.p.roc.service.ICommentService;
import org.azolla.p.roc.service.ITagService;
import org.azolla.p.roc.vo.CommentVo;
import org.azolla.p.roc.vo.TagVo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;

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
    private ICommentService iCommentService;
    @Autowired
    private CacheAware cacheAware;

    @RequestMapping("/ajax/comment/add")
    public void addComment(Integer postId, String commentName, String commentEmail, String commentContent,HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            JSONObject obj = new JSONObject();
            CommentVo commentVo = iCommentService.add(postId, commentName, commentEmail, commentContent, request);

            if(commentVo == null)
            {
                obj.put("err", 1);
                obj.put("msg", "add failed!");
            }
            else
            {
                File qrcodeFolder = File0.newFile(request.getServletContext().getRealPath(String0.SLASH), ServletAware.getGenerateQrcodePath());
                File qrcodeFile = File0.newFile(qrcodeFolder,commentVo.getEmail() + String0.POINT + File0.PNG_FILETYPE);
                if(!qrcodeFile.exists())
                {
                    Img0.qrcode(commentVo.getEmail(),64,64, Paths.get(qrcodeFile.toURI()));
                }
                commentVo.setUsername(String0.html(commentVo.getUsername()));
                commentVo.setEmail(String0.html(commentVo.getEmail()));
                commentVo.setContent(String0.html(commentVo.getContent()));

                obj.put("err", 0);
                obj.put("rst", commentVo);
            }
            out.println(Json0.object2String(obj));
            out.flush();
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

    @Deprecated
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

    @Deprecated
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
