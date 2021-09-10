package com.skeqi.mes.service.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;

import javax.servlet.http.HttpServletRequest;

public interface CMesRecipeTService {

    //查询配方
    public List<CMesRecipeT> findAllRecipe(CMesRecipeT recipe) throws ServicesException;

    //根据id查询配方
    public CMesRecipeT findRecipeByid(Integer id) throws ServicesException;

    //查询最大的配方id
    public Integer findRecipeMaxid();

    //添加配方
    public Integer addRecipe(CMesRecipeT recipe) throws ServicesException;

    //修改配方
    public Integer updateRecipe(CMesRecipeT recipe) throws ServicesException;

    //删除配方
    public Integer delRecipe(Integer id) throws ServicesException;

    //查询配方产品绑定表
    public List<CMesProductionRecipeT> findAllProRecipe(CMesProductionRecipeT recipe) throws ServicesException;

    public CMesProductionRecipeT findProRecipeByid(Integer id) throws ServicesException;

    //添加配方产品绑定表
    public Integer addProRecipe(CMesProductionRecipeT recipe) throws ServicesException;

    //删除配方产品绑定表
    public Integer delProRecipe(Integer id) throws ServicesException;

    public void updateProRecipe(CMesProductionRecipeT cMesProductionRecipeT);

    //配方类型列表
    public List<CMesRecipeTypeT> findAllRecipeType();

    public List<CMesRecipeDatilT> findAllRecipeDatil(CMesRecipeDatilT t) throws ServicesException;

    //根据产品ID查询配方列表
    public List<CMesRecipeT> findAllRecipeByPid(Integer id) throws ServicesException;

    //删除配方
    public Integer delRecipeDatil(CMesRecipeDatilT t) throws ServicesException;

    //添加配方明细
    public Integer addRecipeDetail(CMesRecipeDatilT t) throws ServicesException;

    //根据ID查询配方明细列表
    public CMesRecipeDatilT findRecipeDetailByid(Integer id) throws ServicesException;

    //修改配方明细
    public Integer updateRecipeDetail(CMesRecipeDatilT t) throws ServicesException;

    //查询物料列表
    public List<CMesJlMaterialT> findAllJLMaterial();

    // 查询配方明细id
    public List<CMesRecipeDatilT> findRecipeDetailId(CMesRecipeDatilT cMesRecipeDatilT);

	public CMesRecipeDatilT copyRecipe(CMesRecipeDatilT recipe);


}
