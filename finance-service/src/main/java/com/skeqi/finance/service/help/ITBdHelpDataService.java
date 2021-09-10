package com.skeqi.finance.service.help;

import com.skeqi.finance.domain.help.TBdHelpData;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpDataVo;
import com.skeqi.finance.pojo.bo.help.TBdHelpDataQueryBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpDataAddBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpDataEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 辅助资料Service接口
 *
 * @author toms
 * @date 2021-07-13
 */
public interface ITBdHelpDataService extends IServicePlus<TBdHelpData> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdHelpDataVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdHelpDataVo> queryPageList(TBdHelpDataQueryBo bo);

    TableDataInfo<TBdHelpDataVo> listByType(TBdHelpDataQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdHelpDataVo> queryList(TBdHelpDataQueryBo bo);

	/**
	 * 根据新增业务对象插入辅助资料
	 * @param bo 辅助资料新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdHelpDataAddBo bo);

	/**
	 * 根据编辑业务对象修改辅助资料
	 * @param bo 辅助资料编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdHelpDataEditBo bo);

	/**
	 * 审核
	 * @param bo
	 * @return
	 */
	Boolean audit(Collection<Integer> ids);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
