package com.post.m2m.service.teacher;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.UUIDUtil;

import com.post.m2m.dao.stu_ref_teach.StuRefTeachDAO;
import com.post.m2m.dao.teacher.TeacherDAO;
import com.post.m2m.pojo.dto.teacher.TeacherAddDTO;
import com.post.m2m.pojo.dto.teacher.TeacherUpdateDTO;
import com.post.m2m.pojo.mapper.teacher.TeacherMapper;
import com.post.m2m.pojo.po.teacher.TeacherPO;
import com.post.m2m.pojo.qo.teacher.TeacherQO;
import com.post.m2m.pojo.vo.teacher.TeacherListVO;
import com.post.m2m.pojo.vo.teacher.TeacherShowVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 【Teacher】增删改查服务
 *
 * @author ljm
 * @date 2021/01/25
 */
@Service
public class TeacherService {

    @Autowired
    private StuRefTeachDAO stuRefTeachDAO;
    @Autowired
    private TeacherDAO teacherDAO;


    /**
     * 新增【Teacher】
     *
     * @param teacherDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TeacherPO save(TeacherAddDTO teacherDTO) {
        TeacherPO teacher = TeacherMapper.INSTANCE.fromAddDTO(teacherDTO);
        teacher.setId(UUIDUtil.getUUID());
        teacherDAO.save(teacher);
        return teacher;
    }

    /**
     * 批量新增【Teacher】
     *
     * @param list
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int batchSave(List<TeacherAddDTO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        for (TeacherAddDTO addDTO : list) {
            this.save(addDTO);
        }
        return list.size();
    }

    /**
     * 修改【Teacher】
     *
     * @param teacherUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TeacherPO update(TeacherUpdateDTO teacherUpdateDTO) {
        String id = teacherUpdateDTO.getId();
        TeacherPO teacher = this.getTeacher(id, true);
        TeacherMapper.INSTANCE.setUpdateDTO(teacher, teacherUpdateDTO);
        teacherDAO.update(teacher);
        return teacher;
    }

    /**
     * 查询分页列表
     *
     * @param teacherQO
     * @return
     */
    public PageVO<TeacherListVO> list(TeacherQO teacherQO) {
        PageVO<TeacherListVO> page = teacherDAO.findByPage(teacherQO);
        return page;
    }

    /**
     * 查询【Teacher】选项列表
     *
     * @return
     */
    public List<OptionVO<String, String>> findOptions(OptionQO<String, String> qo) {
        List<OptionVO<String, String>> options = teacherDAO.findOptions(qo);
        return options;
    }

    /**
     * 根据主键获取【Teacher】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public TeacherPO getTeacher(String id, boolean force) {
        TeacherPO teacher = teacherDAO.findById(id);
        if (force && teacher == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return teacher;
    }


    /**
     * 查询【Teacher】详情
     *
     * @param id
     * @return
     */
    public TeacherShowVO show(String id) {
        TeacherPO teacher = this.getTeacher(id, true);
        TeacherShowVO showVO = TeacherMapper.INSTANCE.toShowVO(teacher);
        return showVO;
    }

    /**
     * 删除【Teacher】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(String... ids) {
        int count = 0;
        for (String id : ids) {
            this.checkDeleteByStuRefTeach(id);
            count += teacherDAO.delete(id);
        }
        return count;
    }

    /**
     * 校验是否存在【StuRefTeach】关联
     *
     * @param id
     */
    private void checkDeleteByStuRefTeach(String id) {
        int count = stuRefTeachDAO.getCountByTId(id);
        if (count > 0) {
            throw new BusinessException(ErrorCode.CASCADE_DELETE_ERROR);
        }
    }


}


