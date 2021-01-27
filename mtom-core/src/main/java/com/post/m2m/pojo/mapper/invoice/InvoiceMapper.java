package com.post.m2m.pojo.mapper.invoice;

import com.post.m2m.pojo.dto.invoice.InvoiceAddDTO;
import com.post.m2m.pojo.dto.invoice.InvoiceExcelDTO;
import com.post.m2m.pojo.dto.invoice.InvoiceUpdateDTO;
import com.post.m2m.pojo.po.invoice.InvoicePO;
import com.post.m2m.pojo.vo.invoice.InvoiceExcelVO;
import com.post.m2m.pojo.vo.invoice.InvoiceListVO;
import com.post.m2m.pojo.vo.invoice.InvoiceShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 【Invoice】映射
 *
 * @author ljm
 * @date 2021/01/25
 */
@Mapper
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    /**
     * addDTO映射po
     *
     * @param invoiceAddDTO
     * @return
     */
    InvoicePO fromAddDTO(InvoiceAddDTO invoiceAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param invoicePO
     * @param invoiceUpdateDTO
     */
    void setUpdateDTO(@MappingTarget InvoicePO invoicePO, InvoiceUpdateDTO invoiceUpdateDTO);

    /**
     * po映射showVO
     *
     * @param invoicePO
     * @return
     */
    InvoiceShowVO toShowVO(InvoicePO invoicePO);


    /**
     * excelDTO映射addDTO
     *
     * @param dto
     * @return
     */
    @Mappings({
            @Mapping(target = "type", expression = "java(com.post.m2m.constant.InvoiceType.descToValue(dto.getType()))"),
    })
    InvoiceAddDTO fromExcelDTO(InvoiceExcelDTO dto);

    /**
     * listVO列表转excelVO列表
     *
     * @param list
     * @return
     */
    List<InvoiceExcelVO> toExcelVOList(List<InvoiceListVO> list);

    /**
     * listVO转excelVO
     *
     * @param vo
     * @return
     */
    @Mappings({
            @Mapping(target = "type", expression = "java(com.post.m2m.constant.InvoiceType.valueToDesc(vo.getType()))"),
    })
    InvoiceExcelVO toExcelVO(InvoiceListVO vo);


}

