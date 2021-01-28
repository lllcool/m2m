package com.post.m2m.service.stu_ref_teach;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.UUIDUtil;
import com.post.m2m.dao.stu_ref_teach.StuRefTeachDAO;
import com.post.m2m.dao.stu_ref_teach.StudentDAO;
import com.post.m2m.dao.stu_ref_teach.TeacherDAO;
import com.post.m2m.pojo.dto.stu_ref_teach.StuRefTeachAddDTO;
import com.post.m2m.pojo.dto.stu_ref_teach.StuRefTeachUpdateDTO;
import com.post.m2m.pojo.mapper.stu_ref_teach.StuRefTeachMapper;
import com.post.m2m.pojo.po.stu_ref_teach.StuRefTeachPO;
import com.post.m2m.pojo.po.student.StudentPO;
import com.post.m2m.pojo.po.teacher.TeacherPO;
import com.post.m2m.pojo.qo.stu_ref_teach.StuRefTeachQO;
import com.post.m2m.pojo.vo.stu_ref_teach.StuRefTeachListVO;
import com.post.m2m.pojo.vo.stu_ref_teach.StuRefTeachShowVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 【StuRefTeach】增删改查服务
 *
 * @author ljm
 * @date 2021/01/25
 */
@Service
public class StuRefTeachService {

    @Autowired
    private StuRefTeachDAO stuRefTeachDAO;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private TeacherDAO teacherDAO;


    /**
     * 新增【StuRefTeach】
     *
     * @param stuRefTeachDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public StuRefTeachPO save(StuRefTeachAddDTO stuRefTeachDTO) {
        StuRefTeachPO stuRefTeach = StuRefTeachMapper.INSTANCE.fromAddDTO(stuRefTeachDTO);
        if (stuRefTeach.getStuId() != null) {
            Assert.isTrue(studentDAO.exist(stuRefTeach.getStuId()), "学生ID有误");
        }
        if (stuRefTeach.getTeacherId() != null) {
            Assert.isTrue(teacherDAO.exist(stuRefTeach.getTeacherId()), "老师ID有误");
        }
        stuRefTeach.setId(UUIDUtil.getUUID());
        stuRefTeachDAO.save(stuRefTeach);
        return stuRefTeach;
    }

    /**
     * 批量新增【StuRefTeach】
     *
     * @param list
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int batchSave(List<StuRefTeachAddDTO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        for (StuRefTeachAddDTO addDTO : list) {
            this.save(addDTO);
        }
        return list.size();
    }

    /**
     * 修改【StuRefTeach】
     *
     * @param stuRefTeachUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public StuRefTeachPO update(StuRefTeachUpdateDTO stuRefTeachUpdateDTO) {
        String id = stuRefTeachUpdateDTO.getId();
        StuRefTeachPO stuRefTeach = this.getStuRefTeach(id, true);
        StuRefTeachMapper.INSTANCE.setUpdateDTO(stuRefTeach, stuRefTeachUpdateDTO);
        if (stuRefTeach.getStuId() != null) {
            Assert.isTrue(studentDAO.exist(stuRefTeach.getStuId()), "学生ID有误");
        }
        if (stuRefTeach.getTeacherId() != null) {
            Assert.isTrue(teacherDAO.exist(stuRefTeach.getTeacherId()), "老师ID有误");
        }
        stuRefTeachDAO.update(stuRefTeach);
        return stuRefTeach;
    }

    /**
     * 查询分页列表
     *
     * @param stuRefTeachQO
     * @return
     */
    public PageVO<StuRefTeachListVO> list(StuRefTeachQO stuRefTeachQO) {
        PageVO<StuRefTeachListVO> page = stuRefTeachDAO.findByPage(stuRefTeachQO);
        return page;
    }

    /**
     * 根据主键获取【StuRefTeach】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public StuRefTeachPO getStuRefTeach(String id, boolean force) {
        StuRefTeachPO stuRefTeach = stuRefTeachDAO.findById(id);
        if (force && stuRefTeach == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return stuRefTeach;
    }


    /**
     * 查询【StuRefTeach】详情
     *
     * @param id
     * @return
     */
    public StuRefTeachShowVO show(String id) {
        StuRefTeachPO stuRefTeach = this.getStuRefTeach(id, true);
        StuRefTeachShowVO showVO = StuRefTeachMapper.INSTANCE.toShowVO(stuRefTeach);
        if (stuRefTeach.getStuId() != null) {
            StudentPO _studentPO = studentDAO.findById(stuRefTeach.getStuId());
            showVO.setStuName(_studentPO.getName());
        }
        if (stuRefTeach.getTeacherId() != null) {
            TeacherPO _teacherPO = teacherDAO.findById(stuRefTeach.getTeacherId());
            showVO.setTeaName(_teacherPO.getTeacherName());
            showVO.setTeaSubject(_teacherPO.getSubject());
        }
        return showVO;
    }

    /**
     * 删除【StuRefTeach】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(String... ids) {
        int count = 0;
        for (String id : ids) {
            count += stuRefTeachDAO.delete(id);
        }
        return count;
    }


}


