package com.post.m2m.help.ball;

import com.post.common.util.SafeUtil;
import com.post.m2m.pojo.dto.ball.*;
import com.post.m2m.pojo.po.ball.*;
import com.post.m2m.service.ball.BallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.post.m2m.pojo.example.ball.BallExample.*;

@Component
public class BallHelper {

    @Autowired
    private BallService ballService;

    /**
     * 生成add测试数据
     *
     * @return
     */
    public BallAddDTO getBallAddDTO() {
        BallAddDTO dto = new BallAddDTO();
        dto.setBallName(E_BALL_NAME);
        dto.setPrice(SafeUtil.getInteger(E_PRICE));
        return dto;
    }


    /**
     * 生成update测试数据
     *
     * @return
     */
    public BallUpdateDTO getBallUpdateDTO(BallPO ball) {
        BallUpdateDTO dto = new BallUpdateDTO();
        dto.setId(ball.getId());
        dto.setBallName(ball.getBallName());
        dto.setPrice(ball.getPrice());
        return dto;
    }

    /**
     * 保存示例
     *
     * @return
     */
    public BallPO saveBallExample() {
        BallAddDTO addDTO = this.getBallAddDTO();
        return ballService.save(addDTO);
    }


}

