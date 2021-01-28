package com.post.m2m.pojo.po.teama;

import com.post.common.pojo.po.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * teama
 * <p>团队
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class TeamaPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 团队名称
     */
    private String teamName;


}

