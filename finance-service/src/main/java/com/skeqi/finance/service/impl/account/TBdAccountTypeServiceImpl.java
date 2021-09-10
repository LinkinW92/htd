package com.skeqi.finance.service.impl.account;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.finance.domain.TBdAccountType;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.BorrowEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.account.TBdAccountTypeMapper;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeAddBo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeEditBo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeQueryBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountGroupVo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTableVo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTypeVo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountVo;
import com.skeqi.finance.service.account.ITBdAccountGroupService;
import com.skeqi.finance.service.account.ITBdAccountService;
import com.skeqi.finance.service.account.ITBdAccountTableService;
import com.skeqi.finance.service.account.ITBdAccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 科目类别Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdAccountTypeServiceImpl extends ServicePlusImpl<TBdAccountTypeMapper, TBdAccountType> implements ITBdAccountTypeService {
	//会计要素信息
	@Autowired
	private ITBdAccountGroupService itBdAccountGroupService;
	//科目表
	@Autowired
	private ITBdAccountTableService itBdAccountTableService;
	//科目信息
	@Autowired
	private ITBdAccountService itBdAccountService;


	@Override
	public TBdAccountTypeVo queryById(Integer fAcctTypeId) {
		return baseMapper.queryOne2(fAcctTypeId);
	}

	@Override
	public TableDataInfo<TBdAccountTypeVo> queryPageList(TBdAccountTypeQueryBo bo) {
		if (bo.getPageNum() == null) bo.setPageNum(1);
		if (bo.getPageSize() == null) bo.setPageSize(10);
		IPage<TBdAccountTypeVo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		Page<TBdAccountTypeVo> tBdAccountTypeVoPage = baseMapper.queryList2(page, bo);
		return PageUtils.buildDataInfo(tBdAccountTypeVoPage);
	}

	@Override
	public List<TBdAccountTypeVo> queryList(TBdAccountTypeQueryBo bo) {
		return listVo(buildQueryWrapper(bo), TBdAccountTypeVo.class);
	}

	private LambdaQueryWrapper<TBdAccountType> buildQueryWrapper(TBdAccountTypeQueryBo bo) {
		Map<String, Object> params = bo.getParams();
		LambdaQueryWrapper<TBdAccountType> lqw = Wrappers.lambdaQuery();
		lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdAccountType::getFNumber, bo.getFNumber());
		lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdAccountType::getFName, bo.getFName());
		lqw.eq(bo.getFAcctGroupId() != null, TBdAccountType::getFAcctGroupId, bo.getFAcctGroupId());
		lqw.eq(bo.getFAcctTableId() != null, TBdAccountType::getFAcctTableId, bo.getFAcctTableId());
		lqw.eq(StrUtil.isNotBlank(bo.getFDc()), TBdAccountType::getFDc, bo.getFDc());
		lqw.eq(bo.getFLevel() != null, TBdAccountType::getFLevel, bo.getFLevel());
		lqw.eq(bo.getFParentId() != null, TBdAccountType::getFParentId, bo.getFParentId());
		lqw.eq(StrUtil.isNotBlank(bo.getFPriorplAdjust()), TBdAccountType::getFPriorplAdjust, bo.getFPriorplAdjust());
		lqw.eq(bo.getFCreateOrgid() != null, TBdAccountType::getFCreateOrgid, bo.getFCreateOrgid());
		lqw.eq(bo.getFCreatorid() != null, TBdAccountType::getFCreatorid, bo.getFCreatorid());
		lqw.eq(bo.getFCreateDate() != null, TBdAccountType::getFCreateDate, bo.getFCreateDate());
		lqw.eq(bo.getFUseOrgid() != null, TBdAccountType::getFUseOrgid, bo.getFUseOrgid());
		lqw.eq(bo.getFModifierid() != null, TBdAccountType::getFModifierid, bo.getFModifierid());
		lqw.eq(bo.getFModifyDate() != null, TBdAccountType::getFModifyDate, bo.getFModifyDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdAccountType::getFDocumentStatus, bo.getFDocumentStatus());
		lqw.eq(bo.getFAuditorid() != null, TBdAccountType::getFAuditorid, bo.getFAuditorid());
		lqw.eq(bo.getFAuditDate() != null, TBdAccountType::getFAuditDate, bo.getFAuditDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdAccountType::getFForbidStatus, bo.getFForbidStatus());
		lqw.eq(bo.getFForbidderid() != null, TBdAccountType::getFForbidderid, bo.getFForbidderid());
		lqw.eq(bo.getFForbidDate() != null, TBdAccountType::getFForbidDate, bo.getFForbidDate());
		lqw.eq(bo.getFIssysPreset() != null, TBdAccountType::getFIssysPreset, bo.getFIssysPreset());
		lqw.eq(bo.getFMasterId() != null, TBdAccountType::getFMasterId, bo.getFMasterId());
		return lqw;
	}

	@Override
	public Boolean insertByAddBo(TBdAccountTypeAddBo bo) {
		TBdAccountType add = BeanUtil.toBean(bo, TBdAccountType.class);
		validEntityBeforeSave(add);
		bo.setFCreateDate(new Date());
		bo.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
		bo.setFForbidStatus(BaseEnum.NO.getCode());
		//系统预设
		add.setFIssysPreset(BaseEnum.NO.getCode());
		return save(add);
	}

	@Override
	public Boolean updateByEditBo(TBdAccountTypeEditBo bo) {
		TBdAccountType update = BeanUtil.toBean(bo, TBdAccountType.class);
		validEntityBeforeUpdate(update);
		update.setFModifyDate(new Date());
		return updateById(update);
	}


	/**
	 * 保存前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeSave(TBdAccountType entity) {
		entity.setAddDefault();
		//TODO 做一些数据校验,如唯一约束
		//会计要素表内码
		Integer acctGroupTblid = entity.getFAcctGroupId();
		TBdAccountGroupVo tBdAccountGroupVo = itBdAccountGroupService.queryById(acctGroupTblid);
		if (null == tBdAccountGroupVo) {
			throw new CustomException("会计要素信息不存在", 1000);
		}
		//科目表内码
		Integer acctTableId = entity.getFAcctTableId();
		TBdAccountTableVo tBdAccountTableVo = itBdAccountTableService.queryById(acctTableId);
		if (null == tBdAccountTableVo) {
			throw new CustomException("科目表不存在", 1000);
		}
		//编码为“PER15”的科目类别，同一科目表下科目类别编码需唯一
		String number = entity.getFNumber();
		LambdaQueryWrapper<TBdAccountType> lqw = Wrappers.lambdaQuery();
		lqw.eq(TBdAccountType::getFNumber, number);
		lqw.eq(TBdAccountType::getFAcctTableId, acctGroupTblid);
		lqw.eq(TBdAccountType::getFAcctGroupId, acctTableId);
		TBdAccountType tBdAccountType = getVoOne(lqw, TBdAccountType.class);
		if (tBdAccountType != null && tBdAccountType.getFNumber() != null) {
			throw new CustomException(String.format("编码为“%s”的科目类别，同一科目表下科目类别编码需唯一", number), 1000);
		}
		//借贷方向  1:借方 ；-1：贷方 \r\n
		String dc = entity.getFDc();
		if (BorrowEnum.getObj(dc) == null) {
			throw new CustomException(String.format("借贷方向 只能是（1:借方 ；-1：贷方）,收到的是%s", dc), 1000);
		}
	}

	/**
	 * 修改前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeUpdate(TBdAccountType entity) {
		//TODO 做一些数据校验,如唯一约束
		//会计要素表内码
		Integer acctGroupTblid = entity.getFAcctGroupId();
		TBdAccountGroupVo tBdAccountGroupVo = itBdAccountGroupService.queryById(acctGroupTblid);
		if (null == tBdAccountGroupVo) {
			throw new CustomException("会计要素信息不存在", 1000);
		}
		//科目表内码
		Integer acctTableId = entity.getFAcctTableId();
		if (acctTableId != null) {
			TBdAccountTableVo tBdAccountTableVo = itBdAccountTableService.queryById(acctTableId);
			if (null == tBdAccountTableVo) {
				throw new CustomException("科目表内码", 1000);
			}
		}
		//编码为“PER15”的科目类别，同一科目表下科目类别编码需唯一
		String number = entity.getFNumber();
		if(number != null){
			if(acctGroupTblid == null) throw new CustomException("修改编码，会计要素表id必须",1000);
			if(acctTableId == null) throw new CustomException("修改编码，科目表id必须",1000);
			LambdaQueryWrapper<TBdAccountType> lqw = Wrappers.lambdaQuery();
			lqw.eq(TBdAccountType::getFNumber, number);
			lqw.eq(TBdAccountType::getFAcctTableId, acctGroupTblid);
			lqw.eq(TBdAccountType::getFAcctGroupId, acctTableId);
			TBdAccountType tBdAccountType = getVoOne(lqw, TBdAccountType.class);
			if (tBdAccountType != null && tBdAccountType.getFNumber() != null) {
				throw new CustomException(String.format("编码为“%s”的科目类别，同一科目表下科目类别编码需唯一", number), 1000);
			}
		}
		//借贷方向  1:借方 ；-1：贷方 \r\n
		String dc = entity.getFDc();
		if (BorrowEnum.getObj(dc) == null) {
			throw new CustomException(String.format("借贷方向 只能是（1:借方 ；-1：贷方）,收到的是%s", dc), 1000);
		}

	}

	@Override
	public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		if (isValid) {
			//TODO 做一些业务上的校验,判断是否需要校验
			ids.stream().forEach(id -> {
				//存在科目信息的不能删除后
				TBdAccountQueryBo tBdAccountTypeQueryBo = new TBdAccountQueryBo();
				tBdAccountTypeQueryBo.setFGroupId(id);
				List<TBdAccountVo> tBdAccountVoList = itBdAccountService.queryList(tBdAccountTypeQueryBo);
				if (tBdAccountVoList.size() > 0) {
					throw new CustomException(String.format("%s科目信息存在不能删除", "" + id), 1000);
				}
			});
		}
		return removeByIds(ids);
	}
}
