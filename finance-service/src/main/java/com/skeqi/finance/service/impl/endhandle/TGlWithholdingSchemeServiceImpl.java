package com.skeqi.finance.service.impl.endhandle;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.common.utils.time.DateTimeCalculatorUtil;
import com.skeqi.finance.domain.TBdAccount;
import com.skeqi.finance.domain.TBdCurrency;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.domain.endhandle.withholding.*;
import com.skeqi.finance.domain.rate.TBdRateType;
import com.skeqi.finance.domain.voucher.TGlVoucher;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.enums.VchSourceEnum;
import com.skeqi.finance.mapper.endhandle.*;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.*;
import com.skeqi.finance.pojo.bo.voucher.AccountingDimensionBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryAddBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountFlexentryVo;
import com.skeqi.finance.pojo.vo.endhandle.*;
import com.skeqi.finance.service.basicinformation.base.ITBdCurrencyService;
import com.skeqi.finance.service.basicinformation.base.ITBdExecuteLogService;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherGroupNoService;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherService;
import com.skeqi.finance.service.rate.ITBdRateTypeService;
import com.skeqi.finance.service.account.ITBdAccountFlexentryService;
import com.skeqi.finance.service.account.ITBdAccountService;
import com.skeqi.finance.service.basicinformation.accountbook.ITBdAccountBookService;
import com.skeqi.finance.service.basicinformation.voucher.ITBdVoucherGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.service.endhandle.ITGlWithholdingSchemeService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 凭证预提Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Slf4j
@Service
public class TGlWithholdingSchemeServiceImpl extends ServicePlusImpl<TGlWithholdingSchemeMapper, TGlWithholdingScheme> implements ITGlWithholdingSchemeService {

	@Autowired
	ITBdAccountBookService itBdAccountBookService;
	@Autowired
	ITBdVoucherGroupService iTBdVoucherGroupService;
	@Autowired
	ITBdCurrencyService iTBdCurrencyService;
	@Autowired
	ITBdRateTypeService iTBdRateTypeService;
	@Autowired
	ITBdAccountService iTBdAccountService;
	@Autowired
	ITBdAccountFlexentryService itBdAccountFlexentryService;

	@Autowired
	TGlWithholdingSchemeMapper tGlWithholdingSchemeMapper;
	@Autowired
	TGlWithholdingPeriodMapper tGlWithholdingPeriodMapper;
	@Autowired
	TGlWithholdingInacctMapper tGlWithholdingInacctMapper;
	@Autowired
	TGlWithholdingInacctDimensionMapper tGlWithholdingInacctDimensionMapper;
	@Autowired
	TGlWithholdingAcctMapper tGlWithholdingAcctMapper;
	@Autowired
	TGlWithholdingAcctDimensionMapper tGlWithholdingAcctDimensionMapper;

	@Autowired
	ITGlVoucherService iTGlVoucherService;
	@Autowired
	ITGlVoucherGroupNoService iTGlVoucherGroupNoService;
	@Autowired
	ITBdExecuteLogService iTBdExecuteLogService;

    @Override
    public TGlWithholdingSchemeVo queryById(Integer fSchemeId){
//        return getVoById(fSchemeId, TGlWithholdingSchemeVo.class);
		TGlWithholdingSchemeVo vo=tGlWithholdingSchemeMapper.getInfo(fSchemeId);
		if(null==vo){
			return null;
		}
		List<TGlWithholdingAcctVo> amortAcctVo=tGlWithholdingAcctMapper.queryList(vo.getFSchemeId());
		amortAcctVo.forEach(v->{
			List<TGlWithholdingAcctDimension> list=tGlWithholdingAcctDimensionMapper.findByEntryId(v.getFId());
			v.setDimensions(list);
		});
		vo.setAcctVos(amortAcctVo);
		List<TGlWithholdingInacctVo> inacctVos=tGlWithholdingInacctMapper.queryList(vo.getFSchemeId());
		inacctVos.forEach(v->{
			List<TGlWithholdingInacctDimension> list= tGlWithholdingInacctDimensionMapper.findByEntryId(v.getFId());
			v.setDimensions(list);
		});
		vo.setInacctVos(inacctVos);
		//期间
		List<TGlWithholdingPeriodVo> periodVos =tGlWithholdingPeriodMapper.queryList(vo.getFSchemeId());
		vo.setPeriodVos(periodVos);
		return vo;
    }

    @Override
    public TableDataInfo<TGlWithholdingSchemeVo> queryPageList(TGlWithholdingSchemeQueryBo bo) {
		Page<TGlWithholdingSchemeQueryBo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TGlWithholdingSchemeVo> iPage = tGlWithholdingSchemeMapper.queryPageList(page, bo);
		return PageUtils.buildDataInfo(iPage);
    }

    @Override
    public List<TGlWithholdingSchemeVo> queryList(TGlWithholdingSchemeQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlWithholdingSchemeVo.class);
    }

    private LambdaQueryWrapper<TGlWithholdingScheme> buildQueryWrapper(TGlWithholdingSchemeQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlWithholdingScheme> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TGlWithholdingScheme::getFNumber, bo.getFNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsamort()), TGlWithholdingScheme::getFIsamort, bo.getFIsamort());
        lqw.eq(bo.getFAccountBookId() != null, TGlWithholdingScheme::getFAccountBookId, bo.getFAccountBookId());
        lqw.eq(bo.getFVoucherGroupId() != null, TGlWithholdingScheme::getFVoucherGroupId, bo.getFVoucherGroupId());
        lqw.eq(bo.getFCurrencyId() != null, TGlWithholdingScheme::getFCurrencyId, bo.getFCurrencyId());
        lqw.eq(bo.getFExchangeRateType() != null, TGlWithholdingScheme::getFExchangeRateType, bo.getFExchangeRateType());
        lqw.eq(StrUtil.isNotBlank(bo.getFExplanation()), TGlWithholdingScheme::getFExplanation, bo.getFExplanation());
        lqw.eq(bo.getFPeddingDrawAmount() != null, TGlWithholdingScheme::getFPeddingDrawAmount, bo.getFPeddingDrawAmount());
        lqw.eq(bo.getFAmortizedAmount() != null, TGlWithholdingScheme::getFAmortizedAmount, bo.getFAmortizedAmount());
        lqw.eq(bo.getFEndBalance() != null, TGlWithholdingScheme::getFEndBalance, bo.getFEndBalance());
        lqw.eq(bo.getFLastExecuteTime() != null, TGlWithholdingScheme::getFLastExecuteTime, bo.getFLastExecuteTime());
        lqw.eq(StrUtil.isNotBlank(bo.getFStatus()), TGlWithholdingScheme::getFStatus, bo.getFStatus());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TGlWithholdingScheme::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderId() != null, TGlWithholdingScheme::getFForbidderId, bo.getFForbidderId());
        lqw.eq(bo.getFForbidDate() != null, TGlWithholdingScheme::getFForbidDate, bo.getFForbidDate());
        return lqw;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertByAddBo(TGlWithholdingSchemeAddBo bo) {
		Map map=validEntityBeforeSave(bo);
		TGlWithholdingScheme add = BeanUtil.toBean(bo, TGlWithholdingScheme.class);
		add.setFPeddingDrawAmount(new BigDecimal(map.get("sumPedMoney").toString()));
		add.setFAmortizedAmount(BigDecimal.ZERO);
		add.setFEndBalance(add.getFPeddingDrawAmount());
		this.save(add);
		//新增待摊销数据
		List<TGlWithholdingAcctAddBo> amortAcct=bo.getAmortAcct();
		amortAcct.forEach(v->{
			v.setFSchemeId(add.getFSchemeId());
			TGlWithholdingAcct acct=new TGlWithholdingAcct();
			BeanUtil.copyProperties(v,acct);
			tGlWithholdingAcctMapper.insert(acct);
			//新增维度数据
			List<TGlWithholdingAcctDimension> dl=new ArrayList<>();
			v.getAcctDimension().forEach(v1->{
				TGlWithholdingAcctDimension d=new TGlWithholdingAcctDimension();
				BeanUtil.copyProperties(v1,d);
				d.setAcctId(v.getFProvisionAccount());
				d.setAmortEntryId(acct.getFId());
				dl.add(d);
			});
			tGlWithholdingAcctDimensionMapper.insertAll(dl);
		});
		//新增转入科目数据
		List<TGlWithholdingInacctAddBo> amortInAcct=bo.getAmortInAcct();
		amortInAcct.forEach(v->{
			TGlWithholdingInacct inacct=new TGlWithholdingInacct();
			BeanUtil.copyProperties(v,inacct);
			inacct.setFSchemeId(add.getFSchemeId());
			tGlWithholdingInacctMapper.insert(inacct);
			//新增维度数据
			List<TGlWithholdingInacctDimension> dl=new ArrayList<>();
			v.getInacctDimension().forEach(v1->{
				TGlWithholdingInacctDimension d=new TGlWithholdingInacctDimension();
				BeanUtil.copyProperties(v1,d);
				d.setAcctId(v.getFEnterAccountId());
				d.setAmortEntryId(inacct.getFId());
				dl.add(d);
			});
			tGlWithholdingInacctDimensionMapper.insertAll(dl);
		});
		//新增期间数据
		List<TGlWithholdingPeriodAddBo> amortPeriod=bo.getAmortPeriod();
		amortPeriod.forEach(v->{
			TGlWithholdingPeriod period=new TGlWithholdingPeriod();
			BeanUtil.copyProperties(v,period);
			period.setFSchemeId(add.getFSchemeId());
			tGlWithholdingPeriodMapper.insert(period);
		});
		return true;
    }

    @Override
    public Boolean updateByEditBo(TGlWithholdingSchemeAddBo bo) {
    	if(null==bo.getFSchemeId()){
			throw new CustomException("记录ID不能为空");
		}
		TGlWithholdingScheme old = this.getById(bo.getFSchemeId());
		if(null==old){
			throw new CustomException("记录不存在");
		}
		Map map=validEntityBeforeSave(bo);
		BeanUtil.copyProperties(bo,old);
		old.setFPeddingDrawAmount(new BigDecimal(map.get("sumPedMoney").toString()));
		old.setFAmortizedAmount(BigDecimal.ZERO);
		old.setFEndBalance(old.getFPeddingDrawAmount());
		this.updateById(old);
		//新增待摊销数据
		List<TGlWithholdingAcctAddBo> amortAcct=bo.getAmortAcct();
		tGlWithholdingAcctMapper.delete(new LambdaQueryWrapper<TGlWithholdingAcct>().eq(TGlWithholdingAcct::getFSchemeId,old.getFSchemeId()));
		amortAcct.forEach(v->{
			v.setFSchemeId(old.getFSchemeId());
			TGlWithholdingAcct acct=new TGlWithholdingAcct();
			BeanUtil.copyProperties(v,acct);
			tGlWithholdingAcctMapper.insert(acct);
			//新增维度数据
			List<TGlWithholdingAcctDimension> dl=new ArrayList<>();
			v.getAcctDimension().forEach(v1->{
				TGlWithholdingAcctDimension d=new TGlWithholdingAcctDimension();
				BeanUtil.copyProperties(v1,d);
				d.setAcctId(v.getFProvisionAccount());
				d.setAmortEntryId(acct.getFId());
				dl.add(d);
			});
			tGlWithholdingAcctDimensionMapper.insertAll(dl);
		});
		//新增转入科目数据
		List<TGlWithholdingInacctAddBo> amortInAcct=bo.getAmortInAcct();
		tGlWithholdingInacctMapper.delete(new LambdaQueryWrapper<TGlWithholdingInacct>().eq(TGlWithholdingInacct::getFSchemeId,old.getFSchemeId()));
		amortInAcct.forEach(v->{
			TGlWithholdingInacct inacct=new TGlWithholdingInacct();
			BeanUtil.copyProperties(v,inacct);
			inacct.setFSchemeId(old.getFSchemeId());
			tGlWithholdingInacctMapper.insert(inacct);
			//新增维度数据
			List<TGlWithholdingInacctDimension> dl=new ArrayList<>();
			v.getInacctDimension().forEach(v1->{
				TGlWithholdingInacctDimension d=new TGlWithholdingInacctDimension();
				BeanUtil.copyProperties(v1,d);
				d.setAcctId(v.getFEnterAccountId());
				d.setAmortEntryId(inacct.getFId());
				dl.add(d);
			});
			tGlWithholdingInacctDimensionMapper.insertAll(dl);
		});
		//新增期间数据
		List<TGlWithholdingPeriodAddBo> amortPeriod=bo.getAmortPeriod();
		tGlWithholdingPeriodMapper.delete(new LambdaQueryWrapper<TGlWithholdingPeriod>().eq(TGlWithholdingPeriod::getFSchemeId,old.getFSchemeId()));
		amortPeriod.forEach(v->{
			TGlWithholdingPeriod period=new TGlWithholdingPeriod();
			BeanUtil.copyProperties(v,period);
			period.setFSchemeId(old.getFSchemeId());
			tGlWithholdingPeriodMapper.insert(period);
		});
		return true;
    }

	/**
	 * 执行
	 * @param fId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResult execute(Integer fId){
		TGlWithholdingSchemeVo vo=this.queryById(fId);
		if(null==vo){
          throw new CustomException("执行记录不存在");
		}
		if(BaseEnum.YES.getCode().equals(vo.getFForbidStatus())){
			throw new CustomException("执行记录被禁用，执行失败");
		}
		TBdAccountBook book=itBdAccountBookService.getById(vo.getFAccountBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("账簿不存在",1000);
		}
		List<TGlWithholdingPeriodVo> periodVos=vo.getPeriodVos();
		Optional<TGlWithholdingPeriodVo> item=periodVos.stream().filter(v->(v.getFYear().equals(book.getFCurrentYear()) && v.getFPeriod().equals(book.getFCurrentPeriod()))).findFirst();
		if(!item.isPresent()){
			throw new CustomException("预提期间不在账簿当前期间内，生成失败");
		}
		TGlWithholdingPeriodVo periodVo=item.get();
		TGlVoucherAddBo bo=new TGlVoucherAddBo();
		bo.setFAccountBookId(book.getFBookId());
		bo.setFAcctOrgid(book.getFAccountOrgid().intValue());
		bo.setFDate(DateTimeCalculatorUtil.getDateEndOfMonth(book.getFCurrentYear(),book.getFCurrentPeriod().intValue()));
		bo.setFAttachments(0);
		bo.setFBaseCurrencyId(book.getFCurrencyId().intValue());
		bo.setFInternalind("3");
		bo.setFVoucherGroupId(vo.getFVoucherGroupId());
		TGlVoucherGroupNoQueryBo groupNoQueryBo=new TGlVoucherGroupNoQueryBo();
		groupNoQueryBo.setFBookId(book.getFBookId());
		groupNoQueryBo.setFPeriod(book.getFCurrentPeriod().intValue());
		groupNoQueryBo.setFYear(book.getFCurrentYear());
		groupNoQueryBo.setFVoucherGroupId(vo.getFVoucherGroupId());
		List<Integer> idList=iTGlVoucherGroupNoService.listVchNo(groupNoQueryBo);
		bo.setFVoucherGroupNo(idList.get(0));
		TGlVoucherGroupNoAddBo noAddBo=new TGlVoucherGroupNoAddBo();
		BeanUtils.copyProperties(groupNoQueryBo,noAddBo);
		noAddBo.setFVoucherGroupNo(bo.getFVoucherGroupNo());
		bo.setFDocumentStatus(DataStatusEnum.TMP_SAVE.getCode());
		iTGlVoucherGroupNoService.insertByAddBo(noAddBo);
		List<TGlWithholdingAcctVo> acctVos=vo.getAcctVos();
		List<TGlWithholdingInacctVo> inacctVos=vo.getInacctVos();
		List<TGlVoucherEntryAddBo> vchE = new ArrayList<>();
		//贷方
		acctVos.forEach(v->{
			TGlVoucherEntryAddBo addBo=new TGlVoucherEntryAddBo();
			addBo.setFExplanation(vo.getFExplanation());
			addBo.setFAccountId(v.getFProvisionAccount());
			addBo.setFCurrencyId(vo.getFCurrencyId());
			addBo.setFExchangeRateType(vo.getFExchangeRateType());
			addBo.setFAmountfor(periodVo.getFAmortAmount().multiply(v.getFProvisionRatio()).setScale(2,BigDecimal.ROUND_HALF_UP));
			addBo.setFCredit(periodVo.getFAmortAmount().multiply(v.getFProvisionRatio()).setScale(2,BigDecimal.ROUND_HALF_UP));
			//核算维度
			if(CollectionUtil.isNotEmpty(v.getDimensions())){
				List<AccountingDimensionBo> dimensionInfo=new ArrayList<>();
				v.getDimensions().forEach(b->{
					AccountingDimensionBo d=new AccountingDimensionBo();
					d.setAcctDimsId(b.getDimensionId().intValue());
					d.setDetailCode(b.getDsCode());
					d.setDetailName(b.getDsName());
					dimensionInfo.add(d);
				});
				addBo.setDimensionInfo(dimensionInfo);
			}
			vchE.add(addBo);
		});
		//借方
		inacctVos.forEach(v->{
			TGlVoucherEntryAddBo addBo=new TGlVoucherEntryAddBo();
			addBo.setFExplanation(vo.getFExplanation());
			addBo.setFAccountId(v.getFEnterAccountId());
			addBo.setFCurrencyId(vo.getFCurrencyId());
			addBo.setFExchangeRateType(vo.getFExchangeRateType());
			addBo.setFAmountfor(periodVo.getFAmortAmount().multiply(v.getFEnterRatio()).setScale(2,BigDecimal.ROUND_HALF_UP));
			addBo.setFCredit(periodVo.getFAmortAmount().multiply(v.getFEnterRatio()).setScale(2,BigDecimal.ROUND_HALF_UP));
			//核算维度
			if(CollectionUtil.isNotEmpty(v.getDimensions())){
				List<AccountingDimensionBo> dimensionInfo=new ArrayList<>();
				v.getDimensions().forEach(b->{
					AccountingDimensionBo d=new AccountingDimensionBo();
					d.setAcctDimsId(b.getDimensionId().intValue());
					d.setDetailCode(b.getDsCode());
					d.setDetailName(b.getDsName());
					dimensionInfo.add(d);
				});
				addBo.setDimensionInfo(dimensionInfo);
			}
			vchE.add(addBo);
		});
		try {
			bo.setFSourceBillKey(VchSourceEnum.WITHHOLDING.getCode());
			AjaxResult<TGlVoucher> result=iTGlVoucherService.insertByAddBo(bo);
			//增加日志记录
			TBdExecuteLogAddBo logAddBo=new TBdExecuteLogAddBo();
			logAddBo.setCreateTime(new Date());
			logAddBo.setExecuteStatus(BaseEnum.YES.getCode());
			logAddBo.setOutExecuteId(result.getData().getFVoucherId());
			logAddBo.setExecuteDetail("【预提】生成凭证-"+bo.getFVoucherGroupNo());
			logAddBo.setExecuteId(vo.getFSchemeId());
			logAddBo.setExecuteType(VchSourceEnum.WITHHOLDING.getCode());
			iTBdExecuteLogService.insertByAddBo(logAddBo);
		}catch (Exception e){
			log.error("【预提调用凭证新增异常】异常信息[{}]",e.getMessage());
			throw new CustomException("执行生成凭证异常");
		}
		return AjaxResult.success();
	}

    /**
     * 保存前的数据校验
     *
     * @param bo 实体类数据
     */
    private Map<String, Object> validEntityBeforeSave(TGlWithholdingSchemeAddBo bo){
		Map<String,Object> map=new HashMap<>();
		TBdAccountBook book=itBdAccountBookService.getById(bo.getFAccountBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("账簿不存在");
		}
		TBdVoucherGroup voucherGroup=iTBdVoucherGroupService.getById(bo.getFVoucherGroupId());
		if(null==voucherGroup || !DataStatusEnum.AUDIT.getCode().equals(voucherGroup.getFDocumentStatus())){
			throw new CustomException("凭证字不存在");
		}
		TBdCurrency currency=iTBdCurrencyService.getById(bo.getFCurrencyId());
		if(null==currency || !DataStatusEnum.AUDIT.getCode().equals(currency.getFDocumentStatus())){
			throw new CustomException("币别不存在");
		}
		TBdRateType rateType=iTBdRateTypeService.getById(bo.getFExchangeRateType());
		if(null==rateType || !DataStatusEnum.AUDIT.getCode().equals(rateType.getFDocumentStatus())){
			throw new CustomException("汇率类型不存在");
		}
		//验证预提科目
		List<TGlWithholdingAcctAddBo> amortAcctList=bo.getAmortAcct();
		if(CollectionUtil.isEmpty(amortAcctList)){
			throw new CustomException("预提科目不能为空");
		}
		//预提比例
		BigDecimal sumPedRatio=BigDecimal.ZERO;
		for (int i=1;i<=amortAcctList.size();i++){
			TGlWithholdingAcctAddBo acctBo=amortAcctList.get(i-1);
			TBdAccount account=iTBdAccountService.getById(acctBo.getFProvisionAccount());
			if(null==account || !DataStatusEnum.AUDIT.getCode().equals(account.getFDocumentStatus())){
				throw new CustomException("预提科目第"+i+"行科目信息不存在",1000);
			}

			//核算维度校验
			List<TGlWithholdingAcctDimensionAddBo> dL=acctBo.getAcctDimension();
			Map<Integer,List<TGlWithholdingAcctDimensionAddBo>> tmp1=new HashMap<>();
			if(!CollectionUtil.isEmpty(dL)){
				tmp1=dL.stream().filter(v->(StrUtil.isNotBlank(v.getDsCode()))).collect(Collectors.groupingBy(TGlWithholdingAcctDimensionAddBo::getDimensionId));
			}
			Map<Integer,List<TGlWithholdingAcctDimensionAddBo>> map1=tmp1;
			//科目维度控制表
			List<TBdAccountFlexentryVo> fList=itBdAccountFlexentryService.queryByAcctId(account.getFAcctId().intValue());
			if(!CollectionUtil.isEmpty(fList)){
				boolean op=fList.stream().filter(v->(
						"1".equals(v.getFInputType()) && (CollectionUtil.isEmpty(map1) || !map1.containsKey(v.getFFlexitempropertyId()))
					)
				).findFirst().isPresent();
				if(op){
					throw new CustomException("预提科目第"+i+"行核算维度必录项不能为空",1000);
				}
			}
			sumPedRatio=sumPedRatio.add(acctBo.getFProvisionRatio());
		}
		if(sumPedRatio.compareTo(new BigDecimal(100))!=0){
			throw new CustomException("预提科目比例不等于100%");
		}
		//验证预提期间
		List<TGlWithholdingPeriodAddBo> amortPeriodList=bo.getAmortPeriod();
		if(CollectionUtil.isEmpty(amortPeriodList)){
			throw new CustomException("预提期间不能为空");
		}
		//期间摊销总额 和 总比例
		BigDecimal sumPedMoney=BigDecimal.ZERO;
		for (int i=1;i<=amortPeriodList.size();i++){
			TGlWithholdingPeriodAddBo periodAddBo=amortPeriodList.get(i-1);
			//会计期间校验
			sumPedMoney=sumPedMoney.add(periodAddBo.getFAmortAmount());
		}



		//验证预提转入
		List<TGlWithholdingInacctAddBo> amortInAcctList=bo.getAmortInAcct();
		if(CollectionUtil.isEmpty(amortInAcctList)){
			throw new CustomException("转入科目不能为空");
		}
		//转入总比例
		BigDecimal sumRatio=BigDecimal.ZERO;
		for (int i=1;i<=amortInAcctList.size();i++){
			TGlWithholdingInacctAddBo inacctAddBo=amortInAcctList.get(i-1);
			TBdAccount account=iTBdAccountService.getById(inacctAddBo.getFEnterAccountId());
			if(null==account || !DataStatusEnum.AUDIT.getCode().equals(account.getFDocumentStatus())){
				throw new CustomException("转入科目第"+i+"行科目信息不存在",1000);
			}

			//核算维度校验
			List<TGlWithholdingInacctDimensionAddBo> dL=inacctAddBo.getInacctDimension();
			Map<Integer,List<TGlWithholdingInacctDimensionAddBo>> tmp2=new HashMap<>();
			if(!CollectionUtil.isEmpty(dL)){
				tmp2=dL.stream().filter(v->(StrUtil.isNotBlank(v.getDsCode()))).collect(Collectors.groupingBy(TGlWithholdingInacctDimensionAddBo::getDimensionId));
			}
			Map<Integer,List<TGlWithholdingInacctDimensionAddBo>> map2=tmp2;
			//科目维度控制表
			List<TBdAccountFlexentryVo> fList=itBdAccountFlexentryService.queryByAcctId(account.getFAcctId().intValue());
			if(!CollectionUtil.isEmpty(fList)){
				boolean op=fList.stream().filter(v->(
						"1".equals(v.getFInputType()) && (CollectionUtil.isEmpty(map2) || !map2.containsKey(v.getFFlexitempropertyId()))
					)
				).findFirst().isPresent();
				if(op){
					throw new CustomException("转入科目第"+i+"行核算维度必录项不能为空",1000);
				}
			}
			sumRatio=sumRatio.add(inacctAddBo.getFEnterRatio());
		}
		if(sumRatio.compareTo(new BigDecimal(100))!=0){
			throw new CustomException("转入科目总比例不能超过100%");
		}
		map.put("sumPedMoney",sumPedMoney);
		return map;
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }



}
