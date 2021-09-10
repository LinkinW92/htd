package com.skeqi.finance.service.impl.cashflow;

import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.exception.CustomException;
import com.skeqi.finance.domain.cashflow.TAccount;
import com.skeqi.finance.mapper.cashflow.TAccountMapper;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountVo;
import com.skeqi.finance.pojo.vo.cashflow.TGlVoucherEntryCashVo;
import com.skeqi.finance.service.cashflow.TAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * T型账Service业务层处理
 *
 * @author toms
 * @date 2021-07-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TAccountServiceImpl extends ServicePlusImpl<TAccountMapper, TAccount> implements TAccountService {

	@Resource
	private TAccountMapper mapper;

	@Override
	public List<Map<String,Object>> selectAccountAndFlowProject(TAccount bo) {
		List<TBdAccountVo> accountList = mapper.selectAccount(bo);
		List<Integer> integerList = accountList.stream().map(TBdAccountVo::getFAcctId).collect(Collectors.toList());
		List<TGlVoucherEntryCashVo> tGlVoucherEntryCashVoList = mapper.selectFlowProject(integerList);

		List<Map<String,Object>> mapList = new ArrayList<>();
		accountList.forEach(tBdAccountVo -> {
			Map<String, Object> map = new HashMap<>();
			map.put("fAcctId",tBdAccountVo.getFAcctId());
			map.put("fNumber",tBdAccountVo.getFNumber());
			tGlVoucherEntryCashVoList.forEach(tGlVoucherEntryCashVo -> {
				List<TGlVoucherEntryCashVo> entryCashList = new ArrayList<>();
				if (tBdAccountVo.getFAcctId().equals(tGlVoucherEntryCashVo.getCashAccountId())){
					entryCashList.add(tGlVoucherEntryCashVo);
				}
				map.put("entryCashList",entryCashList);
			});
			mapList.add(map);
		});
		return mapList;
	}

	@Override
	public Boolean updateByList(List<TGlVoucherEntryCashVo> tGlVoucherEntryCashVoList) {
		if (tGlVoucherEntryCashVoList.size()<1){
			throw new CustomException("保存数据不能为空!");
		}
		Boolean result = mapper.updateByList(tGlVoucherEntryCashVoList)>0;
		if (!result){
			throw new CustomException("保存失败!");
		}
		return result;
	}
}
