package com.skeqi.finance.service.asset;

import com.skeqi.finance.domain.asset.TFaAssetTypeGroup;
import com.skeqi.finance.pojo.vo.asset.TFaAssetTypeGroupVo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeGroupQueryBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeGroupAddBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeGroupEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 资产类别组Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITFaAssetTypeGroupService extends IServicePlus<TFaAssetTypeGroup> {
	/**
	 * 查询单个
	 * @return
	 */
	TFaAssetTypeGroupVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TFaAssetTypeGroupVo> queryPageList(TFaAssetTypeGroupQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TFaAssetTypeGroupVo> queryList(TFaAssetTypeGroupQueryBo bo);

	/**
	 * 根据新增业务对象插入资产类别组
	 * @param bo 资产类别组新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TFaAssetTypeGroupAddBo bo);

	/**
	 * 根据编辑业务对象修改资产类别组
	 * @param bo 资产类别组编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TFaAssetTypeGroupEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
