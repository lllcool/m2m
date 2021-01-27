package com.post.m2m.web.rest.invoicedetail;

import com.google.common.collect.Lists;
import com.post.common.util.JsonUtil;
import com.post.m2m.help.invoice.InvoiceHelper;
import com.post.m2m.help.invoicedetail.InvoiceDetailHelper;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailAddDTO;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailUpdateDTO;
import com.post.m2m.pojo.po.invoice.InvoicePO;
import com.post.m2m.pojo.po.invoicedetail.InvoiceDetailPO;
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
 * 【InvoiceDetail】单元测试
 *
 * @author ljm
 * @date 2021/01/25
 */
public class InvoiceDetailControllerTest extends AbstractWebTest {

    @Autowired
    private InvoiceDetailHelper invoiceDetailHelper;
    @Autowired
    private InvoiceHelper invoiceHelper;


    /**
     * 新增【InvoiceDetail】
     */
    @Test
    public void save() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        InvoiceDetailAddDTO addDTO = invoiceDetailHelper.getInvoiceDetailAddDTO(invoice.getId());
        restMockMvc.perform(post(WebConst.ModulePath.INVOICEDETAIL + "/invoiceDetail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(addDTO)))
                .andExpect(status().isCreated());
    }

    /**
     * 修改【InvoiceDetail】
     */
    @Test
    public void update() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        InvoiceDetailPO invoiceDetail = invoiceDetailHelper.saveInvoiceDetailExample(invoice.getId());
        InvoiceDetailUpdateDTO updateDTO = invoiceDetailHelper.getInvoiceDetailUpdateDTO(invoiceDetail);
        restMockMvc.perform(put(WebConst.ModulePath.INVOICEDETAIL + "/invoiceDetail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(updateDTO)))
                .andExpect(status().isOk());
    }

    /**
     * 分页查询【InvoiceDetail】
     */
    @Test
    public void list() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        InvoiceDetailPO invoiceDetail = invoiceDetailHelper.saveInvoiceDetailExample(invoice.getId());
        restMockMvc.perform(get(WebConst.ModulePath.INVOICEDETAIL + "/invoiceDetail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查看【InvoiceDetail】详情
     */
    @Test
    public void show() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        InvoiceDetailPO invoiceDetail = invoiceDetailHelper.saveInvoiceDetailExample(invoice.getId());
        restMockMvc.perform(get(WebConst.ModulePath.INVOICEDETAIL + "/invoiceDetail/{id}", invoiceDetail.getId()))
                .andExpect(status().isOk());
    }

    /**
     * 删除单个【InvoiceDetail】
     */
    @Test
    public void del() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        InvoiceDetailPO invoiceDetail = invoiceDetailHelper.saveInvoiceDetailExample(invoice.getId());
        restMockMvc.perform(delete(WebConst.ModulePath.INVOICEDETAIL + "/invoiceDetail/{id}", invoiceDetail.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【InvoiceDetail】
     */
    @Test
    public void deleteBatch() throws Exception {
        InvoicePO invoice = invoiceHelper.saveInvoiceExample();
        InvoiceDetailPO invoiceDetail = invoiceDetailHelper.saveInvoiceDetailExample(invoice.getId());
        restMockMvc.perform(delete(WebConst.ModulePath.INVOICEDETAIL + "/invoiceDetail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(Lists.newArrayList(invoiceDetail.getId()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 导入【InvoiceDetail】excel
     */
    @Test
    public void importExcel() throws Exception {
        // 首先下载excel模板
        MvcResult mvcResult = restMockMvc.perform(get(WebConst.ModulePath.INVOICEDETAIL + "/invoiceDetail/template"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // 将模板原封不动导入
        MockMultipartFile file = new MockMultipartFile("file", response.getContentAsByteArray());
        restMockMvc.perform(multipart(WebConst.ModulePath.INVOICEDETAIL + "/invoiceDetail/import")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }


}
