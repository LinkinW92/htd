package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/9
 * @Classname CSrmIndicatorsDimension
 * @Description ${Description}
 */

/**
 * 指标维度表
 */
public class CSrmIndicatorsDimension {
    /**
     * 指标维度id
     */
    private Integer id;

    /**
     * 指标编码
     */
    private String indexCoding;

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 评分方式(1.专家评分2.系统评分)
     */
    private String scoreIs;

    /**
     * 指标类型(1.专家评分2.系统评分)
     */
    private String pointerType;

    /**
     * 分值起始值
     */
    private String scoreStart;

    /**
     * 分值截止值
     */
    private String scoreStop;

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

    public String getIndexCoding() {
        return indexCoding;
    }

    public void setIndexCoding(String indexCoding) {
        this.indexCoding = indexCoding;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getScoreIs() {
        return scoreIs;
    }

    public void setScoreIs(String scoreIs) {
        this.scoreIs = scoreIs;
    }

    public String getPointerType() {
        return pointerType;
    }

    public void setPointerType(String pointerType) {
        this.pointerType = pointerType;
    }

    public String getScoreStart() {
        return scoreStart;
    }

    public void setScoreStart(String scoreStart) {
        this.scoreStart = scoreStart;
    }

    public String getScoreStop() {
        return scoreStop;
    }

    public void setScoreStop(String scoreStop) {
        this.scoreStop = scoreStop;
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
        sb.append(", indexCoding=").append(indexCoding);
        sb.append(", indexName=").append(indexName);
        sb.append(", scoreIs=").append(scoreIs);
        sb.append(", pointerType=").append(pointerType);
        sb.append(", scoreStart=").append(scoreStart);
        sb.append(", scoreStop=").append(scoreStop);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
