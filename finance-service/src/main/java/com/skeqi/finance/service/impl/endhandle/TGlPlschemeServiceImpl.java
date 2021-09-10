package com.skeqi.finance.service.impl.endhandle;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.common.utils.time.DateTimeCalculatorUtil;
import com.skeqi.finance.domain.TBdAccount;
import com.skeqi.finance.domain.TBdAccountPeriod;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.domain.endhandle.TGlPlschemeEntry;
import com.skeqi.finance.domain.endhandle.TGlPlschemeFlex;
import com.skeqi.finance.domain.voucher.TGlVoucher;
import com.skeqi.finance.enums.*;
import com.skeqi.finance.mapper.TBdAccountPeriodMapper;
import com.skeqi.finance.mapper.basicinformation.voucher.TGlVoucherMapper;
import com.skeqi.finance.mapper.endhandle.TGlPlschemeEntryMapper;
import com.skeqi.finance.mapper.endhandle.TGlPlschemeFlexMapper;
import com.skeqi.finance.mapper.rate.TBdRateMapper;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogAddBo;
import com.skeqi.finance.pojo.bo.voucher.*;
import com.skeqi.finance.pojo.bo.endhandle.*;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountFlexentryVo;
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeEntryVo;
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
import com.skeqi.finance.service.endhandle.ITGlPlschemeFlexService;
import com.skeqi.finance.service.rate.ITBdRateService;
import com.skeqi.finance.service.rate.ITBdRateTypeService;
import com.skeqi.framecore.web.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.domain.endhandle.TGlPlscheme;
import com.skeqi.finance.mapper.endhandle.TGlPlschemeMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeVo;
import com.skeqi.finance.service.endhandle.ITGlPlschemeService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 结转损益方案Service业务层处理
 *
 * @author toms
 * @date 2021-08-02
 */
@Slf4j
@Service
public class TGlPlschemeServiceImpl extends ServicePlusImpl<TGlPlschemeMapper, TGlPlscheme> implements ITGlPlschemeService {

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
	TGlPlschemeEntryMapper tGlPlschemeEntryMapper;
	@Autowired
	TokenService tokenService;
	@Autowired
	ITGlPlschemeFlexService iTGlPlschemeFlexService;

	@Autowired
	TGlPlschemeFlexMapper tGlPlschemeFlexMapper;
	@Autowired
	TBdAccountPeriodMapper tBdAccountPeriodMapper;
	@Autowired
	TGlVoucherMapper tGlVoucherMapper;
	@Autowired
	ITGlVoucherGroupNoService iTGlVoucherGroupNoService;
	@Autowired
	TBdRateMapper tBdRateMapper;
	@Autowired
	ITBdRateService itBdRateService;
	@Autowired
	ITGlVoucherService iTGlVoucherService;
	@Autowired
	ITBdExecuteLogService iTBdExecuteLogService;


    @Override
    public TGlPlschemeVo queryById(Integer fId){
		TGlPlschemeVo vo=baseMapper.getInfoById(fId);
		if(vo!=null){
			List<TGlPlschemeEntryVo> list=tGlPlschemeEntryMapper.queryByEntryId(vo.getFId().intValue());
			vo.setAcctEntry(list);
			List<TGlPlschemeFlex> flexList=tGlPlschemeFlexMapper.queryFlexById(vo.getFId().intValue());
			vo.setFlexList(flexList);
		}
		return vo;
    }

    @Override
    public TableDataInfo<TGlPlschemeVo> queryPageList(TGlPlschemeQueryBo bo) {
		Page<TGlPlschemeQueryBo> page=new Page<>(bo.getPageNum(),bo.getPageSize());
        IPage<TGlPlschemeVo> iPage=baseMapper.queryPageList(page,bo);
        return PageUtils.buildDataInfo(iPage);
    }

    @Override
    public List<TGlPlschemeVo> queryList(TGlPlschemeQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlPlschemeVo.class);
    }

    private LambdaQueryWrapper<TGlPlscheme> buildQueryWrapper(TGlPlschemeQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlPlscheme> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TGlPlscheme::getFNumber, bo.getFNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFExplanation()), TGlPlscheme::getFExplanation, bo.getFExplanation());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TGlPlscheme::getFDescription, bo.getFDescription());
        lqw.eq(bo.getFAccountBookId() != null, TGlPlscheme::getFAccountBookId, bo.getFAccountBookId());
        lqw.eq(bo.getFGenerateType() != null, TGlPlscheme::getFGenerateType, bo.getFGenerateType());
        lqw.eq(bo.getFOperatorId() != null, TGlPlscheme::getFOperatorId, bo.getFOperatorId());
        lqw.eq(bo.getFFrequency() != null, TGlPlscheme::getFFrequency, bo.getFFrequency());
        lqw.eq(bo.getFGenerateDay() != null, TGlPlscheme::getFGenerateDay, bo.getFGenerateDay());
        lqw.eq(bo.getFGenerateHour() != null, TGlPlscheme::getFGenerateHour, bo.getFGenerateHour());
        lqw.eq(bo.getFGenerateMinute() != null, TGlPlscheme::getFGenerateMinute, bo.getFGenerateMinute());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TGlPlscheme::getFName, bo.getFName());
        lqw.eq(bo.getFLastExecuteTime() != null, TGlPlscheme::getFLastExecuteTime, bo.getFLastExecuteTime());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TGlPlscheme::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TGlPlscheme::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbiderId() != null, TGlPlscheme::getFForbiderId, bo.getFForbiderId());
        lqw.eq(bo.getFForbidDate() != null, TGlPlscheme::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFCreatorId() != null, TGlPlscheme::getFCreatorId, bo.getFCreatorId());
        lqw.eq(bo.getFCreatorDate() != null, TGlPlscheme::getFCreatorDate, bo.getFCreatorDate());
        lqw.eq(bo.getFModifierId() != null, TGlPlscheme::getFModifierId, bo.getFModifierId());
        lqw.eq(bo.getFModifyDate() != null, TGlPlscheme::getFModifyDate, bo.getFModifyDate());
        lqw.eq(bo.getFVchgroupId() != null, TGlPlscheme::getFVchgroupId, bo.getFVchgroupId());
        lqw.eq(bo.getFVoucherDateType() != null, TGlPlscheme::getFVoucherDateType, bo.getFVoucherDateType());
        lqw.eq(bo.getFTransferType() != null, TGlPlscheme::getFTransferType, bo.getFTransferType());
        lqw.eq(bo.getFVoucherType() != null, TGlPlscheme::getFVoucherType, bo.getFVoucherType());
        lqw.eq(bo.getFPlvchProcessType() != null, TGlPlscheme::getFPlvchProcessType, bo.getFPlvchProcessType());
        lqw.eq(bo.getFResultVchProcessType() != null, TGlPlscheme::getFResultVchProcessType, bo.getFResultVchProcessType());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsAcctDc()), TGlPlscheme::getFIsAcctDc, bo.getFIsAcctDc());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsConnectUnit()), TGlPlscheme::getFIsConnectUnit, bo.getFIsConnectUnit());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsMergeplAcct()), TGlPlscheme::getFIsMergeplAcct, bo.getFIsMergeplAcct());
        lqw.eq(bo.getFFlexItem() != null, TGlPlscheme::getFFlexItem, bo.getFFlexItem());
        lqw.eq(bo.getFAcctChoseType() != null, TGlPlscheme::getFAcctChoseType, bo.getFAcctChoseType());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsDraftVoucher()), TGlPlscheme::getFIsDraftVoucher, bo.getFIsDraftVoucher());
        return lqw;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertByAddBo(TGlPlschemeAddBo bo) {
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        validEntityBeforeSave(bo);
		TGlPlscheme add = BeanUtil.toBean(bo, TGlPlscheme.class);
		add.setFCreatorDate(new Date());
		add.setFCreatorId(sysUser.getUserId().intValue());
		this.save(add);
		//核算维度
		if(CollectionUtil.isNotEmpty(bo.getFlexBo())){
			bo.getFlexBo().forEach(v->{
				v.setFId(add.getFId());
				iTGlPlschemeFlexService.insertByAddBo(v);
			});
		}
		//新增分录
		bo.getEntryBo().forEach(v->{
			TGlPlschemeEntry entry=new TGlPlschemeEntry();
			BeanUtil.copyProperties(v,entry);
			entry.setFId(add.getFId());
			tGlPlschemeEntryMapper.insert(entry);
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
	public Boolean execute(Integer fId){
		TGlPlschemeVo vo =this.queryById(fId);
		if(null==vo){
			throw new CustomException("当前记录不存在");
		}
		if(BaseEnum.YES.getCode().equals(vo.getFForbidStatus())){
			throw new CustomException("执行记录被禁用，执行失败");
		}
		TBdAccountBook book=itBdAccountBookService.getById(vo.getFAccountBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("账簿不存在",1000);
		}
		TGlVoucherQueryBo vbo=new TGlVoucherQueryBo();
		vbo.setFYear(BaseEnum.YES.getCode().equals(vo.getFIsadjustVoucher()) ? vo.getFYear() : book.getFCurrentYear());
		vbo.setFPeriod(BaseEnum.YES.getCode().equals(vo.getFIsadjustVoucher()) ? vo.getFPeriod() : book.getFCurrentPeriod().intValue());
		vbo.setFAccountBookId(book.getFBookId());
		vbo.setFSourceBillKey(VchSourceEnum.INCOME_LOSS.getCode());
		TGlVoucherVo tGlVoucherVo=tGlVoucherMapper.queryIncomeLossVch(vbo);
        if(tGlVoucherVo!=null){
			throw new CustomException("结转损益当期凭证生成，如需重新生成先删除之前生成的凭证");
		}
		List<TGlPlschemeEntryVo> acctEntry=vo.getAcctEntry();
		if(CollectionUtil.isEmpty(acctEntry)){
			throw new CustomException("结转科目不存在");
		}

         //选择当前系统日期
		Integer year=book.getFCurrentYear();
		Integer period=book.getFCurrentPeriod().intValue();
		Integer month=12;
		if (!BaseEnum.YES.getCode().equals(vo.getFIsadjustVoucher())){
			TBdAccountPeriod periodInfo=tBdAccountPeriodMapper.findInfo(book.getFPeriodId().intValue(),year,period);
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



		List<Integer> acctList=acctEntry.stream().map(TGlPlschemeEntryVo::getFAcctId).collect(Collectors.toList());
		List<Long> acct=acctList.stream().mapToLong(v->v.intValue()).boxed().collect(Collectors.toList());
		TGlVoucherEntryQueryBo entryQueryBo=new TGlVoucherEntryQueryBo();
		entryQueryBo.setFAccountBookId(book.getFBookId());
		entryQueryBo.setFYear(year);
		entryQueryBo.setFPeriod(period);
		if(VchProcessEnum.ALL.getCode().equals(vo.getFForbidStatus())){
			entryQueryBo.setFPosted(Integer.parseInt(BaseEnum.YES.getCode()));
		}
		entryQueryBo.setAcctList(acct);
		List<TGlVoucherEntryVo> settleAcctList=tGlVoucherMapper.queryExchangeINfo(entryQueryBo);
		if(CollectionUtil.isEmpty(settleAcctList)){
			throw new CustomException("结转科目无凭证数据，结转失败");
		}
		Optional<TGlVoucherEntryVo> op=settleAcctList.stream().filter(v->(BaseEnum.NO.getCode().equals(v.getFPosted()))).findFirst();
		if(op.isPresent()){
			throw new CustomException("结转科目存在未过账凭证，请先过账凭证后再结转操作");
		}
		//凭证
		TGlVoucherAddBo bo=new TGlVoucherAddBo();
		bo.setFAccountBookId(book.getFBookId());
		bo.setFAcctOrgid(book.getFAccountOrgid().intValue());
		bo.setFDate(DateTimeCalculatorUtil.getDateEndOfMonth(book.getFCurrentYear(),month));
		bo.setFAttachments(0);
		bo.setFBaseCurrencyId(book.getFCurrencyId().intValue());
		bo.setFInternalind("3");
		bo.setFVoucherGroupId(vo.getFVchgroupId().intValue());
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
		groupNoQueryBo.setFVoucherGroupId(vo.getFVchgroupId().intValue());
		List<Integer> idList=iTGlVoucherGroupNoService.listVchNo(groupNoQueryBo);
		bo.setFVoucherGroupNo(idList.get(0));
		TGlVoucherGroupNoAddBo noAddBo=new TGlVoucherGroupNoAddBo();
		BeanUtils.copyProperties(groupNoQueryBo,noAddBo);
		noAddBo.setFVoucherGroupNo(bo.getFVoucherGroupNo());
		bo.setFDocumentStatus(DataStatusEnum.TMP_SAVE.getCode());
		iTGlVoucherGroupNoService.insertByAddBo(noAddBo);
		List<TGlVoucherEntryAddBo> voucherEntry=new ArrayList<>();
		//科目分借贷
		Map<String, List<TGlVoucherEntryVo>> map=settleAcctList.stream().collect(Collectors.groupingBy(TGlVoucherEntryVo::getFDc));
		for (Map.Entry<String,  List<TGlVoucherEntryVo>> entry:map.entrySet()){
			BigDecimal debit=BigDecimal.ZERO;
			BigDecimal credit=BigDecimal.ZERO;
			for (TGlVoucherEntryVo entryVo:entry.getValue()){
				if(BorrowEnum.DEBIT.getCode().equals(entry.getKey())){
					debit=debit.add(entryVo.getFDebit()).subtract(entryVo.getFCredit());
				}else {
					credit=credit.add(entryVo.getFCredit()).subtract(entryVo.getFDebit());
				}
				TGlVoucherEntryAddBo addBo=new TGlVoucherEntryAddBo();
				BeanUtils.copyProperties(entryVo,addBo);
				voucherEntry.add(addBo);
			}
			//组织损益科目分录信息
			TGlVoucherEntryAddBo addBo=new TGlVoucherEntryAddBo();
			addBo.setFExplanation(vo.getFExplanation());
			addBo.setFAccountId(vo.getFAccountId());
			//addBo.setFExchangeRate();
			addBo.setFExchangeRateType(book.getFRateTypeId().intValue());
			addBo.setFCurrencyId(book.getFCurrencyId().intValue());

			if(BorrowEnum.DEBIT.getCode().equals(entry.getKey())){
				addBo.setFDc(BorrowEnum.CREDIT.getCode());
				addBo.setFCredit(debit);
			}else {
				addBo.setFDc(BorrowEnum.DEBIT.getCode());
				addBo.setFDebit(credit);
			}
			if(CollectionUtil.isNotEmpty(vo.getFlexList())){
				List<AccountingDimensionBo> dimensionInfo=new ArrayList<>();
				vo.getFlexList().forEach(v->{
					AccountingDimensionBo dimensionBo=new AccountingDimensionBo();
					dimensionBo.setAcctDimsId(v.getFFlexId());
					dimensionBo.setDetailCode(v.getFFlexValue());
					dimensionBo.setDetailName(v.getFFlexName());
					dimensionInfo.add(dimensionBo);
				});
				addBo.setDimensionInfo(dimensionInfo);
			}
			voucherEntry.add(addBo);
		}
		try {
			bo.setFSourceBillKey(VchSourceEnum.INCOME_LOSS.getCode());
			AjaxResult<TGlVoucher> result=iTGlVoucherService.insertByAddBo(bo);
			//增加日志记录
			TBdExecuteLogAddBo logAddBo=new TBdExecuteLogAddBo();
			logAddBo.setCreateTime(new Date());
			logAddBo.setExecuteStatus(BaseEnum.YES.getCode());
			logAddBo.setOutExecuteId(result.getData().getFVoucherId());
			logAddBo.setExecuteDetail("【结转损益】生成凭证-"+bo.getFVoucherGroupNo());
			logAddBo.setExecuteId(vo.getFId().intValue());
			logAddBo.setExecuteType(VchSourceEnum.INCOME_LOSS.getCode());
			iTBdExecuteLogService.insertByAddBo(logAddBo);
		}catch (Exception e){
			log.error("【结转损益调用凭证新增异常】异常信息[{}]",e.getMessage());
			throw new CustomException("执行生成凭证异常");
		}
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateByEditBo(TGlPlschemeEditBo bo) {
		TGlPlscheme old =this.getById(bo.getFId());
		if(null==old){
			throw new CustomException("当前记录不存在");
		}
		TGlPlschemeAddBo update = BeanUtil.toBean(bo, TGlPlschemeAddBo.class);
        validEntityBeforeSave(update);
        BeanUtil.copyProperties(bo,old,"fId","fNumber");
		updateById(old);

		//核算维度
		tGlPlschemeFlexMapper.delete(new LambdaQueryWrapper<TGlPlschemeFlex>().eq(TGlPlschemeFlex::getFId,old.getFId()));
		if(CollectionUtil.isNotEmpty(bo.getFlexBo())){
			bo.getFlexBo().forEach(v->{
				v.setFId(old.getFId());
				iTGlPlschemeFlexService.insertByAddBo(v);
			});
		}
		//新增分录
		tGlPlschemeEntryMapper.delete(new LambdaQueryWrapper<TGlPlschemeEntry>().eq(TGlPlschemeEntry::getFId,old.getFId()));
		bo.getEntryBo().forEach(v->{
			TGlPlschemeEntry entry=new TGlPlschemeEntry();
			BeanUtil.copyProperties(v,entry);
			entry.setFId(old.getFId());
			tGlPlschemeEntryMapper.insert(entry);
		});
        return true;
    }

    /**
     * 保存前的数据校验
     *
     * @param bo 实体类数据
     */
    private void validEntityBeforeSave(TGlPlschemeAddBo bo){
		TBdAccountBook book=itBdAccountBookService.getById(bo.getFAccountBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("账簿不存在");
		}
		TBdVoucherGroup voucherGroup=iTBdVoucherGroupService.getById(bo.getFVchgroupId());
		if(null==voucherGroup || !DataStatusEnum.AUDIT.getCode().equals(voucherGroup.getFDocumentStatus())){
			throw new CustomException("凭证字不存在");
		}
		if(null== ExchangeTransferTypeEnum.getObj(bo.getFTransferType().toString())){
			throw new CustomException("凭证类型不存在");
		}
		if(null== VchCretaeEnum.getObj(bo.getFVoucherType().toString())){
			throw new CustomException("凭证生成方式不存在");
		}
		TBdAccount account=iTBdAccountService.getById(bo.getFAccountId());
		if(null==account || !DataStatusEnum.AUDIT.getCode().equals(account.getFDocumentStatus())){
			throw new CustomException("科目信息不存在");
		}
		//核算维度校验
		List<TGlPlschemeFlexAddBo> dL=bo.getFlexBo();
		Map<Integer,List<TGlPlschemeFlexAddBo>> tmp2=new HashMap<>();
		if(!CollectionUtil.isEmpty(dL)){
			tmp2=dL.stream().filter(v->(v.getFFlexId()!=null)).collect(Collectors.groupingBy(TGlPlschemeFlexAddBo::getFFlexId));
		}
		Map<Integer,List<TGlPlschemeFlexAddBo>> map2=tmp2;
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
		if(CollectionUtil.isEmpty(bo.getEntryBo())){
			throw new CustomException("分录信息不能为空");
		}
		bo.getEntryBo().forEach(v->{
			TBdAccount tmp=iTBdAccountService.getById(v.getFAcctId());
			if(null==tmp || !DataStatusEnum.AUDIT.getCode().equals(tmp.getFDocumentStatus())){
				throw new CustomException("结转科目信息不存在");
			}
		});

    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}
