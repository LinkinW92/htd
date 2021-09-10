package com.skeqi.finance.service.impl.basicinformation.cashflow;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicy;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.*;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowTypeVo;
import com.skeqi.finance.service.basicinformation.cashflow.ITGlCashFlowTypeService;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlowItemTable;
import com.skeqi.finance.mapper.basicinformation.cashflow.TGlCashFlowItemTableMapper;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowItemTableVo;
import com.skeqi.finance.service.basicinformation.cashflow.ITGlCashFlowItemTableService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 现金流量项目-1Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TGlCashFlowItemTableServiceImpl extends ServicePlusImpl<TGlCashFlowItemTableMapper, TGlCashFlowItemTable> implements ITGlCashFlowItemTableService {
	@Resource
	private TokenService tokenService;

	@Resource
	private ITGlCashFlowTypeService cashFlowTypeService;

    @Override
    public TGlCashFlowItemTableVo queryById(Integer fId){
        return getVoById(fId, TGlCashFlowItemTableVo.class);
    }

    @Override
    public TableDataInfo<TGlCashFlowItemTableVo> queryPageList(TGlCashFlowItemTableQueryBo bo) {
        PagePlus<TGlCashFlowItemTable, TGlCashFlowItemTableVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlCashFlowItemTableVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlCashFlowItemTableVo> queryList(TGlCashFlowItemTableQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlCashFlowItemTableVo.class);
    }

    private LambdaQueryWrapper<TGlCashFlowItemTable> buildQueryWrapper(TGlCashFlowItemTableQueryBo bo) {
        LambdaQueryWrapper<TGlCashFlowItemTable> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TGlCashFlowItemTable::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TGlCashFlowItemTable::getFName, bo.getFName());
        lqw.eq(bo.getFAcctGroupTblid() != null, TGlCashFlowItemTable::getFAcctGroupTblid, bo.getFAcctGroupTblid());
        lqw.eq(bo.getFCreatorid() != null, TGlCashFlowItemTable::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TGlCashFlowItemTable::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFModifierid() != null, TGlCashFlowItemTable::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TGlCashFlowItemTable::getFModifyDate, bo.getFModifyDate());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlCashFlowItemTableAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		// 新增
		TGlCashFlowItemTable add = BeanUtil.toBean(bo, TGlCashFlowItemTable.class);
		add.setFCreatorid(user.getUserId().intValue());
		add.setFCreateDate(new Date());
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlCashFlowItemTableEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TGlCashFlowItemTable update = BeanUtil.toBean(bo, TGlCashFlowItemTable.class);
		update.setFModifierid(user.getUserId().intValue());
		update.setFModifyDate(new Date());
        return updateById(update);
    }

    @Override
    public Boolean deleteWithValidById(Collection<Integer> ids) {
    	ids.forEach(id->{
			TGlCashFlowTypeQueryBo tGlCashFlowTypeQueryBo = new TGlCashFlowTypeQueryBo();
			tGlCashFlowTypeQueryBo.setFCashFlowItemTable(id);
			List<TGlCashFlowTypeVo> tGlCashFlowTypeVos = cashFlowTypeService.queryList(tGlCashFlowTypeQueryBo);
			if (tGlCashFlowTypeVos.size()>0){
				throw new CustomException(String.format("该现金流量项目表[ %s ]下存在现金流量项目类别，禁止删除！", id),1000);
			}
			 this.removeById(id);
		});
		return true;
    }
}
