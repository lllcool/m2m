package com.post.m2m.pojo.mapper.stu_ref_teach;

import com.post.m2m.pojo.dto.stu_ref_teach.StuRefTeachAddDTO;
import com.post.m2m.pojo.dto.stu_ref_teach.StuRefTeachExcelDTO;
import com.post.m2m.pojo.dto.stu_ref_teach.StuRefTeachUpdateDTO;
import com.post.m2m.pojo.po.stu_ref_teach.StuRefTeachPO;
import com.post.m2m.pojo.vo.stu_ref_teach.StuRefTeachExcelVO;
import com.post.m2m.pojo.vo.stu_ref_teach.StuRefTeachListVO;
import com.post.m2m.pojo.vo.stu_ref_teach.StuRefTeachShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 【StuRefTeach】映射
 *
 * @author ljm
 * @date 2021/01/25
 */
@Mapper
public interface StuRefTeachMapper {

    StuRefTeachMapper INSTANCE = Mappers.getMapper(StuRefTeachMapper.class);

    /**
     * addDTO映射po
     *
     * @param stuRefTeachAddDTO
     * @return
     */
    StuRefTeachPO fromAddDTO(StuRefTeachAddDTO stuRefTeachAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param stuRefTeachPO
     * @param stuRefTeachUpdateDTO
     */
    void setUpdateDTO(@MappingTarget StuRefTeachPO stuRefTeachPO, StuRefTeachUpdateDTO stuRefTeachUpdateDTO);

    /**
     * po映射showVO
     *
     * @param stuRefTeachPO
     * @return
     */
    StuRefTeachShowVO toShowVO(StuRefTeachPO stuRefTeachPO);


    /**
     * excelDTO映射addDTO
     *
     * @param dto
     * @return
     */
    StuRefTeachAddDTO fromExcelDTO(StuRefTeachExcelDTO dto);

    /**
     * listVO列表转excelVO列表
     *
     * @param list
     * @return
     */
    List<StuRefTeachExcelVO> toExcelVOList(List<StuRefTeachListVO> list);

    /**
     * listVO转excelVO
     *
     * @param vo
     * @return
     */
    StuRefTeachExcelVO toExcelVO(StuRefTeachListVO vo);


}

