package com.skeqi.mes.pojo.wf.linesidelibrary;

/**
    * 线边库字典表
    */
public class CLslDictionaryT {
    private Integer id;

    /**
    * 属性名称
    */
    private String key;

    /**
    * 属性值
    */
    private String value;

    /**
    * 字段描述
    */
    private String describe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
