package com.post.m2m.pojo.mapper.invoicedetail;

import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailAddDTO;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailExcelDTO;
import com.post.m2m.pojo.dto.invoicedetail.InvoiceDetailUpdateDTO;
import com.post.m2m.pojo.po.invoicedetail.InvoiceDetailPO;
import com.post.m2m.pojo.vo.invoicedetail.InvoiceDetailExcelVO;
import com.post.m2m.pojo.vo.invoicedetail.InvoiceDetailListVO;
import com.post.m2m.pojo.vo.invoicedetail.InvoiceDetailShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 【InvoiceDetail】映射
 *
 * @author ljm
 * @date 2021/01/25
 */
@Mapper
public interface InvoiceDetailMapper {

    InvoiceDetailMapper INSTANCE = Mappers.getMapper(InvoiceDetailMapper.class);

    /**
     * addDTO映射po
     *
     * @param invoiceDetailAddDTO
     * @return
     */
    InvoiceDetailPO fromAddDTO(InvoiceDetailAddDTO invoiceDetailAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param invoiceDetailPO
     * @param invoiceDetailUpdateDTO
     */
    void setUpdateDTO(@MappingTarget InvoiceDetailPO invoiceDetailPO, InvoiceDetailUpdateDTO invoiceDetailUpdateDTO);

    /**
     * po映射showVO
     *
     * @param invoiceDetailPO
     * @return
     */
    InvoiceDetailShowVO toShowVO(InvoiceDetailPO invoiceDetailPO);


    /**
     * excelDTO映射addDTO
     *
     * @param dto
     * @return
     */
    InvoiceDetailAddDTO fromExcelDTO(InvoiceDetailExcelDTO dto);

    /**
     * listVO列表转excelVO列表
     *
     * @param list
     * @return
     */
    List<InvoiceDetailExcelVO> toExcelVOList(List<InvoiceDetailListVO> list);

    /**
     * listVO转excelVO
     *
     * @param vo
     * @return
     */
    InvoiceDetailExcelVO toExcelVO(InvoiceDetailListVO vo);


}

