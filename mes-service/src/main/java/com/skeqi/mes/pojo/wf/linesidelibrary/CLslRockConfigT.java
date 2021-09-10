package com.skeqi.mes.pojo.wf.linesidelibrary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
    * 线边仓配置表
 * @author Lenovo
 */
@ApiModel(value="com-skeqi-pojo-wf-linesidelibrary-CLslRockConfigT")
public class CLslRockConfigT implements Serializable {

    private static final long serialVersionUID = 7915568427169530536L;

    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date cdt;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date udt;

    /**
    * 工位id
    */
    @ApiModelProperty(value="工位id")
    private Integer stationId;

    /**
    * 工序
    */
    @ApiModelProperty(value="工序")
    private Integer step;

    /**
    * 物料编号
    */
    @ApiModelProperty(value="物料编号")
    private String materialNo;

    /**
    * 物料名称
    */
    @ApiModelProperty(value="物料名称")
    private String materialName;

    /**
    * 容量
    */
    @ApiModelProperty(value="容量")
    private Integer capacity;

    /**
    * 安全水位
    */
    @ApiModelProperty(value="安全水位")
    private Integer safetyLevel;

    /**
    * 校验规则
    */
    @ApiModelProperty(value="校验规则")
    private String validateRules;

    /**
     * 料架id
     */
    @ApiModelProperty(value="料架id")
    private Integer rockId;

    /**
     * 版本id
     */
    @ApiModelProperty(value="版本id")
    private Integer versionId;

    /**
     * 产品id
     */
    @ApiModelProperty(value="产品id")
    private Integer productId;

    /**
     * 产线id
     */
    @ApiModelProperty(value="产线id")
    private Integer lineId;

    /**
     * 精确数量
     */
    @ApiModelProperty(value="精确数量")
    private Integer quantity;

    /**
     * 需求数量
     */
    @ApiModelProperty(value="需求数量")
    private Integer requiredQuantity;

    /**
     * 追溯方式
     */
    @ApiModelProperty(value="追溯方式")
    private String tracesType;

    public String getTracesType() {
        return tracesType;
    }

    public void setTracesType(String tracesType) {
        this.tracesType = tracesType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(Integer requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCdt() {
        return cdt;
    }

    public void setCdt(Date cdt) {
        this.cdt = cdt;
    }

    public Date getUdt() {
        return udt;
    }

    public void setUdt(Date udt) {
        this.udt = udt;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSafetyLevel() {
        return safetyLevel;
    }

    public void setSafetyLevel(Integer safetyLevel) {
        this.safetyLevel = safetyLevel;
    }

    public String getValidateRules() {
        return validateRules;
    }

    public void setValidateRules(String validateRules) {
        this.validateRules = validateRules;
    }

    public Integer getRockId() {
        return rockId;
    }

    public void setRockId(Integer rockId) {
        this.rockId = rockId;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    @Override
    public String toString() {
        return "CLslRockConfigT{" +
                "id=" + id +
                ", cdt=" + cdt +
                ", udt=" + udt +
                ", stationId=" + stationId +
                ", step=" + step +
                ", materialNo='" + materialNo + '\'' +
                ", materialName='" + materialName + '\'' +
                ", capacity=" + capacity +
                ", safetyLevel=" + safetyLevel +
                ", validateRules='" + validateRules + '\'' +
                ", rockId=" + rockId +
                ", versionId=" + versionId +
                '}';
    }
}
