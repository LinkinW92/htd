package com.skeqi.mes.pojo.chenj.srm.rsp;




 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmTemplate
 * @Description ${Description}
 */

/**
 * 查询调查表模板出参
 */
public class CSrmTemplateRsp {


    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 模板名称
     */
    private String templateName;


    /**
     * 创建人
     */
    private String creator;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "CSrmTemplateRsp{" +
                "createTime='" + createTime + '\'' +
                ", templateName='" + templateName + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
