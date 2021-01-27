package com.post.m2m.web.api.invoice;

import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.invoice.InvoiceAddDTO;
import com.post.m2m.pojo.dto.invoice.InvoiceUpdateDTO;
import com.post.m2m.pojo.qo.invoice.InvoiceQO;
import com.post.m2m.pojo.vo.invoice.InvoiceListVO;
import com.post.m2m.pojo.vo.invoice.InvoiceShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 【Invoice】API
 * <p>swagger接口文档
 *
 * @author ljm
 * @date 2021/01/25
 */
@Api(tags = "【Invoice】API")
public interface InvoiceAPI {

    /**
     * 新增【Invoice】
     */
    @ApiOperation(value = "新增【Invoice】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invoiceAddDTO", dataTypeClass = InvoiceAddDTO.class, value = "新增【Invoice】参数", paramType = "body"),
    })
    ResponseEntity<InvoiceShowVO> save(InvoiceAddDTO invoiceAddDTO) throws Exception;

    /**
     * 修改【Invoice】
     */
    @ApiOperation(value = "修改【Invoice】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invoiceUpdateDTO", dataTypeClass = InvoiceUpdateDTO.class, value = "修改【Invoice】参数", paramType = "body"),
    })
    ResponseEntity<InvoiceShowVO> update(InvoiceUpdateDTO invoiceUpdateDTO);

    /**
     * 分页查询【Invoice】
     */
    @ApiOperation(value = "分页查询【Invoice】")
    ResponseEntity<PageVO<InvoiceListVO>> list(InvoiceQO invoiceQO);

    /**
     * 查询【Invoice】选项列表
     */
    @ApiOperation(value = "查询【Invoice】选项列表")
    ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo);

    /**
     * 查看【Invoice】详情
     */
    @ApiOperation(value = "查看【Invoice】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【Invoice】id", paramType = "path"),
    })
    ResponseEntity<InvoiceShowVO> show(String id);

    /**
     * 删除单个【Invoice】
     */
    @ApiOperation(value = "删除单个【Invoice】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "【Invoice】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(String id);

    /**
     * 批量删除【Invoice】
     */
    @ApiOperation(value = "批量删除【Invoice】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = String.class, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(String[] id);

    /**
     * 导出【Invoice】excel
     */
    @ApiOperation(value = "导出【Invoice】excel")
    void exportExcel(InvoiceQO invoiceQO, HttpServletResponse response) throws Exception;

    /**
     * 导入【Invoice】excel
     */
    @ApiOperation(value = "导入【Invoice】excel")
    ResponseEntity<Integer> importExcel(MultipartFile file) throws Exception;

    /**
     * 下载【Invoice】excel模板
     */
    @ApiOperation(value = "下载【Invoice】excel模板")
    void downloadExcelTemplate(HttpServletResponse response) throws Exception;

}

