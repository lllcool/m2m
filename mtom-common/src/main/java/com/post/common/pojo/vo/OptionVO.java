package com.post.common.pojo.vo;

/**
 * 下拉框选项VO
 *
 * @author ljm
 * @date 2021/01/25
 */
public class OptionVO<K, V> extends AbstractVO {

    private K key;

    private V value;

    public OptionVO(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}

