package com.post.m2m.help.invoicedetail;

import com.post.common.util.SafeUtil;
import com.post.m2m.pojo.dto.invoicedetail.*;
import com.post.m2m.pojo.po.invoicedetail.*;
import com.post.m2m.service.invoicedetail.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.post.m2m.pojo.example.invoicedetail.InvoiceDetailExample.*;

@Component
public class InvoiceDetailHelper {

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    /**
     * 生成add测试数据
     *
     * @return
     */
    public InvoiceDetailAddDTO getInvoiceDetailAddDTO(String invoiceId) {
        InvoiceDetailAddDTO dto = new InvoiceDetailAddDTO();
        dto.setCount(SafeUtil.getInteger(E_COUNT));
        dto.setPeople(E_PEOPLE);
        dto.setPrice(SafeUtil.getDouble(E_PRICE));
        dto.setInvoiceId(invoiceId);
        return dto;
    }


    /**
     * 生成update测试数据
     *
     * @return
     */
    public InvoiceDetailUpdateDTO getInvoiceDetailUpdateDTO(InvoiceDetailPO invoiceDetail) {
        InvoiceDetailUpdateDTO dto = new InvoiceDetailUpdateDTO();
        dto.setId(invoiceDetail.getId());
        dto.setCount(invoiceDetail.getCount());
        dto.setPeople(invoiceDetail.getPeople());
        dto.setPrice(invoiceDetail.getPrice());
        dto.setInvoiceId(invoiceDetail.getInvoiceId());
        return dto;
    }

    /**
     * 保存示例
     *
     * @return
     */
    public InvoiceDetailPO saveInvoiceDetailExample(String invoiceId) {
        InvoiceDetailAddDTO addDTO = this.getInvoiceDetailAddDTO(invoiceId);
        return invoiceDetailService.save(addDTO);
    }


}

