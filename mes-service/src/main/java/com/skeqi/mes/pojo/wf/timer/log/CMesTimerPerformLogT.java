package com.skeqi.mes.pojo.wf.timer.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
    * 定时器执行日志表
    */
@ApiModel(value="com-skeqi-pojo-wf-timer-log-CMesTimerPerformLogT")
public class CMesTimerPerformLogT {
    @ApiModelProperty(value="")
    private Integer id;

    /**
    * 定时器任务编号
    */
    @ApiModelProperty(value="定时器任务编号")
    private String taskCode;

    /**
    * 执行结果(S/E)
    */
    @ApiModelProperty(value="执行结果(S/E)")
    private String result;

    /**
    * 日志内容
    */
    @ApiModelProperty(value="日志内容")
    private String logContent;

    /**
    * 执行时间
    */
    @ApiModelProperty(value="执行时间")
    private String startTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskCode=").append(taskCode);
        sb.append(", result=").append(result);
        sb.append(", logContent=").append(logContent);
        sb.append(", startTime=").append(startTime);
        sb.append("]");
        return sb.toString();
    }
}
