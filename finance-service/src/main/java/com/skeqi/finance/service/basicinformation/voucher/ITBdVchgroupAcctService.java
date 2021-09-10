package com.skeqi.finance.service.basicinformation.voucher;

import com.skeqi.finance.domain.basicinformation.voucher.TBdVchgroupAcct;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVchgroupAcctVo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 凭证字-科目控制Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdVchgroupAcctService extends IServicePlus<TBdVchgroupAcct> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdVchgroupAcctVo queryById(Integer fEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdVchgroupAcctVo> queryPageList(TBdVchgroupAcctQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdVchgroupAcctVo> queryList(TBdVchgroupAcctQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证字-科目控制
	 * @param bo 凭证字-科目控制新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdVchgroupAcctAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证字-科目控制
	 * @param bo 凭证字-科目控制编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdVchgroupAcctEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);


	/**
	 * 校验并批量添加数据
	 * @param acctAddBoList 凭证字-科目控制集合
	 * @return
	 */
	Boolean insertByAddBoList(List<TBdVchgroupAcctAddBo> acctAddBoList);

	/**
	 * 校验并批量修改数据
	 * @param acctEditBoList 凭证字-科目控制集合
	 * @return
	 */
	Boolean updateByEditBoList(List<TBdVchgroupAcctEditBo> acctEditBoList);

	/**
	 * 按凭证子内码查询科目控制列表
	 * @param fVchgroupId
	 * @return
	 */
	List<TBdVchgroupAcctVo> selectByFVchgroupId(Integer fVchgroupId);
}
