package com.post.m2m.pojo.po.teacher;

import com.post.common.pojo.po.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Teacher
 * <p>老师
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class TeacherPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 科目
     */
    private String subject;

    /**
     * 名字
     */
    private String tName;


}

