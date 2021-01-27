package com.post.m2m.web.api.invoicedetail;

import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailAddDTO;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailUpdateDTO;
import com.post.m2m.pojo.qo.invoicedetail.InvoiceDetailQO;
import com.post.m2m.pojo.vo.invoicedetail.InvoiceDetailListVO;
import com.post.m2m.pojo.vo.invoicedetail.InvoiceDetailShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 【InvoiceDetail】API
 * <p>swagger接口文档
 *
 * @author ljm
 * @date 2021/01/25
 */
@Api(tags = "【InvoiceDetail】API")
public interface InvoiceDetailAPI {

    /**
     * 新增【InvoiceDetail】
     */
    @ApiOperation(value = "新增【InvoiceDetail】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invoiceDetailAddDTO", dataTypeClass = InvoiceDetailAddDTO.class, value = "新增【InvoiceDetail】参数", paramType = "body"),
    })
    ResponseEntity<InvoiceDetailShowVO> save(InvoiceDetailAddDTO invoiceDetailAddDTO) throws Exception;

    /**
     * 修改【InvoiceDetail】
     */
    @ApiOperation(value = "修改【InvoiceDetail】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invoiceDetailUpdateDTO", dataTypeClass = InvoiceDetailUpdateDTO.class, value = "修改【InvoiceDetail】参数", paramType = "body"),
    })
    ResponseEntity<InvoiceDetailShowVO> update(InvoiceDetailUpdateDTO invoiceDetailUpdateDTO);

    /**
     * 分页查询【InvoiceDetail】
     */
    @ApiOperation(value = "分页查询【InvoiceDetail】")
    ResponseEntity<PageVO<InvoiceDetailListVO>> list(InvoiceDetailQO invoiceDetailQO);

    /**
     * 查看【InvoiceDetail】详情
     */
    @ApiOperation(value = "查看【InvoiceDetail】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【InvoiceDetail】id", paramType = "path"),
    })
    ResponseEntity<InvoiceDetailShowVO> show(String id);

    /**
     * 删除单个【InvoiceDetail】
     */
    @ApiOperation(value = "删除单个【InvoiceDetail】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【InvoiceDetail】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【InvoiceDetail】
     */
    @ApiOperation(value = "批量删除【InvoiceDetail】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

    /**
     * 导出【InvoiceDetail】excel
     */
    @ApiOperation(value = "导出【InvoiceDetail】excel")
    void exportExcel(InvoiceDetailQO invoiceDetailQO, HttpServletResponse response) throws Exception;

    /**
     * 导入【InvoiceDetail】excel
     */
    @ApiOperation(value = "导入【InvoiceDetail】excel")
    ResponseEntity<Integer> importExcel(MultipartFile file) throws Exception;

    /**
     * 下载【InvoiceDetail】excel模板
     */
    @ApiOperation(value = "下载【InvoiceDetail】excel模板")
    void downloadExcelTemplate(HttpServletResponse response) throws Exception;

}

