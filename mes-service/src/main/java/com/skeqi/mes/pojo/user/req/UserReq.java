package com.skeqi.mes.pojo.user.req;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author chenj
 * @version 6.15
 * @date 2021/3/15 15:32
 */
public class UserReq {

    @ApiModelProperty(value = "当前页码")
    private Integer pageNum;

    @ApiModelProperty(value = "总条数")
    private Integer pageSize;

    @ApiModelProperty(value = "编号")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "权限等级")
    private Integer status;

    @ApiModelProperty(value = "部门")
    private String department;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "UserReq{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                ", department='" + department + '\'' +
                '}';
    }
}
