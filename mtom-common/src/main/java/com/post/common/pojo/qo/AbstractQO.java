package com.post.common.pojo.qo;

import com.post.common.util.JsonUtil;

import java.io.Serializable;

/**
 * 数据查询参数对象超类
 *
 * @author ljm
 * @date 2021/01/25
 */
public abstract class AbstractQO implements Serializable {

    private static final long serialVersionUID = -2460649808778841614L;

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }

}

