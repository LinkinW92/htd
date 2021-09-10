package com.skeqi.mes.controller.dzb.reqobj;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/12 16:22
 * @Description TODO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class WorkInProcessParam {
    public List group;
    public Map where;
    public String start;
    public String end;
    public Integer pageSize;
    public Integer pageNum;


}
