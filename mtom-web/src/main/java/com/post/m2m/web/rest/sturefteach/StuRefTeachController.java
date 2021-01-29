package com.post.m2m.web.rest.sturefteach;

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
import com.post.m2m.pojo.dto.sturefteach.StuRefTeachAddDTO;
import com.post.m2m.pojo.dto.sturefteach.StuRefTeachExcelDTO;
import com.post.m2m.pojo.dto.sturefteach.StuRefTeachUpdateDTO;
import com.post.m2m.pojo.mapper.sturefteach.StuRefTeachMapper;
import com.post.m2m.pojo.po.sturefteach.StuRefTeachPO;
import com.post.m2m.pojo.qo.sturefteach.StuRefTeachQO;
import com.post.m2m.pojo.vo.sturefteach.StuRefTeachExcelVO;
import com.post.m2m.pojo.vo.sturefteach.StuRefTeachListVO;
import com.post.m2m.pojo.vo.sturefteach.StuRefTeachShowVO;
import com.post.m2m.service.sturefteach.StuRefTeachService;
import com.post.m2m.web.AbstractController;
import com.post.m2m.web.api.sturefteach.StuRefTeachAPI;
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
 * 【StuRefTeach】控制器
 *
 * @author ljm
 * @date 2021/01/25
 */
@RestController
@RequestMapping(WebConst.ModulePath.STUREFTEACH + "/stuRefTeach")
public class StuRefTeachController extends AbstractController implements StuRefTeachAPI {

    @Autowired
    private StuRefTeachService stuRefTeachService;
    @Autowired
    private Validator validator;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StuRefTeachShowVO> save(@Valid @RequestBody StuRefTeachAddDTO stuRefTeachAddDTO) throws Exception {
        StuRefTeachPO stuRefTeach = stuRefTeachService.save(stuRefTeachAddDTO);
        return ResponseEntity.created(new URI(WebConst.ModulePath.STUREFTEACH + "/stuRefTeach/" + stuRefTeach.getId()))
                .body(StuRefTeachMapper.INSTANCE.toShowVO(stuRefTeach));
    }

    @Override
    @PutMapping
    public ResponseEntity<StuRefTeachShowVO> update(@Valid @RequestBody StuRefTeachUpdateDTO stuRefTeachUpdateDTO) {
        StuRefTeachPO stuRefTeach = stuRefTeachService.update(stuRefTeachUpdateDTO);
        return ResponseEntity.ok(StuRefTeachMapper.INSTANCE.toShowVO(stuRefTeach));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<StuRefTeachListVO>> list(@Valid StuRefTeachQO stuRefTeachQO) {
        PageVO<StuRefTeachListVO> page = stuRefTeachService.list(stuRefTeachQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<StuRefTeachShowVO> show(@PathVariable String id) {
        StuRefTeachShowVO stuRefTeachShowVO = stuRefTeachService.show(id);
        return ResponseEntity.ok(stuRefTeachShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) {
        int count = stuRefTeachService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody String[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = stuRefTeachService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/export")
    public void exportExcel(@Valid StuRefTeachQO stuRefTeachQO, HttpServletResponse response) throws Exception {
        stuRefTeachQO.setPageSize(Integer.MAX_VALUE);
        stuRefTeachQO.setCurrentPage(1);
        List<StuRefTeachListVO> list = stuRefTeachService.list(stuRefTeachQO).getList();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("StuRefTeach导出", "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), StuRefTeachExcelVO.class)
                .sheet()
                .doWrite(StuRefTeachMapper.INSTANCE.toExcelVOList(list));
    }

    @Override
    @PostMapping("/import")
    public ResponseEntity<Integer> importExcel(@RequestPart MultipartFile file) throws Exception {
        SyncReadExcelListener<StuRefTeachExcelDTO> excelListener = new SyncReadExcelListener();
        EasyExcel.read(file.getInputStream())
                .head(StuRefTeachExcelDTO.class)
                .sheet()
                .headRowNumber(3)
                .registerReadListener(excelListener)
                .doRead();
        List<StuRefTeachAddDTO> list = excelListener.getList().stream()
                .map(excelDTO -> {
                    StuRefTeachAddDTO addDTO = StuRefTeachMapper.INSTANCE.fromExcelDTO(excelDTO);
                    // 校验数据
                    Set<ConstraintViolation<StuRefTeachAddDTO>> set = validator.validate(addDTO);
                    if (!set.isEmpty()) {
                        ConstraintViolation<StuRefTeachAddDTO> violation = set.stream().findFirst().get();
                        String errorMsg = "第" + (excelDTO.getRowIndex() + 1) + "行数据不合法：" + violation.getMessage();
                        throw new ConstraintViolationException(errorMsg, set);
                    }
                    return addDTO;
                })
                .collect(Collectors.toList());
        int count = stuRefTeachService.batchSave(list);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/template")
    public void downloadExcelTemplate(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String title = "StuRefTeach导入模板(" + DateUtil.getDateStr(new Date()) + ")";
        String fileName = URLEncoder.encode(title, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        String[] description = new String[]{
                "模版前三行标题请勿修改",
                "带“*”号为必填项",
                "“学生ID”、“老师ID”请填入id值",
        };
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                // 第一行是标题，第二行是说明
                .registerWriteHandler(new TitleDescriptionWriteHandler(title, description, StuRefTeachExcelDTO.class))
                // 自定义模板单元格样式
                .registerWriteHandler(new TemplateCellStyleStrategy())
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "Sheet1")
                .head(StuRefTeachExcelDTO.class)
                // 从第三行开始写表头
                .relativeHeadRowIndex(2)
                .build();
        excelWriter.write(Arrays.asList(StuRefTeachExcelDTO.example()), writeSheet);

        excelWriter.finish();
    }

}


