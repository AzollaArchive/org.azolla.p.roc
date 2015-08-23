/*
 * @(#)ProfessionalTask.java		Created at 15/8/16
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.crontab;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.azolla.l.ling.io.Close0;
import org.azolla.l.ling.io.File0;
import org.azolla.l.ling.json.Json0;
import org.azolla.l.ling.util.Log0;
import org.azolla.p.roc.aware.CacheAware;
import org.azolla.p.roc.aware.ServletAware;
import org.azolla.p.roc.highcharts.HighChartsJsonVo;
import org.azolla.p.roc.mapper.ProfessionalMapper;
import org.azolla.p.roc.service.IMapperService;
import org.azolla.p.roc.vo.ProfessionalVo;
import org.azolla.p.roc.vo.TagVo;
import org.azolla.w.leon.oss.Oss;
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
public class ProfessionalCrontab
{
    public static final String OSS_ROC_PROFESSIONAL_FOLDER = "roc/professional/";
    @Autowired
    private ServletAware                   servletAware;
    @Autowired
    private IMapperService<ProfessionalVo> iProfessionalMapperService;

    //    @Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "1 * * * * ?")
    public void lst()
    {
        File professoinalFoler = File0.newFile(servletAware.getRealPath(), OSS_ROC_PROFESSIONAL_FOLDER);
        if (!professoinalFoler.exists())
        {
            professoinalFoler.mkdirs();
        }
        File highchartsJsonFile = File0.newFile(professoinalFoler, "highcharts.json");
        List<TagVo> tagVoList = CacheAware.getTagList();
        Map<Integer, HighChartsJsonVo> idHighChartsJsonVoMap = Maps.newHashMap();
        for (TagVo tagVo : tagVoList)
        {
            if (tagVo.getOperable() == 1)
            {
                idHighChartsJsonVoMap.put(tagVo.getId(), new HighChartsJsonVo(tagVo.getDisplayName()));
            }
        }
        for (ProfessionalVo professionalVo : iProfessionalMapperService.lst(ProfessionalMapper.class, new ProfessionalVo()))
        {
            idHighChartsJsonVoMap.get(professionalVo.getTagId()).getData().add(Lists.newArrayList(professionalVo.getAddDate().getTime(), professionalVo.getScore()));
        }
        List<Integer> needRmvKeyList = Lists.newArrayList();
        for (Map.Entry<Integer, HighChartsJsonVo> entry : idHighChartsJsonVoMap.entrySet())
        {
            if (entry.getValue().getData().size() == 0)
            {
                needRmvKeyList.add(entry.getKey());
            }
        }
        for (Integer needRmvKey : needRmvKeyList)
        {
            idHighChartsJsonVoMap.remove(needRmvKey);
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        StringBuffer stringBuffer = null;
        try
        {
            fw = new FileWriter(highchartsJsonFile, false);
            bw = new BufferedWriter(fw);
            bw.write(Json0.object2String(idHighChartsJsonVoMap.values()));
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
        Oss.Ali.putObject(highchartsJsonFile, OSS_ROC_PROFESSIONAL_FOLDER);
    }
}