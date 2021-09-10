package com.skeqi.finance.service.impl.unit;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.enums.RoundTypeEnum;
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitGroupVo;
import com.skeqi.finance.service.unit.ITBdUnitGroupService;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.unit.TBdUnitAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitEditBo;
import com.skeqi.finance.domain.unit.TBdUnit;
import com.skeqi.finance.mapper.TBdUnitMapper;
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitVo;
import com.skeqi.finance.service.unit.ITBdUnitService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 计量单位Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdUnitServiceImpl extends ServicePlusImpl<TBdUnitMapper, TBdUnit> implements ITBdUnitService {

	@Autowired
	ITBdUnitGroupService itBdUnitGroupService;
	@Autowired
	TokenService tokenService;
    @Override
    public TBdUnitVo queryById(Integer fUnitId){
        return getVoById(fUnitId, TBdUnitVo.class);
    }

    @Override
    public TableDataInfo<TBdUnitVo> queryPageList(TBdUnitQueryBo bo) {
        PagePlus<TBdUnit, TBdUnitVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdUnitVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdUnitVo> queryList(TBdUnitQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdUnitVo.class);
    }

    private LambdaQueryWrapper<TBdUnit> buildQueryWrapper(TBdUnitQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdUnit> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdUnit::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdUnit::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TBdUnit::getFDescription, bo.getFDescription());
        lqw.eq(bo.getFUnitGroupId() != null, TBdUnit::getFUnitGroupId, bo.getFUnitGroupId());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsbaseUnit()), TBdUnit::getFIsbaseUnit, bo.getFIsbaseUnit());
        lqw.eq(bo.getFPrecision() != null, TBdUnit::getFPrecision, bo.getFPrecision());
        lqw.eq(StrUtil.isNotBlank(bo.getFRoundType()), TBdUnit::getFRoundType, bo.getFRoundType());
        lqw.eq(bo.getFCreateOrgid() != null, TBdUnit::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdUnit::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdUnit::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdUnit::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdUnit::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdUnit::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdUnit::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdUnit::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdUnit::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdUnit::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdUnit::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdUnit::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdUnit::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdUnit::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdUnitAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        TBdUnit add = BeanUtil.toBean(bo, TBdUnit.class);
        validEntityBeforeSave(add);
        add.setFDocumentStatus(DataStatusEnum.CREATE.getCode());
        add.setFCreateDate(new Date());
        add.setFCreatorid(user.getUserId().intValue());
		this.save(add);
        return true;
    }

    @Override
    public Boolean updateByEditBo(TBdUnitEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TBdUnit unit=this.getById(bo.getFUnitId());
		if(null==unit){
			throw new CustomException("单位信息不存在",1000);
		}
		if(DataStatusEnum.AUDIT.getCode().equals(unit)){
			throw new CustomException("单位信息已审核不能修改",1000);
		}
        BeanUtil.copyProperties(bo,unit);
        validEntityBeforeSave(unit);
        unit.setFModifierid(user.getUserId().intValue());
        return updateById(unit);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdUnit entity){
		TBdUnitGroupVo vo=itBdUnitGroupService.queryById(entity.getFUnitGroupId());
        if(null==vo || (!DataStatusEnum.AUDIT.getCode().equals(vo.getFDocumentStatus()))){
        	throw new CustomException("分组不存在或者未审核",1000);
		}
        if(null==RoundTypeEnum.getObj(entity.getFRoundType())){
			throw new CustomException("舍入类型不存在",1000);
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}
