package com.skeqi.finance.service.impl.rate;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.finance.domain.TBdCurrency;
import com.skeqi.finance.domain.rate.TBdRateType;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.TBdCurrencyMapper;
import com.skeqi.finance.mapper.rate.TBdRateTypeMapper;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.rate.TBdRateAddBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateQueryBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateEditBo;
import com.skeqi.finance.domain.rate.TBdRate;
import com.skeqi.finance.mapper.rate.TBdRateMapper;
import com.skeqi.finance.pojo.vo.basicinformation.rate.TBdRateVo;
import com.skeqi.finance.service.rate.ITBdRateService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 基础汇率Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdRateServiceImpl extends ServicePlusImpl<TBdRateMapper, TBdRate> implements ITBdRateService {

	@Autowired
	TBdCurrencyMapper tBdCurrencyMapper;
	@Autowired
	TBdRateTypeMapper tBdRateTypeMapper;
	@Autowired
	TBdRateMapper tBdRateMapper;
	@Autowired
	TokenService tokenService;
    @Override
    public TBdRateVo queryById(Integer fRateId){
        return getVoById(fRateId, TBdRateVo.class);
    }

	/**
	 * 查询汇率
	 * @return
	 */
	@Override
	public TBdRate getRate(Date date,Integer rateTypeId,Integer fCyforid,Integer fCytoid){
		TBdRate tBdRate=tBdRateMapper.findRateByDateAndType(date,rateTypeId,fCyforid,fCytoid);
		if(null==tBdRate){
			tBdRate=tBdRateMapper.findRateByDateAndType(null,rateTypeId,fCyforid,fCytoid);
		}
		return tBdRate;
	}

    @Override
    public TableDataInfo<TBdRateVo> queryPageList(TBdRateQueryBo bo) {
		Page<TBdRateQueryBo> page=new Page<>(bo.getPageNum(),bo.getPageSize());
		IPage<TBdRateVo> iPage=tBdRateMapper.getRatePage(page,bo);
        return PageUtils.buildDataInfo(iPage);
    }
	@Override
	public List<TBdRateVo> getRateList(TBdRateQueryBo bo) {
		List<TBdRateVo> list=tBdRateMapper.getRateList(bo);
		if(CollectionUtil.isNotEmpty(list)){
			list.forEach(v->{
				TBdRate rate=this.getRate(bo.getFDate(),bo.getFRateTypeId(),v.getFCyforid(),bo.getFCytoid());
				if(rate!=null){
					v.setFReverseexRate(rate.getFReverseexRate());
					v.setFExchangeRate(rate.getFExchangeRate());
				}
			});
		}
		return list;
	}

    @Override
    public List<TBdRateVo> queryList(TBdRateQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdRateVo.class);
    }

    private LambdaQueryWrapper<TBdRate> buildQueryWrapper(TBdRateQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdRate> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFRateTypeId() != null, TBdRate::getFRateTypeId, bo.getFRateTypeId());
        lqw.eq(bo.getFCyforid() != null, TBdRate::getFCyforid, bo.getFCyforid());
        lqw.eq(bo.getFCytoid() != null, TBdRate::getFCytoid, bo.getFCytoid());
        lqw.eq(bo.getFExchangeRate() != null, TBdRate::getFExchangeRate, bo.getFExchangeRate());
        lqw.eq(bo.getFReverseexRate() != null, TBdRate::getFReverseexRate, bo.getFReverseexRate());
        lqw.eq(bo.getFBegDate() != null, TBdRate::getFBegDate, bo.getFBegDate());
        lqw.eq(bo.getFEndDate() != null, TBdRate::getFEndDate, bo.getFEndDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdRate::getFNumber, bo.getFNumber());
        lqw.eq(bo.getFMasterId() != null, TBdRate::getFMasterId, bo.getFMasterId());
        lqw.eq(bo.getFCreateOrgid() != null, TBdRate::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdRate::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdRate::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdRate::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdRate::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdRate::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdRate::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdRate::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdRate::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdRate::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdRate::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdRate::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdRate::getFIssysPreset, bo.getFIssysPreset());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdRateAddBo bo) {
		SysUser user=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        TBdRate add = BeanUtil.toBean(bo, TBdRate.class);
        validEntityBeforeSave(add);
        add.setFUseOrgid(user.getUserId().intValue());
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdRateEditBo bo) {
		SysUser user=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TBdRate old=getById(bo.getFRateId());
		if(null==old){
			throw new CustomException("汇率不存在，修改失败",1000);
		}
		BeanUtil.copyProperties(bo,old,"fRateId");
        validEntityBeforeSave(old);
		old.setFModifierid(user.getUserId().intValue());
        return updateById(old);
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
			  TBdRate old=this.getById(v);
			  old.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
			  this.updateById(old);
		  });
	   }
       return true;
	}

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdRate entity){
		TBdCurrency fcy= tBdCurrencyMapper.selectById(entity.getFCyforid());
		if(null==fcy){
			throw new CustomException("原始币不存在",1000);
		}
		TBdCurrency tcy= tBdCurrencyMapper.selectById(entity.getFCyforid());
		if(null==tcy){
			throw new CustomException("目标币不存在",1000);
		}
		TBdRateType rateType =tBdRateTypeMapper.selectById(entity.getFRateTypeId());
		if(null==rateType){
			throw new CustomException("汇率类型不存在",1000);
		}
		if(entity.getFEndDate().compareTo(entity.getFBegDate())<0){
			throw new CustomException("截止日期必须大于开始日期",1000);
		}
		TBdRate tBdRate=tBdRateMapper.findRateByDateAndType(null,entity.getFRateTypeId(),entity.getFCyforid(),entity.getFCytoid());
		if(tBdRate!=null){
			if(tBdRate.getFEndDate().compareTo(entity.getFBegDate())>0){
				throw new CustomException("汇率生效开始时间不得低于上一个生效结束时间",1000);
			}
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		if(CollectionUtil.isNotEmpty(ids)){
			ids.forEach(v->{
				TBdRate old=this.getById(v);
				if(!DataStatusEnum.AUDIT.getCode().equals(old.getFDocumentStatus())){
					removeById(v);
				}
			});
		}
        return true;
    }
}
