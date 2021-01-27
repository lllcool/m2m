package com.post.m2m.web.rest.invoice;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.DateUtil;
import com.post.m2m.constant.InvoiceType;
import com.post.m2m.excel.handler.ConstConstraintHandler;
import com.post.m2m.excel.handler.TemplateCellStyleStrategy;
import com.post.m2m.excel.handler.TitleDescriptionWriteHandler;
import com.post.m2m.excel.listener.SyncReadExcelListener;
import com.post.m2m.pojo.dto.invoice.InvoiceAddDTO;
import com.post.m2m.pojo.dto.invoice.InvoiceExcelDTO;
import com.post.m2m.pojo.dto.invoice.InvoiceUpdateDTO;
import com.post.m2m.pojo.mapper.invoice.InvoiceMapper;
import com.post.m2m.pojo.po.invoice.InvoicePO;
import com.post.m2m.pojo.qo.invoice.InvoiceQO;
import com.post.m2m.pojo.vo.invoice.InvoiceExcelVO;
import com.post.m2m.pojo.vo.invoice.InvoiceListVO;
import com.post.m2m.pojo.vo.invoice.InvoiceShowVO;
import com.post.m2m.service.invoice.InvoiceService;
import com.post.m2m.web.AbstractController;
import com.post.m2m.web.api.invoice.InvoiceAPI;
import com.post.m2m.web.constant.WebConst;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 【Invoice】控制器
 *
 * @author ljm
 * @date 2021/01/25
 */
@RestController
@RequestMapping(WebConst.ModulePath.INVOICE + "/invoice")
public class InvoiceController extends AbstractController implements InvoiceAPI {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private Validator validator;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InvoiceShowVO> save(@Valid @RequestBody InvoiceAddDTO invoiceAddDTO) throws Exception {
        InvoicePO invoice = invoiceService.save(invoiceAddDTO);
        return ResponseEntity.created(new URI(WebConst.ModulePath.INVOICE + "/invoice/" + invoice.getId()))
                .body(InvoiceMapper.INSTANCE.toShowVO(invoice));
    }

    @Override
    @PutMapping
    public ResponseEntity<InvoiceShowVO> update(@Valid @RequestBody InvoiceUpdateDTO invoiceUpdateDTO) {
        InvoicePO invoice = invoiceService.update(invoiceUpdateDTO);
        return ResponseEntity.ok(InvoiceMapper.INSTANCE.toShowVO(invoice));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<InvoiceListVO>> list(@Valid InvoiceQO invoiceQO) {
        PageVO<InvoiceListVO> page = invoiceService.list(invoiceQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/options")
    public ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo) {
        List<OptionVO<String, String>> options = invoiceService.findOptions(qo);
        return ResponseEntity.ok(options);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<InvoiceShowVO> show(@PathVariable String id) {
        InvoiceShowVO invoiceShowVO = invoiceService.show(id);
        return ResponseEntity.ok(invoiceShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) {
        int count = invoiceService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody String[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = invoiceService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/export")
    public void exportExcel(@Valid InvoiceQO invoiceQO, HttpServletResponse response) throws Exception {
        invoiceQO.setPageSize(Integer.MAX_VALUE);
        invoiceQO.setPageNo(1);
        List<InvoiceListVO> list = invoiceService.list(invoiceQO).getList();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Invoice导出", "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), InvoiceExcelVO.class)
                .sheet()
                .doWrite(InvoiceMapper.INSTANCE.toExcelVOList(list));
    }

    @Override
    @PostMapping("/import")
    public ResponseEntity<Integer> importExcel(@RequestPart MultipartFile file) throws Exception {
        SyncReadExcelListener<InvoiceExcelDTO> excelListener = new SyncReadExcelListener();
        EasyExcel.read(file.getInputStream())
                .head(InvoiceExcelDTO.class)
                .sheet()
                .headRowNumber(3)
                .registerReadListener(excelListener)
                .doRead();
        List<InvoiceAddDTO> list = excelListener.getList().stream()
                .map(excelDTO -> {
                    InvoiceAddDTO addDTO = InvoiceMapper.INSTANCE.fromExcelDTO(excelDTO);
                    // 校验数据
                    Set<ConstraintViolation<InvoiceAddDTO>> set = validator.validate(addDTO);
                    if (!set.isEmpty()) {
                        ConstraintViolation<InvoiceAddDTO> violation = set.stream().findFirst().get();
                        String errorMsg = "第" + (excelDTO.getRowIndex() + 1) + "行数据不合法：" + violation.getMessage();
                        throw new ConstraintViolationException(errorMsg, set);
                    }
                    return addDTO;
                })
                .collect(Collectors.toList());
        int count = invoiceService.batchSave(list);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/template")
    public void downloadExcelTemplate(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String title = "Invoice导入模板(" + DateUtil.getDateStr(new Date()) + ")";
        String fileName = URLEncoder.encode(title, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        String[] description = new String[]{
                "模版前三行标题请勿修改",
                "带“*”号为必填项",
        };
        String[] invoiceTypeConstraint = Arrays.stream(InvoiceType.values()).map(InvoiceType::getDesc).toArray(String[]::new);
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .registerWriteHandler(new ConstConstraintHandler(invoiceTypeConstraint, 3, 3, 0, 0))
                // 第一行是标题，第二行是说明
                .registerWriteHandler(new TitleDescriptionWriteHandler(title, description, InvoiceExcelDTO.class))
                // 自定义模板单元格样式
                .registerWriteHandler(new TemplateCellStyleStrategy())
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "Sheet1")
                .head(InvoiceExcelDTO.class)
                // 从第三行开始写表头
                .relativeHeadRowIndex(2)
                .build();
        excelWriter.write(Arrays.asList(InvoiceExcelDTO.example()), writeSheet);

        excelWriter.finish();
    }

}


