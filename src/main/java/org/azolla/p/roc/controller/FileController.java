/*
 * @(#)CommonController.java		Created at 15/5/2
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.azolla.l.ling.io.Close0;
import org.azolla.l.ling.io.File0;
import org.azolla.l.ling.json.Json0;
import org.azolla.l.ling.lang.Byte0;
import org.azolla.l.ling.lang.String0;
import org.azolla.l.ling.util.Log0;
import org.azolla.w.leon.oss.Oss;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Controller
public class FileController
{

    private static final String OSS_ROC_UPLOAD_FOLDER = "roc/upload/";

    @RequestMapping(value = "/a/simditor", method = RequestMethod.POST)
    public void simditor(MultipartHttpServletRequest request, HttpServletResponse response)
    {
        File attachmentFolder = File0.newFile(request.getServletContext().getRealPath("/"), OSS_ROC_UPLOAD_FOLDER);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;

        Map<String, Boolean> pathResultMap = Maps.newHashMap();
        try
        {
            out = response.getWriter();
            //取得request中的所有文件名
            Iterator<String> iterator = request.getFileNames();
            while (iterator.hasNext())
            {
                pathResultMap = Maps.newHashMap();
                //取得上传文件
                MultipartFile multipartFile = request.getFile(iterator.next());
                if (multipartFile != null)
                {
                    //取得当前上传文件的文件名称
                    String originalFilename = multipartFile.getOriginalFilename();
                    if (!Strings.isNullOrEmpty(originalFilename) && !Strings.isNullOrEmpty(originalFilename.trim()))
                    {
                        String md5 = Byte0.md5(multipartFile.getBytes());
                        File md5Folder = File0.newFile(attachmentFolder, md5);
                        if (!md5Folder.exists())
                        {
                            md5Folder.mkdirs();
                        }
                        File uploadedFile = File0.newFile(md5Folder, originalFilename);
                        if (!uploadedFile.exists())
                        {
                            try
                            {
                                multipartFile.transferTo(uploadedFile);
                                String url = Oss.Ali.putObject(uploadedFile, OSS_ROC_UPLOAD_FOLDER + md5 + String0.SLASH);
                                out.println(simditor(true, url, null));
                            }
                            catch (Exception e)
                            {
                                out.println(simditor(false, null, Json0.toJSONString(pathResultMap.put(originalFilename, false))));
                            }
                        }
                        else
                        {
                            out.println(simditor(true, Oss.Ali.getOssDomain() + OSS_ROC_UPLOAD_FOLDER + md5 + String0.SLASH + originalFilename, null));
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log0.error(this.getClass(), e.toString(), e);
            out.println(simditor(false, null, e.toString()));
        }
        finally
        {
            Close0.close(out);
        }
    }

    private String simditor(boolean success, String file_path, String msg)
    {
        JSONObject obj = new JSONObject();
        obj.put("success", success);
        if (!Strings.isNullOrEmpty(file_path))
        {
            obj.put("file_path", file_path);
        }
        if (!Strings.isNullOrEmpty(msg))
        {
            obj.put("msg", msg);
        }
        return obj.toJSONString();
    }
}
