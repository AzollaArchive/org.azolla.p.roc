/*
 * @(#)Cache.java		Created at 15/4/20
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.aware;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.azolla.p.roc.service.ICategoryService;
import org.azolla.p.roc.service.IConfigService;
import org.azolla.p.roc.service.ITagService;
import org.azolla.p.roc.vo.CategoryVo;
import org.azolla.p.roc.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Component
@Scope(value="singleton")
public class CacheAware
{
    //CACHE
    public static final String CATEGORY_CACHE     = "CATEGORY_MAP";
    public static final String CONFIG_CACHE       = "CONFIG_MAP";
    public static final String TAG_CACHE          = "TAG_LST";
    //KEY
    public static final String LEFT_CATEGORY_LST  = "LEFT_CATEGORY_LST";
    public static final String RIGHT_CATEGORY_LST = "RIGHT_CATEGORY_LST";

    public static final String ROC_CONFIG_KEY_POSTSIZE = "ROC_CONFIG_KEY_POSTSIZE";
    public static final String ROC_CONFIG_KEY_ROCDESC = "ROC_CONFIG_KEY_ROCDESC";
    public static final String ROC_CONFIG_KEY_ROCEMAIL = "ROC_CONFIG_KEY_ROCEMAIL";
    public static final String ROC_CONFIG_KEY_ROCTITLE = "ROC_CONFIG_KEY_ROCTITLE";


    private static ConcurrentMap<String, List<CategoryVo>> CATEGORY_MAP = new ConcurrentHashMap<String, List<CategoryVo>>();
    private static ConcurrentMap<String, String>           CONFIG_MAP   = new ConcurrentHashMap<String, String>();
    private static List<TagVo>                             TAG_LIST     = Lists.newArrayList();

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IConfigService iConfigService;

    @Autowired
    private ITagService iTagService;

    @PostConstruct
    private void pre()
    {
        load();
    }

    public void load()
    {
        load(CATEGORY_CACHE);
        load(CONFIG_CACHE);
        load(TAG_CACHE);
    }

    public void load(String cache)
    {
        clear(cache);
        switch (cache)
        {
            case CATEGORY_CACHE:
                CATEGORY_MAP.put(LEFT_CATEGORY_LST, iCategoryService.lst(CategoryVo.LEFT_ROOT_URL));
                CATEGORY_MAP.put(RIGHT_CATEGORY_LST, iCategoryService.lst(CategoryVo.RIGHT_ROOT_URL));
                break;
            case CONFIG_CACHE:
                CONFIG_MAP.putAll(iConfigService.map());
                break;
            case TAG_CACHE:
                TAG_LIST.addAll(iTagService.lst());
                break;
            default:
                break;
        }
    }

    public void reload()
    {
        reload(CATEGORY_CACHE);
        reload(CONFIG_CACHE);
    }

    public void reload(String cache)
    {
        clear(cache);
        load(cache);
    }

    public void clear()
    {
        clear(CATEGORY_CACHE);
        clear(CONFIG_CACHE);
        clear(TAG_CACHE);
    }

    public void clear(String cache)
    {
        switch (cache)
        {
            case CATEGORY_CACHE : CATEGORY_MAP.clear();break;
            case CONFIG_CACHE : CONFIG_MAP.clear();break;
            case TAG_CACHE : TAG_LIST.clear();break;
            default:break;
        }
    }

    public static List<CategoryVo> getCategoryList(String key)
    {
        return CATEGORY_MAP.get(key);
    }

    public static String getConfigValue(String key)
    {
        return CONFIG_MAP.get(key);
    }

    public static ConcurrentMap<String, String> getConfigMap()
    {
        return CONFIG_MAP;
    }

    public static List<TagVo> getTagList()
    {
        return TAG_LIST;
    }

    public static String getTagDisplayNameString()
    {
        return Joiner.on(",").join(Lists.transform(TAG_LIST, new Function<TagVo, String>()
        {
            @Nullable
            @Override
            public String apply(TagVo input)
            {
                return input.getDisplayName();
            }
        }));
    }

    @PreDestroy
    private void post()
    {
        clear();
    }
}
