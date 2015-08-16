/*
 * @(#)ProfessionalTask.java		Created at 15/8/16
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.task;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.azolla.l.ling.io.Close0;
import org.azolla.l.ling.io.File0;
import org.azolla.l.ling.lang.String0;
import org.azolla.l.ling.util.Date0;
import org.azolla.l.ling.util.Log0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.aware.ServletAware;
import org.azolla.p.roc.mapper.ProfessionalMapper;
import org.azolla.p.roc.service.IMapperService;
import org.azolla.p.roc.vo.ProfessionalVo;
import org.azolla.p.roc.vo.TagVo;
import org.azolla.w.alioss.Oss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Service
public class ProfessionalTask
{
    public static final String OSS_ROC_PROFESSIONAL_FOLDER = "roc/professional/";
    @Autowired
    private ServletAware servletAware;
    @Autowired
    private IMapperService<ProfessionalVo> iProfessionalMapperService;

    //    @Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "1 * * * * ?")
    public void lst()
    {
        File lstCsvFoler = File0.newFile(servletAware.getRealPath(), OSS_ROC_PROFESSIONAL_FOLDER);
        if (!lstCsvFoler.exists())
        {
            lstCsvFoler.mkdirs();
        }
        File lstCsvFile = File0.newFile(lstCsvFoler, "lst.csv");
        List<TagVo> tagVoList = CacheAware.getTagList();
        StringBuffer stringBufferProfessionalTitle = new StringBuffer("datetime");
        int i = 0;
        Map<Integer, Integer> idSeqMap = Maps.newHashMap();
        for (TagVo tagVo : tagVoList)
        {
            if (tagVo.getProfessional() == 1)
            {
                idSeqMap.put(tagVo.getId(), i++);
                stringBufferProfessionalTitle.append(String0.COMMA).append(tagVo.getDisplayName());
            }
        }
        i--;
        List<ProfessionalVo> professionalVoList = iProfessionalMapperService.lst(ProfessionalMapper.class, new ProfessionalVo(0));
        FileWriter fw = null;
        BufferedWriter bw = null;
        StringBuffer stringBuffer = null;
        try
        {
            fw = new FileWriter(lstCsvFile, false);
            bw = new BufferedWriter(fw);

            bw.write(stringBufferProfessionalTitle.toString());
            for (ProfessionalVo professionalVo : professionalVoList)
            {
                stringBuffer = new StringBuffer();
                stringBuffer.append(File0.BR_LINUX);
                stringBuffer.append(Date0.toString(professionalVo.getAddDate(), Date0.DATETIME_WITH_DASH_AND_COLON));
                stringBuffer.append(String0.COMMA);
                int seq = idSeqMap.get(professionalVo.getScoreId());
                stringBuffer.append(Strings.repeat(String0.COMMA, seq));
                stringBuffer.append(professionalVo.getScoreValue());
                stringBuffer.append(Strings.repeat(String0.COMMA, i - seq));
                bw.write(stringBuffer.toString());
            }
            bw.flush();
        }
        catch (Exception e)
        {
            Log0.error(this.getClass(), e.toString(), e);
        }
        finally
        {
            Close0.close(bw);
            Close0.close(fw);
        }
        Oss.Ali.putObject(lstCsvFile, OSS_ROC_PROFESSIONAL_FOLDER);
    }
}