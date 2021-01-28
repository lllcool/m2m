package com.post.m2m.pojo.mapper.teamgroup;

import com.post.m2m.pojo.dto.teamgroup.TeamGroupAddDTO;
import com.post.m2m.pojo.dto.teamgroup.TeamGroupUpdateDTO;
import com.post.m2m.pojo.po.teamgroup.TeamGroupPO;
import com.post.m2m.pojo.vo.teamgroup.TeamGroupShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 【teamGroup】映射
 *
 * @author ljm
 * @date 2021/01/25
 */
@Mapper
public interface TeamGroupMapper {

    TeamGroupMapper INSTANCE = Mappers.getMapper(TeamGroupMapper.class);

    /**
     * addDTO映射po
     *
     * @param teamGroupAddDTO
     * @return
     */
    TeamGroupPO fromAddDTO(TeamGroupAddDTO teamGroupAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param teamGroupPO
     * @param teamGroupUpdateDTO
     */
    void setUpdateDTO(@MappingTarget TeamGroupPO teamGroupPO, TeamGroupUpdateDTO teamGroupUpdateDTO);

    /**
     * po映射showVO
     *
     * @param teamGroupPO
     * @return
     */
    TeamGroupShowVO toShowVO(TeamGroupPO teamGroupPO);


}

