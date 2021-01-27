package com.post.m2m.pojo.mapper.member;

import com.post.m2m.pojo.dto.member.MemberAddDTO;
import com.post.m2m.pojo.dto.member.MemberExcelDTO;
import com.post.m2m.pojo.dto.member.MemberUpdateDTO;
import com.post.m2m.pojo.po.member.MemberPO;
import com.post.m2m.pojo.vo.member.MemberExcelVO;
import com.post.m2m.pojo.vo.member.MemberListVO;
import com.post.m2m.pojo.vo.member.MemberShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 【Member】映射
 *
 * @author ljm
 * @date 2021/01/25
 */
@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    /**
     * addDTO映射po
     *
     * @param memberAddDTO
     * @return
     */
    MemberPO fromAddDTO(MemberAddDTO memberAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param memberPO
     * @param memberUpdateDTO
     */
    void setUpdateDTO(@MappingTarget MemberPO memberPO, MemberUpdateDTO memberUpdateDTO);

    /**
     * po映射showVO
     *
     * @param memberPO
     * @return
     */
    MemberShowVO toShowVO(MemberPO memberPO);


    /**
     * excelDTO映射addDTO
     *
     * @param dto
     * @return
     */
    MemberAddDTO fromExcelDTO(MemberExcelDTO dto);

    /**
     * listVO列表转excelVO列表
     *
     * @param list
     * @return
     */
    List<MemberExcelVO> toExcelVOList(List<MemberListVO> list);

    /**
     * listVO转excelVO
     *
     * @param vo
     * @return
     */
    MemberExcelVO toExcelVO(MemberListVO vo);


}

