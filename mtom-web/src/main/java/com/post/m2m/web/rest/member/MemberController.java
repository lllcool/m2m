package com.post.m2m.web.rest.member;

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
import com.post.m2m.pojo.dto.member.MemberAddDTO;
import com.post.m2m.pojo.dto.member.MemberExcelDTO;
import com.post.m2m.pojo.dto.member.MemberUpdateDTO;
import com.post.m2m.pojo.mapper.member.MemberMapper;
import com.post.m2m.pojo.po.member.MemberPO;
import com.post.m2m.pojo.qo.member.MemberQO;
import com.post.m2m.pojo.vo.member.MemberExcelVO;
import com.post.m2m.pojo.vo.member.MemberListVO;
import com.post.m2m.pojo.vo.member.MemberShowVO;
import com.post.m2m.service.member.MemberService;
import com.post.m2m.web.AbstractController;
import com.post.m2m.web.api.member.MemberAPI;
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
 * 【Member】控制器
 *
 * @author ljm
 * @date 2021/01/25
 */
@RestController
@RequestMapping(WebConst.ModulePath.MEMBER + "/member")
public class MemberController extends AbstractController implements MemberAPI {

    @Autowired
    private MemberService memberService;
    @Autowired
    private Validator validator;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MemberShowVO> save(@Valid @RequestBody MemberAddDTO memberAddDTO) throws Exception {
        MemberPO member = memberService.save(memberAddDTO);
        return ResponseEntity.created(new URI(WebConst.ModulePath.MEMBER + "/member/" + member.getId()))
                .body(MemberMapper.INSTANCE.toShowVO(member));
    }

    @Override
    @PutMapping
    public ResponseEntity<MemberShowVO> update(@Valid @RequestBody MemberUpdateDTO memberUpdateDTO) {
        MemberPO member = memberService.update(memberUpdateDTO);
        return ResponseEntity.ok(MemberMapper.INSTANCE.toShowVO(member));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<MemberListVO>> list(@Valid MemberQO memberQO) {
        PageVO<MemberListVO> page = memberService.list(memberQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberShowVO> show(@PathVariable String id) {
        MemberShowVO memberShowVO = memberService.show(id);
        return ResponseEntity.ok(memberShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) {
        int count = memberService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody String[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = memberService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/export")
    public void exportExcel(@Valid MemberQO memberQO, HttpServletResponse response) throws Exception {
        memberQO.setPageSize(Integer.MAX_VALUE);
        memberQO.setPageNo(1);
        List<MemberListVO> list = memberService.list(memberQO).getList();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Member导出", "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), MemberExcelVO.class)
                .sheet()
                .doWrite(MemberMapper.INSTANCE.toExcelVOList(list));
    }

    @Override
    @PostMapping("/import")
    public ResponseEntity<Integer> importExcel(@RequestPart MultipartFile file) throws Exception {
        SyncReadExcelListener<MemberExcelDTO> excelListener = new SyncReadExcelListener();
        EasyExcel.read(file.getInputStream())
                .head(MemberExcelDTO.class)
                .sheet()
                .headRowNumber(3)
                .registerReadListener(excelListener)
                .doRead();
        List<MemberAddDTO> list = excelListener.getList().stream()
                .map(excelDTO -> {
                    MemberAddDTO addDTO = MemberMapper.INSTANCE.fromExcelDTO(excelDTO);
                    // 校验数据
                    Set<ConstraintViolation<MemberAddDTO>> set = validator.validate(addDTO);
                    if (!set.isEmpty()) {
                        ConstraintViolation<MemberAddDTO> violation = set.stream().findFirst().get();
                        String errorMsg = "第" + (excelDTO.getRowIndex() + 1) + "行数据不合法：" + violation.getMessage();
                        throw new ConstraintViolationException(errorMsg, set);
                    }
                    return addDTO;
                })
                .collect(Collectors.toList());
        int count = memberService.batchSave(list);
        return ResponseEntity.ok(count);
    }

    @Override
    @GetMapping("/template")
    public void downloadExcelTemplate(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String title = "Member导入模板(" + DateUtil.getDateStr(new Date()) + ")";
        String fileName = URLEncoder.encode(title, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        String[] description = new String[]{
                "模版前三行标题请勿修改",
                "带“*”号为必填项",
        };
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                // 第一行是标题，第二行是说明
                .registerWriteHandler(new TitleDescriptionWriteHandler(title, description, MemberExcelDTO.class))
                // 自定义模板单元格样式
                .registerWriteHandler(new TemplateCellStyleStrategy())
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "Sheet1")
                .head(MemberExcelDTO.class)
                // 从第三行开始写表头
                .relativeHeadRowIndex(2)
                .build();
        excelWriter.write(Arrays.asList(MemberExcelDTO.example()), writeSheet);

        excelWriter.finish();
    }

}


