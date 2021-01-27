package com.post.m2m.pojo.po.member;

import com.post.common.pojo.po.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Member
 * <p>成员
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class MemberPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 成员姓名
     */
    private String mName;

    /**
     * 成员年龄
     */
    private Integer age;


}

