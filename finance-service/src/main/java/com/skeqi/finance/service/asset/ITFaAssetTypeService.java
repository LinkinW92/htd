package com.skeqi.finance.service.asset;

import com.skeqi.finance.domain.asset.TFaAssetType;
import com.skeqi.finance.pojo.vo.asset.TFaAssetTypeVo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeQueryBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeAddBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 资产类别Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITFaAssetTypeService extends IServicePlus<TFaAssetType> {
	/**
	 * 查询单个
	 * @return
	 */
	TFaAssetTypeVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TFaAssetTypeVo> queryPageList(TFaAssetTypeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TFaAssetTypeVo> queryList(TFaAssetTypeQueryBo bo);

	/**
	 * 根据新增业务对象插入资产类别
	 * @param bo 资产类别新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TFaAssetTypeAddBo bo);

	/**
	 * 根据编辑业务对象修改资产类别
	 * @param bo 资产类别编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TFaAssetTypeEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids);

	/**
	 * 审核资产类别
	 * @param integerList 主键集合
	 * @return
	 */
	Boolean auditByIds(List<Integer> integerList);

	/**
	 * 反审核
	 * @param ids
	 * @return
	 */
	Boolean antiAuditByIds(List<Integer> ids);

}
