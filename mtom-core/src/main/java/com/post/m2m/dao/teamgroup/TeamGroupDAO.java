package com.post.m2m.dao.teamgroup;

import com.post.common.dao.DAO;
import com.post.m2m.pojo.po.teamgroup.TeamGroupPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 【teamGroup】数据库操作
 *
 * @author ljm
 * @date 2021/01/25
 */
@Repository
@Mapper
public interface TeamGroupDAO extends DAO<TeamGroupPO> {

    int getCountByTeamId(String teamId);


}



