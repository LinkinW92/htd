package com.skeqi.finance.service.impl.basicinformation.accountbook;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.MyBeanUtils;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.finance.domain.TBdAccountPeriod;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.domain.rate.TBdRateType;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.BaseTypeEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.TBdAccountPeriodMapper;
import com.skeqi.finance.mapper.basicinformation.accountbook.TBdAccountBookMapper;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookEditBo;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookQueryBo;
import com.skeqi.finance.pojo.vo.*;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountCalendarVo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTableVo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyVo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAccountSystemVo;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdCurrencyVo;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVoucherGroupVo;
import com.skeqi.finance.pojo.vo.book.BookPolicyVo;
import com.skeqi.finance.pojo.vo.book.TBdAccountBookVo;
import com.skeqi.finance.service.ISysOrganizationService;
import com.skeqi.finance.service.account.ITBdAccountCalendarService;
import com.skeqi.finance.service.basicinformation.base.ITBdCurrencyService;
import com.skeqi.finance.service.account.ITBdAccountTableService;
import com.skeqi.finance.service.basicinformation.accountbook.ITBdAccountBookService;
import com.skeqi.finance.service.basicinformation.accountingpolicies.ITFaAcctPolicyService;
import com.skeqi.finance.service.basicinformation.accountingsystem.ITOrgAccountSystemService;
import com.skeqi.finance.service.basicinformation.voucher.ITBdVoucherGroupService;
import com.skeqi.finance.service.help.ITBdHelpTypeService;
import com.skeqi.finance.service.rate.ITBdRateTypeService;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 账簿Service业务层处理
 *
 * @author toms
 * @date 2021-07-13
 */
@Service
public class TBdAccountBookServiceImpl extends ServicePlusImpl<TBdAccountBookMapper, TBdAccountBook> implements ITBdAccountBookService {

	@Resource
	TBdAccountBookMapper tBdAccountBookMapper;
	@Autowired
	ITOrgAccountSystemService iTOrgAccountSystemService;
	@Autowired
	ISysOrganizationService iSysOrganizationService;
	@Autowired
	ITFaAcctPolicyService iTFaAcctPolicyService;
	@Autowired
	ITBdAccountCalendarService iTBdAccountCalendarService;
	@Autowired
	ITBdAccountTableService iTBdAccountTableService;
	@Autowired
	ITBdCurrencyService iTBdCurrencyService;
	@Autowired
	ITBdHelpTypeService iTBdHelpTypeService;
	@Autowired
	ITBdVoucherGroupService iTBdVoucherGroupService;
	@Autowired
	TBdAccountPeriodMapper tBdAccountPeriodMapper;
	@Autowired
	TokenService tokenService;
	@Autowired
	ITBdRateTypeService iTBdRateTypeService;

    @Override
    public TBdAccountBookVo queryById(Integer fBookId){
        return baseMapper.queryOne(fBookId);
    }

    @Override
    public TableDataInfo<TBdAccountBookVo> queryPageList(TBdAccountBookQueryBo bo) {
		Page<TBdAccountBookQueryBo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TBdAccountBookVo> iPage = tBdAccountBookMapper.queryByPage(page, bo);
        return PageUtils.buildDataInfo(iPage);
    }

	/**
	 * 查询会计政策、日历、记账本位币、汇率类型
	 * @param bo
	 * @return
	 */
	@Override
	public AjaxResult queryAcctInfo(TBdAccountBookQueryBo bo){
        Integer policyId=tBdAccountBookMapper.queryPolicyId(bo.getFAccountOrgid().intValue(),bo.getFAcctsystemId().intValue());
		BookPolicyVo Vo=tBdAccountBookMapper.queryCalendarInfo(policyId);
		return AjaxResult.success(Vo);
	}

    @Override
    public List<TBdAccountBookVo> queryList(TBdAccountBookQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdAccountBookVo.class);
    }

    private LambdaQueryWrapper<TBdAccountBook> buildQueryWrapper(TBdAccountBookQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdAccountBook> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdAccountBook::getFNumber, bo.getFNumber());
        lqw.eq(bo.getFAccttableId() != null, TBdAccountBook::getFAccttableId, bo.getFAccttableId());
        lqw.eq(bo.getFPeriodId() != null, TBdAccountBook::getFPeriodId, bo.getFPeriodId());
        lqw.eq(bo.getFCurrencyId() != null, TBdAccountBook::getFCurrencyId, bo.getFCurrencyId());
        lqw.eq(bo.getFRateTypeId() != null, TBdAccountBook::getFRateTypeId, bo.getFRateTypeId());
        lqw.eq(bo.getFAccountOrgid() != null, TBdAccountBook::getFAccountOrgid, bo.getFAccountOrgid());
        lqw.eq(bo.getFAcctsystemId() != null, TBdAccountBook::getFAcctsystemId, bo.getFAcctsystemId());
        lqw.eq(StrUtil.isNotBlank(bo.getFBookType()), TBdAccountBook::getFBookType, bo.getFBookType());
        lqw.eq(bo.getFDefaultVoucherType() != null, TBdAccountBook::getFDefaultVoucherType, bo.getFDefaultVoucherType());
        lqw.eq(StrUtil.isNotBlank(bo.getFUseAdjustPeriod()), TBdAccountBook::getFUseAdjustPeriod, bo.getFUseAdjustPeriod());
        lqw.eq(StrUtil.isNotBlank(bo.getFInitialStatus()), TBdAccountBook::getFInitialStatus, bo.getFInitialStatus());
        lqw.eq(bo.getFCurrentYear() != null, TBdAccountBook::getFCurrentYear, bo.getFCurrentYear());
        lqw.eq(bo.getFCurrentPeriod() != null, TBdAccountBook::getFCurrentPeriod, bo.getFCurrentPeriod());
        lqw.eq(StrUtil.isNotBlank(bo.getFCrtYearPeriod()), TBdAccountBook::getFCrtYearPeriod, bo.getFCrtYearPeriod());
        lqw.eq(bo.getFStartYear() != null, TBdAccountBook::getFStartYear, bo.getFStartYear());
        lqw.eq(bo.getFStartPeriod() != null, TBdAccountBook::getFStartPeriod, bo.getFStartPeriod());
        lqw.eq(StrUtil.isNotBlank(bo.getFYearandPeriod()), TBdAccountBook::getFYearandPeriod, bo.getFYearandPeriod());
        lqw.eq(bo.getFAcctPolicyid() != null, TBdAccountBook::getFAcctPolicyid, bo.getFAcctPolicyid());
        lqw.eq(StrUtil.isNotBlank(bo.getFApfinType()), TBdAccountBook::getFApfinType, bo.getFApfinType());
        lqw.eq(StrUtil.isNotBlank(bo.getFArfinType()), TBdAccountBook::getFArfinType, bo.getFArfinType());
        lqw.eq(bo.getFCreateOrgid() != null, TBdAccountBook::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdAccountBook::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdAccountBook::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdAccountBook::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdAccountBook::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdAccountBook::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdAccountBook::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdAccountBook::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdAccountBook::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdAccountBook::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdAccountBook::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdAccountBook::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdAccountBook::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdAccountBook::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdAccountBookAddBo bo) {
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        TBdAccountBook add = BeanUtil.toBean(bo, TBdAccountBook.class);
        validEntityBeforeSave(add);
		add.setFCreateDate(new Date());
		add.setFCreatorid(sysUser.getUserId());
		add.setFDocumentStatus(DataStatusEnum.CREATE.getCode());
		add.setFForbidStatus(BaseEnum.NO.getCode());
		add.setFCreateOrgid(bo.getFAccountOrgid());
		add.setFUseAdjustPeriod(BaseEnum.NO.getCode());
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdAccountBookEditBo bo) {
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TBdAccountBook old = this.getById(bo.getFBookId());
		if(null==old){
			throw new CustomException("记录不存在");
		}
		if(DataStatusEnum.AUDIT.getCode().equals(old.getFDocumentStatus())){
			throw new CustomException("审核状态数据无法修改");
		}
        BeanUtil.copyProperties(bo,old,"fBookId");
		MyBeanUtils.copyPropertiesIgnoreNull(bo,old);
        validEntityBeforeSave(old);
        old.setFMasterId(sysUser.getUserId().longValue());
        return updateById(old);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdAccountBook entity){
		TOrgAccountSystemVo system=iTOrgAccountSystemService.queryById(entity.getFAcctsystemId().intValue());
		if((null==system) || !DataStatusEnum.AUDIT.getCode().equals(system.getFDocumentStatus())){
			throw new CustomException("核算体系不存在或者未审核");
		}
		SysOrganizationVo org = iSysOrganizationService.queryById(entity.getFAccountOrgid().intValue());
		if((null==org) || !DataStatusEnum.AUDIT.getCode().equals(org.getStatus().toString())){
			throw new CustomException("核算组织不存在或者未审核");
		}
		TFaAcctPolicyVo policyVo=iTFaAcctPolicyService.queryById(entity.getFAcctPolicyid().intValue());
		if((null==policyVo) || !DataStatusEnum.AUDIT.getCode().equals(policyVo.getFDocumentStatus())){
			throw new CustomException("会计政策不存在或者未审核");
		}
		TBdAccountCalendarVo calendarVo=iTBdAccountCalendarService.queryById(entity.getFPeriodId().intValue());
		if((null==calendarVo) || !DataStatusEnum.AUDIT.getCode().equals(calendarVo.getFDocumentStatus())){
			throw new CustomException("会计日历不存在或者未审核");
		}
		TBdAccountTableVo tableVo=iTBdAccountTableService.queryById(entity.getFAccttableId().intValue());
		if((null==tableVo) || !DataStatusEnum.AUDIT.getCode().equals(tableVo.getFDocumentStatus())){
			throw new CustomException("科目表不存在或者未审核");
		}
		TBdCurrencyVo currencyVo=iTBdCurrencyService.queryById(entity.getFCurrencyId().intValue());
		if((null==currencyVo) || !DataStatusEnum.AUDIT.getCode().equals(currencyVo.getFDocumentStatus())){
			throw new CustomException("币别不存在或者未审核");
		}
		TBdRateType rateType=iTBdRateTypeService.getById (entity.getFRateTypeId().intValue());
		if(null==rateType || !DataStatusEnum.AUDIT.getCode().equals(rateType.getFDocumentStatus())){
			throw new CustomException("汇率类型不存在或者未审核");
		}
		TBdVoucherGroupVo groupVo=iTBdVoucherGroupService.queryById(entity.getFDefaultVoucherType().intValue());
		if((null==groupVo) || !DataStatusEnum.AUDIT.getCode().equals(groupVo.getFDocumentStatus())){
			throw new CustomException("凭证字不存在或者未审核");
		}
        if(null==BaseTypeEnum.getObj(entity.getFBookType())){
			throw new CustomException("账簿类型不存在");
		}
		//如果是主账簿
		if(BaseTypeEnum.MAIN.getCode().equals(entity.getFBookType())){
			Integer count=baseMapper.countMainBook(entity.getFAccountOrgid().intValue(),entity.getFAcctsystemId().intValue());
			if(count>0){
				throw new CustomException("该核算组织和核算体系下已经存在一个主账簿");
			}
		}
        Integer year=Integer.parseInt(entity.getFYearandPeriod().substring(0,4));
        Integer period=Integer.parseInt(entity.getFYearandPeriod().substring(5));
		TBdAccountPeriod periodVo=tBdAccountPeriodMapper.findInfo(entity.getFPeriodId().intValue(),year,period);
		if(null==periodVo){
			throw new CustomException("启用期间不存在");
		}
		entity.setFStartYear(year);
		entity.setFStartPeriod(period);
		entity.setFCrtYearPeriod(entity.getFYearandPeriod());
		entity.setFCurrentYear(year);
		entity.setFCurrentPeriod(period.longValue());

    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		if(isValid && CollectionUtil.isNotEmpty(ids)){
			ids.forEach(v->{
				TBdAccountBook old=this.getById(v);
				if(!DataStatusEnum.AUDIT.getCode().equals(old.getFDocumentStatus())){
					this.removeById(v);
				}
			});
		}
		return true;
    }

    @Override
	public Boolean audit(Collection<Integer> ids){
		SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
    	if(CollectionUtil.isNotEmpty(ids)){
    		ids.forEach(v->{
				TBdAccountBook old=this.getById(v);
				old.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
				old.setFAuditorid(sysUser.getUserId().longValue());
				old.setFAuditDate(new Date());
				this.updateById(old);
			});
		}
    	return true;
	}


}
