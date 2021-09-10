package com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.inform;

/**
    * 告警配置表
    */
public class CMesAlarmConfigT {
    private Integer id;

    /**
    * 配置编号
    */
    private String code;

    /**
    * 执行sql
    */
    private String sql;

    /**
    * 告警方式（0 邮箱，1，短信，2 电话）
    */
    private String way;

    /**
    * 接收人员
    */
    private String receptionStaff;

    /**
    * 数据格式（邮件：表格式/短信：Jason）
    */
    private String dataFormat;

    /**
     * 正文
     */
    private String title;
    /**
     * 正文
     */
    private String content;

    /**
     * 创建时间
     */
    private String cdt;


    /**
     * 修改时间
     */
    private String udt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getReceptionStaff() {
        return receptionStaff;
    }

    public void setReceptionStaff(String receptionStaff) {
        this.receptionStaff = receptionStaff;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCdt() {
        return cdt;
    }

    public void setCdt(String cdt) {
        this.cdt = cdt;
    }

    public String getUdt() {
        return udt;
    }

    public void setUdt(String udt) {
        this.udt = udt;
    }
}
