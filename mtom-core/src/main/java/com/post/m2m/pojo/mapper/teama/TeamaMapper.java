package com.post.m2m.pojo.mapper.teama;

import com.post.m2m.pojo.dto.teama.TeamaAddDTO;
import com.post.m2m.pojo.dto.teama.TeamaUpdateDTO;
import com.post.m2m.pojo.po.teama.TeamaPO;
import com.post.m2m.pojo.vo.teama.TeamaShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 【teama】映射
 *
 * @author ljm
 * @date 2021/01/25
 */
@Mapper
public interface TeamaMapper {

    TeamaMapper INSTANCE = Mappers.getMapper(TeamaMapper.class);

    /**
     * addDTO映射po
     *
     * @param teamaAddDTO
     * @return
     */
    TeamaPO fromAddDTO(TeamaAddDTO teamaAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param teamaPO
     * @param teamaUpdateDTO
     */
    void setUpdateDTO(@MappingTarget TeamaPO teamaPO, TeamaUpdateDTO teamaUpdateDTO);

    /**
     * po映射showVO
     *
     * @param teamaPO
     * @return
     */
    TeamaShowVO toShowVO(TeamaPO teamaPO);


}

