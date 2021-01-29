package com.post.m2m.help.sturefteach;

import com.post.m2m.pojo.dto.sturefteach.*;
import com.post.m2m.pojo.po.sturefteach.*;
import com.post.m2m.service.sturefteach.StuRefTeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.post.m2m.pojo.example.sturefteach.StuRefTeachExample.*;

@Component
public class StuRefTeachHelper {

    @Autowired
    private StuRefTeachService stuRefTeachService;

    /**
     * 生成add测试数据
     *
     * @return
     */
    public StuRefTeachAddDTO getStuRefTeachAddDTO(String stuId, String teacherId) {
        StuRefTeachAddDTO dto = new StuRefTeachAddDTO();
        dto.setStuId(stuId);
        dto.setTeacherId(teacherId);
        return dto;
    }


    /**
     * 生成update测试数据
     *
     * @return
     */
    public StuRefTeachUpdateDTO getStuRefTeachUpdateDTO(StuRefTeachPO stuRefTeach) {
        StuRefTeachUpdateDTO dto = new StuRefTeachUpdateDTO();
        dto.setId(stuRefTeach.getId());
        dto.setStuId(stuRefTeach.getStuId());
        dto.setTeacherId(stuRefTeach.getTeacherId());
        return dto;
    }

    /**
     * 保存示例
     *
     * @return
     */
    public StuRefTeachPO saveStuRefTeachExample(String stuId, String teacherId) {
        StuRefTeachAddDTO addDTO = this.getStuRefTeachAddDTO(stuId, teacherId);
        return stuRefTeachService.save(addDTO);
    }


}

