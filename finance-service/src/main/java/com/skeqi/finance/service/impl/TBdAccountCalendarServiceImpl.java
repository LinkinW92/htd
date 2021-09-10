package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.common.utils.time.DateTimeCalculatorUtil;
import com.skeqi.finance.domain.TBdAccountPeriod;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.TBdAccountPeriodMapper;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodAddBo;
import com.skeqi.finance.service.account.ITBdAccountPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.calendar.TBdAccountCalendarAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.calendar.TBdAccountCalendarQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.calendar.TBdAccountCalendarEditBo;
import com.skeqi.finance.domain.TBdAccountCalendar;
import com.skeqi.finance.mapper.TBdAccountCalendarMapper;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountCalendarVo;
import com.skeqi.finance.service.account.ITBdAccountCalendarService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 会计日历Service业务层处理
 *
 * @author toms
 * @date 2021-07-14
 */
@Service
public class TBdAccountCalendarServiceImpl extends ServicePlusImpl<TBdAccountCalendarMapper, TBdAccountCalendar> implements ITBdAccountCalendarService {

	@Autowired
	TBdAccountPeriodMapper tBdAccountPeriodMapper;
	@Autowired
	ITBdAccountPeriodService iTBdAccountPeriodService;
    @Override
    public TBdAccountCalendarVo queryById(Integer fId){
		TBdAccountCalendarVo vo=getVoById(fId, TBdAccountCalendarVo.class);
		if(null==vo){
			return null;
		}
		QueryWrapper<TBdAccountPeriod> qw=new QueryWrapper();
		qw.eq("f_entry_id",fId);
		List<TBdAccountPeriod> list=tBdAccountPeriodMapper.selectList(qw);
		vo.setPeriodList(list);
        return vo;
    }

    @Override
    public TableDataInfo<TBdAccountCalendarVo> queryPageList(TBdAccountCalendarQueryBo bo) {
        PagePlus<TBdAccountCalendar, TBdAccountCalendarVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdAccountCalendarVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdAccountCalendarVo> queryList(TBdAccountCalendarQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdAccountCalendarVo.class);
    }

    private LambdaQueryWrapper<TBdAccountCalendar> buildQueryWrapper(TBdAccountCalendarQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdAccountCalendar> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdAccountCalendar::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdAccountCalendar::getFName, bo.getFName());
        lqw.eq(bo.getFStartDate() != null, TBdAccountCalendar::getFStartDate, bo.getFStartDate());
        lqw.eq(bo.getFEndDate() != null, TBdAccountCalendar::getFEndDate, bo.getFEndDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFPeriodType()), TBdAccountCalendar::getFPeriodType, bo.getFPeriodType());
        lqw.eq(StrUtil.isNotBlank(bo.getFStartYear()), TBdAccountCalendar::getFStartYear, bo.getFStartYear());
        lqw.eq(bo.getFCreateOrgid() != null, TBdAccountCalendar::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdAccountCalendar::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdAccountCalendar::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdAccountCalendar::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdAccountCalendar::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdAccountCalendar::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdAccountCalendar::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdAccountCalendar::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdAccountCalendar::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdAccountCalendar::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdAccountCalendar::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdAccountCalendar::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdAccountCalendar::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdAccountCalendar::getFMasterId, bo.getFMasterId());
        lqw.eq(bo.getFPeriodCount() != null, TBdAccountCalendar::getFPeriodCount, bo.getFPeriodCount());
        return lqw;
    }

	@Transactional(rollbackFor=Exception.class)
    @Override
    public AjaxResult insertByAddBo(TBdAccountCalendarAddBo bo) {
        TBdAccountCalendar add = BeanUtil.toBean(bo, TBdAccountCalendar.class);
        validEntityBeforeSave(add);
        add.setFEndDate(getEndDate(bo.getFStartDate()));
        add.setFCreateDate(new Date());
		baseMapper.insert(add);
		TBdAccountPeriodAddBo periodAddBo=new TBdAccountPeriodAddBo();
		periodAddBo.setCount(1);
		periodAddBo.setFStartdate(bo.getFStartDate());
		periodAddBo.setFEnddate(getEndDate(bo.getFStartDate()));
		periodAddBo.setFEntryId(add.getFId());
		periodAddBo.setFYear(Integer.parseInt(bo.getFStartYear()));
		periodAddBo.setPeriodType(1);
		iTBdAccountPeriodService.insertByAddBo(periodAddBo);
        return AjaxResult.success();
    }

    @Override
    public Boolean updateByEditBo(TBdAccountCalendarEditBo bo) {
        TBdAccountCalendar update = BeanUtil.toBean(bo, TBdAccountCalendar.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

	/**
	 * 审核
	 * @param ids
	 * @return
	 */
	@Override
	public Boolean audit(Collection<Integer> ids){
		if(CollectionUtil.isNotEmpty(ids)){
			ids.forEach(v->{
				TBdAccountCalendar calendar=this.getById(v);
				if(calendar!=null){
					calendar.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
					this.updateById(calendar);
				}
			});
		}
		return true;
	}

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdAccountCalendar entity){

    }

    @Transactional
    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		ids.forEach(v->{
			TBdAccountCalendar vo=getById(v);
			if((vo!=null) && (!DataStatusEnum.AUDIT.getCode().equals(vo.getFDocumentStatus()))){
				removeById(v);
				iTBdAccountPeriodService.deleteWithValidByFEntryId(vo.getFId());
			}
		});
        return true;
    }

	/**
	 *
	 * @return
	 */
	private Date getEndDate(Date startDate){
		Integer month= DateTimeCalculatorUtil.getMonth(startDate);
		Integer year= DateTimeCalculatorUtil.getYear(startDate);
		if(month!=1){
			year++;
			month--;
		}
		return DateTimeCalculatorUtil.getDateEndOfMonth(year,12);
	}
}
