package com.post.m2m.web.rest.teacher;

import com.google.common.collect.Lists;
import com.post.common.util.JsonUtil;
import com.post.m2m.help.teacher.TeacherHelper;
import com.post.m2m.pojo.dto.teacher.TeacherAddDTO;
import com.post.m2m.pojo.dto.teacher.TeacherUpdateDTO;
import com.post.m2m.pojo.po.teacher.TeacherPO;
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
 * 【Teacher】单元测试
 *
 * @author ljm
 * @date 2021/01/25
 */
public class TeacherControllerTest extends AbstractWebTest {

    @Autowired
    private TeacherHelper teacherHelper;


    /**
     * 新增【Teacher】
     */
    @Test
    public void save() throws Exception {
        TeacherAddDTO addDTO = teacherHelper.getTeacherAddDTO();
        restMockMvc.perform(post(WebConst.ModulePath.TEACHER + "/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(addDTO)))
                .andExpect(status().isCreated());
    }

    /**
     * 修改【Teacher】
     */
    @Test
    public void update() throws Exception {
        TeacherPO teacher = teacherHelper.saveTeacherExample();
        TeacherUpdateDTO updateDTO = teacherHelper.getTeacherUpdateDTO(teacher);
        restMockMvc.perform(put(WebConst.ModulePath.TEACHER + "/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(updateDTO)))
                .andExpect(status().isOk());
    }

    /**
     * 分页查询【Teacher】
     */
    @Test
    public void list() throws Exception {
        TeacherPO teacher = teacherHelper.saveTeacherExample();
        restMockMvc.perform(get(WebConst.ModulePath.TEACHER + "/teacher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查询【Teacher】选项列表
     */
    @Test
    public void findOptions() throws Exception {
        TeacherPO teacher = teacherHelper.saveTeacherExample();
        restMockMvc.perform(get(WebConst.ModulePath.TEACHER + "/teacher/options"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(is(1)));
    }

    /**
     * 查看【Teacher】详情
     */
    @Test
    public void show() throws Exception {
        TeacherPO teacher = teacherHelper.saveTeacherExample();
        restMockMvc.perform(get(WebConst.ModulePath.TEACHER + "/teacher/{id}", teacher.getId()))
                .andExpect(status().isOk());
    }

    /**
     * 删除单个【Teacher】
     */
    @Test
    public void del() throws Exception {
        TeacherPO teacher = teacherHelper.saveTeacherExample();
        restMockMvc.perform(delete(WebConst.ModulePath.TEACHER + "/teacher/{id}", teacher.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【Teacher】
     */
    @Test
    public void deleteBatch() throws Exception {
        TeacherPO teacher = teacherHelper.saveTeacherExample();
        restMockMvc.perform(delete(WebConst.ModulePath.TEACHER + "/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(Lists.newArrayList(teacher.getId()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 导入【Teacher】excel
     */
    @Test
    public void importExcel() throws Exception {
        // 首先下载excel模板
        MvcResult mvcResult = restMockMvc.perform(get(WebConst.ModulePath.TEACHER + "/teacher/template"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // 将模板原封不动导入
        MockMultipartFile file = new MockMultipartFile("file", response.getContentAsByteArray());
        restMockMvc.perform(multipart(WebConst.ModulePath.TEACHER + "/teacher/import")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }


}
