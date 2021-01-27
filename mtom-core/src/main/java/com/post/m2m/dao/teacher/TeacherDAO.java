package com.post.m2m.dao.teacher;

import com.post.common.dao.DAO;
import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.m2m.pojo.po.teacher.TeacherPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【Teacher】数据库操作
 *
 * @author ljm
 * @date 2021/01/25
 */
@Repository
@Mapper
public interface TeacherDAO extends DAO<TeacherPO> {

    List<OptionVO<String, String>> findOptions(OptionQO<String, String> qo);


}



