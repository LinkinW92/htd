package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmIndicatorsDimension
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 指标维度表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmIndicatorsDimensionReq {

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
     * 评分标类型(1.专家评分2.系统评分)
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
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
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

    @Override
    public String toString() {
        return "CSrmIndicatorsDimensionReq{" +
                "indexCoding='" + indexCoding + '\'' +
                ", indexName='" + indexName + '\'' +
                ", scoreIs='" + scoreIs + '\'' +
                ", pointerType='" + pointerType + '\'' +
                ", scoreStart='" + scoreStart + '\'' +
                ", scoreStop='" + scoreStop + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
