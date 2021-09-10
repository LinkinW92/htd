package com.skeqi.finance.service.impl.endhandle;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.common.utils.time.DateTimeCalculatorUtil;
import com.skeqi.finance.domain.TBdAccount;
import com.skeqi.finance.domain.TBdAccountPeriod;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.domain.endhandle.TGlExchangeFlexEntry;
import com.skeqi.finance.domain.endhandle.TGlExchangeScheme;
import com.skeqi.finance.domain.endhandle.TGlExchangeSchemeEntry;
import com.skeqi.finance.domain.rate.TBdRate;
import com.skeqi.finance.domain.rate.TBdRateType;
import com.skeqi.finance.domain.voucher.TGlVoucher;
import com.skeqi.finance.enums.*;
import com.skeqi.finance.mapper.TBdAccountPeriodMapper;
import com.skeqi.finance.mapper.endhandle.TGlExchangeFlexEntryMapper;
import com.skeqi.finance.mapper.endhandle.TGlExchangeSchemeEntryMapper;
import com.skeqi.finance.mapper.endhandle.TGlExchangeSchemeMapper;
import com.skeqi.finance.mapper.basicinformation.voucher.TGlVoucherMapper;
import com.skeqi.finance.mapper.rate.TBdRateMapper;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogAddBo;
import com.skeqi.finance.pojo.bo.voucher.*;
import com.skeqi.finance.pojo.bo.endhandle.*;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountFlexentryVo;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeFlexEntryVo;
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeSchemeEntryVo;
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeSchemeVo;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherEntryVo;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherVo;
import com.skeqi.finance.service.basicinformation.base.ITBdCurrencyService;
import com.skeqi.finance.service.basicinformation.base.ITBdExecuteLogService;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherGroupNoService;
import com.skeqi.finance.service.account.ITBdAccountFlexentryService;
import com.skeqi.finance.service.account.ITBdAccountService;
import com.skeqi.finance.service.basicinformation.accountbook.ITBdAccountBookService;
import com.skeqi.finance.service.basicinformation.voucher.ITBdVoucherGroupService;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherService;
import com.skeqi.finance.service.dimension.ITBdFlexItemPropertyService;
import com.skeqi.finance.service.endhandle.ITGlExchangeSchemeService;
import com.skeqi.finance.service.rate.ITBdRateService;
import com.skeqi.finance.service.rate.ITBdRateTypeService;
import com.skeqi.framecore.web.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 期末调汇方案Service业务层处理
 *
 * @author toms
 * @date 2021-07-30
 */
@Service
@Slf4j
public class TGlExchangeSchemeServiceImpl extends ServicePlusImpl<TGlExchangeSchemeMapper, TGlExchangeScheme> implements ITGlExchangeSchemeService {

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
	ITBdFlexItemPropertyService iTBdFlexItemPropertyService;
	@Autowired
	TokenService tokenService;
	@Autowired
	TGlExchangeFlexEntryMapper tGlExchangeFlexEntryMapper;
	@Autowired
	TGlExchangeSchemeEntryMapper tGlExchangeSchemeEntryMapper;
	@Autowired
	TGlExchangeSchemeMapper tGlExchangeSchemeMapper;
	@Autowired
	TGlVoucherMapper tGlVoucherMapper;
	@Autowired
	ITBdRateService itBdRateService;
	@Autowired
	TBdAccountPeriodMapper tBdAccountPeriodMapper;
	@Autowired
	ITGlVoucherService iTGlVoucherService;
	@Autowired
	ITGlVoucherGroupNoService iTGlVoucherGroupNoService;
	@Autowired
	ITBdExecuteLogService iTBdExecuteLogService;
    @Override
    public TGlExchangeSchemeVo queryById(Integer fId){
		TGlExchangeSchemeVo vo=baseMapper.queryById(fId);
		if(null==vo){
			return null;
		}
		List<TGlExchangeFlexEntry> entryList=tGlExchangeFlexEntryMapper.selectList(new QueryWrapper<TGlExchangeFlexEntry>().eq("f_id",fId));
		if(CollectionUtil.isNotEmpty(entryList)){
			List<TGlExchangeFlexEntryVo> entryVo=BeanUtil.copyToList(entryList, TGlExchangeFlexEntryVo.class);
			vo.setDimension(entryVo);
		}
		List<TGlExchangeSchemeEntryVo> schemeEntryList=tGlExchangeSchemeEntryMapper.queryList(fId);
		if(CollectionUtil.isNotEmpty(schemeEntryList)){
			vo.setAcctEntry(schemeEntryList);
		}
		return vo;
    }

    @Override
    public TableDataInfo<TGlExchangeSchemeVo> queryPageList(TGlExchangeSchemeQueryBo bo) {
		Page<TGlExchangeSchemeQueryBo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TGlExchangeSchemeVo> iPage = tGlExchangeSchemeMapper.queryPageList(page,bo);
        return PageUtils.buildDataInfo(iPage);
    }

    @Override
    public List<TGlExchangeSchemeVo> queryList(TGlExchangeSchemeQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlExchangeSchemeVo.class);
    }

    private LambdaQueryWrapper<TGlExchangeScheme> buildQueryWrapper(TGlExchangeSchemeQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlExchangeScheme> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TGlExchangeScheme::getFNumber, bo.getFNumber());
        lqw.eq(bo.getFAccountBookId() != null, TGlExchangeScheme::getFAccountBookId, bo.getFAccountBookId());
        lqw.eq(bo.getFGenerateType() != null, TGlExchangeScheme::getFGenerateType, bo.getFGenerateType());
        lqw.eq(bo.getFOperatorId() != null, TGlExchangeScheme::getFOperatorId, bo.getFOperatorId());
        lqw.eq(bo.getFFrequency() != null, TGlExchangeScheme::getFFrequency, bo.getFFrequency());
        lqw.eq(bo.getFGenerateDay() != null, TGlExchangeScheme::getFGenerateDay, bo.getFGenerateDay());
        lqw.eq(bo.getFGenerateHour() != null, TGlExchangeScheme::getFGenerateHour, bo.getFGenerateHour());
        lqw.eq(bo.getFGenerateMinute() != null, TGlExchangeScheme::getFGenerateMinute, bo.getFGenerateMinute());
        lqw.eq(bo.getFLastExecuteTime() != null, TGlExchangeScheme::getFLastExecuteTime, bo.getFLastExecuteTime());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TGlExchangeScheme::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TGlExchangeScheme::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbiderId() != null, TGlExchangeScheme::getFForbiderId, bo.getFForbiderId());
        lqw.eq(bo.getFForbidDate() != null, TGlExchangeScheme::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFCreatorId() != null, TGlExchangeScheme::getFCreatorId, bo.getFCreatorId());
        lqw.eq(bo.getFCreatorDate() != null, TGlExchangeScheme::getFCreatorDate, bo.getFCreatorDate());
        lqw.eq(bo.getFModifierId() != null, TGlExchangeScheme::getFModifierId, bo.getFModifierId());
        lqw.eq(bo.getFModifyDate() != null, TGlExchangeScheme::getFModifyDate, bo.getFModifyDate());
        lqw.eq(bo.getFVchgroupId() != null, TGlExchangeScheme::getFVchgroupId, bo.getFVchgroupId());
        lqw.eq(bo.getFVoucherDateType() != null, TGlExchangeScheme::getFVoucherDateType, bo.getFVoucherDateType());
        lqw.eq(bo.getFExchangeType() != null, TGlExchangeScheme::getFExchangeType, bo.getFExchangeType());
        lqw.eq(bo.getFVchProcessType() != null, TGlExchangeScheme::getFVchProcessType, bo.getFVchProcessType());
        lqw.eq(bo.getFResultVchProcessType() != null, TGlExchangeScheme::getFResultVchProcessType, bo.getFResultVchProcessType());
        lqw.eq(bo.getFAcctChoseType() != null, TGlExchangeScheme::getFAcctChoseType, bo.getFAcctChoseType());
        lqw.eq(bo.getFTransferType() != null, TGlExchangeScheme::getFTransferType, bo.getFTransferType());
        lqw.eq(bo.getFExacCount() != null, TGlExchangeScheme::getFExacCount, bo.getFExacCount());
        lqw.eq(bo.getFDc() != null, TGlExchangeScheme::getFDc, bo.getFDc());
        lqw.eq(bo.getFExchangeAcctDc() != null, TGlExchangeScheme::getFExchangeAcctDc, bo.getFExchangeAcctDc());
        lqw.eq(bo.getFAllocateDateType() != null, TGlExchangeScheme::getFAllocateDateType, bo.getFAllocateDateType());
        lqw.eq(StrUtil.isNotBlank(bo.getFPl()), TGlExchangeScheme::getFPl, bo.getFPl());
        lqw.eq(StrUtil.isNotBlank(bo.getFIssetFlexItem()), TGlExchangeScheme::getFIssetFlexItem, bo.getFIssetFlexItem());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsdraftVoucher()), TGlExchangeScheme::getFIsdraftVoucher, bo.getFIsdraftVoucher());
        return lqw;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertByAddBo(TGlExchangeSchemeAddBo bo) {
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        validEntityBeforeSave(bo);
		TGlExchangeScheme add = BeanUtil.toBean(bo, TGlExchangeScheme.class);
		add.setFCreatorId(sysUser.getUserId().intValue());
		add.setFCreatorDate(new Date());
		this.save(add);
		//新增维度分录信息
		if(CollectionUtil.isNotEmpty(bo.getDimension())){
			List<TGlExchangeFlexEntry> flexEntries=new ArrayList<>();
			bo.getDimension().forEach(v->{
				TGlExchangeFlexEntry entry=new TGlExchangeFlexEntry();
				BeanUtil.copyProperties(v,entry);
				entry.setFId(add.getFId());
				flexEntries.add(entry);
			});
			tGlExchangeFlexEntryMapper.insertAll(flexEntries);
		}
		//新增调汇科目信息
		if(CollectionUtil.isNotEmpty(bo.getAcctEntry())){
			List<TGlExchangeSchemeEntry>  schemeEntrys=new ArrayList<>();
			bo.getAcctEntry().forEach(v->{
				TGlExchangeSchemeEntry entry=new TGlExchangeSchemeEntry();
				BeanUtil.copyProperties(v,entry);
				entry.setFId(add.getFId());
				schemeEntrys.add(entry);
			});
			tGlExchangeSchemeEntryMapper.insertAll(schemeEntrys);
		}
        return true;
    }

    @Override
    public Boolean updateByEditBo(TGlExchangeSchemeEditBo bo) {
		TGlExchangeScheme old=this.getById(bo.getFId());
    	if(null==old){
    		throw new CustomException("调汇信息不存在");
		}
		TGlExchangeSchemeAddBo addBo= BeanUtil.toBean(bo,TGlExchangeSchemeAddBo.class);
		validEntityBeforeSave(addBo);
         BeanUtil.copyProperties(bo,old,"fId","fNumber");
		this.updateById(old);
		//新增维度分录信息
		if(CollectionUtil.isNotEmpty(bo.getDimension())){
			tGlExchangeFlexEntryMapper.deleteByFId(bo.getFId());
			List<TGlExchangeFlexEntry> flexEntries=new ArrayList<>();
			bo.getDimension().forEach(v->{
				TGlExchangeFlexEntry entry=new TGlExchangeFlexEntry();
				BeanUtil.copyProperties(v,entry);
				entry.setFId(bo.getFId());
				flexEntries.add(entry);
			});
			tGlExchangeFlexEntryMapper.insertAll(flexEntries);
		}
		//新增调汇科目信息
		if(CollectionUtil.isNotEmpty(bo.getAcctEntry())){
			tGlExchangeSchemeEntryMapper.deleteByFid(bo.getFId());
			List<TGlExchangeSchemeEntry>  schemeEntrys=new ArrayList<>();
			bo.getAcctEntry().forEach(v->{
				TGlExchangeSchemeEntry entry=new TGlExchangeSchemeEntry();
				BeanUtil.copyProperties(v,entry);
				entry.setFId(bo.getFId());
				schemeEntrys.add(entry);
			});
			tGlExchangeSchemeEntryMapper.insertAll(schemeEntrys);
		}
        return true;
    }

	/**
	 * 执行
	 * @param fId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResult execute(Integer fId,boolean flag){
		TGlExchangeSchemeVo vo=this.queryById(fId);
		if(null==vo){
			throw new CustomException("记录不存在");
		}
		if(BaseEnum.YES.getCode().equals(vo.getFForbidStatus())){
			throw new CustomException("执行记录被禁用，执行失败");
		}

		TBdAccountBook book=itBdAccountBookService.getById(vo.getFAccountBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("账簿不存在",1000);
		}

		TGlVoucherQueryBo vbo=new TGlVoucherQueryBo();
		vbo.setFYear( BaseEnum.YES.getCode().equals(vo.getFIsadjustVoucher()) ? vo.getFYear() : book.getFCurrentYear() );
		vbo.setFPeriod( BaseEnum.YES.getCode().equals(vo.getFIsadjustVoucher()) ? vo.getFPeriod() : book.getFCurrentPeriod().intValue() );
		vbo.setFAccountBookId(book.getFBookId());
		vbo.setFSourceBillKey(VchSourceEnum.END_EXCHANGE.getCode());
		TGlVoucherVo tGlVoucherVo=tGlVoucherMapper.queryIncomeLossVch(vbo);
		if(tGlVoucherVo!=null){
			throw new CustomException("调汇科目当期凭证生成，如需重新生成先删除之前生成的凭证");
		}

		List<TGlExchangeSchemeEntryVo> acctEntry=vo.getAcctEntry();
		if(CollectionUtil.isEmpty(acctEntry)){
			throw new CustomException("调汇科目不存在");
		}

		//选择当前系统日期
		Integer year=book.getFCurrentYear();
		Integer period=book.getFCurrentPeriod().intValue();
		Integer month=12;
		TBdAccountPeriod periodInfo = new TBdAccountPeriod();
		if (!BaseEnum.YES.getCode().equals(vo.getFIsadjustVoucher())){
			periodInfo=tBdAccountPeriodMapper.findInfo(book.getFPeriodId().intValue(),year,period);
			month=periodInfo.getFMonth();
			if(null==periodInfo){
				throw new CustomException("账簿所在当前期间不在会计日历中，执行失败");
			}
			if(LocateDateTypeEnum.CURRY_DAY.getCode().equals(vo.getFVoucherDateType())){
				year= DateTimeCalculatorUtil.getYear(new Date());
				month= DateTimeCalculatorUtil.getMonth(new Date());
				periodInfo=tBdAccountPeriodMapper.findPeriodInfo(book.getFPeriodId().intValue(),year,month);
				period=periodInfo.getFPeriod();
			}
		}

		Date endDate= periodInfo.getFPeriodEnddate();
		List<Long> acctList=acctEntry.stream().map(TGlExchangeSchemeEntryVo::getFAcctId).collect(Collectors.toList());
		TGlVoucherEntryQueryBo entryQueryBo=new TGlVoucherEntryQueryBo();
		entryQueryBo.setFAccountBookId(book.getFBookId());
		entryQueryBo.setFYear(year);
		entryQueryBo.setFPeriod(period);
		entryQueryBo.setFCurrencyId(book.getFCurrencyId().intValue());
		if(VchProcessEnum.ALL.getCode().equals(vo.getFVchProcessType().toString())){
			entryQueryBo.setFPosted(Integer.parseInt(BaseEnum.YES.getCode()));
		}
		entryQueryBo.setAcctList(acctList);
		List<TGlVoucherEntryVo> list=tGlVoucherMapper.queryExchangeINfo(entryQueryBo);
		if(CollectionUtil.isEmpty(list)){
			throw new CustomException("已过账凭证中不存在调汇科目");
		}
//		if(VchProcessEnum.NO_VCH.getCode().equals(vo.getFVchProcessType().toString())){
//			Optional<TGlVoucherEntryVo> op=list.stream().filter(v->(BaseEnum.NO.getCode().equals(v.getFPosted()))).findFirst();
//			if(op.isPresent()){
//				throw new CustomException("调汇科目存在未过账凭证，请先过账凭证后再结转操作");
//			}
//		}
		//凭证
		TGlVoucherAddBo bo=new TGlVoucherAddBo();
		bo.setFAccountBookId(book.getFBookId());
		bo.setFAcctOrgid(book.getFAccountOrgid().intValue());
		bo.setFDate(DateTimeCalculatorUtil.getDateEndOfMonth(book.getFCurrentYear(),month));
		bo.setFAttachments(0);
		bo.setFBaseCurrencyId(book.getFCurrencyId().intValue());
		bo.setFInternalind("3");
		bo.setFVoucherGroupId(vo.getFVchgroupId());
		TGlVoucherGroupNoQueryBo groupNoQueryBo=new TGlVoucherGroupNoQueryBo();
		groupNoQueryBo.setFBookId(book.getFBookId());
		if (BaseEnum.YES.getCode().equals(vo.getFIsadjustVoucher())){
			bo.setFIsadjustVoucher(vo.getFIsadjustVoucher());
			bo.setFYear(vo.getFYear());
			bo.setFPeriod(vo.getFPeriod());
			groupNoQueryBo.setFPeriod(vo.getFPeriod());
			groupNoQueryBo.setFYear(vo.getFYear());
		}else {
			groupNoQueryBo.setFPeriod(BaseEnum.YES.getCode().equals(vo.getFIsadjustVoucher()) ? vo.getFPeriod() : book.getFCurrentPeriod().intValue());
			groupNoQueryBo.setFYear(BaseEnum.YES.getCode().equals(vo.getFIsadjustVoucher()) ? vo.getFYear() : book.getFCurrentYear());
		}
		groupNoQueryBo.setFVoucherGroupId(vo.getFVchgroupId());
		List<Integer> idList=iTGlVoucherGroupNoService.listVchNo(groupNoQueryBo);
		bo.setFVoucherGroupNo(idList.get(0));
		TGlVoucherGroupNoAddBo noAddBo=new TGlVoucherGroupNoAddBo();
		BeanUtils.copyProperties(groupNoQueryBo,noAddBo);
		noAddBo.setFVoucherGroupNo(bo.getFVoucherGroupNo());
		bo.setFDocumentStatus(DataStatusEnum.TMP_SAVE.getCode());
		iTGlVoucherGroupNoService.insertByAddBo(noAddBo);
		List<TGlVoucherEntryAddBo> voucherEntry=new ArrayList<>();
		//科目分组
		Map<Integer, List<TGlVoucherEntryVo>> mapAcct=list.stream().collect(Collectors.groupingBy(TGlVoucherEntryVo::getFAccountId));
		mapAcct.forEach((k,vL)->{
			//币别分组
			Map<Integer, List<TGlVoucherEntryVo>> mapCy=vL.stream().collect(Collectors.groupingBy(TGlVoucherEntryVo::getFCurrencyId));
			mapCy.forEach((kc,vcL)->{
				BigDecimal oldVal=BigDecimal.ZERO;
				BigDecimal newVal=BigDecimal.ZERO;
				TBdRate rate=null;
				for (TGlVoucherEntryVo v:vcL){
					if(null==rate){
						rate=itBdRateService.getRate(endDate,v.getFExchangeRateType(),kc,book.getFCurrencyId().intValue());
						if(null==rate){
							throw new CustomException("调汇科目中未设置可用汇率，执行失败");
						}
					}
					//汇率不同计算差值
					if(v.getFExchangeRate().compareTo(rate.getFExchangeRate())!=0){
						oldVal=oldVal.add(v.getFExchangeRate().multiply(v.getFAmountfor()));
						newVal=newVal.add(rate.getFExchangeRate().multiply(v.getFAmountfor()));
					}
				}
				//差值
				BigDecimal cv=newVal.subtract(oldVal);
				if(cv.compareTo(BigDecimal.ZERO)!=0){
					TGlVoucherEntryAddBo addBo=new TGlVoucherEntryAddBo();
					addBo.setFExplanation(vo.getFExplanation());
					addBo.setFAccountId(k);
					addBo.setFExchangeRate(rate.getFExchangeRate());
					addBo.setFExchangeRateType(rate.getFRateTypeId());
					addBo.setFCurrencyId(kc);
					if(cv.compareTo(BigDecimal.ZERO)>0){
						addBo.setFDebit(cv.negate());
						addBo.setFAmount(cv.negate());
						addBo.setFAmountfor(cv.negate().multiply(rate.getFExchangeRate()));
					}else {
						addBo.setFCredit(cv.negate());
						addBo.setFAmount(cv.negate());
						addBo.setFAmountfor(cv.negate().multiply(rate.getFExchangeRate()));
					}
					voucherEntry.add(addBo);

				}
			});
		});
		//查询差值
		BigDecimal sumD=BigDecimal.ZERO;
		BigDecimal sumC=BigDecimal.ZERO;
		if(CollectionUtil.isNotEmpty(voucherEntry)){
			for (TGlVoucherEntryAddBo addBo:voucherEntry){
				sumD=sumD.add(addBo.getFDebit());
				sumC=sumC.add(addBo.getFCredit());
			}
		}
		BigDecimal cv=sumD.subtract(sumC);
		//汇兑科目分录信息组装
		//TBdRate rate=itBdRateService.getRate(endDate,vo.getFExchangeType(),book.getFCurrencyId().intValue());
		TGlVoucherEntryAddBo addBo=new TGlVoucherEntryAddBo();
		addBo.setFExplanation(vo.getFExplanation());
		addBo.setFAccountId(vo.getFExacCount());
		addBo.setFExchangeRate(BigDecimal.ONE);
		addBo.setFExchangeRateType(vo.getFExchangeType());
		addBo.setFCurrencyId(book.getFCurrencyId().intValue());
		if(cv.compareTo(BigDecimal.ZERO)<0){
			addBo.setFDebit(cv.negate());
		}else {
			addBo.setFCredit(cv.negate());
		}
		addBo.setFAmount(cv.negate());
		addBo.setFAmountfor(cv.negate());
		try {
			bo.setFSourceBillKey(VchSourceEnum.END_EXCHANGE.getCode());
			AjaxResult<TGlVoucher> result=iTGlVoucherService.insertByAddBo(bo);
			//增加日志记录
			TBdExecuteLogAddBo logAddBo=new TBdExecuteLogAddBo();
			logAddBo.setCreateTime(new Date());
			logAddBo.setExecuteStatus(BaseEnum.YES.getCode());
			logAddBo.setOutExecuteId(result.getData().getFVoucherId());
			logAddBo.setExecuteDetail("【期末调汇】生成凭证-"+bo.getFVoucherGroupNo());
			logAddBo.setExecuteId(vo.getFId());
			logAddBo.setExecuteType(VchSourceEnum.END_EXCHANGE.getCode());
			iTBdExecuteLogService.insertByAddBo(logAddBo);
		}catch (Exception e){
			log.error("【期末调汇调用凭证新增异常】异常信息[{}]",e.getMessage());
			throw new CustomException("执行生成凭证异常");
		}
		return AjaxResult.success();
	}
    /**
     * 保存前的数据校验
     *
     * @param bo 实体类数据
     */
    private void validEntityBeforeSave(TGlExchangeSchemeAddBo bo){
		TBdAccountBook book=itBdAccountBookService.getById(bo.getFAccountBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("账簿不存在");
		}
		TBdVoucherGroup voucherGroup=iTBdVoucherGroupService.getById(bo.getFVchgroupId());
		if(null==voucherGroup || !DataStatusEnum.AUDIT.getCode().equals(voucherGroup.getFDocumentStatus())){
			throw new CustomException("凭证字不存在");
		}
		TBdRateType rateType=iTBdRateTypeService.getById(bo.getFExchangeType());
		if(null==rateType || !DataStatusEnum.AUDIT.getCode().equals(rateType.getFDocumentStatus())){
			throw new CustomException("汇率类型不存在");
		}
		TBdAccount account=iTBdAccountService.getById(bo.getFExacCount());
		if(null==account || !DataStatusEnum.AUDIT.getCode().equals(account.getFDocumentStatus())){
			throw new CustomException("科目信息不存在");
		}
		if(BaseEnum.YES.getCode().equals(bo.getFIssetFlexItem()) && CollectionUtil.isEmpty(bo.getDimension())){
			throw new CustomException("核算维度不能为空");
		}
		if(null==LocateDateTypeEnum.getObj(bo.getFAllocateDateType().toString())){
			throw new CustomException("调汇日期方式类型不存在");
		}
		if(null==BaseEnum.getObj(bo.getFAcctChoseType().toString())){
			throw new CustomException("科目选择方式不存在");
		}
		if(null== BorrowEnum.getObj(bo.getFDc().toString())){
			throw new CustomException("汇兑损益科目方向不存在");
		}
		if(null== BorrowEnum.getObj(bo.getFExchangeAcctDc().toString())){
			throw new CustomException("汇兑损益科目方向不存在");
		}
		if(null== LocateDateTypeEnum.getObj(bo.getFVoucherDateType().toString())){
			throw new CustomException("凭证日期方式不存在");
		}
		if(null== ExchangeTransferTypeEnum.getObj(bo.getFTransferType().toString())){
			throw new CustomException("调汇类型不存在");
		}
		if(null== VchProcessEnum.getObj(bo.getFVchProcessType().toString())){
			throw new CustomException("未过账外币凭证处理方式不存在");
		}
		if(null== VchProcessResultEnum.getObj(bo.getFResultVchProcessType().toString())){
			throw new CustomException("调汇凭证后续处理方式不存在");
		}

		//核算维度校验
		List<TGlExchangeFlexEntryAddBo> dL=bo.getDimension();
		Map<Integer,List<TGlExchangeFlexEntryAddBo>> tmp2=new HashMap<>();
		if(!CollectionUtil.isEmpty(dL)){
			tmp2=dL.stream().filter(v->(v.getFFlexId()!=null)).collect(Collectors.groupingBy(TGlExchangeFlexEntryAddBo::getFFlexId));
		}
		Map<Integer,List<TGlExchangeFlexEntryAddBo>> map2=tmp2;
		//科目维度控制表
		List<TBdAccountFlexentryVo> fList=itBdAccountFlexentryService.queryByAcctId(account.getFAcctId().intValue());
		if(!CollectionUtil.isEmpty(fList)){
			boolean op=fList.stream().filter(v->(
					"1".equals(v.getFInputType()) && (CollectionUtil.isEmpty(map2) || !map2.containsKey(v.getFFlexitempropertyId()))
				)
			).findFirst().isPresent();
			if(op){
				throw new CustomException("科目核算维度必录项不能为空",1000);
			}
		}
		if(CollectionUtil.isNotEmpty(bo.getAcctEntry())){
			List<TGlExchangeSchemeEntryAddBo> acctEntry=bo.getAcctEntry();
			acctEntry.forEach(v->{
				TBdAccount tmp=iTBdAccountService.getById(v.getFAcctId());
				if(null==tmp || !DataStatusEnum.AUDIT.getCode().equals(tmp.getFDocumentStatus())){
					throw new CustomException("调汇科目信息不存在");
				}
				if(!BaseEnum.YES.getCode().equals(tmp.getFIsallocate())){
					throw new CustomException("该科目信息不是期末调汇科目");
				}
			});
		}
		if(CollectionUtil.isNotEmpty(bo.getDimension())){
			List<TGlExchangeFlexEntryAddBo>  dimension=bo.getDimension();
			dimension.forEach(v->{
				TBdFlexItemPropertyVo vo=iTBdFlexItemPropertyService.queryById(v.getFFlexId());
				if(null==vo || !DataStatusEnum.AUDIT.getCode().equals(vo.getFDocumentStatus())){
					throw new CustomException("核算维度不存在");
				}
				if(StrUtil.isBlank(v.getFFlexValue())){
					throw new CustomException("核算维度值不能为空");
				}
			});
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }










}
