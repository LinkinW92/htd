package com.skeqi.finance.service.impl.report;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.finance.domain.TGlBalance;
import com.skeqi.finance.mapper.report.TGlBalanceMapper;
import com.skeqi.finance.pojo.bo.ABFlexItemPropertyDetailQueryBo;
import com.skeqi.finance.pojo.bo.ABTotalBalanceQueryBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceAddBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceEditBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceQueryBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceVoucherBo;
import com.skeqi.finance.pojo.vo.ABTotalBalanceVo;
import com.skeqi.finance.pojo.vo.FlexItemPropertyDetailVo;
import com.skeqi.finance.pojo.vo.TGlBalanceVo;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherEntryVo;
import com.skeqi.finance.service.report.ITGlBalanceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 科目余额Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TGlBalanceServiceImpl extends ServicePlusImpl<TGlBalanceMapper, TGlBalance> implements ITGlBalanceService {

	private Page<Map> mapPage;

	@Override
	public TGlBalanceVo queryById(Integer fAccountBookId) {
		return getVoById(fAccountBookId, TGlBalanceVo.class);
	}

	@Override
	public TableDataInfo<TGlBalanceVo> queryPageList(TGlBalanceQueryBo bo) {
		if (bo.getPageNum() == null) bo.setPageNum(1);
		if (bo.getPageSize() == null) bo.setPageSize(10);
		IPage<TGlBalance> page = new Page<>(bo.getPageNum(), bo.getPageNum());

		List<TGlBalanceVo> tGlBalanceVoList = baseMapper.queryList2(page, bo).getRecords().stream()
			.map(any -> BeanUtil.toBean(any, TGlBalanceVo.class))
			.collect(Collectors.toList());

		return PageUtils.buildDataInfo(tGlBalanceVoList);
	}

	@Override
	public List<TGlBalanceVo> queryList(TGlBalanceQueryBo bo) {
		return listVo(buildQueryWrapper(bo), TGlBalanceVo.class);
	}

	private LambdaQueryWrapper<TGlBalance> buildQueryWrapper(TGlBalanceQueryBo bo) {
		Map<String, Object> params = bo.getParams();
		LambdaQueryWrapper<TGlBalance> lqw = Wrappers.lambdaQuery();
		validEntityBeforeSelect(bo);
		//会计期间
		lqw.gt(TGlBalance::getFYear, bo.getFYear());
		lqw.gt(TGlBalance::getFPeriod, bo.getFPeriod());
		lqw.lt(TGlBalance::getFYear, bo.getFYear());
		lqw.lt(TGlBalance::getFPeriod, bo.getFPeriod());
		//账簿
		lqw.eq(TGlBalance::getFAccountBookId, bo.getFAccountBookId());

//        lqw.eq(bo.getFAccountBookId() != null, TGlBalance::getFAccountBookId, bo.getFAccountBookId());
		lqw.eq(bo.getFAccountId() != null, TGlBalance::getFAccountId, bo.getFAccountId());
		lqw.eq(StrUtil.isNotBlank(bo.getFDetailCode()), TGlBalance::getFDetailCode, bo.getFDetailCode());
		lqw.eq(bo.getFCurrencyId() != null, TGlBalance::getFCurrencyId, bo.getFCurrencyId());
//        lqw.eq(bo.getFPeriod() != null, TGlBalance::getFPeriod, bo.getFPeriod());
//        lqw.eq(StrUtil.isNotBlank(bo.getFYear()), TGlBalance::getFYear, bo.getFYear());
		lqw.eq(bo.getFBeginBalancefor() != null, TGlBalance::getFBeginBalancefor, bo.getFBeginBalancefor());
		lqw.eq(bo.getFBeginBalance() != null, TGlBalance::getFBeginBalance, bo.getFBeginBalance());
		lqw.eq(bo.getFDebitFor() != null, TGlBalance::getFDebitFor, bo.getFDebitFor());
		lqw.eq(bo.getFDebit() != null, TGlBalance::getFDebit, bo.getFDebit());
		lqw.eq(bo.getFCreditFor() != null, TGlBalance::getFCreditFor, bo.getFCreditFor());
		lqw.eq(bo.getFCredit() != null, TGlBalance::getFCredit, bo.getFCredit());
		lqw.eq(bo.getFYtdDebitfor() != null, TGlBalance::getFYtdDebitfor, bo.getFYtdDebitfor());
		lqw.eq(bo.getFYtdDebit() != null, TGlBalance::getFYtdDebit, bo.getFYtdDebit());
		lqw.eq(bo.getFYtdCreditfor() != null, TGlBalance::getFYtdCreditfor, bo.getFYtdCreditfor());
		lqw.eq(bo.getFYtdCredit() != null, TGlBalance::getFYtdCredit, bo.getFYtdCredit());
		lqw.eq(bo.getFEndBalancefor() != null, TGlBalance::getFEndBalancefor, bo.getFEndBalancefor());
		lqw.eq(bo.getFEndBalance() != null, TGlBalance::getFEndBalance, bo.getFEndBalance());
		lqw.eq(bo.getFAdjustPeriod() != null, TGlBalance::getFAdjustPeriod, bo.getFAdjustPeriod());
		lqw.eq(bo.getFYearPeriod() != null, TGlBalance::getFYearPeriod, bo.getFYearPeriod());
		return lqw;
	}

	@Override
	public Boolean insertByAddBo(TGlBalanceAddBo bo) {
		TGlBalance add = BeanUtil.toBean(bo, TGlBalance.class);
		validEntityBeforeSave(add);
		return save(add);
	}

	@Override
	public Boolean updateByEditBo(TGlBalanceEditBo bo) {
		TGlBalance update = BeanUtil.toBean(bo, TGlBalance.class);
		validEntityBeforeSave(update);
		return updateById(update);
	}

	/**
	 * 查询前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeSelect(TGlBalanceQueryBo entity) {
		//TODO 做一些数据校验,如唯一约束
		Integer fYear = entity.getFYear();
		try {
		} catch (NumberFormatException e) {
			throw new CustomException(String.format("开始年格式不正确%s", fYear));
		}
		Integer efYear = entity.getEfYear();
		try {
			//Integer.parseInt(efYear);
		} catch (NumberFormatException e) {
			throw new CustomException(String.format("结束年格式不正确%s", efYear));
		}

	}

	/**
	 * 保存前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeSave(TGlBalance entity) {
		//TODO 做一些数据校验,如唯一约束
	}

	@Override
	public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		if (isValid) {
			//TODO 做一些业务上的校验,判断是否需要校验
		}
		return removeByIds(ids);
	}


	/**
	 * 更新凭证科目余额
	 *
	 * @param bo
	 * @return
	 */
	@Override
	public AjaxResult updateVoucherBalance(TGlBalanceVoucherBo bo) {
		QueryWrapper<TGlBalance> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("f_account_book_id", bo.getFAccountBookId())
			.eq("f_account_id", bo.getFAccountId())
			.eq("f_currency_id", bo.getFCurrencyId())
			.eq("f_period", bo.getFPeriod())
			.eq("f_year", bo.getFYear());
		if (StrUtil.isNotBlank(bo.getFDetailCode())) {
			queryWrapper.eq("f_detail_code", bo.getFDetailCode());
			queryWrapper.eq("dimension_code", bo.getDimensionCode());
		}
		TGlBalance old = baseMapper.selectOne(queryWrapper);
		if (null == old) {
			//新增
			TGlBalance balance = new TGlBalance();
			BeanUtil.copyProperties(bo, balance);
			baseMapper.insert(balance);
		} else {
			//更新
			old.setFCredit(bo.getFCredit()).setFCreditFor(bo.getFCreditFor())
				.setFDebit(bo.getFDebit()).setFDebitFor(bo.getFDebitFor())
				.setFYtdCredit(bo.getFYtdCredit())
				.setFYtdCreditfor(bo.getFYtdCreditfor())
				.setFYtdDebit(bo.getFYtdDebit())
				.setFYtdDebitfor(bo.getFYtdDebitfor())
				.setFEndBalance(bo.getFDebit().add(old.getFBeginBalance()).subtract(bo.getFCredit()))
				.setFEndBalancefor(bo.getFDebitFor().add(old.getFBeginBalancefor()).subtract(bo.getFCreditFor()));

			LambdaUpdateWrapper<TGlBalance> upWrapper = Wrappers.<TGlBalance>lambdaUpdate()
				.eq(TGlBalance::getFAccountBookId, bo.getFAccountBookId())
				.eq(TGlBalance::getFAccountId, bo.getFAccountId())
				.eq(TGlBalance::getFCurrencyId, bo.getFCurrencyId())
				.eq(TGlBalance::getFPeriod, bo.getFPeriod())
				.eq(TGlBalance::getFYear, bo.getFYear());
			if (StrUtil.isNotBlank(bo.getFDetailCode())) {
				upWrapper.eq(TGlBalance::getFDetailCode, bo.getFDetailCode());
				upWrapper.eq(TGlBalance::getDimensionCode, bo.getDimensionCode());
			}
			baseMapper.update(old, upWrapper);
		}
		return AjaxResult.success();
	}


	@Override
	public TableDataInfo<ABTotalBalanceVo> queryABTotalBalance(ABTotalBalanceQueryBo bo) {
		if (bo.getPageNum() == null) bo.setPageNum(1);
		if (bo.getPageSize() == null) bo.setPageSize(10);
		IPage<TGlBalance> page = new Page<>(bo.getPageNum(), bo.getPageSize());

		List<TGlBalanceVo> tGlBalanceVoList = baseMapper.queryList3(page, bo).getRecords().stream()
			.map(any -> BeanUtil.toBean(any, TGlBalanceVo.class))
			.collect(Collectors.toList());
		List<ABTotalBalanceVo> abTotalBalanceVoList = new ArrayList<>();
		for (TGlBalanceVo balance : tGlBalanceVoList) {
			ABTotalBalanceVo vo1 = new ABTotalBalanceVo();
			ABTotalBalanceVo vo2 = new ABTotalBalanceVo();
			ABTotalBalanceVo vo3 = new ABTotalBalanceVo();
			vo1.setFAccountId(balance.getFAccountId())
				.setFName(balance.getFName())
				.setFYear(balance.getFYear())
				.setFPeriod(balance.getFPeriod())
				.setSummary("期初余额")
				.setFDetailCode(balance.getFDetailCode())
				.setBalance(BigDecimal.valueOf(Math.abs(balance.getFBeginBalance().doubleValue())));
			if ((balance.getFBeginBalance().doubleValue() > 0 && balance.getFDc() == 1)
				|| (balance.getFEndBalance().doubleValue() < 0 && balance.getFDc() == -1)) {
				vo1.setFDc(1);
			} else {
				vo1.setFDc(-1);
			}
			vo2.setFYear(balance.getFYear())
				.setFPeriod(balance.getFPeriod())
				.setSummary("本期合计")
				.setFDebit(balance.getFDebit())
				.setFCredit(balance.getFCredit())
				.setFDc(balance.getFDc())
				.setFDetailCode(balance.getFDetailCode())
				.setBalance(balance.getFEndBalance());
			vo3.setFYear(balance.getFYear())
				.setFPeriod(balance.getFPeriod())
				.setSummary("本年累计")
				.setFDebit(balance.getFYtdDebit())
				.setFCredit(balance.getFYtdCredit())
				.setFDc(balance.getFDc())
				.setFDetailCode(balance.getFDetailCode())
				.setBalance(balance.getFEndBalance());
			abTotalBalanceVoList.add(vo1);
			abTotalBalanceVoList.add(vo2);
			abTotalBalanceVoList.add(vo3);
		}
		TableDataInfo<ABTotalBalanceVo> rspData = new TableDataInfo<>();
		rspData.setCode(HttpStatus.HTTP_OK);
		rspData.setMsg("查询成功");
		rspData.setRows(abTotalBalanceVoList);
		rspData.setTotal(page.getTotal());
		return rspData;
	}

	@Override
	public TableDataInfo<FlexItemPropertyDetailVo> queryFlexItemPropertyDetail(ABFlexItemPropertyDetailQueryBo bo) {
		if (bo.getPageNum() == null) bo.setPageNum(1);
		if (bo.getPageSize() == null) bo.setPageSize(10);
		IPage<TGlBalance> page = new Page<>(bo.getPageNum(), bo.getPageSize());

		Page<Map> mapPage = baseMapper.queryList4(page, bo);
		List<Map> list = mapPage.getRecords();
		//上一个凭证数据对象
		Map m1 = null;
		//同一个科目的核算维度的期初余额，借方金额，贷方金额，
		BigDecimal balance = new BigDecimal(0);
		BigDecimal debit = new BigDecimal(0);
		BigDecimal credit = new BigDecimal(0);
		Integer fAccountBookId = bo.getFAccountBookId();
		//最后的封装对象
		List<Map> VoList = new ArrayList<>();
		for (Map m : list) {
			Integer fPeriod = Integer.parseInt(m.get("fPeriod").toString());
			Integer fYear = Integer.parseInt(m.get("fYear").toString());
			String fDetailCode = m.get("fDetailCode").toString();
			String dsCode = m.get("dsCode").toString();
			Object fData = m.get("fData");
			int fVoucherDc = Integer.parseInt(m.get("fVoucherDc").toString());
			int fAccountDc = Integer.parseInt(m.get("fAccountDc").toString());
			BigDecimal fbalance = (BigDecimal)m.get("balance");

			Map queryMap = new HashMap();
			queryMap.put("fYear", fYear);
			queryMap.put("fPeriod", fPeriod);
			queryMap.put("fAccountBookId", fAccountBookId);
			queryMap.put("fAccountId", m.get("fAccountId"));
			queryMap.put("dimensionCode", fDetailCode);
			queryMap.put("fDetailCode", dsCode);
			if (m1 == null ) {
				//初始化m1
				m1 = new HashMap();
				m1.put("fPeriod", fPeriod);
				m1.put("fYear", fYear);
				m1.put("fDetailCode", fDetailCode);
				m1.put("dsCode", dsCode);
				//期初余额对象
				Map begin = new HashMap();
				begin.put("fDate", fData);
				begin.put("fPeriod", fPeriod);
				begin.put("fYear", fYear);
				begin.put("Summary", "期初余额");
				//查询期初余额
				balance = baseMapper.queryBeginBalance(queryMap);
				debit = new BigDecimal(0);
				credit = new BigDecimal(0);
				begin.put("balance3", balance);
				VoList.add(begin);
			}else{
				//计算上一个的本期和期末余额
				Integer fPeriod2 = Integer.parseInt(m1.get("fPeriod").toString());
				Integer fYear2 = Integer.parseInt(m1.get("fYear").toString());
				String fDetailCode2 = m1.get("fDetailCode").toString();
				String dsCode2 = m1.get("dsCode").toString();
				if(fPeriod2 == fPeriod && fYear2 == fYear && fDetailCode2.equals(fDetailCode) && dsCode2.equals(dsCode)){
					if(fVoucherDc == fAccountDc){
						balance.add((BigDecimal) m.get("balance"));
					}else{
						balance.subtract((BigDecimal) m.get("balance"));
					}
					if(fVoucherDc == 1){
						debit.add(fbalance);
					}else{
						credit.add(fbalance);
					}
				}else{
					//本期余额
					Map thisPeriod = new HashMap();
					thisPeriod.put("fPeriod", fPeriod);
					thisPeriod.put("fYear", fYear);
					thisPeriod.put("Summary", "本期合计");
					thisPeriod.put("balance", debit);
					thisPeriod.put("balance2", credit);
					thisPeriod.put("balance3", balance);
					VoList.add(thisPeriod);
					//期初余额对象
					Map begin = new HashMap();
					balance = baseMapper.queryBeginBalance(queryMap);
					begin.put("fDate", fData);
					begin.put("fPeriod", fPeriod);
					begin.put("fYear", fYear);
					begin.put("Summary", "期初余额");
					begin.put("balance3", balance);
					debit = new BigDecimal(0);
					credit = new BigDecimal(0);
					VoList.add(begin);
				}
			}
			m.put("fDetailCodeStr",String.format("[%s]-[%s]",fDetailCode,dsCode));
			//如果是贷方
			if (fVoucherDc == -1) {
				m.put("fMeasureUnitName2", m.get("fMeasureUnitName"));
				m.put("fUnitPrice2", m.get("fUnitPrice"));
				m.put("fQuantity2", m.get("fQuantity"));
				m.put("balance2", m.get("balance"));
				m.put("fMeasureUnitName", "");
				m.put("fUnitPrice", "");
				m.put("fQuantity", "");
				m.put("balance", "");
			}
			m.put("balance3", balance);
			VoList.add(m);
			m1 = m;
		}
		if(list.size() > 0){
			Map thisPeriod = new HashMap();
			Map m=list.get(list.size()-1);
			thisPeriod.put("fPeriod", m.get("fPeriod").toString());
			thisPeriod.put("fYear", m.get("fYear").toString());
			thisPeriod.put("Summary", "本期合计");
			thisPeriod.put("balance", debit);
			thisPeriod.put("balance2", credit);
			thisPeriod.put("balance3", balance);
			VoList.add(thisPeriod);
		}
		List<FlexItemPropertyDetailVo> flexItemPropertyDetailVoList = VoList.stream()
			.map(any -> BeanUtil.toBean(any, FlexItemPropertyDetailVo.class))
			.collect(Collectors.toList());
		TableDataInfo<FlexItemPropertyDetailVo> rspData = new TableDataInfo<>();
		rspData.setCode(HttpStatus.HTTP_OK);
		rspData.setMsg("查询成功");
		rspData.setRows(flexItemPropertyDetailVoList);
		rspData.setTotal(page.getTotal());
		return rspData;
	}

}
