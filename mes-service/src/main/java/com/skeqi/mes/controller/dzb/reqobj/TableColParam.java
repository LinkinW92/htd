package com.skeqi.mes.controller.dzb.reqobj;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class TableColParam {

    /**
     * id
     */
    Integer id;
    /**
     * 表名id
     */
    Integer tableId;
    /**
     * 显示列名
     */
    String showName;
    /**
     * 列名类型：1:整数，2:字符串，3:日期时间。4:小数。5：日期。6：时间
     */
    Integer colType;
}
