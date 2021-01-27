package com.post.m2m.web.rest.stu_ref_teach;

import com.google.common.collect.Lists;
import com.post.common.util.JsonUtil;
import com.post.m2m.help.stu_ref_teach.StuRefTeachHelper;
import com.post.m2m.pojo.dto.stu_ref_teach.StuRefTeachAddDTO;
import com.post.m2m.pojo.dto.stu_ref_teach.StuRefTeachUpdateDTO;
import com.post.m2m.pojo.po.stu_ref_teach.StuRefTeachPO;
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
 * 【StuRefTeach】单元测试
 *
 * @author ljm
 * @date 2021/01/25
 */
public class StuRefTeachControllerTest extends AbstractWebTest {

    @Autowired
    private StuRefTeachHelper stuRefTeachHelper;


    /**
     * 新增【StuRefTeach】
     */
    @Test
    public void save() throws Exception {
        StuRefTeachAddDTO addDTO = stuRefTeachHelper.getStuRefTeachAddDTO(null, null);
        restMockMvc.perform(post(WebConst.ModulePath.STU_REF_TEACH + "/stuRefTeach")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(addDTO)))
                .andExpect(status().isCreated());
    }

    /**
     * 修改【StuRefTeach】
     */
    @Test
    public void update() throws Exception {
        StuRefTeachPO stuRefTeach = stuRefTeachHelper.saveStuRefTeachExample(null, null);
        StuRefTeachUpdateDTO updateDTO = stuRefTeachHelper.getStuRefTeachUpdateDTO(stuRefTeach);
        restMockMvc.perform(put(WebConst.ModulePath.STU_REF_TEACH + "/stuRefTeach")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(updateDTO)))
                .andExpect(status().isOk());
    }

    /**
     * 分页查询【StuRefTeach】
     */
    @Test
    public void list() throws Exception {
        StuRefTeachPO stuRefTeach = stuRefTeachHelper.saveStuRefTeachExample(null, null);
        restMockMvc.perform(get(WebConst.ModulePath.STU_REF_TEACH + "/stuRefTeach"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查看【StuRefTeach】详情
     */
    @Test
    public void show() throws Exception {
        StuRefTeachPO stuRefTeach = stuRefTeachHelper.saveStuRefTeachExample(null, null);
        restMockMvc.perform(get(WebConst.ModulePath.STU_REF_TEACH + "/stuRefTeach/{id}", stuRefTeach.getId()))
                .andExpect(status().isOk());
    }

    /**
     * 删除单个【StuRefTeach】
     */
    @Test
    public void del() throws Exception {
        StuRefTeachPO stuRefTeach = stuRefTeachHelper.saveStuRefTeachExample(null, null);
        restMockMvc.perform(delete(WebConst.ModulePath.STU_REF_TEACH + "/stuRefTeach/{id}", stuRefTeach.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【StuRefTeach】
     */
    @Test
    public void deleteBatch() throws Exception {
        StuRefTeachPO stuRefTeach = stuRefTeachHelper.saveStuRefTeachExample(null, null);
        restMockMvc.perform(delete(WebConst.ModulePath.STU_REF_TEACH + "/stuRefTeach")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(Lists.newArrayList(stuRefTeach.getId()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 导入【StuRefTeach】excel
     */
    @Test
    public void importExcel() throws Exception {
        // 首先下载excel模板
        MvcResult mvcResult = restMockMvc.perform(get(WebConst.ModulePath.STU_REF_TEACH + "/stuRefTeach/template"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // 将模板原封不动导入
        MockMultipartFile file = new MockMultipartFile("file", response.getContentAsByteArray());
        restMockMvc.perform(multipart(WebConst.ModulePath.STU_REF_TEACH + "/stuRefTeach/import")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }


}
