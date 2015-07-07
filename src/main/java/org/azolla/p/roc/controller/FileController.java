/*
 * @(#)CommonController.java		Created at 15/5/2
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.controller;

import com.google.common.base.Strings;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.azolla.l.ling.io.Close0;
import org.azolla.l.ling.io.File0;
import org.azolla.l.ling.lang.Byte0;
import org.azolla.l.ling.lang.String0;
import org.azolla.l.ling.util.Log0;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Controller
public class FileController
{

    private static final String UPLOAD = "/upload";

    @RequestMapping("/kindeditor")
    public void kindeditor(HttpServletRequest request, HttpServletResponse response)
    {
        //最大文件大小
        long maxSize = 1000000;
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        try
        {
            File attachmentFolder = File0.newFile(request.getServletContext().getRealPath("/"), UPLOAD);
            out = response.getWriter();

            if (!ServletFileUpload.isMultipartContent(request))
            {
                out.println(getError("Please select file."));
                return;
            }

            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            List items = upload.parseRequest(request);
            Iterator itr = items.iterator();
            while (itr.hasNext())
            {
                FileItem item = (FileItem) itr.next();
                String fileName = item.getName();
                long fileSize = item.getSize();
                if (!item.isFormField())
                {
                    //检查文件大小
                    if(item.getSize() > maxSize){
                        out.println(getError("Upload file size exceeds the limit."));
                        return;
                    }
                    //检查扩展名
//                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//                    if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
//                        out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
//                        return;
//                    }
//
//                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//                    String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                    String md5 = Byte0.md5(item.get());
                    String fileType = File0.fileType(fileName);
                    File uploadedFile = null;
                    if(Strings.isNullOrEmpty(fileType) || fileName.equals(fileType))
                    {
                        uploadedFile = File0.newFile(attachmentFolder, md5);
                    }
                    else
                    {
                        uploadedFile = File0.newFile(attachmentFolder, md5+ String0.POINT+fileType);
                    }

                    if(!uploadedFile.exists())
                    {
                        try
                        {
                            item.write(uploadedFile);
                        }
                        catch (Exception e)
                        {
                            out.println(getError("Upload file failed."));
                            return;
                        }
                    }
                    JSONObject obj = new JSONObject();
                    obj.put("error", 0);
                    obj.put("url", UPLOAD + "/" + uploadedFile.getName());
                    out.println(obj.toJSONString());
                    return;
                }
            }
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

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }
}
