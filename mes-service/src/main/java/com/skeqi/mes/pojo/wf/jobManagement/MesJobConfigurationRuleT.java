package com.skeqi.mes.pojo.wf.jobManagement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
    * 作业配置规则表
    */
@ApiModel(value="com-skeqi-pojo-wf-job-MesJobConfigurationRuleT")
public class MesJobConfigurationRuleT {
    /**
    * 规则主键
    */
    @ApiModelProperty(value="规则主键")
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
    * 值
    */
    @ApiModelProperty(value="值")
    private String value;

    /**
    * 作业配置编码
    */
    @ApiModelProperty(value="作业配置编码")
    private String jobCode;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
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
        sb.append(", value=").append(value);
        sb.append(", jobCode=").append(jobCode);
        sb.append("]");
        return sb.toString();
    }
}
