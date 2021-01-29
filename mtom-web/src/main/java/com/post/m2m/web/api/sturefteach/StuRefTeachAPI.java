package com.post.m2m.web.api.sturefteach;

import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.sturefteach.StuRefTeachAddDTO;
import com.post.m2m.pojo.dto.sturefteach.StuRefTeachUpdateDTO;
import com.post.m2m.pojo.qo.sturefteach.StuRefTeachQO;
import com.post.m2m.pojo.vo.sturefteach.StuRefTeachListVO;
import com.post.m2m.pojo.vo.sturefteach.StuRefTeachShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 【StuRefTeach】API
 * <p>swagger接口文档
 *
 * @author ljm
 * @date 2021/01/25
 */
@Api(tags = "【StuRefTeach】API")
public interface StuRefTeachAPI {

    /**
     * 新增【StuRefTeach】
     */
    @ApiOperation(value = "新增【StuRefTeach】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuRefTeachAddDTO", dataTypeClass = StuRefTeachAddDTO.class, value = "新增【StuRefTeach】参数", paramType = "body"),
    })
    ResponseEntity<StuRefTeachShowVO> save(StuRefTeachAddDTO stuRefTeachAddDTO) throws Exception;

    /**
     * 修改【StuRefTeach】
     */
    @ApiOperation(value = "修改【StuRefTeach】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuRefTeachUpdateDTO", dataTypeClass = StuRefTeachUpdateDTO.class, value = "修改【StuRefTeach】参数", paramType = "body"),
    })
    ResponseEntity<StuRefTeachShowVO> update(StuRefTeachUpdateDTO stuRefTeachUpdateDTO);

    /**
     * 分页查询【StuRefTeach】
     */
    @ApiOperation(value = "分页查询【StuRefTeach】")
    ResponseEntity<PageVO<StuRefTeachListVO>> list(StuRefTeachQO stuRefTeachQO);

    /**
     * 查看【StuRefTeach】详情
     */
    @ApiOperation(value = "查看【StuRefTeach】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【StuRefTeach】id", paramType = "path"),
    })
    ResponseEntity<StuRefTeachShowVO> show(String id);

    /**
     * 删除单个【StuRefTeach】
     */
    @ApiOperation(value = "删除单个【StuRefTeach】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【StuRefTeach】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【StuRefTeach】
     */
    @ApiOperation(value = "批量删除【StuRefTeach】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

    /**
     * 导出【StuRefTeach】excel
     */
    @ApiOperation(value = "导出【StuRefTeach】excel")
    void exportExcel(StuRefTeachQO stuRefTeachQO, HttpServletResponse response) throws Exception;

    /**
     * 导入【StuRefTeach】excel
     */
    @ApiOperation(value = "导入【StuRefTeach】excel")
    ResponseEntity<Integer> importExcel(MultipartFile file) throws Exception;

    /**
     * 下载【StuRefTeach】excel模板
     */
    @ApiOperation(value = "下载【StuRefTeach】excel模板")
    void downloadExcelTemplate(HttpServletResponse response) throws Exception;

}

