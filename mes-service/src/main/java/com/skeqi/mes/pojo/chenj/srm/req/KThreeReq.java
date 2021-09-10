package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname KThreeReq
 * @Description
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KThreeReq {

    private String jktype;
    private String method;
    private String filter;

    public String getJktype() {
        return jktype;
    }

    public void setJktype(String jktype) {
        this.jktype = jktype;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return "KThreeReq{" +
                "jktype='" + jktype + '\'' +
                ", method='" + method + '\'' +
                ", filter='" + filter + '\'' +
                '}';
    }
}
