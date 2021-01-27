package com.post.m2m.web.rest.member;

import com.google.common.collect.Lists;
import com.post.common.util.JsonUtil;
import com.post.m2m.help.member.MemberHelper;
import com.post.m2m.pojo.dto.member.MemberAddDTO;
import com.post.m2m.pojo.dto.member.MemberUpdateDTO;
import com.post.m2m.pojo.po.member.MemberPO;
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
 * 【Member】单元测试
 *
 * @author ljm
 * @date 2021/01/25
 */
public class MemberControllerTest extends AbstractWebTest {

    @Autowired
    private MemberHelper memberHelper;


    /**
     * 新增【Member】
     */
    @Test
    public void save() throws Exception {
        MemberAddDTO addDTO = memberHelper.getMemberAddDTO();
        restMockMvc.perform(post(WebConst.ModulePath.MEMBER + "/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(addDTO)))
                .andExpect(status().isCreated());
    }

    /**
     * 修改【Member】
     */
    @Test
    public void update() throws Exception {
        MemberPO member = memberHelper.saveMemberExample();
        MemberUpdateDTO updateDTO = memberHelper.getMemberUpdateDTO(member);
        restMockMvc.perform(put(WebConst.ModulePath.MEMBER + "/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(updateDTO)))
                .andExpect(status().isOk());
    }

    /**
     * 分页查询【Member】
     */
    @Test
    public void list() throws Exception {
        MemberPO member = memberHelper.saveMemberExample();
        restMockMvc.perform(get(WebConst.ModulePath.MEMBER + "/member"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查看【Member】详情
     */
    @Test
    public void show() throws Exception {
        MemberPO member = memberHelper.saveMemberExample();
        restMockMvc.perform(get(WebConst.ModulePath.MEMBER + "/member/{id}", member.getId()))
                .andExpect(status().isOk());
    }

    /**
     * 删除单个【Member】
     */
    @Test
    public void del() throws Exception {
        MemberPO member = memberHelper.saveMemberExample();
        restMockMvc.perform(delete(WebConst.ModulePath.MEMBER + "/member/{id}", member.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【Member】
     */
    @Test
    public void deleteBatch() throws Exception {
        MemberPO member = memberHelper.saveMemberExample();
        restMockMvc.perform(delete(WebConst.ModulePath.MEMBER + "/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(Lists.newArrayList(member.getId()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 导入【Member】excel
     */
    @Test
    public void importExcel() throws Exception {
        // 首先下载excel模板
        MvcResult mvcResult = restMockMvc.perform(get(WebConst.ModulePath.MEMBER + "/member/template"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // 将模板原封不动导入
        MockMultipartFile file = new MockMultipartFile("file", response.getContentAsByteArray());
        restMockMvc.perform(multipart(WebConst.ModulePath.MEMBER + "/member/import")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }


}
