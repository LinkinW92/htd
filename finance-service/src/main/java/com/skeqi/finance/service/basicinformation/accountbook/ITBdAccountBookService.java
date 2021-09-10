package com.skeqi.finance.service.basicinformation.accountbook;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookEditBo;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookQueryBo;
import com.skeqi.finance.pojo.vo.book.TBdAccountBookVo;

import java.util.Collection;
import java.util.List;

/**
 * 账簿Service接口
 *
 * @author toms
 * @date 2021-07-13
 */
public interface ITBdAccountBookService extends IServicePlus<TBdAccountBook> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdAccountBookVo queryById(Integer fBookId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdAccountBookVo> queryPageList(TBdAccountBookQueryBo bo);

	/**
	 * 查询会计政策、日历、记账本位币、汇率类型
	 * @param bo
	 * @return
	 */
	AjaxResult queryAcctInfo(TBdAccountBookQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdAccountBookVo> queryList(TBdAccountBookQueryBo bo);

	/**
	 * 根据新增业务对象插入账簿
	 * @param bo 账簿新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdAccountBookAddBo bo);

	/**
	 * 根据编辑业务对象修改账簿
	 * @param bo 账簿编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdAccountBookEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);

	/**
	 * 审核
	 * @param ids
	 * @return
	 */
	Boolean audit(Collection<Integer> ids);

}
