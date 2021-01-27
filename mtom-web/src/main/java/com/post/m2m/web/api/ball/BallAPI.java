package com.post.m2m.web.api.ball;

import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.ball.BallAddDTO;
import com.post.m2m.pojo.dto.ball.BallUpdateDTO;
import com.post.m2m.pojo.qo.ball.BallQO;
import com.post.m2m.pojo.vo.ball.BallListVO;
import com.post.m2m.pojo.vo.ball.BallShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

/**
 * 【Ball】API
 * <p>swagger接口文档
 *
 * @author ljm
 * @date 2021/01/25
 */
@Api(tags = "【Ball】API")
public interface BallAPI {

    /**
     * 新增【Ball】
     */
    @ApiOperation(value = "新增【Ball】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ballAddDTO", dataTypeClass = BallAddDTO.class, value = "新增【Ball】参数", paramType = "body"),
    })
    ResponseEntity<BallShowVO> save(BallAddDTO ballAddDTO) throws Exception;

    /**
     * 修改【Ball】
     */
    @ApiOperation(value = "修改【Ball】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ballUpdateDTO", dataTypeClass = BallUpdateDTO.class, value = "修改【Ball】参数", paramType = "body"),
    })
    ResponseEntity<BallShowVO> update(BallUpdateDTO ballUpdateDTO);

    /**
     * 分页查询【Ball】
     */
    @ApiOperation(value = "分页查询【Ball】")
    ResponseEntity<PageVO<BallListVO>> list(BallQO ballQO);

    /**
     * 查看【Ball】详情
     */
    @ApiOperation(value = "查看【Ball】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【Ball】id", paramType = "path"),
    })
    ResponseEntity<BallShowVO> show(String id);

    /**
     * 删除单个【Ball】
     */
    @ApiOperation(value = "删除单个【Ball】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【Ball】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【Ball】
     */
    @ApiOperation(value = "批量删除【Ball】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

}

