package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoEditBo;
import com.skeqi.finance.domain.TGlVoucherGroupNo;
import com.skeqi.finance.mapper.TGlVoucherGroupNoMapper;
import com.skeqi.finance.pojo.vo.TGlVoucherGroupNoVo;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherGroupNoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证号排序Service业务层处理
 *
 * @author toms
 * @date 2021-08-09
 */
@Service
public class TGlVoucherGroupNoServiceImpl extends ServicePlusImpl<TGlVoucherGroupNoMapper, TGlVoucherGroupNo> implements ITGlVoucherGroupNoService {

    @Override
    public TGlVoucherGroupNoVo queryById(Integer fId){
        return getVoById(fId, TGlVoucherGroupNoVo.class);
    }

    @Override
    public TableDataInfo<TGlVoucherGroupNoVo> queryPageList(TGlVoucherGroupNoQueryBo bo) {
        PagePlus<TGlVoucherGroupNo, TGlVoucherGroupNoVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlVoucherGroupNoVo.class);
        return PageUtils.buildDataInfo(result);
    }

	/**
	 * 查询凭证号排序
	 * @param bo
	 * @return
	 */
	@Override
	public List<Integer>  listVchNo(TGlVoucherGroupNoQueryBo bo){
		if(null==bo.getFBookId()){
			throw new CustomException("账簿ID不能为空");
		}
		if(null==bo.getFVoucherGroupId()){
			throw new CustomException("凭证字ID不能为空");
		}
		if(null==bo.getFPeriod()){
			throw new CustomException("期间不能为空");
		}
		if(null==bo.getFYear()){
			throw new CustomException("年不能为空");
		}
		List<Integer> list=baseMapper.queryNotContinueNo(bo.getFYear(),bo.getFPeriod(),bo.getFVoucherGroupId(),bo.getFBookId());
		List<Integer> newList=new ArrayList<>();
		Integer max=0;
		if(CollectionUtil.isNotEmpty(list)){
			max=list.get(0);
			if(list.size()!=list.get(0)){
				for (int i = max-1; i >0 ; i--) {
					if(!list.contains(max-i)){
						newList.add(max-i);
					}
				}
			}
		}
		if(newList.size()<10){
			int count=10-newList.size();
			for (int i = max+1,j=0; j <=count ; j++,i++) {
				newList.add(i);
			}
		}
		return newList;
	}



    @Override
    public List<TGlVoucherGroupNoVo> queryList(TGlVoucherGroupNoQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlVoucherGroupNoVo.class);
    }

    private LambdaQueryWrapper<TGlVoucherGroupNo> buildQueryWrapper(TGlVoucherGroupNoQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlVoucherGroupNo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFYear() != null, TGlVoucherGroupNo::getFYear, bo.getFYear());
        lqw.eq(bo.getFVoucherGroupNo() != null, TGlVoucherGroupNo::getFVoucherGroupNo, bo.getFVoucherGroupNo());
        lqw.eq(bo.getFPeriod() != null, TGlVoucherGroupNo::getFPeriod, bo.getFPeriod());
        lqw.eq(bo.getFVoucherGroupId() != null, TGlVoucherGroupNo::getFVoucherGroupId, bo.getFVoucherGroupId());
        lqw.eq(bo.getFBookId() != null, TGlVoucherGroupNo::getFBookId, bo.getFBookId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlVoucherGroupNoAddBo bo) {
        TGlVoucherGroupNo add = BeanUtil.toBean(bo, TGlVoucherGroupNo.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlVoucherGroupNoEditBo bo) {
        TGlVoucherGroupNo update = BeanUtil.toBean(bo, TGlVoucherGroupNo.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlVoucherGroupNo entity){
        if((null==entity.getFBookId()) || (null==entity.getFPeriod()) || (null==entity.getFVoucherGroupId()) || (null==entity.getFVoucherGroupNo())  || (null==entity.getFYear())){
        	throw new CustomException("参数不完成");
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
