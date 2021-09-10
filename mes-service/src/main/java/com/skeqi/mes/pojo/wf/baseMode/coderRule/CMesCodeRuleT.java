package com.skeqi.mes.pojo.wf.baseMode.coderRule;


import java.util.Date;

public class CMesCodeRuleT {

  private long id;
  private String dt;
  private String alterDt;
  private String codeRulePrefix;
  private String codeRule;
  private Integer codeRuleSuffix;
  private String codeType;
  private String codeName;
  private String lastCode;
  private String codeRuleValue;
  private String codeRuleSuffixValue;
  private String explain;

  private Integer delete;
  private Integer enableReset;
  private Integer resetCycle;

  private Date createCodeTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDt() {
    return dt;
  }

  public void setDt(String dt) {
    this.dt = dt;
  }

  public String getAlterDt() {
    return alterDt;
  }

  public void setAlterDt(String alterDt) {
    this.alterDt = alterDt;
  }

  public String getCodeRulePrefix() {
    return codeRulePrefix;
  }

  public void setCodeRulePrefix(String codeRulePrefix) {
    this.codeRulePrefix = codeRulePrefix;
  }

  public String getCodeRule() {
    return codeRule;
  }

  public void setCodeRule(String codeRule) {
    this.codeRule = codeRule;
  }

  public Integer getCodeRuleSuffix() {
    return codeRuleSuffix;
  }

  public void setCodeRuleSuffix(Integer codeRuleSuffix) {
    this.codeRuleSuffix = codeRuleSuffix;
  }

  public String getCodeType() {
    return codeType;
  }

  public void setCodeType(String codeType) {
    this.codeType = codeType;
  }

  public String getCodeName() {
    return codeName;
  }

  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }

  public String getLastCode() {
    return lastCode;
  }

  public void setLastCode(String lastCode) {
    this.lastCode = lastCode;
  }

  public String getCodeRuleValue() {
    return codeRuleValue;
  }

  public void setCodeRuleValue(String codeRuleValue) {
    this.codeRuleValue = codeRuleValue;
  }

  public String getCodeRuleSuffixValue() {
    return codeRuleSuffixValue;
  }

  public void setCodeRuleSuffixValue(String codeRuleSuffixValue) {
    this.codeRuleSuffixValue = codeRuleSuffixValue;
  }

  public String getExplain() {
    return explain;
  }

  public void setExplain(String explain) {
    this.explain = explain;
  }

  public Integer getDelete() {
    return delete;
  }

  public void setDelete(Integer delete) {
    this.delete = delete;
  }

  public Integer getEnableReset() {
    return enableReset;
  }

  public void setEnableReset(Integer enableReset) {
    this.enableReset = enableReset;
  }

  public Integer getResetCycle() {
    return resetCycle;
  }

  public void setResetCycle(Integer resetCycle) {
    this.resetCycle = resetCycle;
  }

  public Date getCreateCodeTime() {
    return createCodeTime;
  }

  public void setCreateCodeTime(Date createCodeTime) {
    this.createCodeTime = createCodeTime;
  }

  @Override
  public String toString() {
    return "CMesCodeRuleT{" +
            "id=" + id +
            ", dt='" + dt + '\'' +
            ", alterDt='" + alterDt + '\'' +
            ", codeRulePrefix='" + codeRulePrefix + '\'' +
            ", codeRule='" + codeRule + '\'' +
            ", codeRuleSuffix=" + codeRuleSuffix +
            ", codeType='" + codeType + '\'' +
            ", codeName='" + codeName + '\'' +
            ", lastCode='" + lastCode + '\'' +
            ", codeRuleValue='" + codeRuleValue + '\'' +
            ", codeRuleSuffixValue='" + codeRuleSuffixValue + '\'' +
            ", explain='" + explain + '\'' +
            ", delete='" + delete + '\'' +
            ", enableReset=" + enableReset +
            ", resetCycle=" + resetCycle +
            ", createCodeTime='" + createCodeTime + '\'' +
            '}';
  }
}
