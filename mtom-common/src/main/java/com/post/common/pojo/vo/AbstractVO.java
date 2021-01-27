package com.post.common.pojo.vo;

import com.post.common.util.JsonUtil;

import java.io.Serializable;

/**
 * 抽象VO
 *
 * @author ljm
 * @date 2021/01/25
 */
public abstract class AbstractVO implements Serializable {

    private static final long serialVersionUID = -1417748095004687576L;

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }

}

