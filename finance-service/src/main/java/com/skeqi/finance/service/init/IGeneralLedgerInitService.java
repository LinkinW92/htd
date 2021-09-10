package com.skeqi.finance.service.init;

import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;

import java.util.Collection;

/**
 * @Created by DZB
 * @Date 2021/7/28 10:17
 * @Description TODO
 */
public interface IGeneralLedgerInitService {


	//批量结束初始化
	Boolean endInitWithValidByIds(Collection<Integer> ids, Boolean isValid);
	//批量结束初始化
	Boolean notendInitWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
