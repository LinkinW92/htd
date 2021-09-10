package com.skeqi.mes.pojo.wf.timer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 定时器配置表
 * @author Lenovo
 */
@ApiModel(value="com-skeqi-pojo-wf-timer-CMesTimerConfigT")
public class CMesTimerConfigT {
    /**
    * 定时器主键
    */
    @ApiModelProperty(value="定时器主键")
    private Integer id;

    /**
     * 定时器主键
     */
    @ApiModelProperty(value="定时器编号")
    private String code;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称")
    private String name;

    /**
    * cron表达式
    */
    @ApiModelProperty(value="cron表达式")
    private String cron;

    /**
    * cron表达式说明
    */
    @ApiModelProperty(value="cron表达式说明")
    private String cronExplain;

    /**
    * 是否启用（0启用，1停止）
    */
    @ApiModelProperty(value="是否启用（0启用，1停止）")
    private Integer status;

    /**
     * 参数
     */
    @ApiModelProperty(value="参数")
    private String params;

    /**
     * 开始时间
     */
    @ApiModelProperty(value="开始时间")
    private String startTime;

    /**
     * 停止时间
     */
    @ApiModelProperty(value="停止时间")
    private String stopTime;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getCronExplain() {
        return cronExplain;
    }

    public void setCronExplain(String cronExplain) {
        this.cronExplain = cronExplain;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CMesTimerConfigT{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", cron='" + cron + '\'' +
                ", cronExplain='" + cronExplain + '\'' +
                ", status=" + status +
                ", params='" + params + '\'' +
                ", startTime='" + startTime + '\'' +
                ", stopTime='" + stopTime + '\'' +
                '}';
    }
}
