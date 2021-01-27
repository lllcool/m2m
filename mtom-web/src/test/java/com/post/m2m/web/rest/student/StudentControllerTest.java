package com.post.m2m.web.rest.student;

import com.google.common.collect.Lists;
import com.post.common.util.JsonUtil;
import com.post.m2m.help.student.StudentHelper;
import com.post.m2m.pojo.dto.student.StudentAddDTO;
import com.post.m2m.pojo.dto.student.StudentUpdateDTO;
import com.post.m2m.pojo.po.student.StudentPO;
import com.post.m2m.web.AbstractWebTest;
import com.post.m2m.web.constant.WebConst;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 【Student】单元测试
 *
 * @author ljm
 * @date 2021/01/25
 */
public class StudentControllerTest extends AbstractWebTest {

    @Autowired
    private StudentHelper studentHelper;


    /**
     * 新增【Student】
     */
    @Test
    public void save() throws Exception {
        StudentAddDTO addDTO = studentHelper.getStudentAddDTO();
        restMockMvc.perform(post(WebConst.ModulePath.STUDENT + "/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(addDTO)))
                .andExpect(status().isCreated());
    }

    /**
     * 修改【Student】
     */
    @Test
    public void update() throws Exception {
        StudentPO student = studentHelper.saveStudentExample();
        StudentUpdateDTO updateDTO = studentHelper.getStudentUpdateDTO(student);
        restMockMvc.perform(put(WebConst.ModulePath.STUDENT + "/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(updateDTO)))
                .andExpect(status().isOk());
    }

    /**
     * 分页查询【Student】
     */
    @Test
    public void list() throws Exception {
        StudentPO student = studentHelper.saveStudentExample();
        restMockMvc.perform(get(WebConst.ModulePath.STUDENT + "/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查询【Student】选项列表
     */
    @Test
    public void findOptions() throws Exception {
        StudentPO student = studentHelper.saveStudentExample();
        restMockMvc.perform(get(WebConst.ModulePath.STUDENT + "/student/options"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(is(1)));
    }

    /**
     * 查看【Student】详情
     */
    @Test
    public void show() throws Exception {
        StudentPO student = studentHelper.saveStudentExample();
        restMockMvc.perform(get(WebConst.ModulePath.STUDENT + "/student/{id}", student.getId()))
                .andExpect(status().isOk());
    }

    /**
     * 删除单个【Student】
     */
    @Test
    public void del() throws Exception {
        StudentPO student = studentHelper.saveStudentExample();
        restMockMvc.perform(delete(WebConst.ModulePath.STUDENT + "/student/{id}", student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【Student】
     */
    @Test
    public void deleteBatch() throws Exception {
        StudentPO student = studentHelper.saveStudentExample();
        restMockMvc.perform(delete(WebConst.ModulePath.STUDENT + "/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(Lists.newArrayList(student.getId()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 导入【Student】excel
     */
    @Test
    public void importExcel() throws Exception {
        // 首先下载excel模板
        MvcResult mvcResult = restMockMvc.perform(get(WebConst.ModulePath.STUDENT + "/student/template"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // 将模板原封不动导入
        MockMultipartFile file = new MockMultipartFile("file", response.getContentAsByteArray());
        restMockMvc.perform(multipart(WebConst.ModulePath.STUDENT + "/student/import")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }


}
