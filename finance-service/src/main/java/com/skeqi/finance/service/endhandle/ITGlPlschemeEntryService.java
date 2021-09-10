package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.TGlPlschemeEntry;
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeEntryVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEntryQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEntryAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEntryEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 结转损益方案分录Service接口
 *
 * @author toms
 * @date 2021-08-02
 */
public interface ITGlPlschemeEntryService extends IServicePlus<TGlPlschemeEntry> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlPlschemeEntryVo queryById(Long fEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlPlschemeEntryVo> queryPageList(TGlPlschemeEntryQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlPlschemeEntryVo> queryList(TGlPlschemeEntryQueryBo bo);

	/**
	 * 根据新增业务对象插入结转损益方案分录
	 * @param bo 结转损益方案分录新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlPlschemeEntryAddBo bo);

	/**
	 * 根据编辑业务对象修改结转损益方案分录
	 * @param bo 结转损益方案分录编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlPlschemeEntryEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
