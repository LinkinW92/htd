package com.skeqi.mes.pojo.wf.jobManagement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
    * 作业配置表
    */
@ApiModel(value="com-skeqi-pojo-wf-job-MesJobConfigurationT")
public class MesJobConfigurationT {
    /**
    * 作业主键
    */
    @ApiModelProperty(value="作业主键")
    private Integer id;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称")
    private String name;

    /**
    * 编码
    */
    @ApiModelProperty(value="编码")
    private String code;

    /**
    * 程序url
    */
    @ApiModelProperty(value="程序url")
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", url=").append(url);
        sb.append("]");
        return sb.toString();
    }
}
