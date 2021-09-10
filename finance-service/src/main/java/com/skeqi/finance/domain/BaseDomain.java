package com.skeqi.finance.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Created by DZB
 * @Date 2021/7/23 15:34
 * @Description TODO
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BaseDomain {

	/** 创建组织 */
	 Integer fCreateOrgid;

	/** 创建人 */
	 Integer fCreatorid;

	/** 创建日期 */
	 Date fCreateDate;

	/** 使用组织 */
	 Integer fUseOrgid;

	/** 修改人 */
	 Integer fModifierid;

	/** 修改日期 */
	 Date fModifyDate;

	/** 数据状态 */
	 String fDocumentStatus;

	/** 审核人 */
	 Integer fAuditorid;

	/** 审核日期 */
	 Date fAuditDate;

	/** 禁用状态 */
	 String fForbidStatus;

	/** 禁用人 */
	 Integer fForbidderid;

	/** 禁用日期 */
	 Date fForbidDate;

	/** 是否系统预设1 系统预设0 非系统预设默认0 */
	 String fIssysPreset;

	/** 组织隔离字段 */
	 Integer fMasterId;

	public void setAddDefault(){
		fCreateOrgid = null;
		fCreatorid = null;
		fCreateDate = null;
		fUseOrgid = null;
		fModifierid = null;
		fModifyDate = null;
		fDocumentStatus = null;
		fAuditorid = null;
		fAuditDate = null;
		fForbidStatus = null;
		fForbidderid = null;
		fForbidDate = null;
		fMasterId = null;
	}
	 public void setUpdateDefault(){
		 fCreateOrgid = null;
		 fCreatorid = null;
		 fCreateDate = null;
		 fUseOrgid = null;
		 fModifierid = null;
		 fModifyDate = null;
		 fDocumentStatus = null;
		 fAuditorid = null;
		 fAuditDate = null;
		 fForbidStatus = null;
		 fForbidderid = null;
		 fForbidDate = null;
		 fIssysPreset = null;
		 fMasterId = null;
	 }
}
