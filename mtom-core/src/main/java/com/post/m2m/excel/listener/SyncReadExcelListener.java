package com.post.m2m.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.post.m2m.pojo.dto.AbstractExcelDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 同步读取excel的监听器
 *
 * @author ljm
 * @date 2021/01/25
 */
public class SyncReadExcelListener<T extends AbstractExcelDTO> extends AnalysisEventListener<T> {

    private final List<T> list = new ArrayList<>();

    @Override
    public void invoke(T data, AnalysisContext context) {
        ReadRowHolder rowHolder = context.readRowHolder();
        data.setRowIndex(rowHolder.getRowIndex());
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public List<T> getList() {
        return list;
    }
}

