package com.post.m2m.web.rest.teacher;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.DateUtil;
import com.post.m2m.excel.handler.TemplateCellStyleStrategy;
import com.post.m2m.excel.handler.TitleDescriptionWriteHandler;
import com.post.m2m.excel.listener.SyncReadExcelListener;
import com.post.m2m.pojo.dto.teacher.TeacherAddDTO;
import com.post.m2m.pojo.dto.teacher.TeacherExcelDTO;
import com.post.m2m.pojo.dto.teacher.TeacherUpdateDTO;
import com.post.m2m.pojo.mapper.teacher.TeacherMapper;
import com.post.m2m.pojo.po.teacher.TeacherPO;
import com.post.m2m.pojo.qo.teacher.TeacherQO;
import com.post.m2m.pojo.vo.teacher.TeacherExcelVO;
import com.post.m2m.pojo.vo.teacher.TeacherListVO;
import com.post.m2m.pojo.vo.teacher.TeacherShowVO;
import com.post.m2m.service.teacher.TeacherService;
import com.post.m2m.web.AbstractController;
import com.post.m2m.web.api.teacher.TeacherAPI;
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
 * 【Teacher】控制器
 *
 * @author ljm
 * @date 2021/01/25
 */
@RestController
@RequestMapping(WebConst.ModulePath.TEACHER + "/teacher")
public class TeacherController extends AbstractController implements TeacherAPI {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private Validator validator;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeacherShowVO> save(@Valid @RequestBody TeacherAddDTO teacherAddDTO) throws Exception {
        TeacherPO teacher = teacherService.save(teacherAddDTO);
        return ResponseEntity.created(new URI(WebConst.ModulePath.TEACHER + "/teacher/" + teacher.getId()))
                .body(TeacherMapper.INSTANCE.toShowVO(teacher));
    }

    @Override
    @PutMapping
    public ResponseEntity<TeacherShowVO> update(@Valid @RequestBody TeacherUpdateDTO teacherUpdateDTO) {
        TeacherPO teacher = teacherService.update(teacherUpdateDTO);
        return ResponseEntity.ok(TeacherMapper.INSTANCE.toShowVO(teacher));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<TeacherListVO>> list(@Valid TeacherQO teacherQO) {
        PageVO<TeacherListVO> page = teacherService.list(teacherQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/options")
    public ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo) {
        List<OptionVO<String, String>> options = teacherService.findOptions(qo);
        return ResponseEntity.ok(options);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<TeacherShowVO> show(@PathVariable String id) {
        TeacherShowVO teacherShowVO = teacherService.show(id);
        return ResponseEntity.ok(teacherShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) {
        int count = teacherService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody String[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = teacherService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/export")
    public void exportExcel(@Valid TeacherQO teacherQO, HttpServletResponse response) throws Exception {
        teacherQO.setPageSize(Integer.MAX_VALUE);
        teacherQO.setCurrentPage(1);
        List<TeacherListVO> list = teacherService.list(teacherQO).getList();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Teacher导出", "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), TeacherExcelVO.class)
                .sheet()
                .doWrite(TeacherMapper.INSTANCE.toExcelVOList(list));
    }

    @Override
    @PostMapping("/import")
    public ResponseEntity<Integer> importExcel(@RequestPart MultipartFile file) throws Exception {
        SyncReadExcelListener<TeacherExcelDTO> excelListener = new SyncReadExcelListener();
        EasyExcel.read(file.getInputStream())
                .head(TeacherExcelDTO.class)
                .sheet()
                .headRowNumber(3)
                .registerReadListener(excelListener)
                .doRead();
        List<TeacherAddDTO> list = excelListener.getList().stream()
                .map(excelDTO -> {
                    TeacherAddDTO addDTO = TeacherMapper.INSTANCE.fromExcelDTO(excelDTO);
                    // 校验数据
                    Set<ConstraintViolation<TeacherAddDTO>> set = validator.validate(addDTO);
                    if (!set.isEmpty()) {
                        ConstraintViolation<TeacherAddDTO> violation = set.stream().findFirst().get();
                        String errorMsg = "第" + (excelDTO.getRowIndex() + 1) + "行数据不合法：" + violation.getMessage();
                        throw new ConstraintViolationException(errorMsg, set);
                    }
                    return addDTO;
                })
                .collect(Collectors.toList());
        int count = teacherService.batchSave(list);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/template")
    public void downloadExcelTemplate(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String title = "Teacher导入模板(" + DateUtil.getDateStr(new Date()) + ")";
        String fileName = URLEncoder.encode(title, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        String[] description = new String[]{
                "模版前三行标题请勿修改",
                "带“*”号为必填项",
        };
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                // 第一行是标题，第二行是说明
                .registerWriteHandler(new TitleDescriptionWriteHandler(title, description, TeacherExcelDTO.class))
                // 自定义模板单元格样式
                .registerWriteHandler(new TemplateCellStyleStrategy())
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "Sheet1")
                .head(TeacherExcelDTO.class)
                // 从第三行开始写表头
                .relativeHeadRowIndex(2)
                .build();
        excelWriter.write(Arrays.asList(TeacherExcelDTO.example()), writeSheet);

        excelWriter.finish();
    }

}


