package com.post.m2m.web.rest.teamgroup;

import com.google.common.collect.Lists;
import com.post.common.util.JsonUtil;
import com.post.m2m.help.teamgroup.TeamGroupHelper;
import com.post.m2m.pojo.dto.teamgroup.TeamGroupAddDTO;
import com.post.m2m.pojo.dto.teamgroup.TeamGroupUpdateDTO;
import com.post.m2m.pojo.po.teamgroup.TeamGroupPO;
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
 * 【teamGroup】单元测试
 *
 * @author ljm
 * @date 2021/01/25
 */
public class TeamGroupControllerTest extends AbstractWebTest {

    @Autowired
    private TeamGroupHelper teamGroupHelper;


    /**
     * 新增【teamGroup】
     */
    @Test
    public void save() throws Exception {
        TeamGroupAddDTO addDTO = teamGroupHelper.getTeamGroupAddDTO(null);
        restMockMvc.perform(post(WebConst.ModulePath.TEAMGROUP + "/teamGroup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(addDTO)))
                .andExpect(status().isCreated());
    }

    /**
     * 修改【teamGroup】
     */
    @Test
    public void update() throws Exception {
        TeamGroupPO teamGroup = teamGroupHelper.saveTeamGroupExample(null);
        TeamGroupUpdateDTO updateDTO = teamGroupHelper.getTeamGroupUpdateDTO(teamGroup);
        restMockMvc.perform(put(WebConst.ModulePath.TEAMGROUP + "/teamGroup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(updateDTO)))
                .andExpect(status().isOk());
    }

    /**
     * 分页查询【teamGroup】
     */
    @Test
    public void list() throws Exception {
        TeamGroupPO teamGroup = teamGroupHelper.saveTeamGroupExample(null);
        restMockMvc.perform(get(WebConst.ModulePath.TEAMGROUP + "/teamGroup"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查看【teamGroup】详情
     */
    @Test
    public void show() throws Exception {
        TeamGroupPO teamGroup = teamGroupHelper.saveTeamGroupExample(null);
        restMockMvc.perform(get(WebConst.ModulePath.TEAMGROUP + "/teamGroup/{id}", teamGroup.getId()))
                .andExpect(status().isOk());
    }

    /**
     * 删除单个【teamGroup】
     */
    @Test
    public void del() throws Exception {
        TeamGroupPO teamGroup = teamGroupHelper.saveTeamGroupExample(null);
        restMockMvc.perform(delete(WebConst.ModulePath.TEAMGROUP + "/teamGroup/{id}", teamGroup.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【teamGroup】
     */
    @Test
    public void deleteBatch() throws Exception {
        TeamGroupPO teamGroup = teamGroupHelper.saveTeamGroupExample(null);
        restMockMvc.perform(delete(WebConst.ModulePath.TEAMGROUP + "/teamGroup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(Lists.newArrayList(teamGroup.getId()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }


}
