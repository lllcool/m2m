package com.post.m2m.pojo.mapper.student;

import com.post.m2m.pojo.dto.student.StudentAddDTO;
import com.post.m2m.pojo.dto.student.StudentExcelDTO;
import com.post.m2m.pojo.dto.student.StudentUpdateDTO;
import com.post.m2m.pojo.po.student.StudentPO;
import com.post.m2m.pojo.vo.student.StudentExcelVO;
import com.post.m2m.pojo.vo.student.StudentListVO;
import com.post.m2m.pojo.vo.student.StudentShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 【Student】映射
 *
 * @author ljm
 * @date 2021/01/25
 */
@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    /**
     * addDTO映射po
     *
     * @param studentAddDTO
     * @return
     */
    StudentPO fromAddDTO(StudentAddDTO studentAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param studentPO
     * @param studentUpdateDTO
     */
    void setUpdateDTO(@MappingTarget StudentPO studentPO, StudentUpdateDTO studentUpdateDTO);

    /**
     * po映射showVO
     *
     * @param studentPO
     * @return
     */
    StudentShowVO toShowVO(StudentPO studentPO);


    /**
     * excelDTO映射addDTO
     *
     * @param dto
     * @return
     */
    StudentAddDTO fromExcelDTO(StudentExcelDTO dto);

    /**
     * listVO列表转excelVO列表
     *
     * @param list
     * @return
     */
    List<StudentExcelVO> toExcelVOList(List<StudentListVO> list);

    /**
     * listVO转excelVO
     *
     * @param vo
     * @return
     */
    StudentExcelVO toExcelVO(StudentListVO vo);


}

