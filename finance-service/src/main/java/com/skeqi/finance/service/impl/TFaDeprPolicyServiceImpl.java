package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicyAsset;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.basicinformation.accountingpolicies.TFaAcctPolicyAssetMapper;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprPolicyAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprPolicyQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprPolicyEditBo;
import com.skeqi.finance.domain.TFaDeprPolicy;
import com.skeqi.finance.mapper.TFaDeprPolicyMapper;
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprPolicyVo;
import com.skeqi.finance.service.depr.ITFaDeprPolicyService;

import javax.annotation.Resource;
import java.util.*;

/**
 * 折旧政策Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TFaDeprPolicyServiceImpl extends ServicePlusImpl<TFaDeprPolicyMapper, TFaDeprPolicy> implements ITFaDeprPolicyService {

	private static String msg = "折旧政策";

	@Resource
	private TokenService tokenService;

	@Resource
	private TFaDeprPolicyMapper mapper;

	/**
	 * 会计政策资产政策
	 */
	@Resource
	private TFaAcctPolicyAssetMapper policyAssetMapper;

    @Override
    public TFaDeprPolicyVo queryById(Integer fPolicyId){
		LambdaQueryWrapper<TFaDeprPolicy> lqw = Wrappers.lambdaQuery();
		lqw.eq(TFaDeprPolicy::getFPolicyId, fPolicyId);
		lqw.ne(TFaDeprPolicy::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
		TFaDeprPolicy tFaDeprPolicy = mapper.selectOne(lqw);
		return BeanUtil.toBean(tFaDeprPolicy, TFaDeprPolicyVo.class);
    }

    @Override
    public TableDataInfo<TFaDeprPolicyVo> queryPageList(TFaDeprPolicyQueryBo bo) {
        PagePlus<TFaDeprPolicy, TFaDeprPolicyVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TFaDeprPolicyVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TFaDeprPolicyVo> queryList(TFaDeprPolicyQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TFaDeprPolicyVo.class);
    }

    private LambdaQueryWrapper<TFaDeprPolicy> buildQueryWrapper(TFaDeprPolicyQueryBo bo) {
        LambdaQueryWrapper<TFaDeprPolicy> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TFaDeprPolicy::getFNumber, bo.getFNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFClearDeprPolicy()), TFaDeprPolicy::getFClearDeprPolicy, bo.getFClearDeprPolicy());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsaffectDepr()), TFaDeprPolicy::getFIsaffectDepr, bo.getFIsaffectDepr());
        lqw.eq(StrUtil.isNotBlank(bo.getFAffectprPolicy()), TFaDeprPolicy::getFAffectprPolicy, bo.getFAffectprPolicy());
        lqw.eq(bo.getFCreateOrgid() != null, TFaDeprPolicy::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TFaDeprPolicy::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TFaDeprPolicy::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TFaDeprPolicy::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TFaDeprPolicy::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TFaDeprPolicy::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TFaDeprPolicy::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TFaDeprPolicy::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TFaDeprPolicy::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TFaDeprPolicy::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TFaDeprPolicy::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TFaDeprPolicy::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TFaDeprPolicy::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TFaDeprPolicy::getFMasterId, bo.getFMasterId());
		lqw.ne(TFaDeprPolicy::getFDocumentStatus, DataStatusEnum.DELETE.getCode());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TFaDeprPolicyAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		// 新增
		TFaDeprPolicy add = BeanUtil.toBean(bo, TFaDeprPolicy.class);
		add.setFCreatorid(user.getUserId().intValue());
		add.setFCreateDate(new Date());
		validEntityBeforeSave(add);
		return save(add);
    }

    @Override
    public Boolean updateByEditBo(TFaDeprPolicyEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TFaDeprPolicy update = BeanUtil.toBean(bo, TFaDeprPolicy.class);
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
    private void validEntityBeforeSave(TFaDeprPolicy entity){
		if (StringUtils.checkValNull(entity)){
			throw new CustomException("保存数据不能为空!",1000);
		}

		LambdaQueryWrapper<TFaDeprPolicy> lqw = Wrappers.lambdaQuery();
		lqw.eq(entity.getFNumber() != null, TFaDeprPolicy::getFNumber, entity.getFNumber());
		TFaDeprPolicy voOne = getVoOne(lqw, TFaDeprPolicy.class);
		if (StringUtils.checkValNotNull(voOne)){
			// 编辑时执行
			if (voOne.getFPolicyId().equals(entity.getFPolicyId())){

				if(BaseEnum.YES.getCode().equals(entity.getFIssysPreset())){
					throw new CustomException("系统预设数据不能修改",1000);
				}

				if(DataStatusEnum.AUDIT.getCode().equals(entity.getFDocumentStatus())){
					throw new CustomException("已审核数据不能修改",1000);
				}

				//验证是否
				if (voOne.getFForbidStatus().equals(BaseEnum.YES.getCode())) {
					throw new CustomException(String.format("%s:%s,已被禁用!",msg,voOne.getFNumber()),1000);
				}
				return;
			}
			throw new CustomException("编码唯一,当前组合已存在!",1000);
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids) {
		//校验
		if (StringUtils.checkValNull(ids)) {
			throw new CustomException("请至少选择一个删除!",1000);
		}
		for (Integer integer : ids) {
			LambdaQueryWrapper<TFaDeprPolicy> lqw = Wrappers.lambdaQuery();
			lqw.eq(TFaDeprPolicy::getFPolicyId, integer);
			lqw.ne(TFaDeprPolicy::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
			TFaDeprPolicy deprPolicy = mapper.selectOne(lqw);

			if (StringUtils.checkValNull(deprPolicy)) {
				throw new CustomException(String.format("内码:%s,数据不存在无法删除!",integer), 1000);
			}
			if (deprPolicy.getFIssysPreset().equals(BaseEnum.YES.getCode())) {
				throw new CustomException(String.format("%s:[%s],为系统预设无法删除!",msg,deprPolicy.getFNumber()), 1000);
			}
			if (deprPolicy.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("%s:[%s],数据状态为暂存、创建或重新审核的数据才允许删除!",msg,deprPolicy.getFNumber()), 1000);
			}
			if (deprPolicy.getFDocumentStatus().equals(DataStatusEnum.CREATE.getCode())){
				// 未使用的直接删除
				if (!removeById(integer)) {
					throw new CustomException(String.format("%s:[%s],删除失败!",msg,deprPolicy.getFNumber()),1000);
				}
			}else {
				// 验证当前数据是否被引用
				String result = this.verifyThatItIsUsed(integer);
				if (StringUtils.checkValNotNull(result)){
					throw new CustomException(String.format("%s:%s,已被%s引用,无法删除!",msg,deprPolicy.getFNumber(),result),1000);
				}
				// 已使用过的数据改状态
				TFaDeprPolicy group = new TFaDeprPolicy();
				group.setFPolicyId(deprPolicy.getFPolicyId());
				group.setFDocumentStatus(DataStatusEnum.DELETE.getCode());
				if (!updateById(group)) {
					throw new CustomException(String.format("%s:[%s],删除失败!",msg,deprPolicy.getFNumber()),1000);
				}
			}
		}
		return true;
    }

	/**
	 * 验证是否被引用
	 * @param fPolicyId
	 */
	private String verifyThatItIsUsed(Integer fPolicyId){
		// 返回结果
		StringBuilder result = new StringBuilder("");

		// 会计政策资产政策 查询字段
		Map<String, Object> map = new HashMap<>();
		map.put("f_depr_policy_id",fPolicyId);

		List<TFaAcctPolicyAsset> tFaAcctPolicyAssetList = policyAssetMapper.selectByMap(map);
		if (tFaAcctPolicyAssetList.size()>0){
			result.append("会计政策资产政策");
			return result.toString();
		}
		return result.toString();

	}

	/**
	 * 审核
	 *
	 * @param ids
	 * @return
	 */
	@Override
	public Boolean auditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		ids.forEach(id->{
			TFaDeprPolicy old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("%s,数据不存在!",id), 1000);
			}
			if (old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())){
				throw new CustomException(String.format("%s:%s已经审核,无法重复审核!",msg,old.getFNumber()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("%s:%s审核失败,请稍后重试!",msg,old.getFNumber()), 1000);
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
			TFaDeprPolicy old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("%s,数据不存在!",id), 1000);
			}

			if (!old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("%s:%s未审核,请先提交审核!",msg,old.getFNumber()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.REJECT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("%s:%s反审核失败,请稍后重试!",msg,old.getFNumber()), 1000);
			}
		});
		return true;
	}
}
