package com.skeqi.finance.service.impl.help;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdBusinessAreaVo;
import com.skeqi.finance.service.basicinformation.base.ITBdBusinessAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeAddBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeQueryBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeEditBo;
import com.skeqi.finance.domain.help.TBdHelpType;
import com.skeqi.finance.mapper.TBdHelpTypeMapper;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpTypeVo;
import com.skeqi.finance.service.help.ITBdHelpTypeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 辅助资料类别Service业务层处理
 *
 * @author toms
 * @date 2021-07-13
 */
@Service
public class TBdHelpTypeServiceImpl extends ServicePlusImpl<TBdHelpTypeMapper, TBdHelpType> implements ITBdHelpTypeService {

	@Autowired
	ITBdBusinessAreaService iTBdBusinessAreaService;
	@Autowired
	TBdHelpTypeMapper tBdHelpTypeMapper;

	private TBdHelpTypeQueryBo buildPage(TBdHelpTypeQueryBo bo){
		bo.setPageSize(bo.getPageNum()>1?(bo.getPageNum()*bo.getPageSize()):bo.getPageSize());
		bo.setPageNum(bo.getPageNum()>1?((bo.getPageNum()-1)*bo.getPageSize()):0);
		return bo;
	}
    @Override
    public TBdHelpTypeVo queryById(Integer fId){
		TBdHelpTypeVo vo=getVoById(fId, TBdHelpTypeVo.class);
		if(null==vo){
			return null;
		}
		String p=tBdHelpTypeMapper.findByParentId(Integer.parseInt(vo.getFParentId()));
		vo.setFParentName(p);
        return vo;
    }

    @Override
    public TableDataInfo<TBdHelpTypeVo> queryPageList(TBdHelpTypeQueryBo bo) {
        PagePlus<TBdHelpType, TBdHelpTypeVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdHelpTypeVo.class);
        return PageUtils.buildDataInfo(result);
    }

	@Override
	public TableDataInfo<TBdHelpTypeVo> queryPageGroup(TBdHelpTypeQueryBo bo) {
		if(bo.getFBusinessArea()!=null){
			Integer parentId=tBdHelpTypeMapper.findByAreaId(Integer.parseInt(bo.getFBusinessArea()));
			bo.setFParentId(parentId);
		}
		List<TBdHelpTypeVo> list=tBdHelpTypeMapper.queryPageGroup(buildPage(bo));
		Integer total=0;
		if(!CollectionUtil.isEmpty(list)){
			total=tBdHelpTypeMapper.queryPageGroupCount(buildPage(bo));
		}
		return PageUtils.buildDataInfo(list,total);
	}

	@Override
	public List<TBdHelpTypeVo> getNextGrade(TBdHelpTypeQueryBo bo){
    	if(null==bo.getFParentId()){
    		return null;
		}
		return listVo(buildQueryWrapper(bo), TBdHelpTypeVo.class);
	}
    @Override
    public List<TBdHelpTypeVo> queryList(TBdHelpTypeQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdHelpTypeVo.class);
    }

    private LambdaQueryWrapper<TBdHelpType> buildQueryWrapper(TBdHelpTypeQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdHelpType> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFBusinessArea() !=null, TBdHelpType::getFBusinessArea, bo.getFBusinessArea());
        lqw.like(StrUtil.isNotBlank(bo.getFTypeName()), TBdHelpType::getFTypeName, bo.getFTypeName());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TBdHelpType::getFDescription, bo.getFDescription());
        lqw.eq(bo.getFParentId()!=null, TBdHelpType::getFParentId, bo.getFParentId());
        lqw.eq(bo.getFCreateOrgid() != null, TBdHelpType::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFUseOrgid() != null, TBdHelpType::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdHelpType::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdHelpType::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFModifierid() != null, TBdHelpType::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdHelpType::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdHelpType::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdHelpType::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdHelpType::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdHelpType::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdHelpType::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdHelpType::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdHelpType::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdHelpType::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertByAddBo(TBdHelpTypeAddBo bo) {
		TBdBusinessAreaVo areaVo=iTBdBusinessAreaService.queryById(bo.getFBusinessArea());
		if(areaVo==null){
			throw new CustomException("领域不存在",1000);
		}
        TBdHelpType add = BeanUtil.toBean(bo, TBdHelpType.class);
		add.setFBusinessAreaName(areaVo.getName());
        validEntityBeforeSave(add);
        add.setFCreateDate(new Date());
        this.save(add);
        return true;
    }

    @Override
    public Boolean updateByEditBo(TBdHelpTypeEditBo bo) {
		TBdHelpType old =getById(bo.getFId());
		if(old==null){
			throw new CustomException("辅助资料类别不存在",1000);
		}
		TBdBusinessAreaVo areaVo=iTBdBusinessAreaService.queryById(bo.getFBusinessArea());
		if(areaVo==null){
			throw new CustomException("领域不存在",1000);
		}
		 BeanUtil.copyProperties(bo,old,"fid");
        validEntityBeforeSave(old);
		old.setFBusinessAreaName(areaVo.getName());
		old.setFBusinessArea(bo.getFBusinessArea());
        return updateById(old);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdHelpType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}
