package com.post.m2m.pojo.mapper.teacher;

import com.post.m2m.pojo.dto.teacher.TeacherAddDTO;
import com.post.m2m.pojo.dto.teacher.TeacherExcelDTO;
import com.post.m2m.pojo.dto.teacher.TeacherUpdateDTO;
import com.post.m2m.pojo.po.teacher.TeacherPO;
import com.post.m2m.pojo.vo.teacher.TeacherExcelVO;
import com.post.m2m.pojo.vo.teacher.TeacherListVO;
import com.post.m2m.pojo.vo.teacher.TeacherShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 【Teacher】映射
 *
 * @author ljm
 * @date 2021/01/25
 */
@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    /**
     * addDTO映射po
     *
     * @param teacherAddDTO
     * @return
     */
    TeacherPO fromAddDTO(TeacherAddDTO teacherAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param teacherPO
     * @param teacherUpdateDTO
     */
    void setUpdateDTO(@MappingTarget TeacherPO teacherPO, TeacherUpdateDTO teacherUpdateDTO);

    /**
     * po映射showVO
     *
     * @param teacherPO
     * @return
     */
    TeacherShowVO toShowVO(TeacherPO teacherPO);


    /**
     * excelDTO映射addDTO
     *
     * @param dto
     * @return
     */
    TeacherAddDTO fromExcelDTO(TeacherExcelDTO dto);

    /**
     * listVO列表转excelVO列表
     *
     * @param list
     * @return
     */
    List<TeacherExcelVO> toExcelVOList(List<TeacherListVO> list);

    /**
     * listVO转excelVO
     *
     * @param vo
     * @return
     */
    TeacherExcelVO toExcelVO(TeacherListVO vo);


}

