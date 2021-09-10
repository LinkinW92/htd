package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.TGlPlschemeFlex;
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeFlexVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeFlexQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeFlexAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeFlexEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 结账损益核算维度Service接口
 *
 * @author toms
 * @date 2021-08-17
 */
public interface ITGlPlschemeFlexService extends IServicePlus<TGlPlschemeFlex> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlPlschemeFlexVo queryById(Integer fEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlPlschemeFlexVo> queryPageList(TGlPlschemeFlexQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlPlschemeFlexVo> queryList(TGlPlschemeFlexQueryBo bo);

	/**
	 * 根据新增业务对象插入结账损益核算维度
	 * @param bo 结账损益核算维度新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlPlschemeFlexAddBo bo);

	/**
	 * 根据编辑业务对象修改结账损益核算维度
	 * @param bo 结账损益核算维度编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlPlschemeFlexEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
