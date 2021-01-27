package com.post.m2m.pojo.po.invoice;

import com.post.common.pojo.po.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Invoice
 * <p>发票信息
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class InvoicePO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 发票类别
     *
     * @see com.post.m2m.constant.InvoiceType
     */
    private Integer type;

    /**
     * 发票名称
     */
    private String invoiceName;

    /**
     * 发票标题
     */
    private String invoiceTitle;


}

