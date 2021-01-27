package com.post.m2m.pojo.mapper.ball;

import com.post.m2m.pojo.dto.ball.BallAddDTO;
import com.post.m2m.pojo.dto.ball.BallUpdateDTO;
import com.post.m2m.pojo.po.ball.BallPO;
import com.post.m2m.pojo.vo.ball.BallShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 【Ball】映射
 *
 * @author ljm
 * @date 2021/01/25
 */
@Mapper
public interface BallMapper {

    BallMapper INSTANCE = Mappers.getMapper(BallMapper.class);

    /**
     * addDTO映射po
     *
     * @param ballAddDTO
     * @return
     */
    BallPO fromAddDTO(BallAddDTO ballAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param ballPO
     * @param ballUpdateDTO
     */
    void setUpdateDTO(@MappingTarget BallPO ballPO, BallUpdateDTO ballUpdateDTO);

    /**
     * po映射showVO
     *
     * @param ballPO
     * @return
     */
    BallShowVO toShowVO(BallPO ballPO);


}

