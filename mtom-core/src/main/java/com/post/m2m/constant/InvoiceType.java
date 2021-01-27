package com.post.m2m.constant;

import com.post.common.validator.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举【发票类别】
 *
 * @author ljm
 * @date 2021/01/25
 */
public enum InvoiceType {

    /**
     * 餐饮
     */
    EAT(1, "餐饮"),
    /**
     * 酒水
     */
    DRINK(2, "酒水"),
    ;


    /**
     * 枚举值罗列，给swagger接口文档展示用
     */
    public static final String VALUES_STR = "1,2";

    private static final Map<Integer, InvoiceType> LOOKUP = new HashMap<>();

    static {
        for (InvoiceType e : InvoiceType.values()) {
            LOOKUP.put(e.value, e);
        }
    }

    private final Integer value;
    private final String desc;


    InvoiceType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static InvoiceType find(Integer value) {
        return LOOKUP.get(value);
    }

    public static InvoiceType findByDesc(String desc) {
        for (InvoiceType e : InvoiceType.values()) {
            if (e.getDesc().equals(desc)) {
                return e;
            }
        }
        return null;
    }


    /**
     * desc映射value
     *
     * @param desc
     * @return
     */
    public static Integer descToValue(String desc) {
        InvoiceType theEnum = findByDesc(desc);
        if (theEnum != null) {
            return theEnum.getValue();
        }
        return null;
    }

    /**
     * value映射desc
     *
     * @param value
     * @return
     */
    public static String valueToDesc(Integer value) {
        InvoiceType theEnum = find(value);
        if (theEnum != null) {
            return theEnum.getDesc();
        }
        return null;
    }

    /**
     * 校验有效性
     */
    @Check
    public static final boolean validate(Integer value) {
        InvoiceType theEnum = find(value);
        return theEnum != null;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}

