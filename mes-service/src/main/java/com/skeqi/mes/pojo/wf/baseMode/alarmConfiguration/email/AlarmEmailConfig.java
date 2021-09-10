package com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
    * 邮箱服务配置
 * @author Lenovo
 */
@ApiModel(value="com-skeqi-pojo-wf-basemode-alarmconfiguration-email-AlarmEmailConfig")
public class AlarmEmailConfig {
    @ApiModelProperty(value="")
    private Integer id;

    /**
    * 发件人
    */
    @ApiModelProperty(value="发件人")
    private String senderEmail;

    /**
    * 服务器
    */
    @ApiModelProperty(value="服务器")
    private String theServer;

    /**
    * 授权码
    */
    @ApiModelProperty(value="授权码")
    private String authorizationCode;

    /**
    * 是否启用(0 关闭，1 开启)
    */
    @ApiModelProperty(value="是否启用(0 关闭，1 开启)")
    private Integer ifEnable;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date cdt;

    /**
    * 修改时间
    */
    @ApiModelProperty(value="修改时间")
    private Date udt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getTheServer() {
        return theServer;
    }

    public void setTheServer(String theServer) {
        this.theServer = theServer;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public Integer getIfEnable() {
        return ifEnable;
    }

    public void setIfEnable(Integer ifEnable) {
        this.ifEnable = ifEnable;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", senderEmail=").append(senderEmail);
        sb.append(", theServer=").append(theServer);
        sb.append(", authorizationCode=").append(authorizationCode);
        sb.append(", ifEnable=").append(ifEnable);
        sb.append(", cdt=").append(cdt);
        sb.append(", udt=").append(udt);
        sb.append("]");
        return sb.toString();
    }
}
