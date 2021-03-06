package com.skeqi.finance.service.impl.asset;

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
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicyAsset;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAccountSystem;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVchgroupAcct;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.basicinformation.accountingpolicies.TFaAcctPolicyAssetMapper;
import com.skeqi.finance.mapper.basicinformation.accountingpolicies.TFaAcctPolicyMapper;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVoucherGroupVo;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeAddBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeQueryBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeEditBo;
import com.skeqi.finance.domain.asset.TFaAssetType;
import com.skeqi.finance.mapper.TFaAssetTypeMapper;
import com.skeqi.finance.pojo.vo.asset.TFaAssetTypeVo;
import com.skeqi.finance.service.asset.ITFaAssetTypeService;

import javax.annotation.Resource;
import java.util.*;

/**
 * ????????????Service???????????????
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TFaAssetTypeServiceImpl extends ServicePlusImpl<TFaAssetTypeMapper, TFaAssetType> implements ITFaAssetTypeService {
	@Resource
	private TokenService tokenService;

	@Resource
	private TFaAssetTypeMapper typeMapper;

	/**
	 * ????????????????????????
	 */
	@Resource
	private TFaAcctPolicyAssetMapper policyAssetMapper;

    @Override
    public TFaAssetTypeVo queryById(Integer fId){
		LambdaQueryWrapper<TFaAssetType> lqw = Wrappers.lambdaQuery();
		lqw.eq(TFaAssetType::getFId, fId);
		lqw.ne(TFaAssetType::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
		TFaAssetType tFaAssetType = typeMapper.selectOne(lqw);
		TFaAssetTypeVo typeVo = BeanUtil.toBean(tFaAssetType, TFaAssetTypeVo.class);
        return typeVo;
    }

    @Override
    public TableDataInfo<TFaAssetTypeVo> queryPageList(TFaAssetTypeQueryBo bo) {
		Page<TFaAssetTypeQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TFaAssetTypeVo> typeVoIPage = typeMapper.queryPageList(userPage,buildQueryWrapper(bo));
		return PageUtils.buildDataInfo(typeVoIPage);
    }

    @Override
    public List<TFaAssetTypeVo> queryList(TFaAssetTypeQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TFaAssetTypeVo.class);
    }

    private LambdaQueryWrapper<TFaAssetType> buildQueryWrapper(TFaAssetTypeQueryBo bo) {
        LambdaQueryWrapper<TFaAssetType> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TFaAssetType::getFNumber, bo.getFNumber());
        lqw.eq(TFaAssetType::getFGroup, bo.getFGroup());
        lqw.eq(StrUtil.isNotBlank(bo.getFCodeRule()), TFaAssetType::getFCodeRule, bo.getFCodeRule());
        lqw.eq(StrUtil.isNotBlank(bo.getFAssetCodeRule()), TFaAssetType::getFAssetCodeRule, bo.getFAssetCodeRule());
        lqw.eq(bo.getFUnitId() != null, TFaAssetType::getFUnitId, bo.getFUnitId());
        lqw.eq(StrUtil.isNotBlank(bo.getFIncludeTax()), TFaAssetType::getFIncludeTax, bo.getFIncludeTax());
        lqw.eq(bo.getFCardSwiftNumber() != null, TFaAssetType::getFCardSwiftNumber, bo.getFCardSwiftNumber());
        lqw.eq(bo.getFAssetSwiftNumber() != null, TFaAssetType::getFAssetSwiftNumber, bo.getFAssetSwiftNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFCodeRuleId()), TFaAssetType::getFCodeRuleId, bo.getFCodeRuleId());
        lqw.eq(StrUtil.isNotBlank(bo.getFAssetCodeRuleid()), TFaAssetType::getFAssetCodeRuleid, bo.getFAssetCodeRuleid());
        lqw.eq(bo.getFCreateOrgid() != null, TFaAssetType::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TFaAssetType::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TFaAssetType::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TFaAssetType::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TFaAssetType::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TFaAssetType::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TFaAssetType::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TFaAssetType::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TFaAssetType::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TFaAssetType::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TFaAssetType::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TFaAssetType::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TFaAssetType::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TFaAssetType::getFMasterId, bo.getFMasterId());
		lqw.ne(TFaAssetType::getFDocumentStatus, DataStatusEnum.DELETE.getCode());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TFaAssetTypeAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		// ??????
		TFaAssetType add = BeanUtil.toBean(bo, TFaAssetType.class);
		add.setFCreatorid(user.getUserId().intValue());
		add.setFCreateDate(new Date());
		validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TFaAssetTypeEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TFaAssetType update = BeanUtil.toBean(bo, TFaAssetType.class);
		update.setFModifierid(user.getUserId().intValue());
		update.setFModifyDate(new Date());
		validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * ????????????????????????
     *
     * @param entity ???????????????
     */
    private void validEntityBeforeSave(TFaAssetType entity){
		if (StringUtils.checkValNull(entity)){
			throw new CustomException("????????????????????????!",1000);
		}

		LambdaQueryWrapper<TFaAssetType> lqw = Wrappers.lambdaQuery();
		lqw.eq(entity.getFNumber() != null, TFaAssetType::getFNumber, entity.getFNumber());
		lqw.eq(TFaAssetType::getFGroup,entity.getFGroup());
		TFaAssetType voOne = getVoOne(lqw, TFaAssetType.class);
		if (StringUtils.checkValNotNull(voOne)){
			// ???????????????
			if (voOne.getFId().equals(entity.getFId())){

				if(BaseEnum.YES.getCode().equals(entity.getFIssysPreset())){
					throw new CustomException("??????????????????????????????",1000);
				}

				if(DataStatusEnum.AUDIT.getCode().equals(entity.getFDocumentStatus())){
					throw new CustomException("???????????????????????????",1000);
				}

				//????????????
				if (voOne.getFForbidStatus().equals(BaseEnum.YES.getCode())) {
					throw new CustomException(String.format("????????????:%s????????????!",voOne.getFNumber()),1000);
				}
				return;
			}
			throw new CustomException("???????????????+????????????,?????????????????????!",1000);
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids) {
		//??????
		if (StringUtils.checkValNull(ids)) {
			throw new CustomException("???????????????????????????!",1000);
		}
		for (Integer integer : ids) {
			LambdaQueryWrapper<TFaAssetType> lqw = Wrappers.lambdaQuery();
			lqw.eq(TFaAssetType::getFId, integer);
			lqw.ne(TFaAssetType::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
			TFaAssetType tFaAssetType = typeMapper.selectOne(lqw);

			if (StringUtils.checkValNull(tFaAssetType)) {
				throw new CustomException(String.format("??????:%s,???????????????????????????!",integer), 1000);
			}
			if (tFaAssetType.getFIssysPreset().equals(BaseEnum.YES.getCode())) {
				throw new CustomException(String.format("????????????:[%s],???????????????????????????!",tFaAssetType.getFNumber()), 1000);
			}
			if (tFaAssetType.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("????????????:[%s],?????????????????????????????????????????????????????????????????????!",tFaAssetType.getFNumber()), 1000);
			}
			if (tFaAssetType.getFDocumentStatus().equals(DataStatusEnum.CREATE.getCode())){
				// ????????????????????????
				if (!removeById(integer)) {
					throw new CustomException(String.format("????????????:[%s],????????????!",tFaAssetType.getFNumber()),1000);
				}
			}else {
				// ?????????????????????????????????
				String result = this.verifyThatItIsUsed(integer);
				if (StringUtils.checkValNotNull(result)){
					throw new CustomException(String.format("????????????:%s,??????%s??????,????????????!",tFaAssetType.getFNumber(),result),1000);
				}
				// ??????????????????????????????
				TFaAssetType group = new TFaAssetType();
				group.setFId(tFaAssetType.getFId());
				group.setFDocumentStatus(DataStatusEnum.DELETE.getCode());
				if (!updateById(group)) {
					throw new CustomException(String.format("????????????:[%s],????????????!",tFaAssetType.getFNumber()),1000);
				}
			}
		}
		return true;
    }

	/**
	 * ?????????????????????
	 * @param fAssetTypeId
	 */
	private String verifyThatItIsUsed(Integer fAssetTypeId){
		// ????????????
		StringBuilder result = new StringBuilder("");

		// ???????????????????????? ????????????
		Map<String, Object> map = new HashMap<>();
		map.put("f_asset_typeid",fAssetTypeId);

		List<TFaAcctPolicyAsset> tFaAcctPolicyAssetList = policyAssetMapper.selectByMap(map);
		if (tFaAcctPolicyAssetList.size()>0){
			result.append("????????????????????????");
			return result.toString();
		}
		return result.toString();

	}


	@Override
	public Boolean auditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		ids.forEach(id->{
			TFaAssetType old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("%s,???????????????!",id), 1000);
			}
			if (old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())){
				throw new CustomException(String.format("????????????:%s????????????,??????????????????!",old.getFNumber()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("????????????:%s????????????,???????????????!",old.getFNumber()), 1000);
			}
		});
		return true;
	}

	@Override
	public Boolean antiAuditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		ids.forEach(id->{
			TFaAssetType old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("%s,???????????????!",id), 1000);
			}

			if (!old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("????????????:%s?????????,??????????????????!",old.getFNumber()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.REJECT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("????????????:%s???????????????,???????????????!",old.getFNumber()), 1000);
			}
		});
		return true;
	}
}
