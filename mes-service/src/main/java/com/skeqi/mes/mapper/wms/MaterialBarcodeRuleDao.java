package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.CWmsMaterialBarcodeRuleT;
import com.skeqi.mes.pojo.wms.CWmsMaterialRuleAttributeT;

/**
 * 物料条码规则
 * @author Administrator
 *
 */
public interface MaterialBarcodeRuleDao {

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
	public Integer addMaterialBarcodeRule(CWmsMaterialBarcodeRuleT dx);

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	public Integer updateMaterialBarcodeRule(CWmsMaterialBarcodeRuleT dx);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public Integer deleteMaterialBarcodeRule(@Param("id")Integer id);

	/**
	 * 查询物料条码规则属性集合
	 * @return
	 */
	public List<CWmsMaterialRuleAttributeT> findMaterialRuleAttributeList();


}
