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
import com.skeqi.finance.domain.endhandle.amortization.*;
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
import com.skeqi.finance.service.endhandle.ITGlAmortizationSchemeService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ????????????Service???????????????
 *
 * @author toms
 * @date 2021-07-27
 */
@Slf4j
@Service
public class TGlAmortizationSchemeServiceImpl extends ServicePlusImpl<TGlAmortizationSchemeMapper, TGlAmortizationScheme> implements ITGlAmortizationSchemeService {

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

	//??????
	@Autowired
	TGlAmortAcctMapper tGlAmortAcctMapper;
	@Autowired
	TGlAmortAcctDimensionMapper tGlAmortAcctDimensionMapper;
	@Autowired
	TGlAmortInacctMapper tGlAmortInacctMapper;
	@Autowired
	TGlAmortInacctDimensionMapper tGlAmortInacctDimensionMapper;
	@Autowired
	TGlAmortPeriodMapper tGlAmortPeriodMapper;
	@Autowired
	TGlAmortizationSchemeMapper tGlAmortizationSchemeMapper;

	@Autowired
	ITGlVoucherService iTGlVoucherService;
	@Autowired
	ITGlVoucherGroupNoService iTGlVoucherGroupNoService;
	@Autowired
	ITBdExecuteLogService iTBdExecuteLogService;

    @Override
    public TGlAmortizationSchemeVo queryById(Integer fSchemeId){
		TGlAmortizationSchemeVo vo=tGlAmortizationSchemeMapper.getInfo(fSchemeId);
		if(null==vo){
			return null;
		}
		List<TGlAmortAcctVo> amortAcctVo=tGlAmortAcctMapper.queryList(vo.getFSchemeId());
		amortAcctVo.forEach(v->{
			List<TGlAmortAcctDimensionVo> vl=tGlAmortAcctDimensionMapper.findByEntryId(v.getFId());
			v.setAcctDimension(vl);
		});
		vo.setAcctVos(amortAcctVo);
		List<TGlAmortInacctVo> inacctVos=tGlAmortInacctMapper.queryList(vo.getFSchemeId());
		inacctVos.forEach(v->{
			List<TGlAmortInacctDimensionVo> vl=tGlAmortInacctDimensionMapper.findByEntryId(v.getFId());
			v.setInacctDimension(vl);
		});
		vo.setInacctVos(inacctVos);
		//??????
		List<TGlAmortPeriodVo> periodVos =tGlAmortPeriodMapper.queryList(vo.getFSchemeId());
		vo.setPeriodVos(periodVos);
        return vo;
    }

    @Override
    public TableDataInfo<TGlAmortizationSchemeVo> queryPageList(TGlAmortizationSchemeQueryBo bo) {
		Page<TGlAmortizationSchemeQueryBo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TGlAmortizationSchemeVo> iPage = tGlAmortizationSchemeMapper.queryPageList(page, bo);
		return PageUtils.buildDataInfo(iPage);
    }

    @Override
    public List<TGlAmortizationSchemeVo> queryList(TGlAmortizationSchemeQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlAmortizationSchemeVo.class);
    }

    private LambdaQueryWrapper<TGlAmortizationScheme> buildQueryWrapper(TGlAmortizationSchemeQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlAmortizationScheme> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TGlAmortizationScheme::getFNumber, bo.getFNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsamort()), TGlAmortizationScheme::getFIsamort, bo.getFIsamort());
        lqw.eq(bo.getFAccountBookId() != null, TGlAmortizationScheme::getFAccountBookId, bo.getFAccountBookId());
        lqw.eq(bo.getFVoucherGroupId() != null, TGlAmortizationScheme::getFVoucherGroupId, bo.getFVoucherGroupId());
        lqw.eq(bo.getFCurrencyId() != null, TGlAmortizationScheme::getFCurrencyId, bo.getFCurrencyId());
        lqw.eq(bo.getFExchangeRateType() != null, TGlAmortizationScheme::getFExchangeRateType, bo.getFExchangeRateType());
        lqw.eq(StrUtil.isNotBlank(bo.getFExplanation()), TGlAmortizationScheme::getFExplanation, bo.getFExplanation());
        lqw.eq(bo.getFPeddingAmortAmount() != null, TGlAmortizationScheme::getFPeddingAmortAmount, bo.getFPeddingAmortAmount());
        lqw.eq(bo.getFAmortedAmount() != null, TGlAmortizationScheme::getFAmortedAmount, bo.getFAmortedAmount());
        lqw.eq(bo.getFEndBalance() != null, TGlAmortizationScheme::getFEndBalance, bo.getFEndBalance());
        lqw.eq(bo.getFLastExecuteTime() != null, TGlAmortizationScheme::getFLastExecuteTime, bo.getFLastExecuteTime());
        lqw.eq(StrUtil.isNotBlank(bo.getFStatus()), TGlAmortizationScheme::getFStatus, bo.getFStatus());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TGlAmortizationScheme::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderId() != null, TGlAmortizationScheme::getFForbidderId, bo.getFForbidderId());
        lqw.eq(bo.getFForbidDate() != null, TGlAmortizationScheme::getFForbidDate, bo.getFForbidDate());
        return lqw;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertByAddBo(TGlAmortizationSchemeAddBo bo) {
        Map map=validEntityBeforeSave(bo);
		TGlAmortizationScheme add = BeanUtil.toBean(bo, TGlAmortizationScheme.class);
		add.setFPeddingAmortAmount(new BigDecimal(map.get("sumPedMoney").toString()));
		add.setFAmortedAmount(BigDecimal.ZERO);
		add.setFEndBalance(add.getFPeddingAmortAmount());
		this.save(add);
		//?????????????????????
		List<TGlAmortAcctAddBo> amortAcct=bo.getAmortAcct();
		amortAcct.forEach(v->{
			v.setFSchemeId(add.getFSchemeId());
			TGlAmortAcct acct=new TGlAmortAcct();
			BeanUtil.copyProperties(v,acct);
			tGlAmortAcctMapper.insert(acct);
			//??????????????????
			if(CollectionUtil.isNotEmpty(v.getAcctDimension())){
				List<TGlAmortAcctDimension> dl=new ArrayList<>();
				v.getAcctDimension().forEach(v1->{
					TGlAmortAcctDimension d=new TGlAmortAcctDimension();
					BeanUtil.copyProperties(v1,d);
					d.setAcctId(v.getFAmortizeAccount());
					d.setAmortEntryId(acct.getFId());
					dl.add(d);
				});
				tGlAmortAcctDimensionMapper.insertAll(dl);
			}
		});
		//????????????????????????
		List<TGlAmortInacctAddBo> amortInAcct=bo.getAmortInAcct();
		amortInAcct.forEach(v->{
			TGlAmortInacct inacct=new TGlAmortInacct();
			BeanUtil.copyProperties(v,inacct);
			inacct.setFSchemeId(add.getFSchemeId());
			tGlAmortInacctMapper.insert(inacct);
			//??????????????????
			if(CollectionUtil.isNotEmpty(v.getInacctDimension())){
				tGlAmortInacctDimensionMapper.delByEntryId(inacct.getFId());
				List<TGlAmortInacctDimension> dl=new ArrayList<>();
				v.getInacctDimension().forEach(v1->{
					TGlAmortInacctDimension d=new TGlAmortInacctDimension();
					BeanUtil.copyProperties(v1,d);
					d.setAcctId(v.getFEnterAccountId());
					d.setAmortEntryId(inacct.getFId());
					dl.add(d);
				});
				tGlAmortInacctDimensionMapper.insertAll(dl);
			}
		});
		//??????????????????
		List<TGlAmortPeriodAddBo> amortPeriod=bo.getAmortPeriod();
		amortPeriod.forEach(v->{
			TGlAmortPeriod period=new TGlAmortPeriod();
			BeanUtil.copyProperties(v,period);
			period.setFSchemeId(add.getFSchemeId());
			tGlAmortPeriodMapper.insert(period);
		});
        return true;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Boolean updateByEditBo(TGlAmortizationSchemeEditBo bo) {
		TGlAmortizationScheme old=this.getById(bo.getFSchemeId());
		if(null==old){
			throw new CustomException("???????????????");
		}
		TGlAmortizationSchemeAddBo addBo = BeanUtil.toBean(bo, TGlAmortizationSchemeAddBo.class);
        validEntityBeforeSave(addBo);
        BeanUtil.copyProperties(bo,old);
		this.updateById(old);
        //?????????????????????
		List<TGlAmortAcctAddBo> amortAcct=bo.getAmortAcct();
		tGlAmortAcctMapper.delete(new LambdaQueryWrapper<TGlAmortAcct>().eq(TGlAmortAcct::getFSchemeId,old.getFSchemeId()));
		amortAcct.forEach(v->{
			v.setFSchemeId(old.getFSchemeId());
			TGlAmortAcct acct=new TGlAmortAcct();
			BeanUtil.copyProperties(v,acct);
			tGlAmortAcctMapper.insert(acct);
			//??????????????????
			if(CollectionUtil.isNotEmpty(v.getAcctDimension())){
				tGlAmortAcctDimensionMapper.delByAmortEntryId(acct.getFId());
				List<TGlAmortAcctDimension> dl=new ArrayList<>();
				v.getAcctDimension().forEach(v1->{
					TGlAmortAcctDimension d=new TGlAmortAcctDimension();
					BeanUtil.copyProperties(v1,d);
					d.setAcctId(v.getFAmortizeAccount());
					d.setAmortEntryId(acct.getFId());
					dl.add(d);
				});
				tGlAmortAcctDimensionMapper.insertAll(dl);
			}
		});
		//????????????????????????
		List<TGlAmortInacctAddBo> amortInAcct=bo.getAmortInAcct();
		tGlAmortInacctMapper.delete(new LambdaQueryWrapper<TGlAmortInacct>().eq(TGlAmortInacct::getFSchemeId,old.getFSchemeId()));
		amortInAcct.forEach(v->{
			TGlAmortInacct inacct=new TGlAmortInacct();
			BeanUtil.copyProperties(v,inacct);
			inacct.setFSchemeId(old.getFSchemeId());
			tGlAmortInacctMapper.insert(inacct);
			//??????????????????
			if(CollectionUtil.isNotEmpty(v.getInacctDimension())){
				List<TGlAmortInacctDimension> dl=new ArrayList<>();
				v.getInacctDimension().forEach(v1->{
					TGlAmortInacctDimension d=new TGlAmortInacctDimension();
					BeanUtil.copyProperties(v1,d);
					d.setAcctId(v.getFEnterAccountId());
					d.setAmortEntryId(inacct.getFId());
					dl.add(d);
				});
				tGlAmortInacctDimensionMapper.insertAll(dl);
			}
		});
		//??????????????????
		List<TGlAmortPeriodAddBo> amortPeriod=bo.getAmortPeriod();
		tGlAmortPeriodMapper.delBySchemeId(old.getFSchemeId());
		amortPeriod.forEach(v->{
			TGlAmortPeriod period=new TGlAmortPeriod();
			BeanUtil.copyProperties(v,period);
			period.setFSchemeId(old.getFSchemeId());
			tGlAmortPeriodMapper.insert(period);
		});
        return true;
    }

    /**
     * ????????????????????????
     *
     * @param bo ???????????????
     */
    private Map<String,Object> validEntityBeforeSave(TGlAmortizationSchemeAddBo bo){
		Map<String,Object> map=new HashMap<>();
		TBdAccountBook book=itBdAccountBookService.getById(bo.getFAccountBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("???????????????");
		}
		TBdVoucherGroup voucherGroup=iTBdVoucherGroupService.getById(bo.getFVoucherGroupId());
		if(null==voucherGroup || !DataStatusEnum.AUDIT.getCode().equals(voucherGroup.getFDocumentStatus())){
			throw new CustomException("??????????????????");
		}
		TBdCurrency currency=iTBdCurrencyService.getById(bo.getFCurrencyId());
		if(null==currency || !DataStatusEnum.AUDIT.getCode().equals(currency.getFDocumentStatus())){
			throw new CustomException("???????????????");
		}
		TBdRateType rateType=iTBdRateTypeService.getById(bo.getFExchangeRateType());
		if(null==rateType || !DataStatusEnum.AUDIT.getCode().equals(rateType.getFDocumentStatus())){
			throw new CustomException("?????????????????????");
		}
        //???????????????
		List<TGlAmortAcctAddBo> amortAcctList=bo.getAmortAcct();
		if(CollectionUtil.isEmpty(amortAcctList)){
			throw new CustomException("???????????????????????????");
		}
		if(amortAcctList.size()>1){
			throw new CustomException("???????????????????????????1???");
		}
		//???????????????
		BigDecimal sumPedMoney=BigDecimal.ZERO;
		for (int i=1;i<=amortAcctList.size();i++){
			TGlAmortAcctAddBo acctBo=amortAcctList.get(i-1);
			TBdAccount account=iTBdAccountService.getById(acctBo.getFAmortizeAccount());
			if(null==account || !DataStatusEnum.AUDIT.getCode().equals(account.getFDocumentStatus())){
				throw new CustomException("??????????????????"+i+"????????????????????????",1000);
			}

			//??????????????????
			List<TGlAmortAcctDimensionAddBo> dL=acctBo.getAcctDimension();
			Map<Integer,List<TGlAmortAcctDimensionAddBo>> tmp1=new HashMap<>();
			if(!CollectionUtil.isEmpty(dL)){
				tmp1=dL.stream().filter(v->(StrUtil.isNotBlank(v.getDsCode()))).collect(Collectors.groupingBy(TGlAmortAcctDimensionAddBo::getDimensionId));
			}
			Map<Integer,List<TGlAmortAcctDimensionAddBo>> map1=tmp1;
			//?????????????????????
			List<TBdAccountFlexentryVo> fList=itBdAccountFlexentryService.queryByAcctId(account.getFAcctId().intValue());
			if(!CollectionUtil.isEmpty(fList)){
				boolean op=fList.stream().filter(v->(
						"1".equals(v.getFInputType()) && (CollectionUtil.isEmpty(map1) || !map1.containsKey(v.getFFlexitempropertyId()))
					)
				).findFirst().isPresent();
				if(op){
					throw new CustomException("??????????????????"+i+"????????????????????????????????????",1000);
				}
			}
			sumPedMoney=sumPedMoney.add(acctBo.getFAmortizingAmount());
		}
		//??????????????????
		List<TGlAmortPeriodAddBo> amortPeriodList=bo.getAmortPeriod();
		if(CollectionUtil.isEmpty(amortPeriodList)){
			throw new CustomException("???????????????????????????");
		}
		//?????????????????? ??? ?????????
		BigDecimal sumPMoney=BigDecimal.ZERO;
		BigDecimal sumPRatio=BigDecimal.ZERO;
		for (int i=1;i<=amortPeriodList.size();i++){
			TGlAmortPeriodAddBo periodAddBo=amortPeriodList.get(i-1);
            //??????????????????
			sumPMoney=sumPMoney.add(periodAddBo.getFAmortamount());
			sumPRatio=sumPRatio.add(periodAddBo.getFAmortratio());
		}
		if(sumPMoney.compareTo(sumPedMoney)!=0){
			throw new CustomException("?????????????????????????????????????????????");
		}
		if(sumPRatio.compareTo(new BigDecimal(100))!=0){
			throw new CustomException("???????????????????????????100%");
		}

		//??????????????????
		List<TGlAmortInacctAddBo> amortInAcctList=bo.getAmortInAcct();
		if(CollectionUtil.isEmpty(amortInAcctList)){
			throw new CustomException("????????????????????????");
		}
		//???????????????
		BigDecimal sumRatio=BigDecimal.ZERO;
		for (int i=1;i<=amortInAcctList.size();i++){
			TGlAmortInacctAddBo inacctAddBo=amortInAcctList.get(i-1);
			TBdAccount account=iTBdAccountService.getById(inacctAddBo.getFEnterAccountId());
			if(null==account || !DataStatusEnum.AUDIT.getCode().equals(account.getFDocumentStatus())){
				throw new CustomException("???????????????"+i+"????????????????????????",1000);
			}

			//??????????????????
			List<TGlAmortInacctDimensionAddBo> dL=inacctAddBo.getInacctDimension();
			Map<Integer,List<TGlAmortInacctDimensionAddBo>> tmp2=new HashMap<>();
			if(!CollectionUtil.isEmpty(dL)){
				tmp2=dL.stream().filter(v->(StrUtil.isNotBlank(v.getDsCode()))).collect(Collectors.groupingBy(TGlAmortInacctDimensionAddBo::getDimensionId));
			}
			Map<Integer,List<TGlAmortInacctDimensionAddBo>> map2=tmp2;
			//?????????????????????
			List<TBdAccountFlexentryVo> fList=itBdAccountFlexentryService.queryByAcctId(account.getFAcctId().intValue());
			if(!CollectionUtil.isEmpty(fList)){
				boolean op=fList.stream().filter(v->(
						"1".equals(v.getFInputType()) && (CollectionUtil.isEmpty(map2) || !map2.containsKey(v.getFFlexitempropertyId()))
					)
				).findFirst().isPresent();
				if(op){
					throw new CustomException("???????????????"+i+"????????????????????????????????????",1000);
				}
			}
			sumRatio=sumRatio.add(inacctAddBo.getFEnterRatio());
		}
		if(sumRatio.compareTo(new BigDecimal(100))!=0){
			throw new CustomException("?????????????????????????????????100%");
		}
		map.put("sumPedMoney",sumPedMoney);
        return map;
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO ???????????????????????????,????????????????????????
        }
        return removeByIds(ids);
    }

	/**
	 * ??????
	 * @param fId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResult execute(Integer fId){
		TGlAmortizationSchemeVo vo=this.queryById(fId);
		if(null==vo){
			throw new CustomException("???????????????");
		}
		if(BaseEnum.YES.getCode().equals(vo.getFForbidStatus())){
			throw new CustomException("????????????????????????????????????");
		}
		TBdAccountBook book=itBdAccountBookService.getById(vo.getFAccountBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("???????????????",1000);
		}

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
        //??????????????????
		List<TGlVoucherEntryAddBo> vchE = new ArrayList<>();
		List<TGlAmortPeriodVo> periodVos=vo.getPeriodVos();
		List<TGlAmortInacctVo> inacctVos=vo.getInacctVos();

		Optional<TGlAmortPeriodVo> item=periodVos.stream().filter(v->(v.getFYear().equals(book.getFCurrentYear()) && v.getFPeriod().equals(book.getFCurrentPeriod()))).findFirst();
		if(!item.isPresent()){
			throw new CustomException("??????????????????????????????????????????????????????");
		}
		TGlAmortPeriodVo periodVo=item.get();
		//??????
		List<TGlAmortAcctVo> acctVos=vo.getAcctVos();
		acctVos.forEach(v->{
			TGlVoucherEntryAddBo addBo=new TGlVoucherEntryAddBo();
			addBo.setFExplanation(vo.getFExplanation());
			addBo.setFAccountId(v.getFAmortizeAccount());
			addBo.setFCurrencyId(vo.getFCurrencyId());
			addBo.setFExchangeRateType(vo.getFExchangeRateType());
			addBo.setFAmountfor(periodVo.getFAmortamount());
			addBo.setFCredit(periodVo.getFAmortamount());
			//????????????
			if(CollectionUtil.isNotEmpty(v.getAcctDimension())){
				List<AccountingDimensionBo> dimensionInfo=new ArrayList<>();
				v.getAcctDimension().forEach(b->{
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
		//??????
		inacctVos.forEach(v->{
			TGlVoucherEntryAddBo addBo=new TGlVoucherEntryAddBo();
			addBo.setFExplanation(vo.getFExplanation());
			addBo.setFAccountId(v.getFEnterAccountId());
			addBo.setFCurrencyId(vo.getFCurrencyId());
			addBo.setFExchangeRateType(vo.getFExchangeRateType());
			addBo.setFAmountfor(periodVo.getFAmortamount().multiply(v.getFEnterRatio()).setScale(2,BigDecimal.ROUND_HALF_UP));
			addBo.setFDebit(periodVo.getFAmortamount().multiply(v.getFEnterRatio()).setScale(2,BigDecimal.ROUND_HALF_UP));
			//????????????
			if(CollectionUtil.isNotEmpty(v.getInacctDimension())){
				List<AccountingDimensionBo> dimensionInfo=new ArrayList<>();
				v.getInacctDimension().forEach(b->{
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
			bo.setFSourceBillKey(VchSourceEnum.AMORTIZATION.getCode());
			AjaxResult<TGlVoucher> result=iTGlVoucherService.insertByAddBo(bo);
			//??????????????????
			TBdExecuteLogAddBo logAddBo=new TBdExecuteLogAddBo();
			logAddBo.setCreateTime(new Date());
			logAddBo.setExecuteStatus(BaseEnum.YES.getCode());
			logAddBo.setOutExecuteId(result.getData().getFVoucherId());
			logAddBo.setExecuteDetail("????????????????????????-"+bo.getFVoucherGroupNo());
			logAddBo.setExecuteId(vo.getFSchemeId());
			logAddBo.setExecuteType(VchSourceEnum.AMORTIZATION.getCode());
			iTBdExecuteLogService.insertByAddBo(logAddBo);
		}catch (Exception e){
			log.error("????????????????????????????????????????????????[{}]",e.getMessage());
			throw new CustomException("????????????????????????");
		}
		return AjaxResult.success();
	}
}
