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
import com.skeqi.finance.domain.TBdAccountTable;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.account.TBdAccountTableMapper;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableAddBo;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableEditBo;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupQueryBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTableVo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTypeVo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountGroupTableVo;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVoucherGroupVo;
import com.skeqi.finance.service.account.ITBdAccountGroupTableService;
import com.skeqi.finance.service.account.ITBdAccountTableService;
import com.skeqi.finance.service.account.ITBdAccountTypeService;
import com.skeqi.finance.service.basicinformation.voucher.ITBdVoucherGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 科目Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdAccountTableServiceImpl extends ServicePlusImpl<TBdAccountTableMapper, TBdAccountTable> implements ITBdAccountTableService {

	//会计要素表
	@Autowired
	private ITBdAccountGroupTableService itBdAccountGroupTableService;
	//科目类型
	@Autowired
	private ITBdAccountTypeService itBdAccountTypeService;

	@Override
	public TBdAccountTableVo queryById(Integer fAcctTableId) {
		return baseMapper.queryOne2(fAcctTableId);
	}

	@Override
	public TableDataInfo<TBdAccountTableVo> queryPageList(TBdAccountTableQueryBo bo) {
		if (bo.getPageNum() == null) bo.setPageNum(1);
		if (bo.getPageSize() == null) bo.setPageSize(10);
		IPage<TBdAccountTableVo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		Page<TBdAccountTableVo> tBdAccountTableVoPage = baseMapper.queryList2(page, bo);
		return PageUtils.buildDataInfo(tBdAccountTableVoPage);
	}

	@Override
	public List<TBdAccountTableVo> queryList(TBdAccountTableQueryBo bo) {
		return listVo(buildQueryWrapper(bo), TBdAccountTableVo.class);
	}

	private LambdaQueryWrapper<TBdAccountTable> buildQueryWrapper(TBdAccountTableQueryBo bo) {
		Map<String, Object> params = bo.getParams();
		LambdaQueryWrapper<TBdAccountTable> lqw = Wrappers.lambdaQuery();
		//不查询逻辑删除数据
		lqw.ne(TBdAccountTable::getFDocumentStatus, DataStatusEnum.DELETE);

		lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdAccountTable::getFName, bo.getFName());
		lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TBdAccountTable::getFDescription, bo.getFDescription());
		lqw.eq(bo.getFAcctGroupTblid() != null, TBdAccountTable::getFAcctGroupTblid, bo.getFAcctGroupTblid());
		lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdAccountTable::getFNumber, bo.getFNumber());
		lqw.eq(bo.getFMaxGrade() != null, TBdAccountTable::getFMaxGrade, bo.getFMaxGrade());
		lqw.eq(StrUtil.isNotBlank(bo.getFIsuseControl()), TBdAccountTable::getFIsuseControl, bo.getFIsuseControl());
		lqw.eq(bo.getFCreateOrgid() != null, TBdAccountTable::getFCreateOrgid, bo.getFCreateOrgid());
		lqw.eq(bo.getFCreatorid() != null, TBdAccountTable::getFCreatorid, bo.getFCreatorid());
		lqw.eq(bo.getFCreateDate() != null, TBdAccountTable::getFCreateDate, bo.getFCreateDate());
		lqw.eq(bo.getFUseOrgid() != null, TBdAccountTable::getFUseOrgid, bo.getFUseOrgid());
		lqw.eq(bo.getFModifierid() != null, TBdAccountTable::getFModifierid, bo.getFModifierid());
		lqw.eq(bo.getFModifyDate() != null, TBdAccountTable::getFModifyDate, bo.getFModifyDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdAccountTable::getFDocumentStatus, bo.getFDocumentStatus());
		lqw.eq(bo.getFAuditorid() != null, TBdAccountTable::getFAuditorid, bo.getFAuditorid());
		lqw.eq(bo.getFAuditDate() != null, TBdAccountTable::getFAuditDate, bo.getFAuditDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdAccountTable::getFForbidStatus, bo.getFForbidStatus());
		lqw.eq(bo.getFForbidderid() != null, TBdAccountTable::getFForbidderid, bo.getFForbidderid());
		lqw.eq(bo.getFForbidDate() != null, TBdAccountTable::getFForbidDate, bo.getFForbidDate());
		lqw.eq(bo.getFIssysPreset() != null, TBdAccountTable::getFIssysPreset, bo.getFIssysPreset());
		lqw.eq(bo.getFMasterId() != null, TBdAccountTable::getFMasterId, bo.getFMasterId());
		return lqw;
	}

	@Override
	public Boolean insertByAddBo(TBdAccountTableAddBo bo) {
		TBdAccountTable add = BeanUtil.toBean(bo, TBdAccountTable.class);
		validEntityBeforeSave(add);
		//创建日期
		add.setFCreateDate(new Date());
		//数据状态
		add.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
		//禁用状态
		add.setFForbidStatus(BaseEnum.NO.getCode());
		//系统预设
		add.setFIssysPreset(BaseEnum.NO.getCode());
		return save(add);
	}

	@Override
	public Boolean updateByEditBo(TBdAccountTableEditBo bo) {
		TBdAccountTable update = BeanUtil.toBean(bo, TBdAccountTable.class);
		validEntityBeforeUpdate(update);
		update.setFModifyDate(new Date());
		return updateById(update);
	}

	/**
	 * 保存前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeSave(TBdAccountTable entity) {
		entity.setAddDefault();
		//TODO 做一些数据校验,如唯一约束
		//编码唯一
		String number = entity.getFNumber();
		LambdaQueryWrapper<TBdAccountTable> lqw = Wrappers.lambdaQuery();
		lqw.eq(TBdAccountTable::getFNumber, number);
		TBdAccountTable tBdAccountTable = getVoOne(lqw, TBdAccountTable.class);
		if (tBdAccountTable != null && tBdAccountTable.getFNumber() != null) {
			throw new CustomException(String.format("%s编码已使用", number), 1000);
		}
		//名称允许重复
		//描述
		//会计要素表内码
		Integer acctGroupTblid = entity.getFAcctGroupTblid();
		TBdAccountGroupTableVo tBdAccountGroupTableVo = itBdAccountGroupTableService.queryById(acctGroupTblid);
		if (null == tBdAccountGroupTableVo) {
			throw new CustomException("会计要素不存在", 1000);
		}
//		else if(BaseEnum.YES.getCode().equals(tBdAccountGroupTableVo.getFForbidStatus())){
//			throw new CustomException(String.format("%s会计要素被禁用",""+acctGroupTblid),1000);
//		}else if(!DataStatusEnum.AUDIT.getCode().equals(tBdAccountGroupTableVo.getFDocumentStatus())){
//			throw new CustomException(String.format("%s会计要素状态是",""+DataStatusEnum.getObj(tBdAccountGroupTableVo.getFDocumentStatus())),1000);
//		}
		//最大科目等级 最大6 ，0表示不限制
		if (entity.getFMaxGrade() < 0 || entity.getFMaxGrade() > 6) {
			throw new CustomException(String.format("最大科目等级最大0-6之间,收到的是%s", entity.getFMaxGrade()), 1000);
		}
		//是否启用管控 1是 0否 ，启用管控则数据为分配型，否则为共享型
		BaseEnum fIsuseControl = BaseEnum.getObj(entity.getFIsuseControl());
		if (null == fIsuseControl) {
			throw new CustomException(String.format("启用管控只能是（1是，0否）,收到的是%s", fIsuseControl.getCode()), 1000);
		}
		if (fIsuseControl.getCode().equals(BaseEnum.YES)) {
			//使用组织,管控组织
			Integer useOrgid = entity.getFUseOrgid();
			if (useOrgid == null) {
				throw new CustomException(String.format("启用管控后管控组织不能为空"), 1000);
			}
		}

	}


	/**
	 * 修改前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeUpdate(TBdAccountTable entity) {
		entity.setUpdateDefault();
		entity.setFIssysPreset(null);
		//TODO 做一些数据校验,如唯一约束
		//编码唯一
		String number = entity.getFNumber();
		Integer id = entity.getFId();
		if (number != null) {
			LambdaQueryWrapper<TBdAccountTable> lqw = Wrappers.lambdaQuery();
			lqw.eq(TBdAccountTable::getFNumber, number);
			lqw.ne(TBdAccountTable::getFId, id);
			TBdAccountTable tBdAccountTable = getVoOne(lqw, TBdAccountTable.class);
			if (tBdAccountTable != null && tBdAccountTable.getFNumber() != null) {
				throw new CustomException(String.format("%s编码已使用", number), 1000);
			}
		}
		//名称不重复。不能为空
		//描述
		//会计要素表内码
		Integer acctGroupTblid = entity.getFAcctGroupTblid();
		if (acctGroupTblid != null) {
			//科目类别存在不能修改会计要素
			TBdAccountTypeQueryBo tBdAccountTypeQueryBo = new TBdAccountTypeQueryBo();
			tBdAccountTypeQueryBo.setFAcctTableId(acctGroupTblid);
			List<TBdAccountTypeVo> tBdAccountTypeVoList = itBdAccountTypeService.queryList(tBdAccountTypeQueryBo);
			if (tBdAccountTypeVoList.size() > 0) {
				throw new CustomException("科目类别存在不能修改会计要素", 1000);
			}

			TBdAccountGroupTableVo tBdAccountGroupTableVo = itBdAccountGroupTableService.queryById(acctGroupTblid);
			if (null == tBdAccountGroupTableVo) {
				throw new CustomException("会计要素不存在", 1000);
			}
//			else if(BaseEnum.YES.getCode().equals(tBdAccountGroupTableVo.getFForbidStatus())){
//				throw new CustomException(String.format("%s会计要素被禁用",""+acctGroupTblid),1000);
//			}else if(!DataStatusEnum.AUDIT.getCode().equals(tBdAccountGroupTableVo.getFDocumentStatus())){
//				throw new CustomException(String.format("%s会计要素状态是",""+DataStatusEnum.getObj(tBdAccountGroupTableVo.getFDocumentStatus())),1000);
//			}
		}

		//最大科目等级 最大6 ，0表示不限制
		Integer maxGrade = entity.getFMaxGrade();
		if (maxGrade != null && (maxGrade < 0 || maxGrade > 6)) {
			throw new CustomException(String.format("最大科目等级最大0-6之间,收到的是%s", entity.getFMaxGrade()), 1000);
		}
		//是否启用管控 1是 0否 ，启用管控则数据为分配型，否则为共享型
		String isuseControl = entity.getFIsuseControl();
		if (isuseControl != null) {
			BaseEnum fIsuseControl = BaseEnum.getObj(isuseControl);
			if (null == fIsuseControl) {
				throw new CustomException(String.format("启用管控只能是（1是，0否）,收到的是%s", fIsuseControl.getCode()), 1000);
			}
			if (fIsuseControl == BaseEnum.YES) {
				//使用组织,管控组织
				Integer useOrgid = entity.getFUseOrgid();
				if (useOrgid == null) {
					throw new CustomException(String.format("启用管控后管控组织不能为空"), 1000);
				}
			}
		}
	}
	//凭证字
	@Autowired
	private ITBdVoucherGroupService itBdVoucherGroupService;
	@Override
	public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		if (isValid) {
			//TODO 做一些业务上的校验,判断是否需要校验
			ids.stream().forEach(id -> {
				//科目类别存在不能删除
				TBdAccountTypeQueryBo tBdAccountTypeQueryBo = new TBdAccountTypeQueryBo();
				tBdAccountTypeQueryBo.setFAcctTableId(id);
				List<TBdAccountTypeVo> tBdAccountTypeVoList = itBdAccountTypeService.queryList(tBdAccountTypeQueryBo);
				if (tBdAccountTypeVoList.size() > 0) {
					throw new CustomException(String.format("%s科目类别存在不能删除", "" + id), 1000);
				}
				//凭证字使用不能删除
				TBdVoucherGroupQueryBo tBdVoucherGroupQueryBo = new TBdVoucherGroupQueryBo();
				tBdVoucherGroupQueryBo.setFAccttableId(id);
				List<TBdVoucherGroupVo> tBdVoucherGroupVoList = itBdVoucherGroupService.queryList(tBdVoucherGroupQueryBo);
				if(tBdVoucherGroupVoList.size()>0){
					throw new CustomException(String.format("%s凭证字存在不能删除", "" + id), 1000);
				}
			});
		}
		return removeByIds(ids);
	}


}
