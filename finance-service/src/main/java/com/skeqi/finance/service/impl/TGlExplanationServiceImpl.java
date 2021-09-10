package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.finance.domain.TGlExplanation;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.TGlExplanationMapper;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationEditBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationQueryBo;
import com.skeqi.finance.pojo.vo.TGlExplanationTypeVo;
import com.skeqi.finance.pojo.vo.TGlExplanationVo;
import com.skeqi.finance.service.ITGlExplanationService;
import com.skeqi.finance.service.ITGlExplanationTypeService;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 摘要库Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TGlExplanationServiceImpl extends ServicePlusImpl<TGlExplanationMapper, TGlExplanation> implements ITGlExplanationService {

	@Resource
	private TokenService tokenService;

	@Resource
	TGlExplanationMapper tGlExplanationMapper;

	@Autowired
	ITGlExplanationTypeService iTGlExplanationTypeService;

    @Override
    public TGlExplanationVo queryById(Integer fId){
        return getVoById(fId, TGlExplanationVo.class);
    }

    @Override
    public TableDataInfo<TGlExplanationVo> queryPageList(TGlExplanationQueryBo bo) {
    	Page<TGlExplanationQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TGlExplanationVo> iPage = tGlExplanationMapper.queryPageList(userPage, bo);
        return PageUtils.buildDataInfo(iPage);
    }

    @Override
    public List<TGlExplanationVo> queryList(TGlExplanationQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlExplanationVo.class);
    }

    private LambdaQueryWrapper<TGlExplanation> buildQueryWrapper(TGlExplanationQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlExplanation> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TGlExplanation::getFNumber, bo.getFNumber());
        lqw.eq(bo.getFExplanationGroupid() != null, TGlExplanation::getFExplanationGroupid, bo.getFExplanationGroupid());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsmultiCollect()), TGlExplanation::getFIsmultiCollect, bo.getFIsmultiCollect());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TGlExplanation::getFName, bo.getFName());
        lqw.eq(bo.getFCreateOrgid() != null, TGlExplanation::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TGlExplanation::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TGlExplanation::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TGlExplanation::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TGlExplanation::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TGlExplanation::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TGlExplanation::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TGlExplanation::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TGlExplanation::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TGlExplanation::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TGlExplanation::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TGlExplanation::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TGlExplanation::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TGlExplanation::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlExplanationAddBo bo) {
        TGlExplanation add = BeanUtil.toBean(bo, TGlExplanation.class);
		TGlExplanationTypeVo vo=iTGlExplanationTypeService.queryById(bo.getFExplanationGroupid());
		if(null==vo){
			throw new CustomException("摘要类型不存");
		}
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		add.setFCreatorid(user.getUserId().intValue());
		add.setFCreateDate(new Date());
		add.setFDocumentStatus(DataStatusEnum.CREATE.getCode());
		add.setFForbidStatus(BaseEnum.NO.getCode());
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlExplanationEditBo bo) {
		TGlExplanation old=this.getById(bo.getFId());
    	if(null==old){
			throw new CustomException("摘要信息不存在");
		}
		TGlExplanationTypeVo vo=iTGlExplanationTypeService.queryById(bo.getFExplanationGroupid());
		if(null==vo){
			throw new CustomException("摘要类型不存");
		}
        BeanUtil.copyProperties(bo, old,"fId");
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		old.setFModifierid(user.getUserId().intValue());
		old.setFModifyDate(new Date());
        validEntityBeforeSave(old);
        return updateById(old);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlExplanation entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
		  ids.forEach(v->{
			  TGlExplanation old=this.getById(v);
			  if(old!=null && !DataStatusEnum.AUDIT.getCode().equals(old.getFDocumentStatus())){
				  removeById(v);
			  }
		  });
		}
        return true;
    }

	@Override
	public boolean auditWithValidByIds(List<Integer> asList) {
		for (Integer id : asList) {
			TGlExplanation update = getById(id);
			if(!DataStatusEnum.AUDIT.getCode().equals(update.getFDocumentStatus())) {
				SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
				update.setFAuditorid(user.getUserId().intValue());
				update.setFAuditDate(new Date());
				update.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
				updateById(update);
			}
		}
		return true;
	}

	@Override
	public boolean deAuditWithValidByIds(List<Integer> asList) {
		for (Integer id : asList) {
			TGlExplanation update = getById(id);
			if(!"1".equals(update.getFDocumentStatus())) {
				throw new CustomException("不是已审核状态不能反审核。", 1000);
			}
			update.setFDocumentStatus("0");
			updateById(update);
		}
		return true;
	}

	@Override
	public boolean disableWithValidByIds(List<Integer> asList) {
		for (Integer id : asList) {
			TGlExplanation update = getById(id);
			if("1".equals(update.getFForbidStatus())) {
				throw new CustomException("已禁用不能禁用。", 1000);
			}
			SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
			update.setFAuditorid(user.getUserId().intValue());
			update.setFAuditDate(new Date());
			update.setFForbidStatus("1");
			updateById(update);
		}
		return true;
	}

	@Override
	public boolean deDisableWithValidByIds(List<Integer> asList) {
		for (Integer id : asList) {
			TGlExplanation update = getById(id);
			if(!"1".equals(update.getFForbidStatus())) {
				throw new CustomException("已禁用才能反禁用。", 1000);
			}
			update.setFForbidStatus("0");
			updateById(update);
		}
		return true;
	}
}
