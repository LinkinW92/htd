package com.skeqi.finance.service.impl.help;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpTypeVo;
import com.skeqi.finance.service.help.ITBdHelpTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.help.TBdHelpDataAddBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpDataQueryBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpDataEditBo;
import com.skeqi.finance.domain.help.TBdHelpData;
import com.skeqi.finance.mapper.TBdHelpDataMapper;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpDataVo;
import com.skeqi.finance.service.help.ITBdHelpDataService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 辅助资料Service业务层处理
 *
 * @author toms
 * @date 2021-07-13
 */
@Service
public class TBdHelpDataServiceImpl extends ServicePlusImpl<TBdHelpDataMapper, TBdHelpData> implements ITBdHelpDataService {
	@Autowired
	ITBdHelpTypeService iTBdHelpTypeService;

	@Autowired
	TBdHelpDataMapper tBdHelpDataMapper;

    @Override
    public TBdHelpDataVo queryById(Integer fId){
        return getVoById(fId, TBdHelpDataVo.class);
    }

	private TBdHelpDataQueryBo buildPage(TBdHelpDataQueryBo bo){
		bo.setPageSize(bo.getPageNum()>1?(bo.getPageNum()*bo.getPageSize()):bo.getPageSize());
		bo.setPageNum(bo.getPageNum()>1?((bo.getPageNum()-1)*bo.getPageSize()):0);
		return bo;
	}
    @Override
    public TableDataInfo<TBdHelpDataVo> queryPageList(TBdHelpDataQueryBo bo) {
		TBdHelpTypeQueryBo typeQueryBo=new TBdHelpTypeQueryBo();
		List<TBdHelpTypeVo>  list=iTBdHelpTypeService.queryList(typeQueryBo);
		Integer type=list.get(0).getFId();
		bo.setFNumber(null);
		bo.setFTypeId(type.longValue());
        PagePlus<TBdHelpData, TBdHelpDataVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdHelpDataVo.class);
        return PageUtils.buildDataInfo(result);
    }

	@Override
	public TableDataInfo<TBdHelpDataVo> listByType(TBdHelpDataQueryBo bo){
		List<TBdHelpDataVo> list=tBdHelpDataMapper.queryPageGroup(buildPage(bo));
		Integer total=0;
		if(!CollectionUtil.isEmpty(list)){
			total=tBdHelpDataMapper.queryPageGroupCount(buildPage(bo));
		}
		return PageUtils.buildDataInfo(list,total);
	}

    @Override
    public List<TBdHelpDataVo> queryList(TBdHelpDataQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdHelpDataVo.class);
    }

    private LambdaQueryWrapper<TBdHelpData> buildQueryWrapper(TBdHelpDataQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdHelpData> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdHelpData::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdHelpData::getFNumber, bo.getFNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFRemark()), TBdHelpData::getFRemark, bo.getFRemark());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdHelpData::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFSort() != null, TBdHelpData::getFSort, bo.getFSort());
        lqw.eq(bo.getFTypeId() != null, TBdHelpData::getFTypeId, bo.getFTypeId());
        lqw.like(StrUtil.isNotBlank(bo.getFTypeName()), TBdHelpData::getFTypeName, bo.getFTypeName());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdHelpDataAddBo bo) {
		TBdHelpTypeVo typeVo=iTBdHelpTypeService.queryById(bo.getFTypeId().intValue());
		if(null==typeVo){
			throw new CustomException("辅助资料类别不存在",1000);
		}
        TBdHelpData add = BeanUtil.toBean(bo, TBdHelpData.class);
        validEntityBeforeSave(add);
        add.setFTypeId(typeVo.getFId().longValue());
        add.setFTypeName(typeVo.getFTypeName());
		if(bo.getFParentId()!=null){
			TBdHelpData parent=getById(bo.getFParentId());
			if(parent==null){
				throw new CustomException("上级辅助资料不存在",1000);
			}
			add.setFParentId(parent.getFId());
		}
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdHelpDataEditBo bo) {
		TBdHelpData old=getById(bo.getFId());
		if(null==old){
			throw new CustomException("辅助资料不存在",1000);
		}
		if(BaseEnum.YES.getCode().equals(old.getFIssysPreset())){
			throw new CustomException("系统预设数据不能修改",1000);
		}
		if(DataStatusEnum.AUDIT.getCode().equals(old.getFDocumentStatus())){
			throw new CustomException("已审核数据不能修改",1000);
		}
		BeanUtil.copyProperties(bo,old,"fId","fTypeId","fTypeName");
		if(bo.getFParentId()!=null){
			TBdHelpData parent=getById(bo.getFParentId());
			if(parent==null){
				throw new CustomException("上级辅助资料不存在",1000);
			}
			old.setFParentId(parent.getFId());
		}
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
		if(CollectionUtil.isNotEmpty(ids)){
			ids.forEach(v->{
				TBdHelpData old=this.getById(v);
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
    private void validEntityBeforeSave(TBdHelpData entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
		ids.forEach(v->{
			TBdHelpData vo=getById(v);
			if((vo!=null) && (!DataStatusEnum.AUDIT.getCode().equals(vo.getFDocumentStatus()))){
				removeById(v);
			}
		});
		return true;
    }
}
