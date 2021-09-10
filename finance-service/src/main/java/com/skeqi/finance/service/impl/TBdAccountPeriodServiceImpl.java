package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.common.utils.time.DateTimeCalculatorUtil;
import com.skeqi.finance.pojo.vo.PeriodVo;
import com.skeqi.finance.pojo.vo.period.AccountPeriodVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodEditBo;
import com.skeqi.finance.domain.TBdAccountPeriod;
import com.skeqi.finance.mapper.TBdAccountPeriodMapper;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountPeriodVo;
import com.skeqi.finance.service.account.ITBdAccountPeriodService;

import java.util.*;

/**
 * 会计期间Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdAccountPeriodServiceImpl extends ServicePlusImpl<TBdAccountPeriodMapper, TBdAccountPeriod> implements ITBdAccountPeriodService {

	@Autowired
	TBdAccountPeriodMapper tBdAccountPeriodMapper;
    @Override
    public TBdAccountPeriodVo queryById(Integer fId){
        return getVoById(fId, TBdAccountPeriodVo.class);
    }

    @Override
    public TableDataInfo<TBdAccountPeriodVo> queryPageList(TBdAccountPeriodQueryBo bo) {
        PagePlus<TBdAccountPeriod, TBdAccountPeriodVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdAccountPeriodVo.class);
        return PageUtils.buildDataInfo(result);
    }

	/**
	 * 查询期间
	 * @param bo
	 * @return
	 */
	@Override
	public List<AccountPeriodVo> listPeriod(TBdAccountPeriodQueryBo bo){
        return tBdAccountPeriodMapper.listPeriod(bo);
	}

    @Override
    public List<TBdAccountPeriodVo> queryList(TBdAccountPeriodQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdAccountPeriodVo.class);
    }

    private LambdaQueryWrapper<TBdAccountPeriod> buildQueryWrapper(TBdAccountPeriodQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdAccountPeriod> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFEntryId() != null, TBdAccountPeriod::getFEntryId, bo.getFEntryId());
        lqw.eq(bo.getFYear() != null, TBdAccountPeriod::getFYear, bo.getFYear());
        lqw.eq(bo.getFPeriod() != null, TBdAccountPeriod::getFPeriod, bo.getFPeriod());
        lqw.eq(bo.getFPeriodStartdate() != null, TBdAccountPeriod::getFPeriodStartdate, bo.getFPeriodStartdate());
        lqw.eq(bo.getFPeriodEnddate() != null, TBdAccountPeriod::getFPeriodEnddate, bo.getFPeriodEnddate());
        lqw.eq(bo.getFQuarter() != null, TBdAccountPeriod::getFQuarter, bo.getFQuarter());
        lqw.eq(bo.getFMonth() != null, TBdAccountPeriod::getFMonth, bo.getFMonth());
        lqw.eq(bo.getFWeek() != null, TBdAccountPeriod::getFWeek, bo.getFWeek());
        lqw.eq(bo.getFEntryseq() != null, TBdAccountPeriod::getFEntryseq, bo.getFEntryseq());
        return lqw;
    }

    @Override
    public AjaxResult insertByAddBo(TBdAccountPeriodAddBo bo) {
    	if(null==bo.getCount()){
			bo.setCount(1);
		}
        TBdAccountPeriod add = BeanUtil.toBean(bo, TBdAccountPeriod.class);
    	add.setFPeriodStartdate(bo.getFStartdate());
    	add.setFPeriodEnddate(bo.getFEnddate());
        validEntityBeforeSave(add);
		List<PeriodVo> sumList=new ArrayList<>();
		for (int i=0;i<bo.getCount();i++){
			List<PeriodVo> list=bo.getPeriodType()==1?calculatePeriodByYear(add):calculatePeriodByWeek(add);
			sumList.addAll(list);
		}
		if(null==bo.getFEntryId()){
			return AjaxResult.success(sumList);
		}
		if(CollectionUtil.isNotEmpty(sumList)){
			sumList.forEach(v->{
				TBdAccountPeriod info =new TBdAccountPeriod();
				BeanUtil.copyProperties(v,info);
				info.setFEntryId(bo.getFEntryId());
				save(info);
			});
		}
        return AjaxResult.success();
    }

    @Override
    public Boolean updateByEditBo(TBdAccountPeriodEditBo bo) {
        TBdAccountPeriod update = BeanUtil.toBean(bo, TBdAccountPeriod.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdAccountPeriod entity){
        if((null==entity.getFPeriodStartdate()) || (null==entity.getFPeriodEnddate())){
        	throw new CustomException("开始和结束日期不能为空",1000);
		}
		if(DateTimeCalculatorUtil.compare(entity.getFPeriodStartdate(),entity.getFPeriodEnddate())>0){
			throw new CustomException("结束要大于开始日期",1000);
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
	@Override
	public Boolean deleteWithValidByFEntryId(Integer id){
		tBdAccountPeriodMapper.delByEntryId(id);
		return true;
	}

	/**
	 * 计算一年会计年度期间
	 */
	private List<PeriodVo> calculatePeriodByYear(TBdAccountPeriod entity){
		List<PeriodVo> list=new ArrayList<>();
		Integer year= DateTimeCalculatorUtil.getYear(entity.getFPeriodStartdate());
		Integer month= DateTimeCalculatorUtil.getMonth(entity.getFPeriodStartdate());
		for (int i=1;i<=12;i++){
			Date sDate=DateTimeCalculatorUtil.getDateStartOfMonth(year,month);
			Date eDate=DateTimeCalculatorUtil.getDateEndOfMonth(year,month);
			PeriodVo vo=new PeriodVo();
			if(i==1){
				vo.setFPeriodStartdate(entity.getFPeriodStartdate());
			}else {
				vo.setFPeriodStartdate(sDate);
			}
			vo.setFPeriodEnddate(eDate);
			vo.setFPeriod(i);
			//季度
			vo.setFQuarter(getQuarter(i));
			vo.setFYear(entity.getFYear());
			vo.setFMonth(month);
			list.add(vo);
			if(month==12){
				year++;
				month=0;
			}
			month++;

		}
		return list;
	}

	/**
	 * 计算一年周期间
	 */
	private List<PeriodVo> calculatePeriodByWeek(TBdAccountPeriod entity){
		List<PeriodVo> list=new ArrayList<>();
		Integer year= DateTimeCalculatorUtil.getYear(entity.getFPeriodStartdate());
		Integer month= DateTimeCalculatorUtil.getMonth(entity.getFPeriodStartdate());
		for (int i=1;i<=12;i++){
			Date sDate=DateTimeCalculatorUtil.getDateStartOfMonth(year,month);
			Date eDate=DateTimeCalculatorUtil.getDateEndOfMonth(year,month);
			PeriodVo vo=new PeriodVo();
			if(i==1){
				vo.setFPeriodStartdate(entity.getFPeriodStartdate());
			}else {
				vo.setFPeriodStartdate(sDate);
			}
			vo.setFPeriodEnddate(eDate);
			vo.setFPeriod(i);
			//季度
			vo.setFQuarter(getQuarter(i));
			vo.setFYear(entity.getFYear());
			list.add(vo);
			if(month==12){
				year++;
				month=0;
			}
			month++;

		}
		return list;
	}

	private Integer getQuarter(Integer step){
		Integer qt=step/3;
		Integer tmp=step%3;
		if(tmp>0){
			qt++;
		}
		return qt;
	}
}
