package com.post.m2m.web.api.teamgroup;

import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.teamgroup.TeamGroupAddDTO;
import com.post.m2m.pojo.dto.teamgroup.TeamGroupUpdateDTO;
import com.post.m2m.pojo.qo.teamgroup.TeamGroupQO;
import com.post.m2m.pojo.vo.teamgroup.TeamGroupListVO;
import com.post.m2m.pojo.vo.teamgroup.TeamGroupShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

/**
 * 【teamGroup】API
 * <p>swagger接口文档
 *
 * @author ljm
 * @date 2021/01/25
 */
@Api(tags = "【teamGroup】API")
public interface TeamGroupAPI {

    /**
     * 新增【teamGroup】
     */
    @ApiOperation(value = "新增【teamGroup】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teamGroupAddDTO", dataTypeClass = TeamGroupAddDTO.class, value = "新增【teamGroup】参数", paramType = "body"),
    })
    ResponseEntity<TeamGroupShowVO> save(TeamGroupAddDTO teamGroupAddDTO) throws Exception;

    /**
     * 修改【teamGroup】
     */
    @ApiOperation(value = "修改【teamGroup】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teamGroupUpdateDTO", dataTypeClass = TeamGroupUpdateDTO.class, value = "修改【teamGroup】参数", paramType = "body"),
    })
    ResponseEntity<TeamGroupShowVO> update(TeamGroupUpdateDTO teamGroupUpdateDTO);

    /**
     * 分页查询【teamGroup】
     */
    @ApiOperation(value = "分页查询【teamGroup】")
    ResponseEntity<PageVO<TeamGroupListVO>> list(TeamGroupQO teamGroupQO);

    /**
     * 查看【teamGroup】详情
     */
    @ApiOperation(value = "查看【teamGroup】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【teamGroup】id", paramType = "path"),
    })
    ResponseEntity<TeamGroupShowVO> show(String id);

    /**
     * 删除单个【teamGroup】
     */
    @ApiOperation(value = "删除单个【teamGroup】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【teamGroup】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【teamGroup】
     */
    @ApiOperation(value = "批量删除【teamGroup】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

}

