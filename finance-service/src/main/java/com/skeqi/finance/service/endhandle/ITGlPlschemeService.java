package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.TGlPlscheme;
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 结转损益方案Service接口
 *
 * @author toms
 * @date 2021-08-02
 */
public interface ITGlPlschemeService extends IServicePlus<TGlPlscheme> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlPlschemeVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlPlschemeVo> queryPageList(TGlPlschemeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlPlschemeVo> queryList(TGlPlschemeQueryBo bo);

	/**
	 * 根据新增业务对象插入结转损益方案
	 * @param bo 结转损益方案新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlPlschemeAddBo bo);

	/**
	 * 根据编辑业务对象修改结转损益方案
	 * @param bo 结转损益方案编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlPlschemeEditBo bo);

	/**
	 * 执行
	 * @param fId
	 * @return
	 */
	Boolean execute(Integer fId);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
