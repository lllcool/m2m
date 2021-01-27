package com.post.m2m.help.invoice;

import com.post.common.util.SafeUtil;
import com.post.m2m.pojo.dto.invoice.*;
import com.post.m2m.pojo.po.invoice.*;
import com.post.m2m.service.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.post.m2m.pojo.example.invoice.InvoiceExample.*;

@Component
public class InvoiceHelper {

    @Autowired
    private InvoiceService invoiceService;

    /**
     * 生成add测试数据
     *
     * @return
     */
    public InvoiceAddDTO getInvoiceAddDTO() {
        InvoiceAddDTO dto = new InvoiceAddDTO();
        dto.setType(SafeUtil.getInteger(E_TYPE));
        dto.setInvoiceName(E_INVOICE_NAME);
        dto.setInvoiceTitle(E_INVOICE_TITLE);
        return dto;
    }


    /**
     * 生成update测试数据
     *
     * @return
     */
    public InvoiceUpdateDTO getInvoiceUpdateDTO(InvoicePO invoice) {
        InvoiceUpdateDTO dto = new InvoiceUpdateDTO();
        dto.setId(invoice.getId());
        dto.setType(invoice.getType());
        dto.setInvoiceName(invoice.getInvoiceName());
        dto.setInvoiceTitle(invoice.getInvoiceTitle());
        return dto;
    }

    /**
     * 保存示例
     *
     * @return
     */
    public InvoicePO saveInvoiceExample() {
        InvoiceAddDTO addDTO = this.getInvoiceAddDTO();
        return invoiceService.save(addDTO);
    }


}

