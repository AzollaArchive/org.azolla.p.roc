/*
 * @(#)AjaxController.java		Created at 15/6/21
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.azolla.l.ling.img.Img0;
import org.azolla.l.ling.io.Close0;
import org.azolla.l.ling.io.File0;
import org.azolla.l.ling.json.Json0;
import org.azolla.l.ling.lang.Char0;
import org.azolla.l.ling.lang.Integer0;
import org.azolla.l.ling.lang.String0;
import org.azolla.l.ling.util.Date0;
import org.azolla.l.ling.util.Log0;
import org.azolla.p.roc.mapper.ProfessionalMapper;
import org.azolla.p.roc.service.ICommentService;
import org.azolla.p.roc.service.IMapperService;
import org.azolla.p.roc.vo.CommentVo;
import org.azolla.p.roc.vo.ProfessionalVo;
import org.azolla.w.alioss.Oss;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Map;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Controller
public class AjaxController
{
    public static final String OSS_ROC_GENERATE_QRCODE_EMAIL_FOLDER = "roc/generate/qrcode/email/";

    @Autowired
    private ICommentService iCommentService;
    @Autowired
    private IMapperService<ProfessionalVo> iProfessionalMapperService;

    private Map<String, String> ipDateMap  = Maps.newHashMap();
    private String              dateFormat = "yyyyMMddHHmm";

    @RequestMapping("/ajax/comment/add")
    public void addComment(Integer postId, String commentName, String commentEmail, String commentContent, String professionalStr, HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            JSONObject obj = new JSONObject();
            String ip = request.getRemoteHost();
            String date = ipDateMap.get(ip);
            String nowDate = Date0.toString(Date0.now(), dateFormat);
            if (nowDate.equals(date))
            {
                obj.put("err", 1);
                obj.put("msg", "Please wait moment !");
            }
            else
            {
                ipDateMap.put(ip, nowDate);

                File qrcodeFolder = File0.newFile(request.getServletContext().getRealPath(String0.SLASH), OSS_ROC_GENERATE_QRCODE_EMAIL_FOLDER);
                if (!qrcodeFolder.exists())
                {
                    qrcodeFolder.mkdirs();
                }
                File qrcodeFile = File0.newFile(qrcodeFolder, commentEmail + String0.POINT + File0.PNG_FILETYPE);
                CommentVo commentVo = null;
                String photoUrl = null;
                if (!qrcodeFile.exists() && Img0.qrcode(commentEmail, 64, 64, Paths.get(qrcodeFile.toURI())))
                {
                    photoUrl = Oss.Ali.putObject(qrcodeFile, OSS_ROC_GENERATE_QRCODE_EMAIL_FOLDER);
                }
                else
                {
                    photoUrl = Oss.Ali.getOssDomain() + OSS_ROC_GENERATE_QRCODE_EMAIL_FOLDER + qrcodeFile.getName();
                }

                commentVo = iCommentService.add(postId, commentName, commentEmail, photoUrl, commentContent, request);

                if (commentVo == null)
                {
                    obj.put("err", 1);
                    obj.put("msg", "Add failed !");
                }
                else
                {
                    commentVo.setUsername(String0.html(commentVo.getUsername()));
                    commentVo.setEmail(String0.html(commentVo.getEmail()));
                    commentVo.setContent(String0.html(commentVo.getContent()));

                    obj.put("err", 0);
                    obj.put("rst", commentVo);

                    if(!Strings.isNullOrEmpty(professionalStr))
                    {
                        String[] professionalArray = professionalStr.split(String0.COMMA);
                        if(professionalArray != null && professionalArray.length > 1){
                            for(int i = 1; i < professionalArray.length; i ++)
                            {
                                String[] professionalScore = professionalArray[i].split(String.valueOf(Char0.COLON));
                                if(professionalScore != null && professionalScore.length == 2  && Integer0.isInt(professionalScore[0]) && Integer0.isInt(professionalScore[1]) && !"0".equalsIgnoreCase(professionalScore[1]))
                                {
                                    iProfessionalMapperService.add(ProfessionalMapper.class, new ProfessionalVo(Integer.valueOf(professionalScore[0]),Integer.valueOf(professionalScore[1]),ip));
                                }
                            }
                        }
                    }
                }
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

    public void lstProfessional(HttpServletResponse response)
    {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            JSONObject obj = new JSONObject();

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
}
