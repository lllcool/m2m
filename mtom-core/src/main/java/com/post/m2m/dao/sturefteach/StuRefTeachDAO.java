package com.post.m2m.dao.sturefteach;

import com.post.common.dao.DAO;
import com.post.m2m.pojo.po.sturefteach.StuRefTeachPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 【StuRefTeach】数据库操作
 *
 * @author ljm
 * @date 2021/01/25
 */
@Repository
@Mapper
public interface StuRefTeachDAO extends DAO<StuRefTeachPO> {

    int getCountByStuId(String stuId);

    int getCountByTeacherId(String teacherId);


}



