package com.skeqi.mes.pojo.api;

/**
 * @date 	2020年1月11日
 * @author yinp
 *
 */
public class GetcurrentstepPT {

	private Integer id;
	private String tempSteprecordCount;
	private String step;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTempSteprecordCount() {
		return tempSteprecordCount;
	}
	public void setTempSteprecordCount(String tempSteprecordCount) {
		this.tempSteprecordCount = tempSteprecordCount;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}

}
