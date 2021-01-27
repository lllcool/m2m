package com.post.m2m.pojo.po.stu_ref_teach;

import com.post.common.pojo.po.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * StuRefTeach
 * <p>老师学生关系表
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class StuRefTeachPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 主键ID
     */
    private String stuId;

    /**
     * 老师
     */
    private String teacherId;


}

