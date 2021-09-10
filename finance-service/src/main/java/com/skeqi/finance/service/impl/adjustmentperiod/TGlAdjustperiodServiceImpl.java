package com.skeqi.finance.service.impl.adjustmentperiod;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAccountSystem;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAcctSysEntry;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.basicinformation.voucher.TBdVoucherGroupMapper;
import com.skeqi.finance.pojo.bo.adjustmentperiod.TGlAdjustperiodpAddBo;
import com.skeqi.finance.pojo.bo.adjustmentperiod.TGlAdjustperiodpEditBo;
import com.skeqi.finance.pojo.bo.adjustmentperiod.TGlAdjustperiodpQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemEditBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherQueryBo;
import com.skeqi.finance.pojo.vo.adjustmentperiod.TGlAdjustperiodVo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAccountSystemVo;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVoucherGroupVo;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherVo;
import com.skeqi.framecore.web.service.TokenService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skeqi.finance.mapper.adjustmentperiod.TGlAdjustperiodMapper;
import com.skeqi.finance.domain.adjustmentperiod.TGlAdjustperiod;
import com.skeqi.finance.service.adjustmentperiod.TGlAdjustperiodService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lenovo
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TGlAdjustperiodServiceImpl extends ServicePlusImpl<TGlAdjustperiodMapper, TGlAdjustperiod> implements TGlAdjustperiodService{
	@Resource
	private TokenService tokenService;

	@Resource
	private TGlAdjustperiodMapper mapper;

	@Override
	public TableDataInfo<TGlAdjustperiodVo> queryPageList(TGlAdjustperiodpQueryBo bo) {
		Page<TGlAdjustperiodpQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TGlAdjustperiodVo> tBdVoucherGroupVoIPage = mapper.queryPageList(userPage,buildQueryWrapper(bo));
		return PageUtils.buildDataInfo(tBdVoucherGroupVoIPage);
	}

	private LambdaQueryWrapper<TGlAdjustperiod> buildQueryWrapper(TGlAdjustperiodpQueryBo bo) {
		LambdaQueryWrapper<TGlAdjustperiod> lqw = Wrappers.lambdaQuery();
		lqw.eq(bo.getFAdjustPeriodId() != null, TGlAdjustperiod::getFAdjustPeriodId, bo.getFAdjustPeriodId());
		lqw.eq(bo.getFBookId() != null, TGlAdjustperiod::getFBookId, bo.getFBookId());
		lqw.eq(bo.getFYear() != null, TGlAdjustperiod::getFYear, bo.getFYear());
		lqw.eq(bo.getFAdjustPeriod() != null, TGlAdjustperiod::getFAdjustPeriod, bo.getFAdjustPeriod());
		lqw.eq(bo.getFAdjustStatus() != null, TGlAdjustperiod::getFAdjustStatus, bo.getFAdjustStatus());
		lqw.eq(bo.getFCreatorId() != null, TGlAdjustperiod::getFCreatorId, bo.getFCreatorId());
		lqw.eq(bo.getFCreateDate() != null, TGlAdjustperiod::getFCreateDate, bo.getFCreateDate());
		lqw.eq(bo.getFModifierId() != null, TGlAdjustperiod::getFModifierId, bo.getFModifierId());
		lqw.eq(bo.getFModifierDate() != null, TGlAdjustperiod::getFModifierDate, bo.getFModifierDate());
		lqw.eq(bo.getFName() != null, TGlAdjustperiod::getFName, bo.getFName());
		lqw.eq(bo.getFDescribe() != null, TGlAdjustperiod::getFDescribe, bo.getFDescribe());
		return lqw;
	}


	@Override
	public Boolean insertByAddBo(TGlAdjustperiodpAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		// 新增
		TGlAdjustperiod add = BeanUtil.toBean(bo, TGlAdjustperiod.class);
		add.setFCreatorId(user.getUserId().intValue());
		add.setFCreateDate(new Date());
		validEntityBeforeSave(add);
		return save(add);
	}

	@Override
	public Boolean updateByEditBo(TGlAdjustperiodpEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TGlAdjustperiod update = BeanUtil.toBean(bo, TGlAdjustperiod.class);
		update.setFModifierId(user.getUserId().intValue());
		update.setFModifierDate(new Date());
		validEntityBeforeSave(update);
		return updateById(update);
	}

	/**
	 * 保存前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeSave(TGlAdjustperiod entity){
		//TODO 做一些数据校验,如唯一约束
		if (StringUtils.checkValNull(entity)){
			throw new CustomException("保存数据不能为空!",1000);
		}
	}

	@Override
	public Boolean deleteWithValidByIds(List<Integer> ids) {
		//校验
		if (StringUtils.checkValNull(ids)) {
			throw new CustomException("请至少选择一个删除!",1000);
		}
		return removeByIds(ids);
	}

	@Override
	public Boolean updateStatus(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		ids.forEach(id->{
			TGlAdjustperiod update = getById(id);
			if(update!=null) {
				update.setFModifierId(user.getUserId().intValue());
				update.setFModifierDate(new Date());
				validEntityBeforeSave(update);
				this.updateById(update);
			}
		});
		return true;
	}

	@Override
	public Integer queryByBookIdAndYear(TGlAdjustperiodpQueryBo bo) {
		if (StringUtils.checkValNull(bo.getFBookId())){
			throw new CustomException("账簿不能为空!",1000);
		}
		if (StringUtils.checkValNull(bo.getFYear())){
			throw new CustomException("年度期间不能为空!",1000);
		}
		Integer integer = mapper.queryByBookIdAndYear(bo);
		if (StringUtils.checkValNotNull(integer)){
			integer++;
		}else {
			integer = 13;
		}
		return integer;
	}

	@Override
	public List<TGlAdjustperiodVo> queryList(TGlAdjustperiodpQueryBo bo) {
		return listVo(buildQueryWrapper(bo), TGlAdjustperiodVo.class);
	}
}
