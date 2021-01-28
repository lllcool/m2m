package com.post.m2m.web.rest.teama;

import com.google.common.collect.Lists;
import com.post.common.util.JsonUtil;
import com.post.m2m.help.teama.TeamaHelper;
import com.post.m2m.pojo.dto.teama.TeamaAddDTO;
import com.post.m2m.pojo.dto.teama.TeamaUpdateDTO;
import com.post.m2m.pojo.po.teama.TeamaPO;
import com.post.m2m.web.AbstractWebTest;
import com.post.m2m.web.constant.WebConst;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 【teama】单元测试
 *
 * @author ljm
 * @date 2021/01/25
 */
public class TeamaControllerTest extends AbstractWebTest {

    @Autowired
    private TeamaHelper teamaHelper;


    /**
     * 新增【teama】
     */
    @Test
    public void save() throws Exception {
        TeamaAddDTO addDTO = teamaHelper.getTeamaAddDTO();
        restMockMvc.perform(post(WebConst.ModulePath.TEAMA + "/teama")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(addDTO)))
                .andExpect(status().isCreated());
    }

    /**
     * 修改【teama】
     */
    @Test
    public void update() throws Exception {
        TeamaPO teama = teamaHelper.saveTeamaExample();
        TeamaUpdateDTO updateDTO = teamaHelper.getTeamaUpdateDTO(teama);
        restMockMvc.perform(put(WebConst.ModulePath.TEAMA + "/teama")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(updateDTO)))
                .andExpect(status().isOk());
    }

    /**
     * 分页查询【teama】
     */
    @Test
    public void list() throws Exception {
        TeamaPO teama = teamaHelper.saveTeamaExample();
        restMockMvc.perform(get(WebConst.ModulePath.TEAMA + "/teama"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查询【teama】选项列表
     */
    @Test
    public void findOptions() throws Exception {
        TeamaPO teama = teamaHelper.saveTeamaExample();
        restMockMvc.perform(get(WebConst.ModulePath.TEAMA + "/teama/options"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(is(1)));
    }

    /**
     * 查看【teama】详情
     */
    @Test
    public void show() throws Exception {
        TeamaPO teama = teamaHelper.saveTeamaExample();
        restMockMvc.perform(get(WebConst.ModulePath.TEAMA + "/teama/{id}", teama.getId()))
                .andExpect(status().isOk());
    }

    /**
     * 删除单个【teama】
     */
    @Test
    public void del() throws Exception {
        TeamaPO teama = teamaHelper.saveTeamaExample();
        restMockMvc.perform(delete(WebConst.ModulePath.TEAMA + "/teama/{id}", teama.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【teama】
     */
    @Test
    public void deleteBatch() throws Exception {
        TeamaPO teama = teamaHelper.saveTeamaExample();
        restMockMvc.perform(delete(WebConst.ModulePath.TEAMA + "/teama")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(Lists.newArrayList(teama.getId()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }


}
