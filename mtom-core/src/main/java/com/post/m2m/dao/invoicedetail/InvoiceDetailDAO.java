package com.post.m2m.dao.invoicedetail;

import com.post.common.dao.DAO;
import com.post.m2m.pojo.po.invoicedetail.InvoiceDetailPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 【InvoiceDetail】数据库操作
 *
 * @author ljm
 * @date 2021/01/25
 */
@Repository
@Mapper
public interface InvoiceDetailDAO extends DAO<InvoiceDetailPO> {

    int getCountByInvoiceId(String invoiceId);


}



