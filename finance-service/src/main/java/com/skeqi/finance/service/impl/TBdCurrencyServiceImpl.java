package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.finance.domain.help.TBdHelpData;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.enums.ShowOrderEnum;
import com.skeqi.finance.mapper.TBdHelpDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.currency.TBdCurrencyAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.currency.TBdCurrencyQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.currency.TBdCurrencyEditBo;
import com.skeqi.finance.domain.TBdCurrency;
import com.skeqi.finance.mapper.TBdCurrencyMapper;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdCurrencyVo;
import com.skeqi.finance.service.basicinformation.base.ITBdCurrencyService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 基础-币别Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdCurrencyServiceImpl extends ServicePlusImpl<TBdCurrencyMapper, TBdCurrency> implements ITBdCurrencyService {

	@Autowired
	TBdHelpDataMapper tBdHelpDataMapper;

    @Override
    public TBdCurrencyVo queryById(Integer fCurrencyId){
        return getVoById(fCurrencyId, TBdCurrencyVo.class);
    }

    @Override
    public TableDataInfo<TBdCurrencyVo> queryPageList(TBdCurrencyQueryBo bo) {
        PagePlus<TBdCurrency, TBdCurrencyVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdCurrencyVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdCurrencyVo> queryList(TBdCurrencyQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdCurrencyVo.class);
    }

    private LambdaQueryWrapper<TBdCurrency> buildQueryWrapper(TBdCurrencyQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdCurrency> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdCurrency::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdCurrency::getFNumber, bo.getFNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFCode()), TBdCurrency::getFCode, bo.getFCode());
        lqw.eq(StrUtil.isNotBlank(bo.getFSysmbol()), TBdCurrency::getFSysmbol, bo.getFSysmbol());
        lqw.eq(bo.getFPricedigits() != null, TBdCurrency::getFPricedigits, bo.getFPricedigits());
        lqw.eq(bo.getFAmountdigits() != null, TBdCurrency::getFAmountdigits, bo.getFAmountdigits());
        lqw.eq(bo.getFPriority() != null, TBdCurrency::getFPriority, bo.getFPriority());
        lqw.eq(StrUtil.isNotBlank(bo.getFIstrans()), TBdCurrency::getFIstrans, bo.getFIstrans());
        lqw.eq(bo.getFMasterId() != null, TBdCurrency::getFMasterId, bo.getFMasterId());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsshowCsymbol()), TBdCurrency::getFIsshowCsymbol, bo.getFIsshowCsymbol());
        lqw.eq(StrUtil.isNotBlank(bo.getFCurrencySymbolid()), TBdCurrency::getFCurrencySymbolid, bo.getFCurrencySymbolid());
        lqw.eq(StrUtil.isNotBlank(bo.getFFormatOrder()), TBdCurrency::getFFormatOrder, bo.getFFormatOrder());
        lqw.eq(bo.getFCreateOrgid() != null, TBdCurrency::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdCurrency::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdCurrency::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdCurrency::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdCurrency::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdCurrency::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdCurrency::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdCurrency::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdCurrency::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdCurrency::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdCurrency::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdCurrency::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdCurrency::getFIssysPreset, bo.getFIssysPreset());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdCurrencyAddBo bo) {
    	//校验货币符号
		TBdCurrency add = BeanUtil.toBean(bo, TBdCurrency.class);
		validEntityBeforeSave(add);
		add.setFCreateDate(new Date());
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdCurrencyEditBo bo) {
		TBdCurrency old=getById(bo.getFCurrencyId());
		if(null==old){
		  throw new CustomException("币别不存在",1000);
		}
        BeanUtil.copyProperties(bo, old,"fCurrencyId");
        validEntityBeforeSave(old);
        return updateById(old);
    }
	/**
	 * 审核
	 * @param ids
	 * @return
	 */
	@Override
	public Boolean audit(Collection<Integer> ids){
		ids.forEach(v->{
			TBdCurrency old=getById(v);
			if(old!=null && DataStatusEnum.CREATE.getCode().equals(old.getFDocumentStatus())){
				old.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
				this.updateById(old);
			}
		});
		return true;
	}

    /**
     * 保存前的数据校验
     *
     * @param add 实体类数据
     */
    private void validEntityBeforeSave(TBdCurrency add){
		if(add.getFIsshowCsymbol()!=null && BaseEnum.YES.getCode().equals(add.getFIsshowCsymbol())){
			if(StringUtils.isBlank(add.getFCurrencySymbolid())){
				throw new CustomException("货币符号不能为空",1000);
			}
			QueryWrapper<TBdHelpData> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(TBdHelpData::getFId, add.getFCurrencySymbolid());
			TBdHelpData tBdHelpData=tBdHelpDataMapper.selectOne(queryWrapper);
			if(null==tBdHelpData){
				throw new CustomException("货币符号不存在",1000);
			}
			add.setFSysmbol(tBdHelpData.getFName());
			add.setFCurrencySymbolid(tBdHelpData.getFId().toString());
			ShowOrderEnum showOrderEnum=ShowOrderEnum.getObj(add.getFFormatOrder());
			if(null==showOrderEnum){
				throw new CustomException("显示格式不存在",1000);
			}
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
          if(CollectionUtil.isNotEmpty(ids)){
          	ids.forEach(v->{
				TBdCurrency old=this.getById(v);
				if(old!=null && !DataStatusEnum.AUDIT.getCode().equals(old.getFDocumentStatus())){
					removeById(v);
				}
			});
		  }
        }
        return true;
    }
}
