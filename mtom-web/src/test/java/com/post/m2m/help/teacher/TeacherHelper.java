package com.post.m2m.help.teacher;

import com.post.m2m.pojo.dto.teacher.*;
import com.post.m2m.pojo.po.teacher.*;
import com.post.m2m.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.post.m2m.pojo.example.teacher.TeacherExample.*;

@Component
public class TeacherHelper {

    @Autowired
    private TeacherService teacherService;

    /**
     * 生成add测试数据
     *
     * @return
     */
    public TeacherAddDTO getTeacherAddDTO() {
        TeacherAddDTO dto = new TeacherAddDTO();
        dto.setSubject(E_SUBJECT);
        dto.setTeacherName(E_TEACHER_NAME);
        return dto;
    }


    /**
     * 生成update测试数据
     *
     * @return
     */
    public TeacherUpdateDTO getTeacherUpdateDTO(TeacherPO teacher) {
        TeacherUpdateDTO dto = new TeacherUpdateDTO();
        dto.setId(teacher.getId());
        dto.setSubject(teacher.getSubject());
        dto.setTeacherName(teacher.getTeacherName());
        return dto;
    }

    /**
     * 保存示例
     *
     * @return
     */
    public TeacherPO saveTeacherExample() {
        TeacherAddDTO addDTO = this.getTeacherAddDTO();
        return teacherService.save(addDTO);
    }


}

