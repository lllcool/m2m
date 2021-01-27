package com.post.m2m.service.invoice;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.UUIDUtil;
import com.post.m2m.dao.invoice.InvoiceDAO;
import com.post.m2m.dao.invoice.InvoiceDetailDAO;
import com.post.m2m.pojo.dto.invoice.InvoiceAddDTO;
import com.post.m2m.pojo.dto.invoice.InvoiceUpdateDTO;
import com.post.m2m.pojo.mapper.invoice.InvoiceMapper;
import com.post.m2m.pojo.po.invoice.InvoicePO;
import com.post.m2m.pojo.qo.invoice.InvoiceQO;
import com.post.m2m.pojo.vo.invoice.InvoiceListVO;
import com.post.m2m.pojo.vo.invoice.InvoiceShowVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 【Invoice】增删改查服务
 *
 * @author ljm
 * @date 2021/01/25
 */
@Service
public class InvoiceService {

    @Autowired
    private InvoiceDAO invoiceDAO;
    @Autowired
    private InvoiceDetailDAO invoiceDetailDAO;


    /**
     * 新增【Invoice】
     *
     * @param invoiceDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public InvoicePO save(InvoiceAddDTO invoiceDTO) {
        InvoicePO invoice = InvoiceMapper.INSTANCE.fromAddDTO(invoiceDTO);
        invoice.setId(UUIDUtil.getUUID());
        invoiceDAO.save(invoice);
        return invoice;
    }

    /**
     * 批量新增【Invoice】
     *
     * @param list
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int batchSave(List<InvoiceAddDTO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        for (InvoiceAddDTO addDTO : list) {
            this.save(addDTO);
        }
        return list.size();
    }

    /**
     * 修改【Invoice】
     *
     * @param invoiceUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public InvoicePO update(InvoiceUpdateDTO invoiceUpdateDTO) {
        String id = invoiceUpdateDTO.getId();
        InvoicePO invoice = this.getInvoice(id, true);
        InvoiceMapper.INSTANCE.setUpdateDTO(invoice, invoiceUpdateDTO);
        invoiceDAO.update(invoice);
        return invoice;
    }

    /**
     * 查询分页列表
     *
     * @param invoiceQO
     * @return
     */
    public PageVO<InvoiceListVO> list(InvoiceQO invoiceQO) {
        PageVO<InvoiceListVO> page = invoiceDAO.findByPage(invoiceQO);
        return page;
    }

    /**
     * 查询【Invoice】选项列表
     *
     * @return
     */
    public List<OptionVO<String, String>> findOptions(OptionQO<String, String> qo) {
        List<OptionVO<String, String>> options = invoiceDAO.findOptions(qo);
        return options;
    }

    /**
     * 根据主键获取【Invoice】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public InvoicePO getInvoice(String id, boolean force) {
        InvoicePO invoice = invoiceDAO.findById(id);
        if (force && invoice == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return invoice;
    }


    /**
     * 查询【Invoice】详情
     *
     * @param id
     * @return
     */
    public InvoiceShowVO show(String id) {
        InvoicePO invoice = this.getInvoice(id, true);
        InvoiceShowVO showVO = InvoiceMapper.INSTANCE.toShowVO(invoice);
        return showVO;
    }

    /**
     * 删除【Invoice】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(String... ids) {
        int count = 0;
        for (String id : ids) {
            this.checkDeleteByInvoiceDetail(id);
            count += invoiceDAO.delete(id);
        }
        return count;
    }

    /**
     * 校验是否存在【InvoiceDetail】关联
     *
     * @param id
     */
    private void checkDeleteByInvoiceDetail(String id) {
        int count = invoiceDetailDAO.getCountByInvoiceId(id);
        if (count > 0) {
            throw new BusinessException(ErrorCode.CASCADE_DELETE_ERROR);
        }
    }


}


