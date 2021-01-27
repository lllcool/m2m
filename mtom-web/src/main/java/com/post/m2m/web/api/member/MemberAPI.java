package com.post.m2m.web.api.member;

import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.member.MemberAddDTO;
import com.post.m2m.pojo.dto.member.MemberUpdateDTO;
import com.post.m2m.pojo.qo.member.MemberQO;
import com.post.m2m.pojo.vo.member.MemberListVO;
import com.post.m2m.pojo.vo.member.MemberShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 【Member】API
 * <p>swagger接口文档
 *
 * @author ljm
 * @date 2021/01/25
 */
@Api(tags = "【Member】API")
public interface MemberAPI {

    /**
     * 新增【Member】
     */
    @ApiOperation(value = "新增【Member】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberAddDTO", dataTypeClass = MemberAddDTO.class, value = "新增【Member】参数", paramType = "body"),
    })
    ResponseEntity<MemberShowVO> save(MemberAddDTO memberAddDTO) throws Exception;

    /**
     * 修改【Member】
     */
    @ApiOperation(value = "修改【Member】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberUpdateDTO", dataTypeClass = MemberUpdateDTO.class, value = "修改【Member】参数", paramType = "body"),
    })
    ResponseEntity<MemberShowVO> update(MemberUpdateDTO memberUpdateDTO);

    /**
     * 分页查询【Member】
     */
    @ApiOperation(value = "分页查询【Member】")
    ResponseEntity<PageVO<MemberListVO>> list(MemberQO memberQO);

    /**
     * 查看【Member】详情
     */
    @ApiOperation(value = "查看【Member】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【Member】id", paramType = "path"),
    })
    ResponseEntity<MemberShowVO> show(String id);

    /**
     * 删除单个【Member】
     */
    @ApiOperation(value = "删除单个【Member】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【Member】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【Member】
     */
    @ApiOperation(value = "批量删除【Member】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

    /**
     * 导出【Member】excel
     */
    @ApiOperation(value = "导出【Member】excel")
    void exportExcel(MemberQO memberQO, HttpServletResponse response) throws Exception;

    /**
     * 导入【Member】excel
     */
    @ApiOperation(value = "导入【Member】excel")
    ResponseEntity<Integer> importExcel(MultipartFile file) throws Exception;

    /**
     * 下载【Member】excel模板
     */
    @ApiOperation(value = "下载【Member】excel模板")
    void downloadExcelTemplate(HttpServletResponse response) throws Exception;

}

