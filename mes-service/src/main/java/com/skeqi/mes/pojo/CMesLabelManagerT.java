package com.skeqi.mes.pojo;


import io.swagger.annotations.ApiModelProperty;

public class CMesLabelManagerT {
    private Integer id;// NUMBER not null,
    @ApiModelProperty(value = "标签编号", required = true)
    private String labelNumber;// NVARCHAR2(30),
    @ApiModelProperty(value = "标签名称", required = true)
    private String labelName;// NVARCHAR2(20),
    @ApiModelProperty(value = "标签规则", required = true)
    private String labelRules;// NVARCHAR2(20),
    @ApiModelProperty(value = "所属产线ID", required = false)
    private Integer lineId;// NUMBER,
    @ApiModelProperty(value = "所属产线ID--API脚本专属渠道", required = false)
    private String lineCode;// NUMBER,
    @ApiModelProperty(value = "介绍", required = true)
    private String dis;// NVARCHAR2(50),
    @ApiModelProperty(value = "修改时间", required = false)
    private String labelDate;// DATE
    @ApiModelProperty(value = "所属产线名称", required = false)
    private String lineName;
    @ApiModelProperty(value = "标签类型ID", required = true)
    private Integer labelTypeId;
    @ApiModelProperty(value = "标签类型编码", required = true)
    private String labelTypeCode;
    @ApiModelProperty(value = "标签类型名称", required = false)
    private String labelTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelNumber() {
        return labelNumber;
    }

    public void setLabelNumber(String labelNumber) {
        this.labelNumber = labelNumber;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelRules() {
        return labelRules;
    }

    public void setLabelRules(String labelRules) {
        this.labelRules = labelRules;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getLabelDate() {
        return labelDate;
    }

    public void setLabelDate(String labelDate) {
        this.labelDate = labelDate;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Integer getLabelTypeId() {
        return labelTypeId;
    }

    public void setLabelTypeId(Integer labelTypeId) {
        this.labelTypeId = labelTypeId;
    }

    public String getLabelTypeCode() {
        return labelTypeCode;
    }

    public void setLabelTypeCode(String labelTypeCode) {
        this.labelTypeCode = labelTypeCode;
    }

    public String getLabelTypeName() {
        return labelTypeName;
    }

    public void setLabelTypeName(String labelTypeName) {
        this.labelTypeName = labelTypeName;
    }

    @Override
    public String toString() {
        return "CMesLabelManagerT [id=" + id + ", labelNumber=" + labelNumber + ", labelName=" + labelName
                + ", labelRules=" + labelRules + ", lineId=" + lineId + ", dis=" + dis + ", labelDate=" + labelDate
                + ", lineName=" + lineName + ", labelTypeId=" + labelTypeId + ", labelTypeCode=" + labelTypeCode + ", labelTypeName=" + labelTypeName + ", lineCode=" + lineCode + "]";
    }

    public CMesLabelManagerT(Integer id, String labelNumber, String labelName, String labelRules, Integer lineId, String lineCode,
                             String dis, String labelDate, String lineName, Integer labelTypeId, String labelTypeCode, String labelTypeName) {
        super();
        this.id = id;
        this.labelNumber = labelNumber;
        this.labelName = labelName;
        this.labelRules = labelRules;
        this.lineId = lineId;
        this.lineCode = lineCode;
        this.dis = dis;
        this.labelDate = labelDate;
        this.lineName = lineName;
        this.labelTypeId = labelTypeId;
        this.labelTypeCode = labelTypeCode;
        this.labelTypeName = labelTypeName;
    }

    public CMesLabelManagerT() {
        super();
    }


}
