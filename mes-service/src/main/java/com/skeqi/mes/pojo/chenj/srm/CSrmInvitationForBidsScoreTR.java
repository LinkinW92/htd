package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmInvitationForBidsScoreTR
 * @Description ${Description}
 */

/**
 * 招标评分模板行表
 */
public class CSrmInvitationForBidsScoreTR {
    /**
     * 招标评分模板行id
     */
    private Integer id;

    /**
     * 模板编号
     */
    private String templateNumber;

    /**
     * 评分要素
     */
    private String scoringElements;

    /**
     * 权重
     */
    private String weight;

    /**
     * 指标
     */
    private String index;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber;
    }

    public String getScoringElements() {
        return scoringElements;
    }

    public void setScoringElements(String scoringElements) {
        this.scoringElements = scoringElements;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateNumber=").append(templateNumber);
        sb.append(", scoringElements=").append(scoringElements);
        sb.append(", weight=").append(weight);
        sb.append(", index=").append(index);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
