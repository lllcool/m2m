package com.post.m2m.pojo.po.student;

import com.post.common.pojo.po.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Student
 * <p>学生
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class StudentPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;


}

