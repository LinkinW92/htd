package com.skeqi.mes.service.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesRecipeDAO;
import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;


@Service
@Transactional
public class CMesRecipeServiceImpl implements CMesRecipeService {

    @Autowired
    private CMesRecipeDAO dao;

    @Override
    public List<CMesTotalRecipeT> findAllTotalRecipe(CMesTotalRecipeT total) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findAllTotalRecipe(total);
    }

    @Override
    public Integer addTotalRecipe(CMesTotalRecipeT c) throws ServicesException {
        // TODO Auto-generated method stub
        if (c.getTotalRecipeName() == null || c.getTotalRecipeName() == "") {
            throw new ParameterNullException("配方名称不能为空", 200);
        }
        List<CMesTotalRecipeT> findTotalRecipeByParam = dao.findTotalRecipeByParam(c.getTotalRecipeName(), null);
        if (findTotalRecipeByParam.size() > 0) {
            throw new NameRepeatException("总配方名称已经存在", 100);
        }
        // 使用外部id
//        if (0 == c.getLineId() || null == c.getLineId()) {
//            c.setLineId(c.getLineCode());
//        } else if (0 == c.getProductionId() || null == c.getProductionId()) {
//            c.setProductionId(c.getProductionCode());
//        }

        //查询该产品是否存在默认工艺路线
        if (c.getStatus() == 1) {
            Integer defaultStatus = dao.findDefaultStatus(c.getProductionId(), c.getLineId());
            if (defaultStatus > 0) {
                throw new NameRepeatException("该产品已存在默认配方", 100);
            }
        }
        return dao.addTotalRecipe(c);
    }

    @Override
    public Integer updateTotalRecipe(CMesTotalRecipeT c) throws ServicesException {
        // TODO Auto-generated method stub
        if (c.getTotalRecipeName() == null || c.getTotalRecipeName() == "") {
            throw new ParameterNullException("配方名称不能为空", 200);
        }
        List<CMesTotalRecipeT> findTotalRecipeByParam = dao.findTotalRecipeByParam(null, c.getId());
        if (!findTotalRecipeByParam.get(0).getTotalRecipeName().equals(c.getTotalRecipeName())) {   //修改前后的名称不同
            List<CMesTotalRecipeT> findTotalRecipeByParam2 = dao.findTotalRecipeByParam(c.getTotalRecipeName(), null);
            if (findTotalRecipeByParam2.size() > 0) {
                throw new NameRepeatException("总配方名称已经存在", 100);
            }
        }
        if (c.getStatus() == 1) {
            Integer defaultStatus = dao.findDefaultStatus(c.getProductionId(), c.getLineId());
            if (defaultStatus > 0 && defaultStatus != c.getId()) {
                throw new NameRepeatException("该产品已存在默认配方", 100);
            }
        }
        return dao.updateTotalRecipe(c);
    }

    @Override
    public Integer delTotalRecipe(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null) {
            throw new ParameterNullException("id不能为空", 200);
        }
        CMesRecipe c = new CMesRecipe();
        c.setTotalId(id);
        List<CMesRecipe> findAllRecipe = dao.findAllRecipe(c);
        if (findAllRecipe.size() > 0) {
            throw new NameRepeatException("此总配方下还有配方未解绑，不能删除", 110);
        }
        return dao.delTotalRecipe(id);
    }

    @Override
    public List<CMesRecipe> findAllRecipe(CMesRecipe c) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findAllRecipe(c);
    }

    @Override
    public Integer addRecipe(CMesRecipe c) throws ServicesException {
        // TODO Auto-generated method stub
        // 使用外部id
//        if (null == c.getTotalId() || 0 == c.getTotalId()) {
//            c.setTotalId(c.getStationCode());
//        } else if (null == c.getStationId() || 0 == c.getStationId()) {
//            c.setStationId(c.getStationCode());
//        }

        if (c.getTotalId() == null) {
            throw new ParameterNullException("总配方id不能为空", 200);
        } else if (c.getRecipeName() == null || c.getRecipeName() == "") {
            throw new ParameterNullException("配方名称不能为空", 200);
        } else if (c.getStationId() == null) {
            throw new ParameterNullException("工位不能为空", 200);
        }
        List<CMesRecipe> findRecipeByParam = dao.findRecipeByParam(c.getRecipeName(), null, c.getTotalId());
        if (findRecipeByParam.size() > 0) {
            throw new NameRepeatException("配方名称已经存在", 100);
        }
        Integer num = dao.findRecipeCountByStationId(c);
        if (num > 0) {
            throw new NameRepeatException("工位已经存在", 100);
        }
        return dao.addRecipe(c);
    }

    @Override
    public Integer updateRecipe(CMesRecipe c) throws ServicesException {
        // TODO Auto-generated method stub
        if (c.getTotalId() == null) {
            throw new ParameterNullException("总配方id不能为空", 200);
        } else if (c.getRecipeName() == null || c.getRecipeName() == "") {
            throw new ParameterNullException("配方名称不能为空", 200);
        } else if (c.getStationId() == null) {
            throw new ParameterNullException("工位不能为空", 200);
        }
        List<CMesRecipe> findRecipeByParam = dao.findRecipeByParam(null, c.getId(), null);
        if (!findRecipeByParam.get(0).getRecipeName().equals(c.getRecipeName())) {
            List<CMesRecipe> findRecipeByParam2 = dao.findRecipeByParam(c.getRecipeName(), null, c.getTotalId());
            if (findRecipeByParam2.size() > 0) {
                throw new NameRepeatException("总配方名称已经存在", 100);
            }
        }
        return dao.updateRecipe(c);
    }

    @Override
    public Integer delRecipe(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        List<CMesRecipeDatilT> findAllRecipeDatil = dao.findAllRecipeDatil(id);
        if (findAllRecipeDatil.size() > 0) {
            throw new NameRepeatException("此配方下还有明细未删除", 110);
        }
        return dao.delRecipe(id);
    }

    @Override
    public List<CMesTotalRecipeT> findTotalRecipeByParam(String name, Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findTotalRecipeByParam(name, id);
    }

    @Override
    public List<CMesStationT> findStation(CMesStationT c) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findStation(c);
    }

    @Override
    public List<CMesRecipe> findRecipeByParam(String name, Integer id, Integer totalId) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findRecipeByParam(name, id, totalId);
    }

    @Override
    public Integer editStatus(Integer id, Integer status) throws ServicesException {
        // TODO Auto-generated method stub
        List<CMesTotalRecipeT> findTotalRecipeByParam = dao.findTotalRecipeByParam(null, id);
        for (CMesTotalRecipeT cMesTotalRecipeT : findTotalRecipeByParam) {
            dao.editStatusPro(cMesTotalRecipeT.getProductionId());
        }
        return dao.editStatus(id, status);
    }

}
