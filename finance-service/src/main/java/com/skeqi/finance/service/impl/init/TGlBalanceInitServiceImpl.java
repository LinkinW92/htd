package com.skeqi.finance.service.impl.init;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.finance.domain.TGlBalanceInit;
import com.skeqi.finance.mapper.TGlBalanceInitMapper;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceInitAddBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceInitEditBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceInitQueryBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountVo;
import com.skeqi.finance.pojo.vo.book.TBdAccountBookVo;
import com.skeqi.finance.pojo.vo.init.TGlBalanceInitVo;
import com.skeqi.finance.service.account.ITBdAccountService;
import com.skeqi.finance.service.basicinformation.accountbook.ITBdAccountBookService;
import com.skeqi.finance.service.init.ITGlBalanceInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 科目初始录入数据Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TGlBalanceInitServiceImpl extends ServicePlusImpl<TGlBalanceInitMapper, TGlBalanceInit> implements ITGlBalanceInitService {

	@Override
	public TGlBalanceInitVo queryById(Integer fAccountBookId) {
		return getVoById(fAccountBookId, TGlBalanceInitVo.class);
	}

	@Override
	public TableDataInfo<TGlBalanceInitVo> queryPageList(TGlBalanceInitQueryBo bo) {
		if (bo.getPageNum() == null) bo.setPageNum(1);
		if (bo.getPageSize() == null) bo.setPageSize(10);
		IPage<TGlBalanceInitVo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		System.out.println("testestestest");
		Page<TGlBalanceInitVo> tGlBalanceInitVoPage = baseMapper.queryList2(page, bo);
		return PageUtils.buildDataInfo(tGlBalanceInitVoPage);
	}

	@Override
	public TableDataInfo<TGlBalanceInitVo> queryPageList2(TGlBalanceInitQueryBo bo) {
		if(bo.getFAccountId() == null){
			throw new CustomException("科目内码不能为空");
		}
		if (bo.getPageNum() == null) bo.setPageNum(1);
		if (bo.getPageSize() == null) bo.setPageSize(10);
		IPage<TGlBalanceInitVo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		Page<TGlBalanceInitVo> tGlBalanceInitVoPage = baseMapper.queryList3(page, bo);
		return PageUtils.buildDataInfo(tGlBalanceInitVoPage);
	}

	@Override
	public List<TGlBalanceInitVo> queryList(TGlBalanceInitQueryBo bo) {
		return listVo(buildQueryWrapper(bo), TGlBalanceInitVo.class);
	}

	private LambdaQueryWrapper<TGlBalanceInit> buildQueryWrapper(TGlBalanceInitQueryBo bo) {
		Map<String, Object> params = bo.getParams();
		LambdaQueryWrapper<TGlBalanceInit> lqw = Wrappers.lambdaQuery();
		lqw.eq(bo.getFAccountBookId() != null, TGlBalanceInit::getFAccountBookId, bo.getFAccountBookId());
		lqw.eq(bo.getFAccountId() != null, TGlBalanceInit::getFAccountId, bo.getFAccountId());
		lqw.eq(bo.getFCurrencyId() != null, TGlBalanceInit::getFCurrencyId, bo.getFCurrencyId());
		lqw.eq(bo.getFPeriod() != null, TGlBalanceInit::getFPeriod, bo.getFPeriod());
		lqw.eq(bo.getFYear()!=null, TGlBalanceInit::getFYear, bo.getFYear());
		lqw.eq(StrUtil.isNotBlank(bo.getFDc()), TGlBalanceInit::getFDc, bo.getFDc());
		lqw.eq(bo.getFBeginBalancefor() != null, TGlBalanceInit::getFBeginBalancefor, bo.getFBeginBalancefor());
		lqw.eq(bo.getFBeginBalance() != null, TGlBalanceInit::getFBeginBalance, bo.getFBeginBalance());
		lqw.eq(bo.getFDebitFor() != null, TGlBalanceInit::getFDebitFor, bo.getFDebitFor());
		lqw.eq(bo.getFDebit() != null, TGlBalanceInit::getFDebit, bo.getFDebit());
		lqw.eq(bo.getFCreditFor() != null, TGlBalanceInit::getFCreditFor, bo.getFCreditFor());
		lqw.eq(bo.getFCredit() != null, TGlBalanceInit::getFCredit, bo.getFCredit());
		lqw.eq(bo.getFYtdDebitfor() != null, TGlBalanceInit::getFYtdDebitfor, bo.getFYtdDebitfor());
		lqw.eq(bo.getFYtdDebit() != null, TGlBalanceInit::getFYtdDebit, bo.getFYtdDebit());
		lqw.eq(bo.getFYtdCreditfor() != null, TGlBalanceInit::getFYtdCreditfor, bo.getFYtdCreditfor());
		lqw.eq(bo.getFYtdCredit() != null, TGlBalanceInit::getFYtdCredit, bo.getFYtdCredit());
		lqw.eq(bo.getFEndBalancefor() != null, TGlBalanceInit::getFEndBalancefor, bo.getFEndBalancefor());
		lqw.eq(bo.getFEndBalance() != null, TGlBalanceInit::getFEndBalance, bo.getFEndBalance());
		lqw.eq(bo.getFAdjustPeriod() != null, TGlBalanceInit::getFAdjustPeriod, bo.getFAdjustPeriod());
		lqw.eq(bo.getFYearPeriod() != null, TGlBalanceInit::getFYearPeriod, bo.getFYearPeriod());
		return lqw;
	}

	@Override
	public Boolean insertByAddBo(TGlBalanceInitAddBo bo) {
		LambdaQueryWrapper<TGlBalanceInit> lqw = Wrappers.lambdaQuery();
		lqw.eq( TGlBalanceInit::getFAccountBookId, bo.getFAccountBookId());
		lqw.eq(TGlBalanceInit::getFAccountId, bo.getFAccountId());
		lqw.eq(bo.getDimensionCode()!=null,TGlBalanceInit::getDimensionCode, bo.getDimensionCode());
		lqw.eq(bo.getDimensionCode()!=null,TGlBalanceInit::getFDetailCode, bo.getFDetailCode());
		if(bo.getDimensionCode()==null){
			lqw.isNull(TGlBalanceInit::getDimensionCode);
			lqw.isNull(TGlBalanceInit::getFDetailCode);
		}
		TGlBalanceInit tGlBalanceInit = this.baseMapper.selectOne(lqw);
		if(tGlBalanceInit == null) {
			TGlBalanceInit add = BeanUtil.toBean(bo, TGlBalanceInit.class);
			validEntityBeforeSave(add);
			return save(add);
		}else{
			return updateByEditBo(BeanUtil.toBean(bo, TGlBalanceInitEditBo.class));
		}
	}

	@Override
	public Boolean updateByEditBo(TGlBalanceInitEditBo bo) {
		TGlBalanceInit update = BeanUtil.toBean(bo, TGlBalanceInit.class);
		validEntityBeforeSave(update);
		return updateById(update);
	}

	//账簿
	@Autowired
	private ITBdAccountBookService itBdAccountBookService;
	//科目信息
	@Autowired
	private ITBdAccountService itBdAccountService;
	/**
	 * 保存前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeSave(TGlBalanceInit entity) {
		//TODO 做一些数据校验,如唯一约束
		//账簿
		Integer accountBookId = entity.getFAccountBookId();
		TBdAccountBookVo tBdAccountBookVo = itBdAccountBookService.queryById(accountBookId);
		if(tBdAccountBookVo == null){
			throw new CustomException("账簿不存在");
		}
		entity.setFYear(tBdAccountBookVo.getFStartYear());
		entity.setFPeriod(tBdAccountBookVo.getFStartPeriod());
		//科目内码
		Integer accountId = entity.getFAccountId();
		TBdAccountVo tBdAccountVo = itBdAccountService.queryById(accountId);
		if(tBdAccountVo == null){
			throw new CustomException("科目不存在");
		}

	}

	@Override
	public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		if (isValid) {
			//TODO 做一些业务上的校验,判断是否需要校验
		}
		return removeByIds(ids);
	}

}
