package com.skeqi.finance.service.impl.endhandle.transfer;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.common.utils.time.DateTimeCalculatorUtil;
import com.skeqi.finance.domain.endhandle.VchQueryBo;
import com.skeqi.finance.domain.rate.TBdRate;
import com.skeqi.finance.enums.*;
import com.skeqi.finance.mapper.TBdAccountPeriodMapper;
import com.skeqi.finance.mapper.basicinformation.accountbook.TBdAccountBookMapper;
import com.skeqi.finance.mapper.basicinformation.voucher.TGlVoucherMapper;
import com.skeqi.finance.mapper.dimension.TBdFlexItemPropertyMapper;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogAddBo;
import com.skeqi.finance.domain.*;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.domain.rate.TBdRateType;
import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransfer;
import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransferEntry;
import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransferEntryItem;
import com.skeqi.finance.domain.voucher.TGlVoucher;
import com.skeqi.finance.mapper.endhandle.TGlAutoTransferEntryItemMapper;
import com.skeqi.finance.mapper.endhandle.TGlAutoTransferEntryMapper;
import com.skeqi.finance.mapper.report.TGlBalanceMapper;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceQueryBo;
import com.skeqi.finance.pojo.bo.voucher.*;
import com.skeqi.finance.pojo.bo.transfer.*;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountFlexentryVo;
import com.skeqi.finance.pojo.vo.period.AccountPeriodVo;
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferEntryItemVo;
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferEntryVo;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherVo;
import com.skeqi.finance.pojo.vo.voucher.VoucherBalanceVo;
import com.skeqi.finance.service.basicinformation.base.ITBdExecuteLogService;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherGroupNoService;
import com.skeqi.finance.service.account.ITBdAccountFlexentryService;
import com.skeqi.finance.service.account.ITBdAccountService;
import com.skeqi.finance.service.basicinformation.base.ITBdCurrencyService;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherService;
import com.skeqi.finance.service.rate.ITBdRateService;
import com.skeqi.finance.service.rate.ITBdRateTypeService;
import com.skeqi.finance.service.basicinformation.accountbook.ITBdAccountBookService;
import com.skeqi.finance.service.basicinformation.voucher.ITBdVoucherGroupService;
import com.skeqi.finance.service.endhandle.transfer.ITGlAutoTransferEntryItemService;
import com.skeqi.finance.service.endhandle.transfer.ITGlAutoTransferEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.mapper.endhandle.TGlAutoTransferMapper;
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferVo;
import com.skeqi.finance.service.endhandle.transfer.ITGlAutoTransferService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ???????????????Service???????????????
 *
 * @author toms
 * @date 2021-07-26
 */
@Slf4j
@Service
public class TGlAutoTransferServiceImpl extends ServicePlusImpl<TGlAutoTransferMapper, TGlAutoTransfer> implements ITGlAutoTransferService {

	@Autowired
	ITBdAccountBookService itBdAccountBookService;
	@Autowired
	ITBdVoucherGroupService iTBdVoucherGroupService;
	@Autowired
	ITBdAccountService iTBdAccountService;
	@Autowired
	ITBdCurrencyService iTBdCurrencyService;
	@Autowired
	ITBdRateTypeService iTBdRateTypeService;
	@Autowired
	TGlAutoTransferEntryMapper tGlAutoTransferEntryMapper;
	@Autowired
	ITGlAutoTransferEntryService itGlAutoTransferEntryService;
	@Autowired
	ITBdAccountFlexentryService itBdAccountFlexentryService;
	@Autowired
	TGlAutoTransferEntryItemMapper tGlAutoTransferEntryItemMapper;
	@Autowired
	ITGlAutoTransferEntryItemService itGlAutoTransferEntryItemService;
	@Autowired
	ITGlVoucherService iTGlVoucherService;
	@Autowired
	TGlBalanceMapper tGlBalanceMapper;
	@Autowired
	ITBdExecuteLogService iTBdExecuteLogService;
	@Autowired
	ITGlVoucherGroupNoService iTGlVoucherGroupNoService;
	@Autowired
	TBdFlexItemPropertyMapper tBdFlexItemPropertyMapper;
	@Autowired
	TGlVoucherMapper tGlVoucherMapper;
	@Autowired
	ITBdRateService itBdRateService;
	@Resource
	TBdAccountBookMapper tBdAccountBookMapper;
	@Autowired
	TBdAccountPeriodMapper tBdAccountPeriodMapper;
    @Override
    public TGlAutoTransferVo queryById(Integer fTransferId){
		TGlAutoTransferVo vo=baseMapper.queryInfoById(fTransferId);
		if(null==vo){
			return null;
		}
		List<TGlAutoTransferEntryVo> list=itGlAutoTransferEntryService.queryListByPid(fTransferId);
        vo.setTransferEntries(list);
        if(CollectionUtil.isNotEmpty(list)){
			list.forEach(v->{
				if(BaseEnum.YES.getCode().equals(v.getFItemIsvalid())){
					TGlAutoTransferEntryItemQueryBo bo=new TGlAutoTransferEntryItemQueryBo();
					bo.setFTransferEntryId(v.getFTransferEntryId());
					List<TGlAutoTransferEntryItemVo> vl=itGlAutoTransferEntryItemService.queryList(bo);
					v.setItemVoList(vl);
				}
			});
		}
        return vo;
    }

    @Override
    public TableDataInfo<TGlAutoTransferVo> queryPageList(TGlAutoTransferQueryBo bo) {
    	Page<TGlAutoTransferQueryBo> page=new Page<>(bo.getPageNum(), bo.getPageSize());
        IPage<TGlAutoTransferVo> result=baseMapper.queryPageList(page,bo);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlAutoTransferVo> queryList(TGlAutoTransferQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlAutoTransferVo.class);
    }

    private LambdaQueryWrapper<TGlAutoTransfer> buildQueryWrapper(TGlAutoTransferQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlAutoTransfer> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TGlAutoTransfer::getFNumber, bo.getFNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFName()), TGlAutoTransfer::getFName, bo.getFName());
        lqw.eq(bo.getFBookId() != null, TGlAutoTransfer::getFBookId, bo.getFBookId());
        lqw.eq(StrUtil.isNotBlank(bo.getFTransferType()), TGlAutoTransfer::getFTransferType, bo.getFTransferType());
        lqw.eq(bo.getFVoucherGroupId() != null, TGlAutoTransfer::getFVoucherGroupId, bo.getFVoucherGroupId());
        lqw.eq(StrUtil.isNotBlank(bo.getFPeriodRange()), TGlAutoTransfer::getFPeriodRange, bo.getFPeriodRange());
        lqw.eq(bo.getFLastDate() != null, TGlAutoTransfer::getFLastDate, bo.getFLastDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TGlAutoTransfer::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderId() != null, TGlAutoTransfer::getFForbidderId, bo.getFForbidderId());
        lqw.eq(bo.getFForbidDate() != null, TGlAutoTransfer::getFForbidDate, bo.getFForbidDate());
        return lqw;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertByAddBo(TGlAutoTransferAddBo bo) {
        TGlAutoTransfer add = BeanUtil.toBean(bo, TGlAutoTransfer.class);
        validEntityBeforeSave(bo);
        add.setFForbidStatus(BaseEnum.NO.getCode());
		this.save(add);
        //??????????????????
		List<TGlAutoTransferEntryAddBo> entryList=bo.getTransferEntry();
		entryList.forEach(v->{
			TGlAutoTransferEntry entry =new TGlAutoTransferEntry();
			BeanUtil.copyProperties(v,entry);
			entry.setFTransferId(add.getFTransferId());
			tGlAutoTransferEntryMapper.insert(entry);
			//??????????????????
			List<TGlAutoTransferEntryItemAddBo> itemBo = v.getItemBo();
			if(CollectionUtil.isNotEmpty(itemBo)){
				List<TGlAutoTransferEntryItem> items=new ArrayList<>();
				itemBo.forEach(v2->{
					TGlAutoTransferEntryItem item=new TGlAutoTransferEntryItem();
					BeanUtil.copyProperties(v2,item);
					item.setFTransferEntryId(entry.getFTransferEntryId());
					items.add(item);
				});
				tGlAutoTransferEntryItemMapper.insertAll(items);

			}
		});
        return true;
    }

	@Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateByEditBo(TGlAutoTransferEditBo bo) {
		TGlAutoTransfer old=this.getById(bo.getFTransferId());
		if(null==old){
			throw new CustomException("??????????????????????????????");
		}
        TGlAutoTransferAddBo addBo = BeanUtil.toBean(bo, TGlAutoTransferAddBo.class);
        validEntityBeforeSave(addBo);
        BeanUtils.copyProperties(addBo,old);
		this.updateById(old);
		//??????????????????
		List<TGlAutoTransferEntryAddBo> entryList=bo.getTransferEntry();
		tGlAutoTransferEntryMapper.delByTransferId(old.getFTransferId());
		entryList.forEach(v->{
			TGlAutoTransferEntry entry =new TGlAutoTransferEntry();
			BeanUtil.copyProperties(v,entry);
			entry.setFTransferId(old.getFTransferId());
			tGlAutoTransferEntryMapper.insert(entry);

			//??????????????????
			List<TGlAutoTransferEntryItemAddBo> itemBo = v.getItemBo();
			if(CollectionUtil.isNotEmpty(itemBo)){
				tGlAutoTransferEntryItemMapper.delByTransferEntryId(v.getFTransferEntryId());
				List<TGlAutoTransferEntryItem> items=new ArrayList<>();
				itemBo.forEach(v2->{
					TGlAutoTransferEntryItem item=new TGlAutoTransferEntryItem();
					BeanUtil.copyProperties(v2,item);
					item.setFTransferEntryId(entry.getFTransferEntryId());
					items.add(item);
				});
				tGlAutoTransferEntryItemMapper.insertAll(items);
			}
		});
        return true;
    }

	/**
	 * ????????????
	 * @param fId
	 * @return
	 */
	@Override
	public AjaxResult execute(Integer fId){
		TGlAutoTransferVo vo=this.queryById(fId);
		if(null==vo){
			throw new CustomException("?????????????????????");
		}
		if(BaseEnum.YES.getCode().equals(vo.getFForbidStatus())){
			throw new CustomException("????????????????????????????????????");
		}
		TBdAccountBook book=itBdAccountBookService.getById(vo.getFBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("???????????????",1000);
		}
		List<Integer> idList = Arrays.stream(vo.getFPeriodRange().split(",")).map(Integer::parseInt).collect(Collectors.toList());
		if(!idList.contains(book.getFCurrentPeriod().intValue())){
			log.error("??????????????????[{}]","?????????????????????????????????");
			throw new CustomException("?????????????????????????????????");
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
		List<Integer> noList=iTGlVoucherGroupNoService.listVchNo(groupNoQueryBo);
		bo.setFVoucherGroupNo(noList.get(0));
		TGlVoucherGroupNoAddBo noAddBo=new TGlVoucherGroupNoAddBo();
		BeanUtils.copyProperties(groupNoQueryBo,noAddBo);
		noAddBo.setFVoucherGroupNo(bo.getFVoucherGroupNo());
		bo.setFDocumentStatus(DataStatusEnum.TMP_SAVE.getCode());
		iTGlVoucherGroupNoService.insertByAddBo(noAddBo);
		List<TGlVoucherEntryAddBo> voucherEntry=new ArrayList<>();
		if(CollectionUtil.isNotEmpty(vo.getTransferEntries())){
			//????????????
			BigDecimal outSumMoney=BigDecimal.ZERO;
			String fdc=BorrowEnum.DEBIT.getCode();
			for (TGlAutoTransferEntryVo v:vo.getTransferEntries()){
				if(!TransferTypeEnum.OUT.getCode().equals(v.getFType())){
					continue;
				}
				TGlVoucherEntryAddBo addBo=new TGlVoucherEntryAddBo();
				addBo.setFExplanation(v.getFExplanation());
				addBo.setFAccountId(v.getFAccountId());
				addBo.setFCurrencyId(v.getFCurrencyId());
				addBo.setFExchangeRateType(v.getFExchangeRateType());
				if(book.getFCurrencyId()==v.getFCurrencyId().longValue()){
					addBo.setFExchangeRate(BigDecimal.ONE);
				}else {
					TBdRate tBdRate=itBdRateService.getRate(null,v.getFExchangeRateType(),v.getFCurrencyId(),book.getFCurrencyId().intValue());
					if(null==tBdRate){
						throw new CustomException("????????????????????????????????????????????????????????????",1000);
					}
					addBo.setFExchangeRate(tBdRate.getFExchangeRate());
				}

				//????????????????????? ????????????
				//?????????????????????
				VoucherBalanceBo queryBo=new VoucherBalanceBo();
				queryBo.setFYear(book.getFCurrentYear().intValue());
				queryBo.setFPeriod(book.getFCurrentPeriod().intValue());
				queryBo.setFAccountBookId(book.getFBookId());
				queryBo.setFAccountId(v.getFAccountId());
				queryBo.setFCurrencyId(v.getFCurrencyId());
				List<TGlAutoTransferEntryItemVo> item=v.getItemVoList();
				if(CollectionUtil.isNotEmpty(item)){
					List<String> itemList=item.stream().map(TGlAutoTransferEntryItemVo::getFBeginItemNumber).collect(Collectors.toList());
					String detailCode=StrUtil.join("/",itemList);
					queryBo.setFDetailCode(detailCode);
				}
				//??????????????????
				if(BaseEnum.NO.getCode().equals(v.getFPosted())){
					queryBo.setFPosted(BaseEnum.YES.getCode());
				}
				VoucherBalanceVo balance=tGlVoucherMapper.queryVoucherBalance(queryBo);
				TBdAccount account=iTBdAccountService.getById(v.getFAccountId());
				//??????-?????? <0 ?????????  >0??????
				BigDecimal cd=balance.getFDebit().subtract(balance.getFCredit()).multiply(v.getFPercentage()).setScale(2,BigDecimal.ROUND_HALF_UP);
				outSumMoney=outSumMoney.add(cd);
				if(BorrowEnum.DEBIT.getCode().equals(account.getFDc())){
					addBo.setFCredit(cd);
					addBo.setFDebit(BigDecimal.ZERO);
					//??????????????????????????????????????????
					addBo.setFAmountfor(cd);
					fdc=BorrowEnum.CREDIT.getCode();
				}else {
					addBo.setFCredit(BigDecimal.ZERO);
					addBo.setFDebit(cd);
					//??????????????????????????????????????????
					addBo.setFAmountfor(cd);

				}
				//??????
				if(CollectionUtil.isNotEmpty(item)){
					List<AccountingDimensionBo> dimensionInfo=new ArrayList<>();

					item.forEach(i->{
						AccountingDimensionBo d=new AccountingDimensionBo();
						d.setAcctDimsId(i.getFFlexitemPropertyId());
						d.setDetailCode(i.getFBeginItemNumber());
						d.setDetailName(i.getFItemNumber());
						dimensionInfo.add(d);
					});
					addBo.setDimensionInfo(dimensionInfo);
				}
				voucherEntry.add(addBo);
			}
			//??????
			for (TGlAutoTransferEntryVo v:vo.getTransferEntries()){
				if(!TransferTypeEnum.OUT.getCode().equals(v.getFType())){
					TGlVoucherEntryAddBo addBo=new TGlVoucherEntryAddBo();
					addBo.setFExplanation(v.getFExplanation());
					addBo.setFAccountId(v.getFAccountId());
					addBo.setFCurrencyId(v.getFCurrencyId());
					addBo.setFExchangeRateType(v.getFExchangeRateType());
					BigDecimal avg=outSumMoney.multiply(v.getFPercentage()).setScale(2,BigDecimal.ROUND_HALF_UP);
					if(BorrowEnum.DEBIT.getCode().equals(fdc)){
						addBo.setFCredit(avg);
						addBo.setFDebit(BigDecimal.ZERO);
						//??????????????????????????????????????????
						addBo.setFAmountfor(avg);
						fdc=BorrowEnum.CREDIT.getCode();
					}else {
						addBo.setFCredit(BigDecimal.ZERO);
						addBo.setFDebit(avg);
						//??????????????????????????????????????????
						addBo.setFAmountfor(avg);

					}

					//??????
					List<TGlAutoTransferEntryItemVo> item=v.getItemVoList();
					if(CollectionUtil.isNotEmpty(item)){
						List<AccountingDimensionBo> dimensionInfo=new ArrayList<>();

						item.forEach(i->{
							AccountingDimensionBo d=new AccountingDimensionBo();
							d.setAcctDimsId(i.getFFlexitemPropertyId());
							d.setDetailCode(i.getFBeginItemNumber());
							d.setDetailName(i.getFItemNumber());
							dimensionInfo.add(d);
						});
						addBo.setDimensionInfo(dimensionInfo);
					}
					voucherEntry.add(addBo);
				}

			}

		}
		bo.setVoucherEntry(voucherEntry);
		AjaxResult res=null;
		try {
			bo.setFSourceBillKey(VchSourceEnum.AUTO_TRANSFER.getCode());
			AjaxResult<TGlVoucher> result=iTGlVoucherService.insertByAddBo(bo);
			//??????????????????
			TBdExecuteLogAddBo logAddBo=new TBdExecuteLogAddBo();
			logAddBo.setCreateTime(new Date());
			logAddBo.setExecuteStatus(BaseEnum.YES.getCode());
			logAddBo.setOutExecuteId(result.getData().getFVoucherId());
			logAddBo.setExecuteDetail("??????????????????????????????-"+bo.getFVoucherGroupNo());
			logAddBo.setExecuteId(vo.getFTransferId());
			logAddBo.setExecuteType(VchSourceEnum.AUTO_TRANSFER.getCode());
			 res=iTBdExecuteLogService.insertByAddBo(logAddBo);
		}catch (Exception e){
			log.error("??????????????????????????????????????????????????????[{}]",e.getMessage());
			throw new CustomException("????????????????????????");
		}finally {
			TGlAutoTransfer transfer=this.baseMapper.selectById(fId);
			transfer.setFLastDate(new Date());
			this.updateById(transfer);
		}
		return AjaxResult.success(res.getData());
	}

    /**
     * ????????????????????????
     *
     * @param bo ???????????????
     */
    private void validEntityBeforeSave(TGlAutoTransferAddBo bo){
		try {
			List<Integer> idList = Arrays.stream(bo.getFPeriodRange().split(",")).map(Integer::parseInt).collect(Collectors.toList());
			if(CollectionUtil.isEmpty(idList)){
				throw new CustomException("????????????????????????");
			}
		}catch (Exception e){
			throw new CustomException("????????????????????????");
		}
		TBdAccountBook book=itBdAccountBookService.getById(bo.getFBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("???????????????",1000);
		}
		TBdVoucherGroup voucherGroup=iTBdVoucherGroupService.getById(bo.getFVoucherGroupId());
		if(null==voucherGroup || !DataStatusEnum.AUDIT.getCode().equals(voucherGroup.getFDocumentStatus())){
			throw new CustomException("??????????????????",1000);
		}
		List<TGlAutoTransferEntryAddBo> entryList=bo.getTransferEntry();
		if(CollectionUtil.isEmpty(entryList)){
			throw new CustomException("????????????????????????",1000);
		}
		if(entryList.size()<2){
			throw new CustomException("???????????????????????????",1000);
		}
		BigDecimal inRate=BigDecimal.ZERO;
		BigDecimal outRate=BigDecimal.ZERO;
		for (int i=1;i<=entryList.size();i++){
			TGlAutoTransferEntryAddBo v=entryList.get(i-1);
			TBdAccount account=iTBdAccountService.getById(v.getFAccountId());
			if(null==account || !DataStatusEnum.AUDIT.getCode().equals(account.getFDocumentStatus())){
				throw new CustomException("???"+i+"????????????????????????",1000);
			}
			TBdCurrency currency=iTBdCurrencyService.getById(v.getFCurrencyId());
			if(null==currency || !DataStatusEnum.AUDIT.getCode().equals(currency.getFDocumentStatus())){
				throw new CustomException("???"+i+"??????????????????",1000);
			}
			TBdRateType rateType=iTBdRateTypeService.getById(v.getFExchangeRateType());
			if(null==rateType || !DataStatusEnum.AUDIT.getCode().equals(rateType.getFDocumentStatus())){
				throw new CustomException("???"+i+"????????????????????????",1000);
			}
			if(null==BaseEnum.getObj(v.getFPosted())){
				throw new CustomException("???"+i+"????????????????????????",1000);
			}
			if(null==BaseEnum.getObj(v.getFItemIsvalid())){
				throw new CustomException("???"+i+"??????????????????????????????????????????",1000);
			}
			if(BaseEnum.YES.getCode().equals(v.getFItemIsvalid())){
				if(CollectionUtil.isEmpty(v.getItemBo())){
					throw new CustomException("???"+i+"????????????????????????????????????????????????",1000);
				}
			}
			if(null==TransferTypeEnum.getObj(v.getFType())){
				throw new CustomException("???"+i+"????????????????????????",1000);
			}
			if(null==BorrowEnum.getObj(v.getFDc().toString())){
				throw new CustomException("???"+i+"??????????????????",1000);
			}
			//??????
			if(TransferTypeEnum.IN.getCode().equals(v.getFType())){
				inRate=inRate.add(v.getFPercentage());
			}else {
				outRate=outRate.add(v.getFPercentage());
			}
			List<TGlAutoTransferEntryItemAddBo> dL=v.getItemBo();
			Map<Integer,List<TGlAutoTransferEntryItemAddBo>> tmp=new HashMap<>();
			if(!CollectionUtil.isEmpty(dL)){
				dL.forEach(d->{
					TBdFlexItemProperty source=tBdFlexItemPropertyMapper.selectById(d.getFFlexitemPropertyId());
					if(null==source || !DataStatusEnum.AUDIT.getCode().equals(source.getFDocumentStatus())){
						throw new CustomException("?????????????????????");
					}
				});
				tmp=dL.stream().filter(item->(StrUtil.isNotBlank(item.getFBeginItemNumber()))).collect(Collectors.groupingBy(TGlAutoTransferEntryItemAddBo::getFFlexitemPropertyId));
			}
			Map<Integer,List<TGlAutoTransferEntryItemAddBo>> map=tmp;
			//?????????????????????
			List<TBdAccountFlexentryVo> fList=itBdAccountFlexentryService.queryByAcctId(account.getFAcctId().intValue());
			if(!CollectionUtil.isEmpty(fList)){
				boolean op=fList.stream().filter(vf->(
						"1".equals(vf.getFInputType()) && (CollectionUtil.isEmpty(map) || !map.containsKey(vf.getFFlexitempropertyId()))
					)
				).findFirst().isPresent();
				if(op){
					throw new CustomException("???"+i+"????????????????????????????????????",1000);
				}
				v.setFItemIsvalid(BaseEnum.YES.getCode());
			}
		}
		if(inRate.compareTo(new BigDecimal(100))!=0){
			throw new CustomException("???????????????[??????]??????[????????????]??????????????????100%???",1000);
		}
		if(outRate.compareTo(BigDecimal.ZERO)<=0){
			throw new CustomException("???????????????[?????????????????????]???????????????[????????????]????????????0%???",1000);
		}
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            ids.forEach(v->{
            	baseMapper.deleteById(v);
            	//????????????
				QueryWrapper<TGlAutoTransferEntry> wrapper = Wrappers.query();
				wrapper.eq("f_transfer_id",v);
				List<TGlAutoTransferEntry> list=tGlAutoTransferEntryMapper.selectList(wrapper);
				if(CollectionUtil.isNotEmpty(list)){
					list.forEach(v2->{
						tGlAutoTransferEntryItemMapper.delByTransferEntryId(v2.getFTransferEntryId());
					});
				}
				tGlAutoTransferEntryMapper.delByTransferId(v);
			});
        }
        return removeByIds(ids);
    }

	/**
	 * ????????????????????????
	 * @return
	 */
	@Override
	public AjaxResult queryVchLogPage(VchQueryBo bo){
		Page<VchQueryBo> page=new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TGlVoucherVo> iPage=baseMapper.queryVchLogPage(page,bo);
	   return AjaxResult.success(iPage);
	}

	/**
	 * ??????
	 * @param ids
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResult settleAcct(Collection<Integer> ids){
        ids.forEach(id->{
			TBdAccountBook book=tBdAccountBookMapper.selectById(id);
			if(!book.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())){
                throw new CustomException(book.getFBookName()+"???????????????????????????");
			}
			List<TGlVoucher> list=tGlVoucherMapper.queryListByBook(book.getFBookId(),book.getFCurrentYear(),book.getFCurrentPeriod().intValue());
			List<TGlVoucher> noPostedList =list.stream().filter(v->v.getFPosted().equals(BaseEnum.NO.getCode())).collect(Collectors.toList());
			if(CollectionUtil.isNotEmpty(noPostedList)){
				throw new CustomException(book.getFBookName()+"??????????????????????????????????????????");
			}
			//????????????
			if(CollectionUtil.isNotEmpty(list)){
				list.forEach(v->{
					v.setFSettle(BaseEnum.YES.getCode());
					tGlVoucherMapper.updateById(v);
				});
			}
			//??????????????????
			AccountPeriodVo accountPeriodVo=tBdAccountPeriodMapper.queryNextPeriod(book.getFPeriodId().intValue(),book.getFCurrentYear().intValue(),book.getFCurrentYear().intValue()+1,book.getFCurrentPeriod().intValue());
             if(null==accountPeriodVo){
				 throw new CustomException(book.getFBookName()+"??????????????????????????????????????????");
			 }
			book.setFCurrentYear(accountPeriodVo.getFYear());
			book.setFCurrentPeriod(accountPeriodVo.getFPeriod().longValue());
			book.setFModifyDate(new Date());
			tBdAccountBookMapper.updateById(book);

		});
		return AjaxResult.success();
	}

	/**
	 * ?????????
	 * @param ids
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResult settleAcctNo(Collection<Integer> ids){
		ids.forEach(id->{
			TBdAccountBook book=tBdAccountBookMapper.selectById(id);
			if(!book.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())){
				throw new CustomException(book.getFBookName()+"???????????????????????????");
			}
			AccountPeriodVo accountPeriodVo=tBdAccountPeriodMapper.queryPrePeriod(book.getFPeriodId().intValue(),book.getFCurrentYear().intValue(),book.getFCurrentYear().intValue()-1,book.getFCurrentPeriod().intValue());
			if(null==accountPeriodVo){
				throw new CustomException(book.getFBookName()+"?????????????????????????????????????????????");
			}
			List<TGlVoucher> list=tGlVoucherMapper.queryListByBook(book.getFBookId(),accountPeriodVo.getFYear(),accountPeriodVo.getFPeriod().intValue());
			if(CollectionUtil.isNotEmpty(list)){
				//????????????
				list.forEach(v->{
					v.setFSettle(BaseEnum.NO.getCode());
					tGlVoucherMapper.updateById(v);
				});
			}
		});
		return AjaxResult.success();
	}
}
