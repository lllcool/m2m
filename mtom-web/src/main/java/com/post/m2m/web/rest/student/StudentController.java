package com.post.m2m.web.rest.student;

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
import com.post.m2m.pojo.dto.student.StudentAddDTO;
import com.post.m2m.pojo.dto.student.StudentExcelDTO;
import com.post.m2m.pojo.dto.student.StudentUpdateDTO;
import com.post.m2m.pojo.mapper.student.StudentMapper;
import com.post.m2m.pojo.po.student.StudentPO;
import com.post.m2m.pojo.qo.student.StudentQO;
import com.post.m2m.pojo.vo.student.StudentExcelVO;
import com.post.m2m.pojo.vo.student.StudentListVO;
import com.post.m2m.pojo.vo.student.StudentShowVO;
import com.post.m2m.service.student.StudentService;
import com.post.m2m.web.AbstractController;
import com.post.m2m.web.api.student.StudentAPI;
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
 * 【Student】控制器
 *
 * @author ljm
 * @date 2021/01/25
 */
@RestController
@RequestMapping(WebConst.ModulePath.STUDENT + "/student")
public class StudentController extends AbstractController implements StudentAPI {

    @Autowired
    private StudentService studentService;
    @Autowired
    private Validator validator;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentShowVO> save(@Valid @RequestBody StudentAddDTO studentAddDTO) throws Exception {
        StudentPO student = studentService.save(studentAddDTO);
        return ResponseEntity.created(new URI(WebConst.ModulePath.STUDENT + "/student/" + student.getId()))
                .body(StudentMapper.INSTANCE.toShowVO(student));
    }

    @Override
    @PutMapping
    public ResponseEntity<StudentShowVO> update(@Valid @RequestBody StudentUpdateDTO studentUpdateDTO) {
        StudentPO student = studentService.update(studentUpdateDTO);
        return ResponseEntity.ok(StudentMapper.INSTANCE.toShowVO(student));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<StudentListVO>> list(@Valid StudentQO studentQO) {
        PageVO<StudentListVO> page = studentService.list(studentQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/options")
    public ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo) {
        List<OptionVO<String, String>> options = studentService.findOptions(qo);
        return ResponseEntity.ok(options);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentShowVO> show(@PathVariable String id) {
        StudentShowVO studentShowVO = studentService.show(id);
        return ResponseEntity.ok(studentShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) {
        int count = studentService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody String[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = studentService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/export")
    public void exportExcel(@Valid StudentQO studentQO, HttpServletResponse response) throws Exception {
        studentQO.setPageSize(Integer.MAX_VALUE);
        studentQO.setPageNo(1);
        List<StudentListVO> list = studentService.list(studentQO).getList();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Student导出", "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), StudentExcelVO.class)
                .sheet()
                .doWrite(StudentMapper.INSTANCE.toExcelVOList(list));
    }

    @Override
    @PostMapping("/import")
    public ResponseEntity<Integer> importExcel(@RequestPart MultipartFile file) throws Exception {
        SyncReadExcelListener<StudentExcelDTO> excelListener = new SyncReadExcelListener();
        EasyExcel.read(file.getInputStream())
                .head(StudentExcelDTO.class)
                .sheet()
                .headRowNumber(3)
                .registerReadListener(excelListener)
                .doRead();
        List<StudentAddDTO> list = excelListener.getList().stream()
                .map(excelDTO -> {
                    StudentAddDTO addDTO = StudentMapper.INSTANCE.fromExcelDTO(excelDTO);
                    // 校验数据
                    Set<ConstraintViolation<StudentAddDTO>> set = validator.validate(addDTO);
                    if (!set.isEmpty()) {
                        ConstraintViolation<StudentAddDTO> violation = set.stream().findFirst().get();
                        String errorMsg = "第" + (excelDTO.getRowIndex() + 1) + "行数据不合法：" + violation.getMessage();
                        throw new ConstraintViolationException(errorMsg, set);
                    }
                    return addDTO;
                })
                .collect(Collectors.toList());
        int count = studentService.batchSave(list);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/template")
    public void downloadExcelTemplate(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String title = "Student导入模板(" + DateUtil.getDateStr(new Date()) + ")";
        String fileName = URLEncoder.encode(title, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        String[] description = new String[]{
                "模版前三行标题请勿修改",
                "带“*”号为必填项",
        };
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                // 第一行是标题，第二行是说明
                .registerWriteHandler(new TitleDescriptionWriteHandler(title, description, StudentExcelDTO.class))
                // 自定义模板单元格样式
                .registerWriteHandler(new TemplateCellStyleStrategy())
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "Sheet1")
                .head(StudentExcelDTO.class)
                // 从第三行开始写表头
                .relativeHeadRowIndex(2)
                .build();
        excelWriter.write(Arrays.asList(StudentExcelDTO.example()), writeSheet);

        excelWriter.finish();
    }

}


