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
 * 资产类别Service业务层处理
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
	 * 会计政策资产政策
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
		// 新增
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
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TFaAssetType entity){
		if (StringUtils.checkValNull(entity)){
			throw new CustomException("保存数据不能为空!",1000);
		}

		LambdaQueryWrapper<TFaAssetType> lqw = Wrappers.lambdaQuery();
		lqw.eq(entity.getFNumber() != null, TFaAssetType::getFNumber, entity.getFNumber());
		lqw.eq(TFaAssetType::getFGroup,entity.getFGroup());
		TFaAssetType voOne = getVoOne(lqw, TFaAssetType.class);
		if (StringUtils.checkValNotNull(voOne)){
			// 编辑时执行
			if (voOne.getFId().equals(entity.getFId())){

				if(BaseEnum.YES.getCode().equals(entity.getFIssysPreset())){
					throw new CustomException("系统预设数据不能修改",1000);
				}

				if(DataStatusEnum.AUDIT.getCode().equals(entity.getFDocumentStatus())){
					throw new CustomException("已审核数据不能修改",1000);
				}

				//验证是否
				if (voOne.getFForbidStatus().equals(BaseEnum.YES.getCode())) {
					throw new CustomException(String.format("资产类别:%s已被禁用!",voOne.getFNumber()),1000);
				}
				return;
			}
			throw new CustomException("资产类别组+编码唯一,当前组合已存在!",1000);
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids) {
		//校验
		if (StringUtils.checkValNull(ids)) {
			throw new CustomException("请至少选择一个删除!",1000);
		}
		for (Integer integer : ids) {
			LambdaQueryWrapper<TFaAssetType> lqw = Wrappers.lambdaQuery();
			lqw.eq(TFaAssetType::getFId, integer);
			lqw.ne(TFaAssetType::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
			TFaAssetType tFaAssetType = typeMapper.selectOne(lqw);

			if (StringUtils.checkValNull(tFaAssetType)) {
				throw new CustomException(String.format("内码:%s,数据不存在无法删除!",integer), 1000);
			}
			if (tFaAssetType.getFIssysPreset().equals(BaseEnum.YES.getCode())) {
				throw new CustomException(String.format("资产类别:[%s],为系统预设无法删除!",tFaAssetType.getFNumber()), 1000);
			}
			if (tFaAssetType.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("资产类别:[%s],数据状态为暂存、创建或重新审核的数据才允许删除!",tFaAssetType.getFNumber()), 1000);
			}
			if (tFaAssetType.getFDocumentStatus().equals(DataStatusEnum.CREATE.getCode())){
				// 未使用的直接删除
				if (!removeById(integer)) {
					throw new CustomException(String.format("资产类别:[%s],删除失败!",tFaAssetType.getFNumber()),1000);
				}
			}else {
				// 验证当前数据是否被引用
				String result = this.verifyThatItIsUsed(integer);
				if (StringUtils.checkValNotNull(result)){
					throw new CustomException(String.format("资产类别:%s,已被%s引用,无法删除!",tFaAssetType.getFNumber(),result),1000);
				}
				// 已使用过的数据改状态
				TFaAssetType group = new TFaAssetType();
				group.setFId(tFaAssetType.getFId());
				group.setFDocumentStatus(DataStatusEnum.DELETE.getCode());
				if (!updateById(group)) {
					throw new CustomException(String.format("资产类别:[%s],删除失败!",tFaAssetType.getFNumber()),1000);
				}
			}
		}
		return true;
    }

	/**
	 * 验证是否被引用
	 * @param fAssetTypeId
	 */
	private String verifyThatItIsUsed(Integer fAssetTypeId){
		// 返回结果
		StringBuilder result = new StringBuilder("");

		// 会计政策资产政策 查询字段
		Map<String, Object> map = new HashMap<>();
		map.put("f_asset_typeid",fAssetTypeId);

		List<TFaAcctPolicyAsset> tFaAcctPolicyAssetList = policyAssetMapper.selectByMap(map);
		if (tFaAcctPolicyAssetList.size()>0){
			result.append("会计政策资产政策");
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
				throw new CustomException(String.format("%s,数据不存在!",id), 1000);
			}
			if (old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())){
				throw new CustomException(String.format("资产类别:%s已经审核,无法重复审核!",old.getFNumber()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("资产类别:%s审核失败,请稍后重试!",old.getFNumber()), 1000);
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
				throw new CustomException(String.format("%s,数据不存在!",id), 1000);
			}

			if (!old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("资产类别:%s未审核,请先提交审核!",old.getFNumber()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.REJECT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("资产类别:%s反审核失败,请稍后重试!",old.getFNumber()), 1000);
			}
		});
		return true;
	}
}
