package com.post.m2m.dao.ball;

import com.post.common.dao.DAO;
import com.post.m2m.pojo.po.ball.BallPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 【Ball】数据库操作
 *
 * @author ljm
 * @date 2021/01/25
 */
@Repository
@Mapper
public interface BallDAO extends DAO<BallPO> {


}



