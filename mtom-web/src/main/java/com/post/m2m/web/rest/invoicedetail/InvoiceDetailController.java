package com.post.m2m.web.rest.invoicedetail;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.DateUtil;
import com.post.m2m.excel.handler.TemplateCellStyleStrategy;
import com.post.m2m.excel.handler.TitleDescriptionWriteHandler;
import com.post.m2m.excel.listener.SyncReadExcelListener;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailAddDTO;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailExcelDTO;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailUpdateDTO;
import com.post.m2m.pojo.mapper.invoicedetail.InvoiceDetailMapper;
import com.post.m2m.pojo.po.invoicedetail.InvoiceDetailPO;
import com.post.m2m.pojo.qo.invoicedetail.InvoiceDetailQO;
import com.post.m2m.pojo.vo.invoicedetail.InvoiceDetailExcelVO;
import com.post.m2m.pojo.vo.invoicedetail.InvoiceDetailListVO;
import com.post.m2m.pojo.vo.invoicedetail.InvoiceDetailShowVO;
import com.post.m2m.service.invoicedetail.InvoiceDetailService;
import com.post.m2m.web.AbstractController;
import com.post.m2m.web.api.invoicedetail.InvoiceDetailAPI;
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
 * 【InvoiceDetail】控制器
 *
 * @author ljm
 * @date 2021/01/25
 */
@RestController
@RequestMapping(WebConst.ModulePath.INVOICEDETAIL + "/invoiceDetail")
public class InvoiceDetailController extends AbstractController implements InvoiceDetailAPI {

    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @Autowired
    private Validator validator;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InvoiceDetailShowVO> save(@Valid @RequestBody InvoiceDetailAddDTO invoiceDetailAddDTO) throws Exception {
        InvoiceDetailPO invoiceDetail = invoiceDetailService.save(invoiceDetailAddDTO);
        return ResponseEntity.created(new URI(WebConst.ModulePath.INVOICEDETAIL + "/invoiceDetail/" + invoiceDetail.getId()))
                .body(InvoiceDetailMapper.INSTANCE.toShowVO(invoiceDetail));
    }

    @Override
    @PutMapping
    public ResponseEntity<InvoiceDetailShowVO> update(@Valid @RequestBody InvoiceDetailUpdateDTO invoiceDetailUpdateDTO) {
        InvoiceDetailPO invoiceDetail = invoiceDetailService.update(invoiceDetailUpdateDTO);
        return ResponseEntity.ok(InvoiceDetailMapper.INSTANCE.toShowVO(invoiceDetail));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<InvoiceDetailListVO>> list(@Valid InvoiceDetailQO invoiceDetailQO) {
        PageVO<InvoiceDetailListVO> page = invoiceDetailService.list(invoiceDetailQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<InvoiceDetailShowVO> show(@PathVariable String id) {
        InvoiceDetailShowVO invoiceDetailShowVO = invoiceDetailService.show(id);
        return ResponseEntity.ok(invoiceDetailShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) {
        int count = invoiceDetailService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody String[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = invoiceDetailService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/export")
    public void exportExcel(@Valid InvoiceDetailQO invoiceDetailQO, HttpServletResponse response) throws Exception {
        invoiceDetailQO.setPageSize(Integer.MAX_VALUE);
        invoiceDetailQO.setCurrentPage(1);
        List<InvoiceDetailListVO> list = invoiceDetailService.list(invoiceDetailQO).getList();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("InvoiceDetail导出", "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), InvoiceDetailExcelVO.class)
                .sheet()
                .doWrite(InvoiceDetailMapper.INSTANCE.toExcelVOList(list));
    }

    @Override
    @PostMapping("/import")
    public ResponseEntity<Integer> importExcel(@RequestPart MultipartFile file) throws Exception {
        SyncReadExcelListener<InvoiceDetailExcelDTO> excelListener = new SyncReadExcelListener();
        EasyExcel.read(file.getInputStream())
                .head(InvoiceDetailExcelDTO.class)
                .sheet()
                .headRowNumber(3)
                .registerReadListener(excelListener)
                .doRead();
        List<InvoiceDetailAddDTO> list = excelListener.getList().stream()
                .map(excelDTO -> {
                    InvoiceDetailAddDTO addDTO = InvoiceDetailMapper.INSTANCE.fromExcelDTO(excelDTO);
                    // 校验数据
                    Set<ConstraintViolation<InvoiceDetailAddDTO>> set = validator.validate(addDTO);
                    if (!set.isEmpty()) {
                        ConstraintViolation<InvoiceDetailAddDTO> violation = set.stream().findFirst().get();
                        String errorMsg = "第" + (excelDTO.getRowIndex() + 1) + "行数据不合法：" + violation.getMessage();
                        throw new ConstraintViolationException(errorMsg, set);
                    }
                    return addDTO;
                })
                .collect(Collectors.toList());
        int count = invoiceDetailService.batchSave(list);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/template")
    public void downloadExcelTemplate(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String title = "InvoiceDetail导入模板(" + DateUtil.getDateStr(new Date()) + ")";
        String fileName = URLEncoder.encode(title, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        String[] description = new String[]{
                "模版前三行标题请勿修改",
                "带“*”号为必填项",
                "“发票ID”请填入id值",
        };
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                // 第一行是标题，第二行是说明
                .registerWriteHandler(new TitleDescriptionWriteHandler(title, description, InvoiceDetailExcelDTO.class))
                // 自定义模板单元格样式
                .registerWriteHandler(new TemplateCellStyleStrategy())
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "Sheet1")
                .head(InvoiceDetailExcelDTO.class)
                // 从第三行开始写表头
                .relativeHeadRowIndex(2)
                .build();
        excelWriter.write(Arrays.asList(InvoiceDetailExcelDTO.example()), writeSheet);

        excelWriter.finish();
    }

}


