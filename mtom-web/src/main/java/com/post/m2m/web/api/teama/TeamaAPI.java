package com.post.m2m.web.api.teama;

import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.teama.TeamaAddDTO;
import com.post.m2m.pojo.dto.teama.TeamaUpdateDTO;
import com.post.m2m.pojo.qo.teama.TeamaQO;
import com.post.m2m.pojo.vo.teama.TeamaListVO;
import com.post.m2m.pojo.vo.teama.TeamaShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 【teama】API
 * <p>swagger接口文档
 *
 * @author ljm
 * @date 2021/01/25
 */
@Api(tags = "【teama】API")
public interface TeamaAPI {

    /**
     * 新增【teama】
     */
    @ApiOperation(value = "新增【teama】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teamaAddDTO", dataTypeClass = TeamaAddDTO.class, value = "新增【teama】参数", paramType = "body"),
    })
    ResponseEntity<TeamaShowVO> save(TeamaAddDTO teamaAddDTO) throws Exception;

    /**
     * 修改【teama】
     */
    @ApiOperation(value = "修改【teama】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teamaUpdateDTO", dataTypeClass = TeamaUpdateDTO.class, value = "修改【teama】参数", paramType = "body"),
    })
    ResponseEntity<TeamaShowVO> update(TeamaUpdateDTO teamaUpdateDTO);

    /**
     * 分页查询【teama】
     */
    @ApiOperation(value = "分页查询【teama】")
    ResponseEntity<PageVO<TeamaListVO>> list(TeamaQO teamaQO);

    /**
     * 查询【teama】选项列表
     */
    @ApiOperation(value = "查询【teama】选项列表")
    ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo);

    /**
     * 查看【teama】详情
     */
    @ApiOperation(value = "查看【teama】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【teama】id", paramType = "path"),
    })
    ResponseEntity<TeamaShowVO> show(String id);

    /**
     * 删除单个【teama】
     */
    @ApiOperation(value = "删除单个【teama】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【teama】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【teama】
     */
    @ApiOperation(value = "批量删除【teama】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

}

