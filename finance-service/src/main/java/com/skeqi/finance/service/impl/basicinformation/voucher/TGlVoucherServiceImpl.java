package com.skeqi.finance.service.impl.basicinformation.voucher;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.common.utils.time.DateTimeCalculatorUtil;
import com.skeqi.common.utils.time.DateTimeFormatterUtil;
import com.skeqi.finance.domain.*;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlow;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.domain.rate.TBdRate;
import com.skeqi.finance.domain.rate.TBdRateType;
import com.skeqi.finance.domain.voucher.TGlVoucher;
import com.skeqi.finance.domain.voucher.TGlVoucherEntry;
import com.skeqi.finance.domain.voucher.TGlVoucherEntryCash;
import com.skeqi.finance.domain.voucher.TGlVoucherEntryDimension;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.BorrowEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.TBdAccountPeriodMapper;
import com.skeqi.finance.mapper.basicinformation.voucher.TBdVchgroupAcctMapper;
import com.skeqi.finance.mapper.cashflow.TGlVoucherEntryCashMapper;
import com.skeqi.finance.mapper.dimension.TBdFlexItemPropertyMapper;
import com.skeqi.finance.mapper.rate.TBdRateMapper;
import com.skeqi.finance.mapper.basicinformation.voucher.TGlVoucherEntryDimensionMapper;
import com.skeqi.finance.mapper.basicinformation.voucher.TGlVoucherEntryMapper;
import com.skeqi.finance.mapper.report.TGlBalanceMapper;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceVoucherBo;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoAddBo;
import com.skeqi.finance.pojo.bo.cashflow.CashFlowBO;
import com.skeqi.finance.pojo.bo.cashflow.TGlVoucherCashFlowBo;
import com.skeqi.finance.pojo.bo.voucher.*;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountFlexentryVo;
import com.skeqi.finance.pojo.vo.book.TBdAccountBookVo;
import com.skeqi.finance.pojo.vo.cashflow.CashFlowAcctVo;
import com.skeqi.finance.pojo.vo.cashflow.TGlVoucherEntryCashVo;
import com.skeqi.finance.pojo.vo.cashflow.VoucherCashFlowVo;
import com.skeqi.finance.pojo.vo.voucher.*;
import com.skeqi.finance.service.*;
import com.skeqi.finance.service.account.ITBdAccountFlexentryService;
import com.skeqi.finance.service.account.ITBdAccountService;
import com.skeqi.finance.service.account.ITBdAccountTableService;
import com.skeqi.finance.service.basicinformation.base.ITBdCurrencyService;
import com.skeqi.finance.service.basicinformation.accountbook.ITBdAccountBookService;
import com.skeqi.finance.service.basicinformation.cashflow.ITGlCashFlowService;
import com.skeqi.finance.service.basicinformation.voucher.*;
import com.skeqi.finance.service.cashflow.ITGlVoucherEntryCashService;
import com.skeqi.finance.service.rate.ITBdRateService;
import com.skeqi.finance.service.rate.ITBdRateTypeService;
import com.skeqi.finance.service.report.ITGlBalanceService;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.mapper.basicinformation.voucher.TGlVoucherMapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 凭证录入主Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TGlVoucherServiceImpl extends ServicePlusImpl<TGlVoucherMapper, TGlVoucher> implements ITGlVoucherService {

	@Autowired
	ISysOrganizationService iSysOrganizationService;

	@Autowired
	ITBdAccountBookService itBdAccountBookService;
	@Autowired
	ITBdVoucherGroupService iTBdVoucherGroupService;
	@Autowired
	ITGlExplanationService itGlExplanationService;
	@Autowired
    ITBdAccountTableService iTBdAccountTableService;
	@Autowired
    ITBdAccountService iTBdAccountService;
	@Autowired
	ITBdAccountFlexentryService itBdAccountFlexentryService;
	@Autowired
	ITBdCurrencyService iTBdCurrencyService;
	@Autowired
	ITBdRateTypeService iTBdRateTypeService;
	@Autowired
	ITGlVoucherEntryService iTGlVoucherEntryService;

	@Autowired
	ITGlVoucherEntryCashService iTGlVoucherEntryCashService;
	@Autowired
	ITGlVoucherEntryDimensionService iTGlVoucherEntryDimensionService;
	@Autowired
	TokenService tokenService;
	@Autowired
	ITGlCashFlowService iTGlCashFlowService;
	@Autowired
	TBdRateMapper tBdRateMapper;
	@Autowired
	ITBdRateService itBdRateService;

	@Autowired
	TGlVoucherMapper tGlVoucherMapper;

	@Autowired
	TGlVoucherEntryMapper tGlVoucherEntryMapper;

	@Autowired
	TGlVoucherEntryDimensionMapper tGlVoucherEntryDimensionMapper;
	@Autowired
	ITGlBalanceService iTGlBalanceService;
	@Autowired
	private TBdVchgroupAcctMapper tBdVchgroupAcctMapper;
	@Autowired
	TBdAccountPeriodMapper tBdAccountPeriodMapper;
	@Autowired
	TGlBalanceMapper tGlBalanceMapper;
	@Autowired
	ITGlVoucherGroupNoService iTGlVoucherGroupNoService;
	@Autowired
	TBdFlexItemPropertyMapper tBdFlexItemPropertyMapper;
    @Autowired
	TGlVoucherEntryCashMapper tGlVoucherEntryCashMapper;
	/**
	 * 获取详情
	 * @param fVoucherId
	 * @return
	 */
    @Override
    public VoucherVo queryById(Integer fVoucherId){
		VoucherVo voucherVo=tGlVoucherMapper.getInfoById(fVoucherId);
		if(null==voucherVo){
          return null;
		}
        List<VoucherEntryVo> entryVos =tGlVoucherEntryMapper.getPageByVid(fVoucherId);
		if(CollectionUtil.isEmpty(entryVos)){
			return voucherVo;
		}
		entryVos.forEach(v->{
			TGlVoucherEntryDimensionVo voucherEntryDimensionVo=tGlVoucherEntryDimensionMapper.getByEntryId(v.getFEntryId());
            v.setDimensionVo(voucherEntryDimensionVo);
		});
		voucherVo.setEntryVoList(entryVos);
        return voucherVo;
    }


    @Override
    public TableDataInfo<VoucherPageVo> queryPageList(TGlVoucherQueryBo bo) {
		Page<TGlVoucherQueryBo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<VoucherPageVo> iPage=tGlVoucherMapper.getVoucherPage(page,bo);
		return PageUtils.buildDataInfo(iPage);
    }

    @Override
    public List<TGlVoucherVo> queryList(TGlVoucherQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlVoucherVo.class);
    }

    private LambdaQueryWrapper<TGlVoucher> buildQueryWrapper(TGlVoucherQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlVoucher> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFAccountBookId() != null, TGlVoucher::getFAccountBookId, bo.getFAccountBookId());
        lqw.eq(bo.getFAcctOrgid() != null, TGlVoucher::getFAcctOrgid, bo.getFAcctOrgid());
        lqw.eq(bo.getFDate() != null, TGlVoucher::getFDate, bo.getFDate());
        lqw.eq(bo.getFYear() != null, TGlVoucher::getFYear, bo.getFYear());
        lqw.eq(bo.getFPeriod() != null, TGlVoucher::getFPeriod, bo.getFPeriod());
        lqw.eq(StrUtil.isNotBlank(bo.getFBillNo()), TGlVoucher::getFBillNo, bo.getFBillNo());
        lqw.eq(bo.getFVoucherGroupId() != null, TGlVoucher::getFVoucherGroupId, bo.getFVoucherGroupId());
        lqw.eq(bo.getFVoucherGroupNo() != null, TGlVoucher::getFVoucherGroupNo, bo.getFVoucherGroupNo());
        lqw.eq(bo.getFAttachments() != null, TGlVoucher::getFAttachments, bo.getFAttachments());
        lqw.eq(StrUtil.isNotBlank(bo.getFInternalind()), TGlVoucher::getFInternalind, bo.getFInternalind());
        lqw.eq(StrUtil.isNotBlank(bo.getFReference()), TGlVoucher::getFReference, bo.getFReference());
        lqw.eq(bo.getFSettleTypeId() != null, TGlVoucher::getFSettleTypeId, bo.getFSettleTypeId());
        lqw.eq(StrUtil.isNotBlank(bo.getFSettleNo()), TGlVoucher::getFSettleNo, bo.getFSettleNo());
        lqw.eq(bo.getFBaseCurrencyId() != null, TGlVoucher::getFBaseCurrencyId, bo.getFBaseCurrencyId());
        lqw.eq(bo.getFDebitTotal() != null, TGlVoucher::getFDebitTotal, bo.getFDebitTotal());
        lqw.eq(bo.getFCreditTotal() != null, TGlVoucher::getFCreditTotal, bo.getFCreditTotal());
        lqw.eq(bo.getFCashierId() != null, TGlVoucher::getFCashierId, bo.getFCashierId());
        lqw.eq(bo.getFCreatorid() != null, TGlVoucher::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TGlVoucher::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFModifierid() != null, TGlVoucher::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TGlVoucher::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TGlVoucher::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(StrUtil.isNotBlank(bo.getFChecked()), TGlVoucher::getFChecked, bo.getFChecked());
        lqw.eq(bo.getFCheckerId() != null, TGlVoucher::getFCheckerId, bo.getFCheckerId());
        lqw.eq(bo.getFAuditDate() != null, TGlVoucher::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFPosted()), TGlVoucher::getFPosted, bo.getFPosted());
        lqw.eq(bo.getFPosterId() != null, TGlVoucher::getFPosterId, bo.getFPosterId());
        lqw.eq(bo.getFPostDate() != null, TGlVoucher::getFPostDate, bo.getFPostDate());
        lqw.eq(bo.getFAdjustPeriod() != null, TGlVoucher::getFAdjustPeriod, bo.getFAdjustPeriod());
        lqw.eq(StrUtil.isNotBlank(bo.getFInvalid()), TGlVoucher::getFInvalid, bo.getFInvalid());
        lqw.eq(bo.getFInvaliderId() != null, TGlVoucher::getFInvaliderId, bo.getFInvaliderId());
        lqw.eq(bo.getFMapvchId() != null, TGlVoucher::getFMapvchId, bo.getFMapvchId());
        lqw.eq(StrUtil.isNotBlank(bo.getFSourceBillKey()), TGlVoucher::getFSourceBillKey, bo.getFSourceBillKey());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsadjustVoucher()), TGlVoucher::getFIsadjustVoucher, bo.getFIsadjustVoucher());
        lqw.eq(StrUtil.isNotBlank(bo.getFSystemId()), TGlVoucher::getFSystemId, bo.getFSystemId());
        lqw.eq(StrUtil.isNotBlank(bo.getFIscashFlow()), TGlVoucher::getFIscashFlow, bo.getFIscashFlow());
        lqw.eq(bo.getFIssplit() != null, TGlVoucher::getFIssplit, bo.getFIssplit());
        lqw.eq(bo.getFIsvirPost() != null, TGlVoucher::getFIsvirPost, bo.getFIsvirPost());
        lqw.eq(bo.getFPrintTimes() != null, TGlVoucher::getFPrintTimes, bo.getFPrintTimes());
        return lqw;
    }

	@Transactional(rollbackFor=Exception.class)
    @Override
    public AjaxResult insertByAddBo(TGlVoucherAddBo bo) {
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		Map map=validEntityBeforeSave(bo);
        TGlVoucher add = BeanUtil.toBean(bo, TGlVoucher.class);
		add.setFCreateDate(new Date());
		add.setFBaseCurrencyId(Integer.parseInt(map.get("orgCurrency").toString()));
		add.setFDebitTotal(new BigDecimal(map.get("debitMoney").toString()));
		add.setFCreditTotal(new BigDecimal(map.get("creditMoney").toString()));
		add.setFIscashFlow(map.get("fIsadjustVoucher").toString());
		add.setFCreatorid(sysUser.getUserId().intValue());
		if(null==add.getFDocumentStatus()){
			add.setFDocumentStatus(DataStatusEnum.CREATE.getCode());
		}
		//凭证号重复校验
		Integer vchNoCount=tGlVoucherMapper.countVchNo(add.getFAccountBookId(),add.getFYear(), add.getFPeriod(), add.getFVoucherGroupId(),add.getFVoucherGroupNo());
		if(vchNoCount!=null && vchNoCount>0){
			throw new CustomException("凭证号重复");
		}
		//新增凭证
        baseMapper.insert(add);
        //新增凭证号记录
		TGlVoucherGroupNoAddBo noAddBo=new TGlVoucherGroupNoAddBo();
		noAddBo.setFBookId(add.getFAccountBookId());
		noAddBo.setFPeriod(add.getFPeriod());
		noAddBo.setFYear(add.getFYear());
		noAddBo.setFVoucherGroupId(add.getFVoucherGroupId());
		noAddBo.setFVoucherGroupNo(add.getFVoucherGroupNo());
		iTGlVoucherGroupNoService.insertByAddBo(noAddBo);
		//新增凭证分录数据
		List<TGlVoucherEntryAddBo> list=bo.getVoucherEntry();
		list.forEach(v->{
			v.setFVoucherId(add.getFVoucherId());
			Integer veId=iTGlVoucherEntryService.insertByAddBo(v);
            //新增维度数据
			if(CollectionUtil.isNotEmpty(v.getDimensionInfo())){
				TGlVoucherEntryDimension dimension=new TGlVoucherEntryDimension();
				dimension.setVoucherEntryId(veId);
				dimension.setBookId(bo.getFAccountBookId());
				dimension.setAcctId(v.getFAccountId());
				dimension.setCreateTime(new Date());
				dimension.setCreateUser(sysUser.getUserId().intValue());
				v.getDimensionInfo().forEach(d->{
					if(StrUtil.isBlank(dimension.getDimensionCode())){
						dimension.setDimensionCode(d.getAcctDimsId().toString());
					}else {
						String t=String.format("%s/%s",dimension.getDimensionCode(),d.getAcctDimsId().toString());
						dimension.setDimensionCode(t);
					}
					if(StrUtil.isBlank(dimension.getDsCode())){
						dimension.setDsCode(d.getDetailCode());
					}else {
						String t=String.format("%s/%s",dimension.getDsCode(),d.getDetailCode());
						dimension.setDsCode(t);
					}
					if(StrUtil.isBlank(dimension.getDsName())){
						dimension.setDsName(d.getDetailName());
					}else {
						String t=String.format("%s/%s",dimension.getDsName(),d.getDetailName());
						dimension.setDsName(t);
					}
					if(StrUtil.isBlank(dimension.getDimensionAll())){
						String t=String.format("%s/%s",d.getDetailCode(),d.getDetailName());
						dimension.setDimensionAll(t);
					}else {
						String t=String.format("%s/%s",d.getDetailCode(),d.getDetailName());
						String t2=String.format("%s;%s",dimension.getDimensionAll(),t);
						dimension.setDimensionAll(t2);
					}
				});

				iTGlVoucherEntryDimensionService.save(dimension);
				iTGlVoucherEntryService.updateDetailCode(veId,dimension.getDsCode(),dimension.getDimensionCode());
			}
		});
        return AjaxResult.success(add);
    }

    @Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResult assignCashFlow(List<TGlVoucherCashFlowBo> list){
		List<TGlVoucherEntryCash> saveList =new ArrayList<>();
		Map<String,Integer> map=new HashMap<>();
		list.forEach(bo->{
			TGlVoucherEntry voucherEntry=iTGlVoucherEntryService.getById(bo.getFVoucherEntryId());
			if(null==voucherEntry){
				throw new CustomException("凭证分录信息不存在",1000);
			}
//			if(BaseEnum.YES.getCode().equals(voucherEntry.getFCashFlowItem())){
//				throw new CustomException("现金流量已经指定，无需指定");
//			}
			map.put("mainVoucherId",voucherEntry.getFVoucherId());
			TBdAccountBook  book=itBdAccountBookService.getById(bo.getBookId());
			if(null==book){
				throw new CustomException("账簿不存在",1000);
			}
			TBdAccount account=iTBdAccountService.getById(voucherEntry.getFAccountId());
			if(null==account){
				throw new CustomException("科目信息不存在",1000);
			}
			if(BaseEnum.NO.getCode().equals(account.getFIscash()) && BaseEnum.NO.getCode().equals(account.getFIsbank())){
				throw new CustomException("当前科目不是现金流量",1000);
			}
			List<CashFlowBO> cashFlowBOS=bo.getCashFlow();
			if(CollectionUtil.isEmpty(cashFlowBOS)){
				throw new CustomException("指定对方科目不能为空",1000);
			}
			BigDecimal cfMoney=BigDecimal.ZERO;
			//对方凭证分录科目信息检查
			for (CashFlowBO c:cashFlowBOS){
				if(null==c.getAmount()){
					throw new CustomException("对方科目本位金不能为空",1000);
				}
				TGlVoucherEntry voucherEntry2=iTGlVoucherEntryService.getById(c.getFVoucherEntryId());
				if(null==voucherEntry2){
					throw new CustomException("对方科目信息不存在",1000);
				}
				TBdAccount account2=iTBdAccountService.getById(voucherEntry2.getFAccountId());
				if(null==account){
					throw new CustomException("对方科目信息不存在",1000);
				}
				if(BaseEnum.YES.getCode().equals(account2.getFIscash())){
					throw new CustomException("对方科目也是现金项目不用指定",1000);
				}
				//主表项目检测
				if(c.getMainTableId()!=null){
					TGlCashFlow flow=iTGlCashFlowService.getById(c.getMainTableId());
					if(null==flow){
						throw new CustomException("主表项目不存在",1000);
					}
				}else {
					throw new CustomException("主表项目不能为空",1000);
				}
				//附表项目检测
				if(c.getAttachTableId()!=null){
					TGlCashFlow flow=iTGlCashFlowService.getById(c.getAttachTableId());
					if(null==flow){
						throw new CustomException("附表项目不存在",1000);
					}
				}
				if((c.getAttachTableId()!=null) && (c.getMainTableId()!=null)){
					throw new CustomException("主表和附表项目不能同时存在",1000);
				}
				if(c.getAttachTableId()!=null){
					c.setMainTableId(c.getAttachTableId());
					c.setAttachTableId(null);
				}
				cfMoney=cfMoney.add(c.getAmount());
				TGlVoucherEntryCash cash=new TGlVoucherEntryCash()
					                      .setCashAccountId(voucherEntry.getFAccountId())
					                       .setFExplanation(c.getFExplanation())
					                       .setForAcctId(voucherEntry2.getFAccountId())
					                        .setAmountFor(c.getAmount().divide(voucherEntry2.getFExchangeRate(),2,BigDecimal.ROUND_HALF_UP))
					                         .setCurrencyId(voucherEntry2.getFCurrencyId())
					                       .setAmount(c.getAmount()).setAttachTableId(c.getAttachTableId()).setMainTableId(c.getMainTableId())
					                        .setBookId(bo.getBookId()).setVoucherEntryId(bo.getFVoucherEntryId()).setForVoucherEntryId(voucherEntry2.getFEntryId());
				saveList.add(cash);
			}
			//检查本位金是否一致
			if(cfMoney.compareTo(voucherEntry.getFAmount())!=0){
				throw new CustomException("现金科目和对方科目本位金不一致",1000);
			}

			//更新分表指定现金流量状态
			voucherEntry.setFCashFlowItem(BaseEnum.YES.getCode());
			iTGlVoucherEntryService.updateById(voucherEntry);
			//删除记录
			tGlVoucherEntryCashMapper.delByVid(bo.getFVoucherEntryId());
		});
		//新增
		iTGlVoucherEntryCashService.insertBatch(saveList);
		//更新主表现金流量状态
        if(CollectionUtil.isNotEmpty(map)){
			Integer mainVoucherId=map.get("mainVoucherId");
			TGlVoucher voucher=getById(mainVoucherId);
			voucher.setFModifyDate(new Date());
			voucher.setFIscashFlow(BaseEnum.YES.getCode());
			this.updateById(voucher);
		}
    	return AjaxResult.success();
	}

	/**
	 * 获取现金流量
	 * @param id
	 * @return
	 */
	@Override
	public AjaxResult getCashFlow(Integer id){
		List<VoucherEntryVo> orgList=iTGlVoucherEntryService.queryByVchId(id);
		if(CollectionUtil.isEmpty(orgList)){
			throw new CustomException("凭证分录信息不存在",1000);
		}
		boolean cashFlag=false;
		boolean noCashFlag=false;
		//现金流量项目集合
		List<VoucherEntryVo> list=new ArrayList<>();
		for (VoucherEntryVo v:orgList) {
			TBdAccount account=iTBdAccountService.getById(v.getFAccountId());
			if((BaseEnum.YES.getCode().equals(account.getFIscash()) || BaseEnum.YES.getCode().equals(account.getFIsbank()))){
				cashFlag=true;
				list.add(v);
			}else {
				noCashFlag=true;
			}
		}
		if(!cashFlag || !noCashFlag){
			throw new CustomException("凭证无需指定现金流量!");
		}

		VoucherCashFlowVo cashFlowVo=new VoucherCashFlowVo();
		if(CollectionUtil.isNotEmpty(list)){
			Integer vId=list.get(0).getFVoucherId();
			List<CashFlowAcctVo> flowAcct=list.stream().map(v->(BeanUtil.toBean(v,CashFlowAcctVo.class))).collect(Collectors.toList());
			cashFlowVo.setFlowAcct(flowAcct);
			List<Integer> ids=list.stream().map(VoucherEntryVo::getFEntryId).collect(Collectors.toList());
			List<TGlVoucherEntryCashVo> entryCashVos =iTGlVoucherEntryCashService.queryByList(ids);
			cashFlowVo.setCashFor(entryCashVos);
			VoucherVo voucherVo=tGlVoucherMapper.getInfoById(vId);
			cashFlowVo.setFAccountBookId(voucherVo.getFAccountBookId());
			cashFlowVo.setFAccountBookName(voucherVo.getFBookName());
		}

	  return AjaxResult.success(cashFlowVo);
	}

	/**
	 * 自动指定现金流量
	 * @param id
	 * @return
	 */
	@Override
	public AjaxResult autoAssign(Integer id){
		List<VoucherEntryVo> orgList=iTGlVoucherEntryService.queryByVchId(id);
		if(CollectionUtil.isEmpty(orgList)){
			throw new CustomException("凭证分录信息不存在，自动失败",1000);
		}
		//现金流量项目集合
		List<VoucherEntryVo> list=new ArrayList<>();
		for (VoucherEntryVo v:orgList) {
			TBdAccount account=iTBdAccountService.getById(v.getFAccountId());
			if(BaseEnum.NO.getCode().equals(account.getFIscash()) && BaseEnum.NO.getCode().equals(account.getFIsbank())){
				list.add(v);
			}
		}
	   return AjaxResult.success(list);
	}

    @Override
    public Boolean updateByEditBo(TGlVoucherEditBo bo) {
		TGlVoucher oldv=getById(bo.getFVoucherId());
		if(null==oldv){
			throw new CustomException("凭证不存在",1000);
		}
		if(!DataStatusEnum.REJECT.getCode().equals(oldv.getFDocumentStatus()) && !DataStatusEnum.CREATE.getCode().equals(oldv.getFDocumentStatus()) && !DataStatusEnum.TMP_SAVE.getCode().equals(oldv.getFDocumentStatus())){
			throw new CustomException("当前状态不能修改",1000);
		}
		if(BaseEnum.YES.getCode().equals(oldv.getFPosted())){
			throw new CustomException("凭证已经过账，修改失败",1000);
		}
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		//凭证号重复校验
		if(!oldv.getFVoucherGroupNo().equals(bo.getFVoucherGroupNo())){
			Integer vchNoCount=tGlVoucherMapper.countVchNo(oldv.getFAccountBookId(),oldv.getFYear(), oldv.getFPeriod(), oldv.getFVoucherGroupId(),bo.getFVoucherGroupNo());
			if(vchNoCount!=null && vchNoCount>0){
				throw new CustomException("凭证号重复");
			}
			//删除老凭证号
			tGlVoucherMapper.delVchNo(oldv.getFAccountBookId(),oldv.getFYear(), oldv.getFPeriod(), oldv.getFVoucherGroupId(),oldv.getFVoucherGroupNo());
			//新增凭证号记录
			TGlVoucherGroupNoAddBo noAddBo=new TGlVoucherGroupNoAddBo();
			noAddBo.setFBookId(oldv.getFAccountBookId());
			noAddBo.setFPeriod(oldv.getFPeriod());
			noAddBo.setFYear(oldv.getFYear());
			noAddBo.setFVoucherGroupId(oldv.getFVoucherGroupId());
			noAddBo.setFVoucherGroupNo(bo.getFVoucherGroupNo());
			iTGlVoucherGroupNoService.insertByAddBo(noAddBo);
		}
		TGlVoucherAddBo addBo=new TGlVoucherAddBo();
		BeanUtil.copyProperties(oldv,addBo);
		addBo.setFVoucherGroupNo(bo.getFVoucherGroupNo());
		addBo.setVoucherEntry(bo.getVoucherEntry());
		Map map=validEntityBeforeSave(addBo);
        BeanUtil.copyProperties(addBo,oldv);
		//更新凭证数据
		oldv.setFBaseCurrencyId(Integer.parseInt(map.get("orgCurrency").toString()));
		oldv.setFDebitTotal(new BigDecimal(map.get("debitMoney").toString()));
		oldv.setFCreditTotal(new BigDecimal(map.get("creditMoney").toString()));
		oldv.setFIscashFlow(map.get("fIsadjustVoucher").toString());
		oldv.setFModifyDate(new Date());
		oldv.setFModifierid(sysUser.getUserId().intValue());
		baseMapper.updateById(oldv);
		//更新凭证分录数据
		List<TGlVoucherEntryAddBo> list=bo.getVoucherEntry();
		//删除分录
		Map<String,Object> entryMap=new HashMap<>();
		entryMap.put("f_voucher_id",oldv.getFVoucherId());
		iTGlVoucherEntryService.removeByMap(entryMap);
		list.forEach(v->{
			v.setFVoucherId(oldv.getFVoucherId());
			Integer veId=iTGlVoucherEntryService.insertByAddBo(v);
			//更新维度数据
			if(CollectionUtil.isNotEmpty(v.getDimensionInfo())){
				//内码ID
				Map<String,Object> par=new HashMap<>();
				par.put("voucher_entry_id",veId);
				iTGlVoucherEntryDimensionService.removeByMap(par);
				TGlVoucherEntryDimension dimension=new TGlVoucherEntryDimension();
				dimension.setVoucherEntryId(veId);
				dimension.setBookId(bo.getFAccountBookId());
				dimension.setAcctId(v.getFAccountId());
				dimension.setCreateTime(new Date());
				dimension.setCreateUser(sysUser.getUserId().intValue());
				v.getDimensionInfo().forEach(d->{
					if(StrUtil.isBlank(dimension.getDimensionCode())){
						dimension.setDimensionCode(d.getAcctDimsId().toString());
					}else {
						String t=String.format("%s/%s",dimension.getDimensionCode(),d.getAcctDimsId().toString());
						dimension.setDimensionCode(t);
					}
					if(StrUtil.isBlank(dimension.getDsCode())){
						dimension.setDsCode(d.getDetailCode());
					}else {
						String t=String.format("%s/%s",dimension.getDsCode(),d.getDetailCode());
						dimension.setDsCode(t);
					}
					if(StrUtil.isBlank(dimension.getDsName())){
						dimension.setDsName(d.getDetailCode());
					}else {
						String t=String.format("%s/%s",dimension.getDsName(),d.getDetailName());
						dimension.setDsName(t);
					}
					if(StrUtil.isBlank(dimension.getDimensionAll())){
						String t=String.format("%s/%s",d.getDetailCode(),d.getDetailName());
						dimension.setDimensionAll(t);
					}else {
						String t=String.format("%s/%s",d.getDetailCode(),d.getDetailName());
						String t2=String.format("%s;%s",dimension.getDimensionAll(),t);
						dimension.setDimensionAll(t2);
					}
				});

				iTGlVoucherEntryDimensionService.save(dimension);
				iTGlVoucherEntryService.updateDetailCode(veId,dimension.getDsCode(),dimension.getDimensionCode());
			}
		});
		return true;
    }


	/**
	 * 审核
	 * @param fId
	 * @return
	 */
	@Override
	public AjaxResult audit(Integer fId){
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TGlVoucher oldv=getById(fId);
		if(null==oldv){
			throw new CustomException("凭证不存在",1000);
		}
		if(!DataStatusEnum.SAVE.getCode().equals(oldv.getFDocumentStatus())){
			throw new CustomException("凭证不是保存状态审核失败",1000);
		}
		if( DataStatusEnum.AUDIT.getCode().equals(oldv.getFDocumentStatus())){
			throw new CustomException("凭证状态已审核不得重复审核",1000);
		}
		oldv.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
		oldv.setFModifyDate(new Date());
		oldv.setFModifierid(sysUser.getUserId().intValue());
		oldv.setFChecked(BaseEnum.YES.getCode());
		oldv.setFCheckerId(sysUser.getUserId().intValue());
		oldv.setFAuditDate(new Date());
		this.updateById(oldv);
		return AjaxResult.success();
	}

	/**
	 * 反审核
	 * @param fId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResult auditNo(Integer fId){
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TGlVoucher oldv=getById(fId);
		if(null==oldv){
			throw new CustomException("凭证不存在",1000);
		}
		if(!DataStatusEnum.AUDIT.getCode().equals(oldv.getFDocumentStatus())){
			throw new CustomException("凭证不是审核状态，反审核失败",1000);
		}
        if(BaseEnum.YES.getCode().equals(oldv.getFPosted())){
			throw new CustomException("凭证已经过账，反审核失败",1000);
		}
        if(BaseEnum.YES.getCode().equals(oldv.getFSettle())){
			throw new CustomException("凭证已经结账，反审核失败",1000);
		}
		oldv.setFDocumentStatus(DataStatusEnum.REJECT.getCode());
		oldv.setFModifyDate(new Date());
		oldv.setFModifierid(sysUser.getUserId().intValue());
		oldv.setFChecked(BaseEnum.NO.getCode());
		oldv.setFCheckerId(sysUser.getUserId().intValue());
		oldv.setFAuditDate(new Date());
		oldv.setFModifierid(sysUser.getUserId().intValue());
		this.updateById(oldv);

		//扣除科目表余额
		List<VoucherEntryVo> list=iTGlVoucherEntryService.queryByVchId(fId);
		if(CollectionUtil.isNotEmpty(list)){
			list.forEach(v->{
				VoucherBalanceBo bo=new VoucherBalanceBo();
				bo.setFAccountBookId(oldv.getFAccountBookId())
					.setFAccountId(v.getFAccountId())
					.setFCurrencyId(v.getFCurrencyId())
					.setFPeriod(oldv.getFPeriod())
					.setFYear(oldv.getFYear())
					.setFDetailCode(v.getFDetailCode())
				    .setFVoucherId(fId);
				this.updateAcctBalance(bo,false);
			});
		}
		return AjaxResult.success();
	}
	/**
	 * 保存
	 * @param vId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResult save(Integer vId){
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TGlVoucher oldv=getById(vId);
		if(null==oldv){
			throw new CustomException("凭证不存在",1000);
		}
		if(!DataStatusEnum.TMP_SAVE.getCode().equals(oldv.getFDocumentStatus()) && !DataStatusEnum.CREATE.getCode().equals(oldv.getFDocumentStatus())){
			throw new CustomException("凭证不是暂存或者创建状态，保存失败",1000);
		}
		List<VoucherEntryVo> list=iTGlVoucherEntryService.queryByVchId(vId);
		if(CollectionUtil.isEmpty(list)){
			throw new CustomException("凭证分录信息不存在，保存失败",1000);
		}
		boolean cashFlag=false;
		boolean noCashFlag=false;
		for (VoucherEntryVo v:list) {
			TBdAccount account=iTBdAccountService.getById(v.getFAccountId());
			if((BaseEnum.YES.getCode().equals(account.getFIscash()) || BaseEnum.YES.getCode().equals(account.getFIsbank())) && BaseEnum.NO.getCode().equals(v.getFCashFlowItem())){
				cashFlag=true;
			}else {
				noCashFlag=true;
			}
		}
		if(cashFlag && noCashFlag){
			throw new CustomException("凭证录入必须要指定现金流量!");
		}
		oldv.setFDocumentStatus(DataStatusEnum.SAVE.getCode());
		oldv.setFModifyDate(new Date());
		oldv.setFModifierid(sysUser.getUserId().intValue());
		this.updateById(oldv);
		for (VoucherEntryVo v:list){
			//更新科目余额表
			VoucherBalanceBo bo=new VoucherBalanceBo();
			bo.setFAccountBookId(oldv.getFAccountBookId())
			.setFAccountId(v.getFAccountId())
			.setFCurrencyId(v.getFCurrencyId())
			.setFPeriod(oldv.getFPeriod())
			.setFYear(oldv.getFYear())
			.setDimensionCode(v.getDimensionCode())
			.setFDetailCode(v.getFDetailCode());
			this.updateAcctBalance(bo,true);
		}
		return AjaxResult.success();
	}

	/**
	 * 更新科目余额表
	 */
	public void  updateAcctBalance(VoucherBalanceBo bo,boolean flag){
		VoucherBalanceVo balanceVo=baseMapper.queryVoucherBalance(bo);
		TGlBalanceVoucherBo voucherBo=new TGlBalanceVoucherBo()
			.setFAccountBookId(bo.getFAccountBookId()).setFAccountId(bo.getFAccountId())
			.setFAdjustPeriod(Integer.parseInt(BaseEnum.NO.getCode()))
			.setFBeginBalance(BigDecimal.ZERO)
			.setFBeginBalancefor(BigDecimal.ZERO).setFYear(bo.getFYear())
			.setFPeriod(bo.getFPeriod()).setFYearPeriod(String.format("%s.%s",bo.getFYear(),bo.getFPeriod()))
			.setFCurrencyId(bo.getFCurrencyId()).setFCredit(balanceVo.getFCredit()).setFCreditFor((balanceVo.getFCredit().compareTo(BigDecimal.ZERO)<=0)?BigDecimal.ZERO:balanceVo.getFAmountfor())
			.setFDebit(balanceVo.getFDebit()).setFDebitFor((balanceVo.getFDebit().compareTo(BigDecimal.ZERO)<=0)?BigDecimal.ZERO:balanceVo.getFAmountfor())
			.setFEndBalance(balanceVo.getFAmount()).setFEndBalancefor(balanceVo.getFAmountfor())
			.setFYtdCredit(balanceVo.getFCredit()).setFYtdCreditfor((balanceVo.getFCredit().compareTo(BigDecimal.ZERO)<=0)?BigDecimal.ZERO:balanceVo.getFAmountfor())
			.setFYtdDebit(balanceVo.getFDebit()).setFYtdDebitfor((balanceVo.getFDebit().compareTo(BigDecimal.ZERO)<=0)?BigDecimal.ZERO:balanceVo.getFAmountfor());
		if(StrUtil.isNotBlank(bo.getFDetailCode())){
			voucherBo.setFDetailCode(bo.getFDetailCode());
			voucherBo.setDimensionCode(bo.getDimensionCode());
		}
		if(!flag){
			voucherBo.setFDebit(voucherBo.getFDebit().negate());
			voucherBo.setFDebitFor(voucherBo.getFDebitFor().negate());
			voucherBo.setFCredit(voucherBo.getFCredit().negate());
			voucherBo.setFCreditFor(voucherBo.getFCreditFor().negate());
			voucherBo.setFEndBalance(voucherBo.getFEndBalance().negate());
			voucherBo.setFEndBalancefor(voucherBo.getFEndBalancefor().negate());
			voucherBo.setFYtdCredit(voucherBo.getFYtdCredit().negate());
			voucherBo.setFYtdCreditfor(voucherBo.getFYtdCreditfor().negate());
			voucherBo.setFYtdDebit(voucherBo.getFYtdDebit().negate());
			voucherBo.setFYtdDebitfor(voucherBo.getFYtdDebitfor().negate());
		}
		iTGlBalanceService.updateVoucherBalance(voucherBo);
	}
	/**
	 * 作废
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResult invalid(Integer fId){
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TGlVoucher oldv=getById(fId);
		if(null==oldv){
			throw new CustomException("凭证不存在",1000);
		}
		if( DataStatusEnum.AUDIT.getCode().equals(oldv.getFDocumentStatus())){
			throw new CustomException("凭证状态已审核不得作废",1000);
		}
		if(BaseEnum.YES.getCode().equals(oldv.getFPosted())){
			throw new CustomException("凭证已经过账，作废失败",1000);
		}
		oldv.setFModifyDate(new Date());
		oldv.setFModifierid(sysUser.getUserId().intValue());
		oldv.setFInvalid(BaseEnum.YES.getCode());
		oldv.setFInvaliderId(sysUser.getUserId().intValue());
		this.updateById(oldv);
		//更新科目余额
		return AjaxResult.success();
	}

	/**
	 * 凭证过账
	 * @param ids
	 * @return
	 */
	@Override
	public AjaxResult voucherPost(List<Integer> ids){
        if(CollectionUtil.isEmpty(ids)){
        	throw new CustomException("凭证ID不能为空",1000);
		}
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        ids.forEach(v->{
			TGlVoucher oldv=getById(v);
			if(null==oldv){
				throw new CustomException("凭证不存在",1000);
			}
			if(! DataStatusEnum.AUDIT.getCode().equals(oldv.getFDocumentStatus())){
				throw new CustomException("凭证状态有未审核不得过账",1000);
			}
			if(BaseEnum.YES.getCode().equals(oldv.getFPosted())){
				throw new CustomException("凭证已经过账，不用重复过账",1000);
			}
			oldv.setFPosted(BaseEnum.YES.getCode());
			oldv.setFPosterId(sysUser.getUserId().intValue());
			oldv.setFPostDate(new Date());
			this.updateById(oldv);
		});
		return AjaxResult.success();
	}
    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private Map validEntityBeforeSave(TGlVoucherAddBo entity){
		if(StrUtil.isBlank(entity.getFInternalind())){
			throw new CustomException("凭证来源不能为空");
		}
    	Map<String,Object> result=new HashMap<>();
    	if(CollectionUtil.isEmpty(entity.getVoucherEntry())){
			throw new CustomException("凭证分录信息不能为空",1000);
		}
		TBdAccountBook  book=itBdAccountBookService.getById(entity.getFAccountBookId());
		if(null==book || !DataStatusEnum.AUDIT.getCode().equals(book.getFDocumentStatus())){
			throw new CustomException("账簿不存在",1000);
		}
		if(book.getFAccountOrgid().equals(entity.getFAccountBookId())){
			throw new CustomException("账簿核算组织错误",1000);
		}
		SysOrganization org=iSysOrganizationService.getById(entity.getFAcctOrgid());
		if(null==org){
			throw new CustomException("核算组织不存在",1000);
		}
		TBdVoucherGroup voucherGroup=iTBdVoucherGroupService.getById(entity.getFVoucherGroupId());
		if(null==voucherGroup || !DataStatusEnum.AUDIT.getCode().equals(voucherGroup.getFDocumentStatus())){
			throw new CustomException("凭证字不存在",1000);
		}
		if(null==entity.getFVoucherGroupNo()){
			throw new CustomException("凭证号不能为空",1000);
		}
		// 验证非调整期间 就正常凭证录入
		if (StringUtils.checkValNull(entity.getFIsadjustVoucher())){
			//获取年月
			Integer year= DateTimeCalculatorUtil.getYear(entity.getFDate());
			Integer month= DateTimeCalculatorUtil.getMonth(entity.getFDate());
			entity.setFYear(year);
			TBdAccountPeriod periodVo=tBdAccountPeriodMapper.findPeriodInfo(book.getFPeriodId().intValue(),year,month);
			if(null==periodVo){
				throw new CustomException("日期不在会计期间范围内");
			}
			entity.setFPeriod(periodVo.getFPeriod());
		}
		//凭证字校验
		//tBdVchgroupAcctMapper.s()
		List<TGlVoucherEntryAddBo> list=entity.getVoucherEntry();
		//贷方金额
		BigDecimal creditMoney=BigDecimal.ZERO;
		//借方金额
		BigDecimal debitMoney=BigDecimal.ZERO;
		//本位币
		Integer orgCurrency=book.getFCurrencyId().intValue();
		for (int i=1;i<=list.size();i++){
			TGlVoucherEntryAddBo bo=list.get(i-1);
			TBdAccount account=iTBdAccountService.getById(bo.getFAccountId());
			if(null==account || !DataStatusEnum.AUDIT.getCode().equals(account.getFDocumentStatus())){
				throw new CustomException("第"+i+"行科目信息不存在",1000);
			}
			if(((null==bo.getFCredit()) || (bo.getFCredit().compareTo(BigDecimal.ZERO)==0)) && (null==bo.getFDebit() || (bo.getFDebit().compareTo(BigDecimal.ZERO)==0))){
				throw new CustomException("第"+i+"行金额不能为空",1000);
			}
			if((bo.getFCredit()!=null && (bo.getFCredit().compareTo(BigDecimal.ZERO)!=0)) && (bo.getFDebit()!=null && (bo.getFDebit().compareTo(BigDecimal.ZERO)!=0))){
				throw new CustomException("第"+i+"行借贷方金额不能同时存在",1000);
			}
			if(null==bo.getFAmountfor()){
				throw new CustomException("第"+i+"行原始金额不为空",1000);
			}
			String fdc=(bo.getFCredit()!=null && bo.getFCredit().compareTo(BigDecimal.ZERO)!=0)? BorrowEnum.CREDIT.getCode():BorrowEnum.DEBIT.getCode();
			bo.setFDc(fdc);
			if(BaseEnum.YES.getCode().equals(account.getFIsquantities())){
				if(bo.getFUnitPrice().multiply(bo.getFQuantity()).compareTo(bo.getFAmountfor())!=0){
					throw new CustomException("数量乘以单价不等于原币金额",1000);
				}
			}else {
				bo.setFUnitPrice(BigDecimal.ZERO);
				bo.setFQuantity(BigDecimal.ZERO);
			}
			//汇率模块
			TBdCurrency currency=iTBdCurrencyService.getById(bo.getFCurrencyId());
			if(null==currency || !DataStatusEnum.AUDIT.getCode().equals(currency.getFDocumentStatus())){
				throw new CustomException("分录第"+i+"行币别不存在",1000);
			}
			//校验汇率
			TBdRateType rateType=iTBdRateTypeService.getById(bo.getFExchangeRateType());
			if(null==rateType || !DataStatusEnum.AUDIT.getCode().equals(rateType.getFDocumentStatus())){
				throw new CustomException("分录第"+i+"行汇率类型不存在",1000);
			}
			if(BigDecimal.ZERO.compareTo(bo.getFExchangeRate())>=0){
				throw new CustomException("分录第"+i+"行汇率值必须大于0");
			}
			//配置汇率值 book.getFCurrencyId().intValue() 本位币
			if(!bo.getFCurrencyId().equals(book.getFCurrencyId().intValue())){
				TBdRate tBdRate=itBdRateService.getRate(entity.getFDate(),bo.getFExchangeRateType(),bo.getFCurrencyId(),book.getFCurrencyId().intValue());
				if(null==tBdRate){
					throw new CustomException("当前外币未设置换算汇率，请前往先配置汇率",1000);
				}
			 }else {
				bo.setFExchangeRate(BigDecimal.ONE);
			}
			Integer money=(bo.getFCredit()!=null && bo.getFCredit().compareTo(BigDecimal.ZERO)!=0)?bo.getFAmountfor().multiply(bo.getFExchangeRate()).compareTo(bo.getFCredit()):bo.getFAmountfor().multiply(bo.getFExchangeRate()).compareTo(bo.getFDebit());
			if(money!=0){
				throw new CustomException("分录第"+i+"行原币金额不等于贷方金额或者借方金额，请核查");
			}
			//本位币金额
			BigDecimal amount=bo.getFAmountfor().multiply(bo.getFExchangeRate()).setScale(currency.getFAmountdigits(),BigDecimal.ROUND_HALF_UP);
			bo.setFAmount(amount);
			//借贷金额统计
			creditMoney=creditMoney.add((bo.getFCredit()!=null && bo.getFCredit().compareTo(BigDecimal.ZERO)!=0)?bo.getFCredit():BigDecimal.ZERO);
			debitMoney=debitMoney.add(((bo.getFDebit()!=null && bo.getFDebit().compareTo(BigDecimal.ZERO)!=0)?bo.getFDebit():BigDecimal.ZERO));

			//核算维度校验
            List<AccountingDimensionBo> dL=bo.getDimensionInfo();
            Map<Integer,List<AccountingDimensionBo>> tmp=new HashMap<>();
            if(!CollectionUtil.isEmpty(dL)){
            	dL.forEach(v->{
					TBdFlexItemProperty source=tBdFlexItemPropertyMapper.selectById(v.getAcctDimsId());
					if(null==source || !DataStatusEnum.AUDIT.getCode().equals(source.getFDocumentStatus())){
						throw new CustomException("维度信息不存在");
					}
				});
				tmp=dL.stream().filter(v->(StrUtil.isNotBlank(v.getDetailCode()))).collect(Collectors.groupingBy(AccountingDimensionBo::getAcctDimsId));
			}
			Map<Integer,List<AccountingDimensionBo>> map=tmp;


            //科目维度控制表
			List<TBdAccountFlexentryVo> fList=itBdAccountFlexentryService.queryByAcctId(account.getFAcctId().intValue());
			if(!CollectionUtil.isEmpty(fList)){
              boolean op=fList.stream().filter(v->(
				  "1".equals(v.getFInputType()) && (CollectionUtil.isEmpty(map) || !map.containsKey(v.getFFlexitempropertyId()))
				  )
			  ).findFirst().isPresent();
              if(op){
				  throw new CustomException("第"+i+"行核算维度必录项不能为空",1000);
			  }
			}
		}
		//借贷金额校验
		if((creditMoney.compareTo(BigDecimal.ZERO)==0) && (debitMoney.compareTo(BigDecimal.ZERO)==0)){
			throw new CustomException("借方或贷方金额不能为0，请核实！",1000);
		}
		if(creditMoney.compareTo(debitMoney)!=0){
			throw new CustomException("借方金额不等于贷方金额，请核实！",1000);
		}
		result.put("creditMoney",creditMoney);
		result.put("debitMoney",debitMoney);
		result.put("orgCurrency",orgCurrency);
		// 是否是调整期间
		result.put("fIsadjustVoucher",entity.getFIsadjustVoucher());
		return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
			ids.forEach(v->{
				TGlVoucher voucher=baseMapper.selectById(v);
				if(null==voucher){
					throw new CustomException("记录不存在");
				}
				if(!DataStatusEnum.CREATE.getCode().equals(voucher.getFDocumentStatus())){
					throw new CustomException("凭证状态不支持删除");
				}
				baseMapper.deleteById(v);
				List<VoucherEntryVo> entryVos =tGlVoucherEntryMapper.getPageByVid(v);
				entryVos.forEach(v1->{
					Map<String,Object> par=new HashMap<>();
					par.put("voucher_entry_id",v1.getFEntryId());
					iTGlVoucherEntryDimensionService.removeByMap(par);
				});
                tGlVoucherEntryMapper.delByVchEntryId(v);
                //删除凭证号
				QueryWrapper queryWrapper=Wrappers.query();
				queryWrapper.eq("f_voucher_group_id",voucher.getFVoucherGroupId());
				queryWrapper.eq("f_book_id",voucher.getFAccountBookId());
				queryWrapper.eq("f_voucher_group_no",voucher.getFVoucherGroupNo());
				queryWrapper.eq("f_year",voucher.getFYear());
				queryWrapper.eq("f_period",voucher.getFPeriod());
				iTGlVoucherGroupNoService.remove(queryWrapper);
			});
        }
        return true;
    }

	/**
	 * 查询明细类分帐
	 * @param bo
	 * @return
	 */
	@Override
	public AjaxResult listDetail(BalanceVchDetailQueryBo bo){
		//期初余额计算
		if(null==bo.getEfYear() || null==bo.getEfPeriod()){
			bo.setEfYear(bo.getFYear());
			bo.setEfPeriod(bo.getFPeriod());
		}
		List<BalanceVchVo> list=tGlBalanceMapper.queryByVch(bo);
		List<BalanceDetailVo> detailVos=new ArrayList<>();
		String fDcInit=BorrowEnum.DEBIT.getCode();
		//本年累计
		BalanceDetailVo yearVo=new BalanceDetailVo();
		if(CollectionUtil.isNotEmpty(list)){
			BalanceDetailVo vo=new BalanceDetailVo();
			vo.setFDate(DateTimeFormatterUtil.getFirstDayOfMonth(bo.getFYear(),bo.getFPeriod()));
			BalanceVchVo balance=list.get(0);
			fDcInit=balance.getFDc();
			//借方
			if(BorrowEnum.DEBIT.getCode().equals(balance.getFDc())){
				vo.setFCredit(BigDecimal.ZERO);
				vo.setFCreditFor(BigDecimal.ZERO);
				vo.setFDebit(balance.getFBeginBalance());
				vo.setFDebitFor(balance.getFBeginBalancefor());
				vo.setFDc(balance.getFBeginBalance().compareTo(BigDecimal.ZERO)==0?BorrowEnum.FLAT.getCode():BorrowEnum.DEBIT.getCode());
				vo.setFExplanation("期初余额");
				vo.setBalance(balance.getFBeginBalance());
				detailVos.add(vo);
			}else if(BorrowEnum.CREDIT.getCode().equals(balance.getFDc())){
				vo.setFDebit(BigDecimal.ZERO);
				vo.setFDebitFor(BigDecimal.ZERO);
				vo.setFCredit(balance.getFBeginBalance());
				vo.setFCreditFor(balance.getFBeginBalancefor());
				vo.setFExplanation("期初余额");
				vo.setFDc(balance.getFBeginBalance().compareTo(BigDecimal.ZERO)==0?BorrowEnum.FLAT.getCode():BorrowEnum.CREDIT.getCode());
				vo.setBalance(balance.getFBeginBalance());
				detailVos.add(vo);
			}
			yearVo.setFCredit(balance.getFCredit()).
				setFDebit(balance.getFDebit()).setFExplanation("本年累计")
				.setBalance(balance.getFEndBalance())
				.setFDate(DateTimeFormatterUtil.getFirstDayOfMonth(bo.getFYear(),bo.getFPeriod()));
		}
		String fDc=fDcInit;
		List<BalanceDetailVo> vchVoList=baseMapper.queryVchDetail(bo);
		if(CollectionUtil.isNotEmpty(vchVoList)){
			Map<Date,List<BalanceDetailVo>> tmp=vchVoList.stream().collect(Collectors.groupingBy(BalanceDetailVo::getFDate));
			//本期合计
			BalanceDetailVo pSum= new BalanceDetailVo().
				                      setBalance(BigDecimal.ZERO).setFCredit(BigDecimal.ZERO).setFDebit(BigDecimal.ZERO).setFDc(fDc).setFExplanation("本期合计");
			for(List<BalanceDetailVo> vL:tmp.values()){
				//统计当日合计值
				BalanceDetailVo vSum=new BalanceDetailVo();
				BigDecimal c=BigDecimal.ZERO;
				BigDecimal d=BigDecimal.ZERO;
				Date fd=null;
				for (BalanceDetailVo dv:vL){
					dv.setFDc(fDc);
					c=c.add(dv.getFCredit());
					d=d.add(dv.getFDebit());
					fd=dv.getFDate();
				}
				vSum.setFDc(fDc);
				vSum.setFDate(fd);
				vSum.setFCredit(c);
				vSum.setFDebit(d);
				vSum.setBalance(d.subtract(c));
				vL.add(vSum);
				detailVos.addAll(vL);
				pSum.setFDebit(pSum.getFDebit().add(vSum.getFDebit()));
				pSum.setFCredit(pSum.getFCredit().add(vSum.getFCredit()));
				pSum.setBalance(pSum.getBalance().add(vSum.getBalance()));
			}
			//本期合计
			detailVos.add(pSum);
		}
        //本年累计
		detailVos.add(yearVo);
		return AjaxResult.success(detailVos);
	}














}
