/*
 * @(#)Simditor.java		Created at 15/8/22
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.simditor;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.azolla.p.roc.vo.PostVo;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Service
public class Simditor
{
    public static final String MORE = "<more>";

    public static final List<PostVo> more(List<PostVo> lst)
    {
        return Lists.transform(lst, new Function<PostVo, PostVo>()
        {
            @Nullable
            @Override
            public PostVo apply(PostVo input)
            {
                return input.setContent(input.getContent().split(MORE)[0]);
            }
        });
    }
}
