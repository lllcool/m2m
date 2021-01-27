package com.post.m2m.pojo.po.ball;

import com.post.common.pojo.po.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Ball
 * <p>球
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class BallPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 球类名称
     */
    private String bName;

    /**
     * 价钱
     */
    private Integer price;


}

