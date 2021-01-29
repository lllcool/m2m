                    package com.post.m2m.service.student;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.UUIDUtil;
import com.post.m2m.dao.stuRefTeach.StuRefTeachDAO;
import com.post.m2m.dao.student.StudentDAO;
import com.post.m2m.pojo.dto.student.StudentAddDTO;
import com.post.m2m.pojo.dto.student.StudentUpdateDTO;
import com.post.m2m.pojo.mapper.student.StudentMapper;
import com.post.m2m.pojo.po.student.StudentPO;
import com.post.m2m.pojo.qo.student.StudentQO;
import com.post.m2m.pojo.vo.student.StudentListVO;
import com.post.m2m.pojo.vo.student.StudentShowVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

                    /**
 * 【Student】增删改查服务
 *
 * @author ljm
 * @date 2021/01/25
 */
@Service
public class StudentService {

    @Autowired
    private StuRefTeachDAO stuRefTeachDAO;
    @Autowired
    private StudentDAO studentDAO;


/**
* 新增【Student】
*
* @param studentDTO
* @return
*/
@Transactional(rollbackFor = RuntimeException.class)
public StudentPO save(StudentAddDTO studentDTO) {
StudentPO student = StudentMapper.INSTANCE.fromAddDTO(studentDTO);
            student.setId(UUIDUtil.getUUID());
studentDAO.save(student);
return student;
}

/**
* 批量新增【Student】
*
* @param list
* @return
*/
@Transactional(rollbackFor = RuntimeException.class)
public int batchSave(List
<StudentAddDTO> list) {
    if (CollectionUtils.isEmpty(list)) {
    return 0;
    }
    for (StudentAddDTO addDTO : list) {
    this.save(addDTO);
    }
    return list.size();
    }

    /**
    * 修改【Student】
    *
    * @param studentUpdateDTO
    * @return
    */
    @Transactional(rollbackFor = RuntimeException.class)
    public StudentPO update(StudentUpdateDTO studentUpdateDTO) {
    String id = studentUpdateDTO.getId();
    StudentPO student = this.getStudent(id, true);
    StudentMapper.INSTANCE.setUpdateDTO(student, studentUpdateDTO);
    studentDAO.update(student);
    return student;
    }

    /**
    * 查询分页列表
    *
    * @param studentQO
    * @return
    */
    public PageVO
    <StudentListVO> list(StudentQO studentQO) {
        PageVO
        <StudentListVO> page = studentDAO.findByPage(studentQO);
            return page;
            }

                        /**
                        * 查询【Student】选项列表
                        *
                        * @return
                        */
                        public List
                        <OptionVO<String, String>> findOptions(OptionQO<String, String> qo) {
                        List
                        <OptionVO<String, String>> options = studentDAO.findOptions(qo);
                        return options;
                        }

                    /**
                    * 根据主键获取【Student】
                    *
                    * @param id 主键
                    * @param force 是否强制获取
                    * @return
                    */
                    public StudentPO getStudent(String id, boolean
                    force) {
                    StudentPO student = studentDAO.findById(id);
                    if (force && student == null) {
                    throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
                    }
                    return student;
                    }


                    /**
                    * 查询【Student】详情
                    *
                    * @param id
                    * @return
                    */
                    public StudentShowVO show(String id) {
                    StudentPO student = this.getStudent(id, true);
                    StudentShowVO showVO = StudentMapper.INSTANCE.toShowVO(student);
                    return showVO;
                    }

                    /**
                    * 删除【Student】
                    *
                    * @param ids
                    * @return
                    */
                    @Transactional(rollbackFor = RuntimeException.class)
                    public int delete(String... ids) {
                    int count = 0;
                    for (String id : ids) {
                        this.checkDeleteByStuRefTeach(id);
                    count += studentDAO.delete(id);
                    }
                    return count;
                    }

                        /**
                        * 校验是否存在【StuRefTeach】关联
                        *
                        * @param id
                        */
                        private void checkDeleteByStuRefTeach(String id) {
                                int count = stuRefTeachDAO.getCountByStuId(id);
                                if (count > 0) {
                                throw new BusinessException(ErrorCode.CASCADE_DELETE_ERROR);
                                }
                        }


                    }


