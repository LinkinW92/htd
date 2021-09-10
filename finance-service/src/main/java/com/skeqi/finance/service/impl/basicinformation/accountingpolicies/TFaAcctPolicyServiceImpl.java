package com.skeqi.finance.service.impl.basicinformation.accountingpolicies;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.finance.domain.TGlVoucherGroupNo;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicyAsset;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAccountSystem;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAcctSysEntry;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.domain.endhandle.TGlExchangeScheme;
import com.skeqi.finance.domain.endhandle.amortization.TGlAmortizationScheme;
import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransfer;
import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingScheme;
import com.skeqi.finance.domain.voucher.TGlVoucher;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.basicinformation.accountbook.TBdAccountBookMapper;
import com.skeqi.finance.mapper.basicinformation.accountingsystem.TOrgAccountSystemMapper;
import com.skeqi.finance.mapper.basicinformation.accountingsystem.TOrgAcctSysEntryMapper;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemEditBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAccountSystemVo;
import com.skeqi.finance.service.basicinformation.accountingpolicies.ITFaAcctPolicyAssetService;
import com.skeqi.finance.service.basicinformation.accountingsystem.ITOrgAcctSysEntryService;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyEditBo;
import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicy;
import com.skeqi.finance.mapper.basicinformation.accountingpolicies.TFaAcctPolicyMapper;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyVo;
import com.skeqi.finance.service.basicinformation.accountingpolicies.ITFaAcctPolicyService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 会计政策Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TFaAcctPolicyServiceImpl extends ServicePlusImpl<TFaAcctPolicyMapper, TFaAcctPolicy> implements ITFaAcctPolicyService {
	@Resource
	private TokenService tokenService;

	@Resource
	private TFaAcctPolicyMapper policyMapper;

	@Resource
	private ITFaAcctPolicyAssetService policyAssetService;

	/**
	 * 账簿
	 */
	@Resource
	private TBdAccountBookMapper bookMapper;

	/**
	 * 会计核算体系
	 */
	@Resource
	private TOrgAccountSystemMapper accountSystemMapper;


    @Override
    public TFaAcctPolicyVo queryById(Integer fAcctpolicyId){
		LambdaQueryWrapper<TFaAcctPolicy> lqw = Wrappers.lambdaQuery();
		lqw.eq(TFaAcctPolicy::getFAcctpolicyId, fAcctpolicyId);
		lqw.ne(TFaAcctPolicy::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
		TFaAcctPolicy policy = policyMapper.selectOne(lqw);
        return BeanUtil.toBean(policy, TFaAcctPolicyVo.class);
    }

    @Override
    public TableDataInfo<TFaAcctPolicyVo> queryPageList(TFaAcctPolicyQueryBo bo) {
		Page<TFaAcctPolicyQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TFaAcctPolicyVo> policyVoIPage = policyMapper.queryPageList(userPage,bo);
        return PageUtils.buildDataInfo(policyVoIPage);
    }

    @Override
    public List<TFaAcctPolicyVo> queryList(TFaAcctPolicyQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TFaAcctPolicyVo.class);
    }

    private LambdaQueryWrapper<TFaAcctPolicy> buildQueryWrapper(TFaAcctPolicyQueryBo bo) {
        LambdaQueryWrapper<TFaAcctPolicy> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TFaAcctPolicy::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TFaAcctPolicy::getFNumber, bo.getFNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TFaAcctPolicy::getFDescription, bo.getFDescription());
        lqw.eq(bo.getFCurrencyId() != null, TFaAcctPolicy::getFCurrencyId, bo.getFCurrencyId());
        lqw.eq(bo.getFAcctcalendarId() != null, TFaAcctPolicy::getFAcctcalendarId, bo.getFAcctcalendarId());
        lqw.eq(bo.getFAcctgroupId() != null, TFaAcctPolicy::getFAcctgroupId, bo.getFAcctgroupId());
        lqw.eq(bo.getFRatetypeId() != null, TFaAcctPolicy::getFRatetypeId, bo.getFRatetypeId());
        lqw.eq(StrUtil.isNotBlank(bo.getFIscalbyexpItem()), TFaAcctPolicy::getFIscalbyexpItem, bo.getFIscalbyexpItem());
        lqw.eq(StrUtil.isNotBlank(bo.getFIstaxAmount()), TFaAcctPolicy::getFIstaxAmount, bo.getFIstaxAmount());
        lqw.eq(StrUtil.isNotBlank(bo.getFIscalbyactualCost()), TFaAcctPolicy::getFIscalbyactualCost, bo.getFIscalbyactualCost());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsdefault()), TFaAcctPolicy::getFIsdefault, bo.getFIsdefault());
        lqw.eq(bo.getFCreateOrgid() != null, TFaAcctPolicy::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TFaAcctPolicy::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TFaAcctPolicy::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TFaAcctPolicy::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TFaAcctPolicy::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TFaAcctPolicy::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TFaAcctPolicy::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TFaAcctPolicy::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TFaAcctPolicy::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TFaAcctPolicy::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TFaAcctPolicy::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TFaAcctPolicy::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TFaAcctPolicy::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TFaAcctPolicy::getFMasterId, bo.getFMasterId());
		lqw.ne(TFaAcctPolicy::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TFaAcctPolicyAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		// 新增会计政策
		TFaAcctPolicy add = BeanUtil.toBean(bo, TFaAcctPolicy.class);
		add.setFCreatorid(user.getUserId().intValue());
		add.setFCreateDate(new Date());
		validEntityBeforeSave(add);
		Boolean save = save(add);
		if (!save){
			throw new CustomException("保存失败,请稍后重试!",1000);
		}
		if (bo.getPolicyAssetList().size()>0){
			//新增资产政策
			bo.getPolicyAssetList().forEach(tFaAcctPolicyAsset -> tFaAcctPolicyAsset.setFAcctpolicyId(add.getFAcctpolicyId()));
			save = policyAssetService.insertByAddByList(bo.getPolicyAssetList());
		}
		return save;
    }

    @Override
    public Boolean updateByEditBo(TFaAcctPolicyEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TFaAcctPolicy update = BeanUtil.toBean(bo, TFaAcctPolicy.class);
		update.setFModifierid(user.getUserId().intValue());
		update.setFModifyDate(new Date());
		validEntityBeforeSave(update);
		Boolean up = updateById(update);
		if (up){
			//编辑资产政策
			if (bo.getPolicyAssetList().size()>0){
				bo.getPolicyAssetList().forEach(editBo -> editBo.setFAcctpolicyId(bo.getFAcctpolicyId()));
				up = policyAssetService.updateByEditBoList(bo.getPolicyAssetList());
			}
		}
		return up;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TFaAcctPolicy entity){
		//TODO 做一些数据校验,如唯一约束
		if (StringUtils.checkValNull(entity)){
			throw new CustomException("保存数据不能为空!",1000);
		}
		LambdaQueryWrapper<TFaAcctPolicy> lqw = Wrappers.lambdaQuery();
		lqw.eq(entity.getFNumber() != null, TFaAcctPolicy::getFNumber, entity.getFNumber());
		TFaAcctPolicy voOne = getVoOne(lqw, TFaAcctPolicy.class);
		if (StringUtils.checkValNotNull(voOne)){
			// 编辑时执行
			if (voOne.getFAcctpolicyId().equals(entity.getFAcctpolicyId())){

				if(BaseEnum.YES.getCode().equals(entity.getFIssysPreset())){
					throw new CustomException("系统预设数据不能修改",1000);
				}

				if(DataStatusEnum.AUDIT.getCode().equals(entity.getFDocumentStatus())){
					throw new CustomException("已审核数据不能修改",1000);
				}

				//验证是否
				if (voOne.getFForbidStatus().equals(BaseEnum.YES.getCode())) {
					throw new CustomException("当前数据已被禁用!",1000);
				}
				return;
			}
			throw new CustomException("当前编码已存在!",1000);
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids) {
		//校验
		if (StringUtils.checkValNull(ids)) {
			throw new CustomException("请至少选择一个删除!",1000);
		}
		for (Integer integer : ids) {
			LambdaQueryWrapper<TFaAcctPolicy> lqw = Wrappers.lambdaQuery();
			lqw.eq(TFaAcctPolicy::getFAcctpolicyId, integer);
			lqw.ne(TFaAcctPolicy::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
			TFaAcctPolicy policy = policyMapper.selectOne(lqw);

			if (StringUtils.checkValNull(policy)) {
				throw new CustomException(String.format("内码:%s数据不存在无法删除!",policy.getFName()), 1000);
			}
			if (BaseEnum.YES.getCode().equals(policy.getFIssysPreset())) {
				throw new CustomException(String.format("会计政策:%s,为系统预设无法删除!",policy.getFName()), 1000);
			}
			if (policy.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("会计政策:%s,已审核数据不能删除!",policy.getFName()), 1000);
			}

			if (policy.getFDocumentStatus().equals(DataStatusEnum.CREATE.getCode())){
				// 未使用的直接删除
				if (!removeById(integer)) {

					throw new CustomException(String.format("会计政策:%s,删除失败!",policy.getFName()),1000);
				}
				//删除资产政策
				LambdaQueryWrapper<TFaAcctPolicyAsset> lambdaQuery = Wrappers.lambdaQuery();
				lambdaQuery.eq(TFaAcctPolicyAsset::getFAcctpolicyId, policy.getFAcctpolicyId());
				policyAssetService.remove(lambdaQuery);
			}else {
				// 验证当前数据是否被引用
				String result = this.verifyThatItIsUsed(integer);
				if (StringUtils.checkValNotNull(result)){
					throw new CustomException(String.format("会计政策:%s,已被%s引用,无法删除!",policy.getFName(),result),1000);
				}
				// 已使用过的数据改状态
				TFaAcctPolicy accountSystem = new TFaAcctPolicy();
				accountSystem.setFAcctpolicyId(policy.getFAcctpolicyId());
				accountSystem.setFDocumentStatus(DataStatusEnum.DELETE.getCode());
				if (!updateById(accountSystem)) {
					throw new CustomException(String.format("会计政策:%s,删除失败!",policy.getFName()),1000);
				}
			}
		}
		return true;
    }

	/**
	 * 验证是否被引用
	 * @param fAccPolicyId
	 */
	private String verifyThatItIsUsed(Integer fAccPolicyId){
		// 返回结果
		StringBuilder result = new StringBuilder("");
		// 账簿 查询字段
		Map<String, Object> bookMap = new HashMap<>();
		bookMap.put("f_acct_policyid",fAccPolicyId);

		List<TBdAccountBook> tBdAccountBooks = bookMapper.selectByMap(bookMap);
		if (tBdAccountBooks.size()>0){
			result.append("账簿:");
			tBdAccountBooks.stream().map(bdAccountBook -> bdAccountBook.getFBookName() + ",").forEach(result::append);
			return result.toString();
		}

		List<TOrgAccountSystem> tOrgAcctSysEntryList = accountSystemMapper.selectByFAccPolicyId(fAccPolicyId);
		if (tOrgAcctSysEntryList.size()>0){
			result.append("会计核算体系:");
			tOrgAcctSysEntryList.forEach(tOrgAcctSysEntry -> result.append(tOrgAcctSysEntry.getFNumber()).append(","));
			return result.toString();
		}
		return result.toString();
	}


	@Override
	public Boolean auditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		ids.forEach(id->{
			TFaAcctPolicy old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("数据不存在!",id), 1000);
			}

			if (old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())){
				throw new CustomException(String.format("会计政策:%s已经审核,无法重复审核!",old.getFName()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("会计政策:%s审核失败,请稍后重试!",old.getFName()), 1000);
			}
		});
		return true;
	}

	/**
	 * 反审核
	 *
	 * @param ids
	 * @return
	 */
	@Override
	public Boolean antiAuditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		ids.forEach(id->{
			TFaAcctPolicy old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("数据不存在!",id), 1000);
			}

			if (!old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("会计政策:%s未审核,请先提交审核!",old.getFName()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.REJECT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("会计政策:%s反审核失败,请稍后重试!",old.getFName()), 1000);
			}
		});
		return true;
	}




    @Override
	public Boolean disableByEditBo(TFaAcctPolicyEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TFaAcctPolicyVo tFaAcctPolicyVo = queryById(bo.getFAcctpolicyId());
		if (tFaAcctPolicyVo.getFForbidStatus().equals(bo.getFForbidStatus())){
			switch (bo.getFForbidStatus()) {
				case "0":
					throw new CustomException("会计政策:" + tFaAcctPolicyVo.getFName() + ",已被解禁,无法重复解禁!", 1000);
				case "1":
					throw new CustomException("会计政策:" + tFaAcctPolicyVo.getFName() + ",已禁用,无法重复禁用!", 1000);
			}
		}
		TFaAcctPolicy update = BeanUtil.toBean(bo, TFaAcctPolicy.class);
		update.setFModifierid(user.getUserId().intValue());
		update.setFModifyDate(new Date());
		update.setFForbidderid(user.getUserId().intValue());
		update.setFForbidDate(new Date());
		return updateById(update);
	}
}
