package com.skeqi.finance.domain.basicinformation.cashflow;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 现金流量项目类别-2对象 t_gl_cash_flow_type
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_cash_flow_type")
public class TGlCashFlowType implements Serializable {

    private static final long serialVersionUID=1L;


    /** 现金流量项目类别内码 */
    @TableId(value = "f_item_typeid")
    private Integer fItemTypeid;

    /** 编码 */
    private String fNumber;

    /** 名称 */
    private String fName;

    /** 类别属性 主表项目 = 1，附表项目 = 2 */
    private Integer fItemGroupid;

    /** 项目分类 经营活动 = 1投资活动 = 2筹资活动 = 3汇率变动和其他 = 4   */
    private Integer fGroupTypeid;

    /** 描述 */
    private String fDescription;

    /** 现金流量项目表  */
    private Integer fCashFlowItemTable;

    /** 创建组织 */
    private Integer fCreateOrgid;

    /** 创建人 */
    private Integer fCreatorid;

    /** 创建日期 */
    private Date fCreateDate;

    /** 使用组织 */
    private Integer fUseOrgid;

    /** 修改人 */
    private Integer fModifierid;

    /** 修改日期 */
    private Date fModifyDate;

    /** 组织隔离字段 */
    private Integer fMasterId;

}
