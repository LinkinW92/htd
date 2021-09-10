package com.skeqi.finance.service.impl.init;

import com.skeqi.common.exception.CustomException;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.mapper.TGlBalanceInitMapper;
import com.skeqi.finance.mapper.basicinformation.accountbook.TBdAccountBookMapper;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookEditBo;
import com.skeqi.finance.service.basicinformation.accountbook.ITBdAccountBookService;
import com.skeqi.finance.service.init.IGeneralLedgerInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Created by DZB
 * @Date 2021/7/28 10:17
 * @Description TODO
 */
@Service
public class GeneralLedgerInitServiceImpl implements IGeneralLedgerInitService {

	@Autowired
	private ITBdAccountBookService itBdAccountBookService;
	@SuppressWarnings("all")
	@Autowired
	private TBdAccountBookMapper tBdAccountBookMapper;
	//科目初始化数据
	@SuppressWarnings("all")
	@Autowired
	private TGlBalanceInitMapper tGlBalanceInitMapper;

	@Override
	public Boolean endInitWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		ids.stream().forEach(id -> {
			TBdAccountBook edit = new TBdAccountBook();
			edit.setFBookId(id);
			edit.setFInitialStatus(BaseEnum.YES.getCode());
			if (tBdAccountBookMapper.updateById(edit) != 1) {
				throw new CustomException(String.format("%s结束失败", "" + id));
			}
			//初始化表数据 转入 主表数据
			tGlBalanceInitMapper.endInit(id);
		});
		return true;
	}

	@Override
	public Boolean notendInitWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		ids.stream().forEach(id -> {
			TBdAccountBookEditBo edit = new TBdAccountBookEditBo();
			edit.setFBookId(id);
			edit.setFInitialStatus(BaseEnum.NO.getCode());
			if (!itBdAccountBookService.updateByEditBo(edit)) {
				throw new CustomException(String.format("%s反结束失败", "" + id));
			}
			//删除初始化数据
			tGlBalanceInitMapper.notendInit(id);
		});
		return true;
	}
}
