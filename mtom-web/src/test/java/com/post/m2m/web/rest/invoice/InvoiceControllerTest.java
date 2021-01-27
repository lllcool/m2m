package com.post.m2m.web.rest.invoice;

import com.google.common.collect.Lists;
import com.post.common.util.JsonUtil;
import com.post.m2m.help.invoice.InvoiceHelper;
import com.post.m2m.pojo.dto.invoice.InvoiceAddDTO;
import com.post.m2m.pojo.dto.invoice.InvoiceUpdateDTO;
import com.post.m2m.pojo.po.invoice.InvoicePO;
import com.post.m2m.web.AbstractWebTest;
import com.post.m2m.web.constant.WebConst;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 【Invoice】单元测试
 *
 * @author ljm
 * @date 2021/01/25
 */
public class InvoiceControllerTest extends AbstractWebTest {

    @Autowired
    private InvoiceHelper invoiceHelper;


    /**
     * 新增【Invoice】
     */
    @Test
    public void save() throws Exception {
        InvoiceAddDTO addDTO = invoiceHelper.getInvoiceAddDTO();
        restMockMvc.perform(post(WebConst.ModulePath.INVOICE + "/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(addDTO)))
                .andExpect(status().isCreated());
    }

    /**
     * 修改【Invoice】
     */
    @Test
    public void update() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        InvoiceUpdateDTO updateDTO = invoiceHelper.getInvoiceUpdateDTO(invoice);
        restMockMvc.perform(put(WebConst.ModulePath.INVOICE + "/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(updateDTO)))
                .andExpect(status().isOk());
    }

    /**
     * 分页查询【Invoice】
     */
    @Test
    public void list() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        restMockMvc.perform(get(WebConst.ModulePath.INVOICE + "/invoice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查询【Invoice】选项列表
     */
    @Test
    public void findOptions() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        restMockMvc.perform(get(WebConst.ModulePath.INVOICE + "/invoice/options"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(is(1)));
    }

    /**
     * 查看【Invoice】详情
     */
    @Test
    public void show() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        restMockMvc.perform(get(WebConst.ModulePath.INVOICE + "/invoice/{id}", invoice.getId()))
                .andExpect(status().isOk());
    }

    /**
     * 删除单个【Invoice】
     */
    @Test
    public void del() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        restMockMvc.perform(delete(WebConst.ModulePath.INVOICE + "/invoice/{id}", invoice.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【Invoice】
     */
    @Test
    public void deleteBatch() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        restMockMvc.perform(delete(WebConst.ModulePath.INVOICE + "/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(Lists.newArrayList(invoice.getId()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 导入【Invoice】excel
     */
    @Test
    public void importExcel() throws Exception {
        // 首先下载excel模板
        MvcResult mvcResult = restMockMvc.perform(get(WebConst.ModulePath.INVOICE + "/invoice/template"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // 将模板原封不动导入
        MockMultipartFile file = new MockMultipartFile("file", response.getContentAsByteArray());
        restMockMvc.perform(multipart(WebConst.ModulePath.INVOICE + "/invoice/import")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }


}
