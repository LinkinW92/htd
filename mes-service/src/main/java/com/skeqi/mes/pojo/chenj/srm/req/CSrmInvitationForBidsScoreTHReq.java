package com.skeqi.mes.pojo.chenj.srm.req;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmInvitationForBidsScoreTH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 招标评分模板行表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmInvitationForBidsScoreTHReq {


    /**
     * 模板编号
     */
    private String templateNumber;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 类型(1.专家评分2.系统评分)
     */
    private String type;

    /**
     * 状态(1.已禁用2.已启用)
     */
    private String status;


    /**
     * 权重
     */
    private String weight;

    /**
     * 指标
     */
    private String index;


    /**
     * @return
     */
    private Integer pageNum;
    /**
     * @return
     */
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "CSrmInvitationForBidsScoreTHReq{" +
                "templateNumber='" + templateNumber + '\'' +
                ", templateName='" + templateName + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", weight='" + weight + '\'' +
                ", index='" + index + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
