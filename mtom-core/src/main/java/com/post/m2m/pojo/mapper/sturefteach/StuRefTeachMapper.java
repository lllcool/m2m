package com.post.m2m.pojo.mapper.sturefteach;

import com.post.m2m.pojo.dto.sturefteach.StuRefTeachAddDTO;
import com.post.m2m.pojo.dto.sturefteach.StuRefTeachExcelDTO;
import com.post.m2m.pojo.dto.sturefteach.StuRefTeachUpdateDTO;
import com.post.m2m.pojo.po.sturefteach.StuRefTeachPO;
import com.post.m2m.pojo.vo.sturefteach.StuRefTeachExcelVO;
import com.post.m2m.pojo.vo.sturefteach.StuRefTeachListVO;
import com.post.m2m.pojo.vo.sturefteach.StuRefTeachShowVO;
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

