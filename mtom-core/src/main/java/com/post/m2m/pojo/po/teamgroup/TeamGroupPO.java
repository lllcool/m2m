package com.post.m2m.pojo.po.teamgroup;

import com.post.common.pojo.po.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * teamGroup
 * <p>团队组成
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class TeamGroupPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 数量
     */
    private String count;

    /**
     * 团队
     */
    private String teamId;


}

