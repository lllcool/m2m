package com.post.m2m.pojo.po.invoicedetail;

import com.post.common.pojo.po.AbstractPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * InvoiceDetail
 * <p>发票明细
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class InvoiceDetailPO extends AbstractPO {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 开票人
     */
    private String people;

    /**
     * 价格
     */
    private Double price;

    /**
     * 发票ID
     */
    private String invoiceId;


}

