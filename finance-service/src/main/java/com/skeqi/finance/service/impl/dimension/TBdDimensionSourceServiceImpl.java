package com.skeqi.finance.service.impl.dimension;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.exception.CustomException;
import com.skeqi.finance.domain.TBdDimensionSource;
import com.skeqi.finance.domain.help.TBdBaseType;
import com.skeqi.finance.domain.help.TBdHelpData;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.dimension.TBdDimensionSourceMapper;
import com.skeqi.finance.pojo.bo.TBdDimensionSource.*;
import com.skeqi.finance.pojo.bo.help.TBdHelpDataQueryBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpDataVo;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpTypeVo;
import com.skeqi.finance.pojo.vo.dimension.DimensionSourceData;
import com.skeqi.finance.pojo.vo.dimension.TBdDimensionSourceVo;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;
import com.skeqi.finance.service.dimension.ITBdDimensionSourceService;
import com.skeqi.finance.service.dimension.ITBdFlexItemPropertyService;
import com.skeqi.finance.service.help.ITBdHelpDataService;
import com.skeqi.finance.service.help.ITBdHelpTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 维度来源Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdDimensionSourceServiceImpl extends ServicePlusImpl<TBdDimensionSourceMapper, TBdDimensionSource> implements ITBdDimensionSourceService {

	//辅助资料类别
	@Autowired
	private ITBdHelpTypeService itBdHelpTypeService;
	//辅助资料数据
	@Autowired
	private ITBdHelpDataService itBdHelpDataService;
	//维度来源
	@Autowired
	private ITBdFlexItemPropertyService itBdFlexItemPropertyService;

	@Override
	public TBdDimensionSourceVo queryById(Integer fId) {
		TBdDimensionSourceVo vo = getVoById(fId, TBdDimensionSourceVo.class);
		return vo;
	}


	@Override
	public TableDataInfo<TBdDimensionSourceVo> queryPageList(TBdDimensionSourceQueryBo bo) {

		TableDataInfo<TBdDimensionSourceVo> rspData = new TableDataInfo<>();
		if(bo.getFType().equals("2")){
			List<TBdHelpTypeVo> tBdHelpTypeVos = itBdHelpTypeService.queryList(new TBdHelpTypeQueryBo());
			List<TBdDimensionSourceVo> tBdDimensionSourceVos = new ArrayList<>();

			for(TBdHelpTypeVo tBdHelpTypeVo : tBdHelpTypeVos){
				TBdDimensionSourceVo dimensionSourceVo = new TBdDimensionSourceVo();
				dimensionSourceVo.setFId(tBdHelpTypeVo.getFId());
				dimensionSourceVo.setFName(tBdHelpTypeVo.getFNumber());
				dimensionSourceVo.setFName(tBdHelpTypeVo.getFTypeName());
				tBdDimensionSourceVos.add(dimensionSourceVo);
			}
			rspData.setCode(HttpStatus.HTTP_OK);
			rspData.setMsg("查询成功");
			rspData.setRows(tBdDimensionSourceVos);
			rspData.setTotal(tBdHelpTypeVos.size());
			return rspData;
		}else {
			List<TBdDimensionSourceVo> tBdDimensionSourceVoList = baseMapper.listBastType();
			rspData.setRows(tBdDimensionSourceVoList);
			rspData.setCode(HttpStatus.HTTP_OK);
			rspData.setMsg("查询成功");
			return rspData;
		}
	}

	@Override
	public DimensionSourceData queryData(int id) {
		//查询维度来源类型
		TBdFlexItemPropertyVo tBdFlexItemPropertyVo = itBdFlexItemPropertyService.queryById(id);
		if(tBdFlexItemPropertyVo == null) throw new CustomException("维度来源不存在");
		DimensionSourceData dimensionSourceData = new DimensionSourceData();
		dimensionSourceData.setFId(id);
		if(tBdFlexItemPropertyVo.getFType().equals("2")){
			TBdHelpDataQueryBo tBdHelpDataQueryBo = new TBdHelpDataQueryBo();
			tBdHelpDataQueryBo.setFTypeId(tBdFlexItemPropertyVo.getFDimensionSourceId().longValue());
			List<TBdHelpDataVo> bdHelpDataVos = itBdHelpDataService.queryList(tBdHelpDataQueryBo);
			List<Map> date = new ArrayList<>();
			for(TBdHelpDataVo tBdHelpDataVo : bdHelpDataVos){
				Map m = new HashMap();
				m.put("id",tBdHelpDataVo.getFId());
				m.put("number",tBdHelpDataVo.getFNumber());
				m.put("name",tBdHelpDataVo.getFName());
				date.add(m);
			}
			dimensionSourceData.setListName(tBdFlexItemPropertyVo.getFName());
			dimensionSourceData.setData(date);
		}else{
//			TBdBaseType oneBastType = baseMapper.getOneBastType(id);
//			List<Map> tBdBaseData = baseMapper.listBaseData(oneBastType.getFListSql());
			List<TBdHelpData> tBdHelpDataList = baseMapper.listBaseData2(id);
			List<Map> date = new ArrayList<>();
			for(TBdHelpData tBdBaseData : tBdHelpDataList){
				Map m = new HashMap();
				m.put("id",tBdBaseData.getFId());
				m.put("number",tBdBaseData.getFNumber());
				m.put("name",tBdBaseData.getFName());
				date.add(m);
			}
			dimensionSourceData.setListName(tBdFlexItemPropertyVo.getFName());
			dimensionSourceData.setData(date);
		}
		return dimensionSourceData;
	}


	@Override
	public List<TBdDimensionSourceVo> queryList(TBdDimensionSourceQueryBo bo) {
		return listVo(buildQueryWrapper(bo), TBdDimensionSourceVo.class);
	}

	private LambdaQueryWrapper<TBdDimensionSource> buildQueryWrapper(TBdDimensionSourceQueryBo bo) {
		Map<String, Object> params = bo.getParams();
		LambdaQueryWrapper<TBdDimensionSource> lqw = Wrappers.lambdaQuery();
		//不查询逻辑删除数据
		lqw.ne(TBdDimensionSource::getFDocumentStatus, DataStatusEnum.DELETE);
		//多条件查询
		lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdDimensionSource::getFNumber, bo.getFNumber());
		lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdDimensionSource::getFName, bo.getFName());
		lqw.eq(StrUtil.isNotBlank(bo.getFType()), TBdDimensionSource::getFType, bo.getFType());
		lqw.eq(StrUtil.isNotBlank(bo.getFStrategyType()), TBdDimensionSource::getFStrategyType, bo.getFStrategyType());
		lqw.eq(bo.getFCreateOrgid() != null, TBdDimensionSource::getFCreateOrgid, bo.getFCreateOrgid());
		lqw.eq(bo.getFCreatorid() != null, TBdDimensionSource::getFCreatorid, bo.getFCreatorid());
		lqw.eq(bo.getFCreateDate() != null, TBdDimensionSource::getFCreateDate, bo.getFCreateDate());
		lqw.eq(bo.getFUseOrgid() != null, TBdDimensionSource::getFUseOrgid, bo.getFUseOrgid());
		lqw.eq(bo.getFModifierid() != null, TBdDimensionSource::getFModifierid, bo.getFModifierid());
		lqw.eq(bo.getFModifyDate() != null, TBdDimensionSource::getFModifyDate, bo.getFModifyDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdDimensionSource::getFDocumentStatus, bo.getFDocumentStatus());
		lqw.eq(bo.getFAuditorid() != null, TBdDimensionSource::getFAuditorid, bo.getFAuditorid());
		lqw.eq(bo.getFAuditDate() != null, TBdDimensionSource::getFAuditDate, bo.getFAuditDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdDimensionSource::getFForbidStatus, bo.getFForbidStatus());
		lqw.eq(bo.getFForbidderid() != null, TBdDimensionSource::getFForbidderid, bo.getFForbidderid());
		lqw.eq(bo.getFForbidDate() != null, TBdDimensionSource::getFForbidDate, bo.getFForbidDate());
		lqw.eq(bo.getFIssysPreset() != null, TBdDimensionSource::getFIssysPreset, bo.getFIssysPreset());
		lqw.eq(bo.getFMasterId() != null, TBdDimensionSource::getFMasterId, bo.getFMasterId());
		return lqw;
	}

	@Override
	public Boolean insertByAddBo(TBdDimensionSourceAddBo bo) {
		TBdDimensionSource add = BeanUtil.toBean(bo, TBdDimensionSource.class);
		add.setAddDefault();
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
	public Boolean updateByEditBo(TBdDimensionSourceEditBo bo) {
		TBdDimensionSource update = BeanUtil.toBean(bo, TBdDimensionSource.class);
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
	private void validEntityBeforeSave(TBdDimensionSource entity) {
		//TODO 做一些数据校验,如唯一约束
//		//类型 1基础资料 2辅助资料
//		String type = entity.getFType();
//		DataTypeEnum typeEnum = DataTypeEnum.getObj(type);
//		if (null == typeEnum) {
//			throw new CustomException(String.format("系统预设只能是1是 0否,收到的是%s", type), 1000);
//		}
	}

	/**
	 * 修改前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeUpdate(TBdDimensionSource entity) {
		entity.setUpdateDefault();
		//TODO 做一些数据校验,如唯一约束
	}


	@Override
	public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		if (isValid) {
			//TODO 做一些业务上的校验,判断是否需要校验

		}
		return removeByIds(ids);
	}



}
