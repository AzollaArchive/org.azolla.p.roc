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
import com.google.common.collect.Maps;
import org.azolla.l.ling.lang.String0;
import org.azolla.p.roc.dao.IMapperDao;
import org.azolla.p.roc.mapper.CategoryMapper;
import org.azolla.p.roc.mapper.ConfigMapper;
import org.azolla.p.roc.mapper.TagMapper;
import org.azolla.p.roc.service.ICategoryService;
import org.azolla.p.roc.vo.CategoryVo;
import org.azolla.p.roc.vo.ConfigVo;
import org.azolla.p.roc.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
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
@Service
@Scope(value = "singleton")
public class CacheAware
{
    //CACHE
    public static final String CATEGORY_CACHE     = "CATEGORY_MAP";
    public static final String CONFIG_CACHE       = "CONFIG_MAP";
    public static final String TAG_CACHE          = "TAG_LST";
    //KEY
    public static final String LEFT_CATEGORY_LST  = "LEFT_CATEGORY_LST";
    public static final String RIGHT_CATEGORY_LST = "RIGHT_CATEGORY_LST";

    public static final String ROC_POST_SIZE = "ROC_POST_SIZE";
    public static final String ROC_DESC      = "ROC_DESC";//WEB
    public static final String ROC_EMAIL     = "ROC_EMAIL";//WEB
    public static final String ROC_TITLE     = "ROC_TITLE";//WEB

    private static ConcurrentMap<String, List<CategoryVo>> CATEGORY_MAP = new ConcurrentHashMap<String, List<CategoryVo>>();
    private static ConcurrentMap<String, String>           CONFIG_MAP   = new ConcurrentHashMap<String, String>();
    private static List<TagVo>                             TAG_LIST     = Lists.newArrayList();

    private static ConcurrentMap<Integer, CategoryVo> CATEGORY_ID_VO_MAP  = Maps.newConcurrentMap();
    private static ConcurrentMap<String, CategoryVo>  CATEGORY_URL_VO_MAP = Maps.newConcurrentMap();
    private static ConcurrentMap<Integer, TagVo>      TAG_ID_VO_MAP       = Maps.newConcurrentMap();
    private static ConcurrentMap<String, TagVo>       TAG_URL_VO_MAP      = Maps.newConcurrentMap();

    @Autowired
    private ICategoryService       iCategoryService;
    @Autowired
    private IMapperDao<CategoryVo> iCategoryMapperDao;
    @Autowired
    private IMapperDao<ConfigVo>   iConfigMapperDao;
    @Autowired
    private IMapperDao<TagVo>      iTagMapperDao;

    public static List<CategoryVo> getCategoryList(@Nonnull String key)
    {
        return CATEGORY_MAP.get(key);
    }

    public static String getConfigValue(@Nonnull String key)
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

    public static String getKeywordsString()
    {
        return new StringBuffer().append(Joiner.on(String0.COMMA).join(Lists.transform(Lists.newArrayList(CATEGORY_ID_VO_MAP.values().iterator()), new Function<CategoryVo, String>()
        {
            @Nullable
            @Override
            public String apply(@Nullable CategoryVo input)
            {
                return input == null ? "" : input.getDisplayName();
            }
        }))).append(String0.COMMA).append(Joiner.on(String0.COMMA).join(Lists.transform(TAG_LIST, new Function<TagVo, String>()
        {
            @Nullable
            @Override
            public String apply(@Nullable TagVo input)
            {
                return input == null ? "" : input.getDisplayName();
            }
        }))).toString();
    }

    public static CategoryVo getCategoryVoById(@Nonnull Integer categoryId)
    {
        return CATEGORY_ID_VO_MAP.get(categoryId);
    }

    public static CategoryVo getCategoryVoByUrl(@Nonnull String urlName)
    {
        return CATEGORY_URL_VO_MAP.get(urlName);
    }

    public static TagVo getTagVoById(@Nonnull Integer tagId)
    {
        return TAG_ID_VO_MAP.get(tagId);
    }

    public static TagVo getTagVoByUrl(@Nonnull String urlName)
    {
        return TAG_URL_VO_MAP.get(urlName);
    }

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

    public void load(@Nonnull String cache)
    {
        clear(cache);
        switch (cache)
        {
            case CATEGORY_CACHE:
                CATEGORY_MAP.put(LEFT_CATEGORY_LST, iCategoryService.loop(CategoryVo.LEFT_ROOT_ID));
                CATEGORY_MAP.put(RIGHT_CATEGORY_LST, iCategoryService.loop(CategoryVo.RIGHT_ROOT_ID));
                CATEGORY_ID_VO_MAP.clear();
                CATEGORY_URL_VO_MAP.clear();
                for (CategoryVo categoryVo : iCategoryMapperDao.lst(CategoryMapper.class, new CategoryVo()))
                {
                    CATEGORY_ID_VO_MAP.put(categoryVo.getId(), categoryVo);
                    CATEGORY_URL_VO_MAP.put(categoryVo.getUrlName(), categoryVo);
                }
                break;
            case CONFIG_CACHE:
                CONFIG_MAP.clear();
                ;
                for (ConfigVo configVo : iConfigMapperDao.lst(ConfigMapper.class, new ConfigVo()))
                {
                    CONFIG_MAP.put(configVo.getRocKey(), configVo.getRocValue());
                }
                break;
            case TAG_CACHE:
                TAG_LIST.addAll(iTagMapperDao.lst(TagMapper.class, new TagVo()));
                TAG_ID_VO_MAP.clear();
                TAG_URL_VO_MAP.clear();
                for (TagVo tagVo : TAG_LIST)
                {
                    TAG_ID_VO_MAP.put(tagVo.getId(), tagVo);
                    TAG_URL_VO_MAP.put(tagVo.getUrlName(), tagVo);
                }
                break;
            default:
                break;
        }
    }

    public void reload()
    {
        reload(CATEGORY_CACHE);
        reload(CONFIG_CACHE);
        reload(TAG_CACHE);
    }

    public void reload(@Nonnull String cache)
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

    public void clear(@Nonnull String cache)
    {
        switch (cache)
        {
            case CATEGORY_CACHE:
                CATEGORY_MAP.clear();
                break;
            case CONFIG_CACHE:
                CONFIG_MAP.clear();
                break;
            case TAG_CACHE:
                TAG_LIST.clear();
                break;
            default:
                break;
        }
    }

    @PreDestroy
    private void post()
    {
        clear();
    }
}
