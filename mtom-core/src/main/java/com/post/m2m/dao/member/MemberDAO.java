package com.post.m2m.dao.member;

import com.post.common.dao.DAO;
import com.post.m2m.pojo.po.member.MemberPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 【Member】数据库操作
 *
 * @author ljm
 * @date 2021/01/25
 */
@Repository
@Mapper
public interface MemberDAO extends DAO<MemberPO> {


}



