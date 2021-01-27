package com.post.m2m.web.api.student;

import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.student.StudentAddDTO;
import com.post.m2m.pojo.dto.student.StudentUpdateDTO;
import com.post.m2m.pojo.qo.student.StudentQO;
import com.post.m2m.pojo.vo.student.StudentListVO;
import com.post.m2m.pojo.vo.student.StudentShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 【Student】API
 * <p>swagger接口文档
 *
 * @author ljm
 * @date 2021/01/25
 */
@Api(tags = "【Student】API")
public interface StudentAPI {

    /**
     * 新增【Student】
     */
    @ApiOperation(value = "新增【Student】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentAddDTO", dataTypeClass = StudentAddDTO.class, value = "新增【Student】参数", paramType = "body"),
    })
    ResponseEntity<StudentShowVO> save(StudentAddDTO studentAddDTO) throws Exception;

    /**
     * 修改【Student】
     */
    @ApiOperation(value = "修改【Student】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentUpdateDTO", dataTypeClass = StudentUpdateDTO.class, value = "修改【Student】参数", paramType = "body"),
    })
    ResponseEntity<StudentShowVO> update(StudentUpdateDTO studentUpdateDTO);

    /**
     * 分页查询【Student】
     */
    @ApiOperation(value = "分页查询【Student】")
    ResponseEntity<PageVO<StudentListVO>> list(StudentQO studentQO);

    /**
     * 查询【Student】选项列表
     */
    @ApiOperation(value = "查询【Student】选项列表")
    ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo);

    /**
     * 查看【Student】详情
     */
    @ApiOperation(value = "查看【Student】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【Student】id", paramType = "path"),
    })
    ResponseEntity<StudentShowVO> show(String id);

    /**
     * 删除单个【Student】
     */
    @ApiOperation(value = "删除单个【Student】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【Student】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【Student】
     */
    @ApiOperation(value = "批量删除【Student】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

    /**
     * 导出【Student】excel
     */
    @ApiOperation(value = "导出【Student】excel")
    void exportExcel(StudentQO studentQO, HttpServletResponse response) throws Exception;

    /**
     * 导入【Student】excel
     */
    @ApiOperation(value = "导入【Student】excel")
    ResponseEntity<Integer> importExcel(MultipartFile file) throws Exception;

    /**
     * 下载【Student】excel模板
     */
    @ApiOperation(value = "下载【Student】excel模板")
    void downloadExcelTemplate(HttpServletResponse response) throws Exception;

}

