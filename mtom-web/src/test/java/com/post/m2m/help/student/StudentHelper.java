package com.post.m2m.help.student;

import com.post.common.util.SafeUtil;
import com.post.m2m.pojo.dto.student.*;
import com.post.m2m.pojo.po.student.*;
import com.post.m2m.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.post.m2m.pojo.example.student.StudentExample.*;

@Component
public class StudentHelper {

    @Autowired
    private StudentService studentService;

    /**
     * 生成add测试数据
     *
     * @return
     */
    public StudentAddDTO getStudentAddDTO() {
        StudentAddDTO dto = new StudentAddDTO();
        dto.setName(E_NAME);
        dto.setAge(SafeUtil.getInteger(E_AGE));
        return dto;
    }


    /**
     * 生成update测试数据
     *
     * @return
     */
    public StudentUpdateDTO getStudentUpdateDTO(StudentPO student) {
        StudentUpdateDTO dto = new StudentUpdateDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        return dto;
    }

    /**
     * 保存示例
     *
     * @return
     */
    public StudentPO saveStudentExample() {
        StudentAddDTO addDTO = this.getStudentAddDTO();
        return studentService.save(addDTO);
    }


}

