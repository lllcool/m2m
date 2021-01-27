package com.post.m2m.service.ball;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.UUIDUtil;
import com.post.m2m.dao.ball.BallDAO;
import com.post.m2m.pojo.dto.ball.BallAddDTO;
import com.post.m2m.pojo.dto.ball.BallUpdateDTO;
import com.post.m2m.pojo.mapper.ball.BallMapper;
import com.post.m2m.pojo.po.ball.BallPO;
import com.post.m2m.pojo.qo.ball.BallQO;
import com.post.m2m.pojo.vo.ball.BallListVO;
import com.post.m2m.pojo.vo.ball.BallShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【Ball】增删改查服务
 *
 * @author ljm
 * @date 2021/01/25
 */
@Service
public class BallService {

    @Autowired
    private BallDAO ballDAO;


    /**
     * 新增【Ball】
     *
     * @param ballDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public BallPO save(BallAddDTO ballDTO) {
        BallPO ball = BallMapper.INSTANCE.fromAddDTO(ballDTO);
        ball.setId(UUIDUtil.getUUID());
        ballDAO.save(ball);
        return ball;
    }

    /**
     * 修改【Ball】
     *
     * @param ballUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public BallPO update(BallUpdateDTO ballUpdateDTO) {
        String id = ballUpdateDTO.getId();
        BallPO ball = this.getBall(id, true);
        BallMapper.INSTANCE.setUpdateDTO(ball, ballUpdateDTO);
        ballDAO.update(ball);
        return ball;
    }

    /**
     * 查询分页列表
     *
     * @param ballQO
     * @return
     */
    public PageVO<BallListVO> list(BallQO ballQO) {
        PageVO<BallListVO> page = ballDAO.findByPage(ballQO);
        return page;
    }

    /**
     * 根据主键获取【Ball】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public BallPO getBall(String id, boolean force) {
        BallPO ball = ballDAO.findById(id);
        if (force && ball == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return ball;
    }


    /**
     * 查询【Ball】详情
     *
     * @param id
     * @return
     */
    public BallShowVO show(String id) {
        BallPO ball = this.getBall(id, true);
        BallShowVO showVO = BallMapper.INSTANCE.toShowVO(ball);
        return showVO;
    }

    /**
     * 删除【Ball】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(String... ids) {
        int count = 0;
        for (String id : ids) {
            count += ballDAO.delete(id);
        }
        return count;
    }


}


