package com.post.m2m.web.api.teacher;

import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.teacher.TeacherAddDTO;
import com.post.m2m.pojo.dto.teacher.TeacherUpdateDTO;
import com.post.m2m.pojo.qo.teacher.TeacherQO;
import com.post.m2m.pojo.vo.teacher.TeacherListVO;
import com.post.m2m.pojo.vo.teacher.TeacherShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 【Teacher】API
 * <p>swagger接口文档
 *
 * @author ljm
 * @date 2021/01/25
 */
@Api(tags = "【Teacher】API")
public interface TeacherAPI {

    /**
     * 新增【Teacher】
     */
    @ApiOperation(value = "新增【Teacher】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherAddDTO", dataTypeClass = TeacherAddDTO.class, value = "新增【Teacher】参数", paramType = "body"),
    })
    ResponseEntity<TeacherShowVO> save(TeacherAddDTO teacherAddDTO) throws Exception;

    /**
     * 修改【Teacher】
     */
    @ApiOperation(value = "修改【Teacher】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherUpdateDTO", dataTypeClass = TeacherUpdateDTO.class, value = "修改【Teacher】参数", paramType = "body"),
    })
    ResponseEntity<TeacherShowVO> update(TeacherUpdateDTO teacherUpdateDTO);

    /**
     * 分页查询【Teacher】
     */
    @ApiOperation(value = "分页查询【Teacher】")
    ResponseEntity<PageVO<TeacherListVO>> list(TeacherQO teacherQO);

    /**
     * 查询【Teacher】选项列表
     */
    @ApiOperation(value = "查询【Teacher】选项列表")
    ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo);

    /**
     * 查看【Teacher】详情
     */
    @ApiOperation(value = "查看【Teacher】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【Teacher】id", paramType = "path"),
    })
    ResponseEntity<TeacherShowVO> show(String id);

    /**
     * 删除单个【Teacher】
     */
    @ApiOperation(value = "删除单个【Teacher】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【Teacher】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【Teacher】
     */
    @ApiOperation(value = "批量删除【Teacher】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

    /**
     * 导出【Teacher】excel
     */
    @ApiOperation(value = "导出【Teacher】excel")
    void exportExcel(TeacherQO teacherQO, HttpServletResponse response) throws Exception;

    /**
     * 导入【Teacher】excel
     */
    @ApiOperation(value = "导入【Teacher】excel")
    ResponseEntity<Integer> importExcel(MultipartFile file) throws Exception;

    /**
     * 下载【Teacher】excel模板
     */
    @ApiOperation(value = "下载【Teacher】excel模板")
    void downloadExcelTemplate(HttpServletResponse response) throws Exception;

}

