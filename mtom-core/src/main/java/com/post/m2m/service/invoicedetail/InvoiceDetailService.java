package com.post.m2m.service.invoicedetail;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.UUIDUtil;
import com.post.m2m.dao.invoicedetail.InvoiceDAO;
import com.post.m2m.dao.invoicedetail.InvoiceDetailDAO;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailAddDTO;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailUpdateDTO;
import com.post.m2m.pojo.mapper.invoicedetail.InvoiceDetailMapper;
import com.post.m2m.pojo.po.invoicedetail.InvoiceDetailPO;
import com.post.m2m.pojo.po.invoicedetail.InvoicePO;
import com.post.m2m.pojo.qo.invoicedetail.InvoiceDetailQO;
import com.post.m2m.pojo.vo.invoicedetail.InvoiceDetailListVO;
import com.post.m2m.pojo.vo.invoicedetail.InvoiceDetailShowVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 【InvoiceDetail】增删改查服务
 *
 * @author ljm
 * @date 2021/01/25
 */
@Service
public class InvoiceDetailService {

    @Autowired
    private InvoiceDAO invoiceDAO;
    @Autowired
    private InvoiceDetailDAO invoiceDetailDAO;


    /**
     * 新增【InvoiceDetail】
     *
     * @param invoiceDetailDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public InvoiceDetailPO save(InvoiceDetailAddDTO invoiceDetailDTO) {
        InvoiceDetailPO invoiceDetail = InvoiceDetailMapper.INSTANCE.fromAddDTO(invoiceDetailDTO);
        if (invoiceDetail.getInvoiceId() != null) {
            Assert.isTrue(invoiceDAO.exist(invoiceDetail.getInvoiceId()), "发票ID有误");
        }
        invoiceDetail.setId(UUIDUtil.getUUID());
        invoiceDetailDAO.save(invoiceDetail);
        return invoiceDetail;
    }

    /**
     * 批量新增【InvoiceDetail】
     *
     * @param list
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int batchSave(List<InvoiceDetailAddDTO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        for (InvoiceDetailAddDTO addDTO : list) {
            this.save(addDTO);
        }
        return list.size();
    }

    /**
     * 修改【InvoiceDetail】
     *
     * @param invoiceDetailUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public InvoiceDetailPO update(InvoiceDetailUpdateDTO invoiceDetailUpdateDTO) {
        String id = invoiceDetailUpdateDTO.getId();
        InvoiceDetailPO invoiceDetail = this.getInvoiceDetail(id, true);
        InvoiceDetailMapper.INSTANCE.setUpdateDTO(invoiceDetail, invoiceDetailUpdateDTO);
        if (invoiceDetail.getInvoiceId() != null) {
            Assert.isTrue(invoiceDAO.exist(invoiceDetail.getInvoiceId()), "发票ID有误");
        }
        invoiceDetailDAO.update(invoiceDetail);
        return invoiceDetail;
    }

    /**
     * 查询分页列表
     *
     * @param invoiceDetailQO
     * @return
     */
    public PageVO<InvoiceDetailListVO> list(InvoiceDetailQO invoiceDetailQO) {
        PageVO<InvoiceDetailListVO> page = invoiceDetailDAO.findByPage(invoiceDetailQO);
        return page;
    }

    /**
     * 根据主键获取【InvoiceDetail】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public InvoiceDetailPO getInvoiceDetail(String id, boolean force) {
        InvoiceDetailPO invoiceDetail = invoiceDetailDAO.findById(id);
        if (force && invoiceDetail == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return invoiceDetail;
    }


    /**
     * 查询【InvoiceDetail】详情
     *
     * @param id
     * @return
     */
    public InvoiceDetailShowVO show(String id) {
        InvoiceDetailPO invoiceDetail = this.getInvoiceDetail(id, true);
        InvoiceDetailShowVO showVO = InvoiceDetailMapper.INSTANCE.toShowVO(invoiceDetail);
        if (invoiceDetail.getInvoiceId() != null) {
            InvoicePO _invoicePO = invoiceDAO.findById(invoiceDetail.getInvoiceId());
            showVO.setInvoiceName(_invoicePO.getInvoiceName());
        }
        return showVO;
    }

    /**
     * 删除【InvoiceDetail】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(String... ids) {
        int count = 0;
        for (String id : ids) {
            count += invoiceDetailDAO.delete(id);
        }
        return count;
    }


}


