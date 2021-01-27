package com.post.m2m.web.rest.ball;

import com.google.common.collect.Lists;
import com.post.common.util.JsonUtil;
import com.post.m2m.help.ball.BallHelper;
import com.post.m2m.pojo.dto.ball.BallAddDTO;
import com.post.m2m.pojo.dto.ball.BallUpdateDTO;
import com.post.m2m.pojo.po.ball.BallPO;
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
 * 【Ball】单元测试
 *
 * @author ljm
 * @date 2021/01/25
 */
public class BallControllerTest extends AbstractWebTest {

    @Autowired
    private BallHelper ballHelper;


    /**
     * 新增【Ball】
     */
    @Test
    public void save() throws Exception {
        BallAddDTO addDTO = ballHelper.getBallAddDTO();
        restMockMvc.perform(post(WebConst.ModulePath.BALL + "/ball")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(addDTO)))
                .andExpect(status().isCreated());
    }

    /**
     * 修改【Ball】
     */
    @Test
    public void update() throws Exception {
        BallPO ball = ballHelper.saveBallExample();
        BallUpdateDTO updateDTO = ballHelper.getBallUpdateDTO(ball);
        restMockMvc.perform(put(WebConst.ModulePath.BALL + "/ball")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(updateDTO)))
                .andExpect(status().isOk());
    }

    /**
     * 分页查询【Ball】
     */
    @Test
    public void list() throws Exception {
        BallPO ball = ballHelper.saveBallExample();
        restMockMvc.perform(get(WebConst.ModulePath.BALL + "/ball"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查看【Ball】详情
     */
    @Test
    public void show() throws Exception {
        BallPO ball = ballHelper.saveBallExample();
        restMockMvc.perform(get(WebConst.ModulePath.BALL + "/ball/{id}", ball.getId()))
                .andExpect(status().isOk());
    }

    /**
     * 删除单个【Ball】
     */
    @Test
    public void del() throws Exception {
        BallPO ball = ballHelper.saveBallExample();
        restMockMvc.perform(delete(WebConst.ModulePath.BALL + "/ball/{id}", ball.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【Ball】
     */
    @Test
    public void deleteBatch() throws Exception {
        BallPO ball = ballHelper.saveBallExample();
        restMockMvc.perform(delete(WebConst.ModulePath.BALL + "/ball")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(Lists.newArrayList(ball.getId()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }


}
