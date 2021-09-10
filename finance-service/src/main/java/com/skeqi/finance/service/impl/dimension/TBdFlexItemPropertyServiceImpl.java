package com.skeqi.finance.service.impl.dimension;

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
import com.skeqi.finance.domain.TBdFlexItemProperty;
import com.skeqi.finance.domain.help.TBdBaseType;
import com.skeqi.finance.domain.help.TBdHelpType;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.TBdHelpTypeMapper;
import com.skeqi.finance.mapper.dimension.TBdDimensionSourceMapper;
import com.skeqi.finance.mapper.dimension.TBdFlexItemPropertyMapper;
import com.skeqi.finance.pojo.bo.TBdAccountFlexentry.TBdAccountFlexentryQueryBo;
import com.skeqi.finance.pojo.bo.TBdDimensionSource.DisableBo;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyAddBo;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyEditBo;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyQueryBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountFlexentryVo;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;
import com.skeqi.finance.service.basicinformation.base.BaseTableService;
import com.skeqi.finance.service.account.ITBdAccountFlexentryService;
import com.skeqi.finance.service.dimension.ITBdDimensionSourceService;
import com.skeqi.finance.service.dimension.ITBdFlexItemPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 核算维度Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdFlexItemPropertyServiceImpl extends ServicePlusImpl<TBdFlexItemPropertyMapper, TBdFlexItemProperty> implements ITBdFlexItemPropertyService, BaseTableService {


	//科目核算维度组
	@Autowired
	private ITBdAccountFlexentryService itBdAccountFlexentryService;
	@SuppressWarnings("all")
	@Autowired
	private TBdDimensionSourceMapper tBdDimensionSourceMapper;
	@SuppressWarnings("all")
	@Autowired
	private TBdHelpTypeMapper tBdHelpTypeMapper;

	@Override
	public TBdFlexItemPropertyVo queryById(Integer fId) {
		return baseMapper.queryOne2(fId);
	}

	@Override
	public TableDataInfo<TBdFlexItemPropertyVo> queryPageList(TBdFlexItemPropertyQueryBo bo) {
		if (bo.getPageNum() == null) bo.setPageNum(1);
		if (bo.getPageSize() == null) bo.setPageSize(10);
		IPage<TBdFlexItemPropertyVo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		Page<TBdFlexItemPropertyVo> tBdFlexItemPropertyVoPage = baseMapper.queryList2(page, bo);
		return PageUtils.buildDataInfo(tBdFlexItemPropertyVoPage);
	}

	@Override
	public List<TBdFlexItemPropertyVo> queryList(TBdFlexItemPropertyQueryBo bo) {
		return listVo(buildQueryWrapper(bo), TBdFlexItemPropertyVo.class);
	}

	private LambdaQueryWrapper<TBdFlexItemProperty> buildQueryWrapper(TBdFlexItemPropertyQueryBo bo) {
		Map<String, Object> params = bo.getParams();
		LambdaQueryWrapper<TBdFlexItemProperty> lqw = Wrappers.lambdaQuery();
		lqw.eq(bo.getFDimensionSourceId() != null, TBdFlexItemProperty::getFDimensionSourceId, bo.getFDimensionSourceId());
		lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdFlexItemProperty::getFName, bo.getFName());
		lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TBdFlexItemProperty::getFDescription, bo.getFDescription());
		lqw.eq(bo.getFCreateOrgid() != null, TBdFlexItemProperty::getFCreateOrgid, bo.getFCreateOrgid());
		lqw.eq(bo.getFCreatorid() != null, TBdFlexItemProperty::getFCreatorid, bo.getFCreatorid());
		lqw.eq(bo.getFCreateDate() != null, TBdFlexItemProperty::getFCreateDate, bo.getFCreateDate());
		lqw.eq(bo.getFUseOrgid() != null, TBdFlexItemProperty::getFUseOrgid, bo.getFUseOrgid());
		lqw.eq(bo.getFModifierid() != null, TBdFlexItemProperty::getFModifierid, bo.getFModifierid());
		lqw.eq(bo.getFModifyDate() != null, TBdFlexItemProperty::getFModifyDate, bo.getFModifyDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdFlexItemProperty::getFDocumentStatus, bo.getFDocumentStatus());
		lqw.eq(bo.getFAuditorid() != null, TBdFlexItemProperty::getFAuditorid, bo.getFAuditorid());
		lqw.eq(bo.getFAuditDate() != null, TBdFlexItemProperty::getFAuditDate, bo.getFAuditDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdFlexItemProperty::getFForbidStatus, bo.getFForbidStatus());
		lqw.eq(bo.getFForbidderid() != null, TBdFlexItemProperty::getFForbidderid, bo.getFForbidderid());
		lqw.eq(bo.getFForbidDate() != null, TBdFlexItemProperty::getFForbidDate, bo.getFForbidDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFIssysPreset()), TBdFlexItemProperty::getFIssysPreset, bo.getFIssysPreset());
		lqw.eq(bo.getFMasterId() != null, TBdFlexItemProperty::getFMasterId, bo.getFMasterId());
		return lqw;
	}

	@Override
	public Boolean insertByAddBo(TBdFlexItemPropertyAddBo bo) {
		TBdFlexItemProperty add = BeanUtil.toBean(bo, TBdFlexItemProperty.class);
		validEntityBeforeSave(add);
		//创建日期
		add.setFCreateDate(new Date());
		//数据状态
		add.setFDocumentStatus(DataStatusEnum.CREATE.getCode());
		//禁用状态
		add.setFForbidStatus(BaseEnum.NO.getCode());
		//系统预设
		add.setFIssysPreset(BaseEnum.NO.getCode());
		return save(add);
	}

	@Override
	public Boolean updateByEditBo(TBdFlexItemPropertyEditBo bo) {
		TBdFlexItemProperty update = BeanUtil.toBean(bo, TBdFlexItemProperty.class);
		update.setUpdateDefault();
		validEntityBeforeUpdate(update);
		Date now = new Date();
		//修改时间
		update.setFAuditDate(now);
		return updateById(update);
	}

	/**
	 * 保存前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeSave(TBdFlexItemProperty entity) {
		entity.setAddDefault();
		//TODO 做一些数据校验,如唯一约束
		//维度来源
		Integer dimensionSourceId = entity.getFDimensionSourceId();
		String type = entity.getFType();
		if (type.equals("1")) {
			TBdBaseType oneBastType = tBdDimensionSourceMapper.getOneBastType(dimensionSourceId);
			if (oneBastType == null) {
				throw new CustomException(String.format("基础资料不存在"), 1000);
			}
		} else {
			TBdHelpType tBdHelpType = tBdHelpTypeMapper.selectById(dimensionSourceId);
			if (tBdHelpType == null) {
				throw new CustomException(String.format("辅助资料不存在"), 1000);
			}
		}
//		Integer dimensionSourceId = entity.getFDimensionSourceId();
//		TBdDimensionSourceVo tBdDimensionSourceVo = itBdDimensionSourceService.queryById(dimensionSourceId);
//		if (null == tBdDimensionSourceVo) {
//			throw new CustomException("维度来源不存在", 1000);
//		} else if (BaseEnum.YES.getCode().equals(tBdDimensionSourceVo.getFForbidStatus())) {
//			throw new CustomException(String.format("%s维度来源被禁用", "" + dimensionSourceId), 1000);
//		} else if (!DataStatusEnum.AUDIT.getCode().equals(tBdDimensionSourceVo.getFDocumentStatus())) {
//			throw new CustomException(String.format("%s维度来源状态是", "" + DataStatusEnum.getObj(tBdDimensionSourceVo.getFDocumentStatus())), 1000);
//		}
		//编码
		String number = entity.getFNumber();
		LambdaQueryWrapper<TBdFlexItemProperty> lqw = Wrappers.lambdaQuery();
		lqw.eq(TBdFlexItemProperty::getFNumber, number);
		TBdFlexItemPropertyVo tBdFlexItemPropertyVo = getVoOne(lqw, TBdFlexItemPropertyVo.class);
		if (tBdFlexItemPropertyVo != null && tBdFlexItemPropertyVo.getFNumber() != null) {
			throw new CustomException(String.format("%s编码已使用", number), 1000);
		}

	}


	/**
	 * 修改前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeUpdate(TBdFlexItemProperty entity) {
		//TODO 做一些数据校验,如唯一约束
		//如果审核状态：编码不修改。维度来源不修改。
		TBdFlexItemPropertyVo bdFlexItemPropertyVo = queryById(entity.getFId());
		if (bdFlexItemPropertyVo.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
			entity.setFNumber(null);
			entity.setFDimensionSourceId(null);
		}

		//维度来源
		Integer dimensionSourceId = entity.getFDimensionSourceId();
		String type = entity.getFType();
		if (type.equals("1")) {
			TBdBaseType oneBastType = tBdDimensionSourceMapper.getOneBastType(dimensionSourceId);
			if (oneBastType == null) {
				throw new CustomException(String.format("基础资料不存在"), 1000);
			}
		} else {
			TBdHelpType tBdHelpType = tBdHelpTypeMapper.selectById(dimensionSourceId);
			if (tBdHelpType == null) {
				throw new CustomException(String.format("辅助资料不存在"), 1000);
			}
		}
		//编码
		String number = entity.getFNumber();
		if (number != null) {
			if (bdFlexItemPropertyVo.getFNumber().equals(number)) {
				throw new CustomException(String.format("%s编码已使用", number), 1000);
			}
		}
	}

	@Override
	public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		if (isValid) {
			//TODO 做一些业务上的校验,判断是否需要校验
			//科目核算维度组分录使用不能删
			ids.stream().forEach(id -> {
				TBdAccountFlexentryQueryBo afqb = new TBdAccountFlexentryQueryBo();
				afqb.setFFlexitempropertyId(id);
				List<TBdAccountFlexentryVo> tBdAccountFlexentryVos = itBdAccountFlexentryService.queryList(afqb);
				if (tBdAccountFlexentryVos.size() > 0) {
					throw new CustomException(String.format("%s科目核算维度组分录使用不能删除", "" + id), 1000);
				}
			});
		}
		return removeByIds(ids);
	}

	@Override
	public Boolean audit(Collection ids) {
		Date now = new Date();
		ids.stream().forEach(id -> {
			TBdFlexItemProperty update = new TBdFlexItemProperty();
			update.setFAuditDate(now);
			update.setFId(Integer.parseInt(id.toString()));
			update.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
			updateById(update);
		});
		return true;
	}

	@Override
	public Boolean disable(DisableBo bo) {
		Date now = new Date();

		TBdFlexItemProperty update = BeanUtil.toBean(bo, TBdFlexItemProperty.class);
		//科目核算维度组分录使用不能删除
		TBdAccountFlexentryQueryBo afqb = new TBdAccountFlexentryQueryBo();
		Integer id = update.getFId();
		afqb.setFFlexitempropertyId(id);
		List<TBdAccountFlexentryVo> tBdAccountFlexentryVos = itBdAccountFlexentryService.queryList(afqb);
		if (tBdAccountFlexentryVos.size() > 0) {
			throw new CustomException(String.format("%s科目核算维度组分录使用不能删除", "" + id), 1000);
		}
		//禁用状态只能是：1是，0否
		String forbidStatus = update.getFForbidStatus();
		BaseEnum forBaseEnum = BaseEnum.getObj(forbidStatus);
		if (null == forBaseEnum) {
			throw new CustomException(String.format("禁用状态只能是：1是，0否,收到的是%s", forbidStatus), 1000);
		}
		//禁用时间
		update.setFForbidDate(now);
		return updateById(update);
	}
}
