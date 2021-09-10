package com.skeqi.mes.service.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.CWmsMaterialBarcodeRuleT;

/**
 * 物料条码规则
 * @author Administrator
 *
 */
public interface MaterialBarcodeRuleService {

	/**
	 * 查询集合
	 * @param dx
	 * @return
	 */
	public List<CWmsMaterialBarcodeRuleT> findMaterialBarcodeRuleList(CWmsMaterialBarcodeRuleT dx);

	/**
	 * 新增
	 * @param dx
	 * @return
	 */
	public int addMaterialBarcodeRule(CWmsMaterialBarcodeRuleT dx)  throws Exception ;

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	public int updateMaterialBarcodeRule(CWmsMaterialBarcodeRuleT dx)  throws Exception ;

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteMaterialBarcodeRule(@Param("id")Integer id);

}
