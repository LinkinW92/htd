package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.Exception.util.UnknownException;
import com.skeqi.mes.mapper.all.CMesRecipeTDAO;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;

@Service
@Transactional
public class CMesRecipeTServiceImpl implements CMesRecipeTService {

    @Autowired
    private CMesRecipeTDAO dao;

    @Override
    public List<CMesRecipeT> findAllRecipe(CMesRecipeT recipe) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findAllRecipe(recipe);
    }

    @Override
    public CMesRecipeT findRecipeByid(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("id不能为空", 200);
        }
        return dao.findRecipeByid(id);
    }

    @Override
    public Integer addRecipe(CMesRecipeT recipe) throws ServicesException {
        // TODO Auto-generated method stub
        if (recipe.getRecipeName() == null || recipe.getRecipeName() == "") {
            throw new ParameterNullException("name不能为空", 200);
        }
        List<CMesRecipeT> findAllRecipe = dao.findAllRecipe(recipe); // 判断name是否重复
        if (findAllRecipe.size() > 0) {
            throw new NameRepeatException("配方名称重复", 100);
        }
        return dao.addRecipe(recipe);
    }

    @Override
    public Integer updateRecipe(CMesRecipeT recipe) throws ServicesException {
        // TODO Auto-generated method stub
        if (recipe.getRecipeName() == null || recipe.getRecipeName() == "") {
            throw new ParameterNullException("name不能为空", 200);
        } else if (recipe.getId() == null || recipe.getId() == 0) {
            throw new ParameterNullException("id不能为空", 200);
        }

        CMesRecipeT findRecipeByid = dao.findRecipeByid(recipe.getId()); // 根据id查询原name
        if (!findRecipeByid.getRecipeName().equals(recipe.getRecipeName())) {
            List<CMesRecipeT> findAllRecipe = dao.findAllRecipe(recipe);
            if (findAllRecipe.size() > 0) {
                throw new NameRepeatException("配方名称重复", 100);
            }
        }
        return dao.updateRecipe(recipe);
    }

    @Override
    public Integer delRecipe(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("id不能为空", 200);
        }
        CMesProductionRecipeT findProRecipeByid = dao.findProRecipeByid(id);
        if (findProRecipeByid != null) {
            throw new ParameterNullException("没有解绑不能删除", 201);
        }
        return dao.delRecipe(id);
    }

    @Override
    public List<CMesProductionRecipeT> findAllProRecipe(CMesProductionRecipeT recipe) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findAllProRecipe(recipe);
    }

    @Override
    public Integer addProRecipe(CMesProductionRecipeT recipe) throws ServicesException {
        // TODO Auto-generated method stub
        if (recipe.getProductionId() == null || recipe.getProductionId() == 0) {
            throw new ParameterNullException("产品id不能为空", 200);
        } else if (recipe.getRecipeId() == null || recipe.getRecipeId() == 0) {
            throw new ParameterNullException("配方id不能为空", 200);
        } else if (recipe.getStationId() == null || recipe.getStationId() == 0) {
            throw new ParameterNullException("工位id不能为空", 200);
        }
        return dao.addProRecipe(recipe);
    }

    @Override
    public Integer delProRecipe(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("id不能为空", 200);
        }

        return dao.delProRecipe(id);
    }

    @Override
    public Integer findRecipeMaxid() {
        // TODO Auto-generated method stub
        return dao.findRecipeMaxid();
    }

    @Override
    public void updateProRecipe(CMesProductionRecipeT cMesProductionRecipeT) {
        dao.updateProRecipe(cMesProductionRecipeT);

    }

    @Override
    public CMesProductionRecipeT findProRecipeByid(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findProRecipeByid(id);
    }

    @Override
    public List<CMesRecipeDatilT> findAllRecipeDatil(CMesRecipeDatilT t) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findAllRecipeDatil(t);
    }

    @Override
    public List<CMesRecipeT> findAllRecipeByPid(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("id不能为空", 200);
        }
        return dao.findAllRecipeByPid(id);
    }

    @Override
    public Integer delRecipeDatil(CMesRecipeDatilT t) throws ServicesException {
        // TODO Auto-generated method stub
        if (t.getId() == null) {
            throw new ParameterNullException("id不能为空", 200);
        }
        Integer delRecipeDatil = dao.delRecipeDatil(t.getId()); // 删除配方明细
        if (delRecipeDatil <= 0) {
            throw new UnknownException("未知错误", 300);
        }
        if (t.getStepno() == null) {
            throw new ParameterNullException("步序不能为空", 200);
        }
        if (t.getRecipeId() == null) {
            throw new ParameterNullException("配方id不能为空", 200);
        }
        dao.updateRecipeDetailStep(t);
        return 1;
    }

    @Override
    public List<CMesRecipeTypeT> findAllRecipeType() {
        // TODO Auto-generated method stub
        return dao.findAllRecipeType();
    }

    @Override
    public Integer addRecipeDetail(CMesRecipeDatilT t) throws ServicesException {
        // TODO Auto-generated method stub
        if ("1".toString().equals(t.getStepCategory()) || "16".toString().equals(t.getStepCategory())
                || "17".toString().equals(t.getStepCategory())) { // 扫描总成

            Integer findMaxStepNo = dao.findMaxStepNo(t.getRecipeId()); // 获取下一个步序
            findMaxStepNo = findMaxStepNo + 1;
            t.setStepno(findMaxStepNo.toString());
            Integer addRecipeDetail = dao.addRecipeDetail(t);
            if (addRecipeDetail <= 0) {
                throw new UnknownException("未知错误", 300);
            }
        } else if ("2".toString().equals(t.getStepCategory())) { // 扫描员工号

            Integer findMaxStepNo = dao.findMaxStepNo(t.getRecipeId()); // 获取下一个步序
            findMaxStepNo = findMaxStepNo + 1;
            t.setStepno(findMaxStepNo.toString());
            Integer addRecipeDetail = dao.addRecipeDetail(t);
            if (addRecipeDetail <= 0) {
                throw new UnknownException("未知错误", 300);
            }
        }
        if ("3".toString().equals(t.getStepCategory()) || "4".toString().equals(t.getStepCategory())) {

            Integer findMaxStepNo = dao.findMaxStepNo(t.getRecipeId()); // 获取下一个步序
            findMaxStepNo = findMaxStepNo + 1;
            t.setStepno(findMaxStepNo.toString());
            Integer findMaterialByid = dao.findMaterialByid(t.getMaterialName()); // 判断该物料是否存在于物料表

            if (findMaterialByid != 0) {
                t.setMaterialId(findMaterialByid.toString());
//				t.setMaterialId(findMaterialByid.toString());
            } else {

                t.setMaterialName(t.getMaterialName());
            }
            Integer addRecipeDetail = dao.addRecipeDetail(t);
            if (addRecipeDetail <= 0) {
                throw new UnknownException("未知错误", 300);
            }
        }
        if ("5".toString().equals(t.getStepCategory()) || "6".toString().equals(t.getStepCategory())) {

            Integer findMaxStepNo = dao.findMaxStepNo(t.getRecipeId()); // 获取下一个步序
            findMaxStepNo = findMaxStepNo + 1;
            t.setStepno(findMaxStepNo.toString());
            Integer addRecipeDetail = dao.addRecipeDetail(t);
            if (addRecipeDetail <= 0) {
                throw new UnknownException("未知错误", 300);
            }
        } else if ("7".toString().equals(t.getStepCategory())) {

            Integer findMaxStepNo = dao.findMaxStepNo(t.getRecipeId()); // 获取下一个步序
            findMaxStepNo = findMaxStepNo + 1;
            t.setStepno(findMaxStepNo.toString());
            Integer addRecipeDetail = dao.addRecipeDetail(t);
            if (addRecipeDetail <= 0) {
                throw new UnknownException("未知错误", 300);
            }
        } else if ("8".toString().equals(t.getStepCategory()) || "9".toString().equals(t.getStepCategory())
                || "13".toString().equals(t.getStepCategory()) || "15".toString().equals(t.getStepCategory())) {

            Integer findMaxStepNo = dao.findMaxStepNo(t.getRecipeId()); // 获取下一个步序
            findMaxStepNo = findMaxStepNo + 1;
            t.setStepno(findMaxStepNo.toString());
            Integer addRecipeDetail = dao.addRecipeDetail(t);
            if (addRecipeDetail <= 0) {
                throw new UnknownException("未知错误", 300);
            }
        } else if ("10".toString().equals(t.getStepCategory()) || "11".toString().equals(t.getStepCategory())
                || "12".toString().equals(t.getStepCategory())) {

            Integer findMaxStepNo = dao.findMaxStepNo(t.getRecipeId()); // 获取下一个步序
            findMaxStepNo = findMaxStepNo + 1;
            t.setStepno(findMaxStepNo.toString());
            Integer addRecipeDetail = dao.addRecipeDetail(t);
            if (addRecipeDetail <= 0) {
                throw new UnknownException("未知错误", 300);
            }
        } else if ("14".toString().equals(t.getStepCategory())) {

            Integer findMaxStepNo = dao.findMaxStepNo(t.getRecipeId()); // 获取下一个步序
            findMaxStepNo = findMaxStepNo + 1;
            t.setStepno(findMaxStepNo.toString());
            Integer addRecipeDetail = dao.addRecipeDetail(t);
            if (addRecipeDetail <= 0) {
                throw new UnknownException("未知错误", 300);
            }
        }
        return 1;
    }

    @Override
    public CMesRecipeDatilT findRecipeDetailByid(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("ID不能为空", 200);
        }
        return dao.findRecipeDetailByid(id);
    }

    @Override
    public Integer updateRecipeDetail(CMesRecipeDatilT t) throws ServicesException {
        {
            // TODO Auto-generated method stub
            if ("1".toString().equals(t.getStepCategory()) || "16".toString().equals(t.getStepCategory())
                    || "17".toString().equals(t.getStepCategory())) { // 扫描总成

                if (t.getOldstepNo() > Integer.parseInt(t.getStepno())) { // 修改前的工序大于修改后
                    Integer flag = 1;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                } else if (t.getOldstepNo() < Integer.parseInt(t.getStepno())) {
                    Integer flag = 0;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                }
                Integer updateRecipeDetail = dao.updateRecipeDetail(t);
                if (updateRecipeDetail <= 0) {
                    throw new UnknownException("未知错误", 300);
                }

            } else if ("2".toString().equals(t.getStepCategory())) { // 扫描员工号
                System.err.println("===" + t.getOldstepNo() + "====" + Integer.parseInt(t.getStepno()));
                if (t.getOldstepNo() > Integer.parseInt(t.getStepno())) { // 修改前的工序大于修改后
                    Integer flag = 1;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                } else if (t.getOldstepNo() < Integer.parseInt(t.getStepno())) {
                    Integer flag = 0;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                }
                Integer updateRecipeDetail = dao.updateRecipeDetail(t);
                if (updateRecipeDetail <= 0) {
                    throw new UnknownException("未知错误", 300);
                }
            }
            if ("3".toString().equals(t.getStepCategory()) || "4".toString().equals(t.getStepCategory())) {

                Integer findMaterialByid = dao.findMaterialByid(t.getMaterialName()); // 判断该物料是否存在于物料表
                if (findMaterialByid != 0) {
                    t.setMaterialId(findMaterialByid.toString());
                } else {
                    t.setMaterialId(null);
                }
                System.err.println("===" + t.getOldstepNo() + "====" + Integer.parseInt(t.getStepno()));
                if (t.getOldstepNo() > Integer.parseInt(t.getStepno())) { // 修改前的工序大于修改后
                    Integer flag = 1;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                } else if (t.getOldstepNo() < Integer.parseInt(t.getStepno())) {
                    Integer flag = 0;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                }
                Integer updateRecipeDetail = dao.updateRecipeDetail(t);
                if (updateRecipeDetail <= 0) {
                    throw new UnknownException("未知错误", 300);
                }
            }
            if ("5".toString().equals(t.getStepCategory()) || "6".toString().equals(t.getStepCategory())) {

                if (t.getOldstepNo() > Integer.parseInt(t.getStepno())) { // 修改前的工序大于修改后
                    Integer flag = 1;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                } else if (t.getOldstepNo() < Integer.parseInt(t.getStepno())) {
                    Integer flag = 0;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                }
                Integer updateRecipeDetail = dao.updateRecipeDetail(t);
                if (updateRecipeDetail <= 0) {
                    throw new UnknownException("未知错误", 300);
                }
            } else if ("7".toString().equals(t.getStepCategory())) {

                if (t.getOldstepNo() > Integer.parseInt(t.getStepno())) { // 修改前的工序大于修改后
                    Integer flag = 1;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                } else if (t.getOldstepNo() < Integer.parseInt(t.getStepno())) {
                    Integer flag = 0;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                }
                Integer updateRecipeDetail = dao.updateRecipeDetail(t);
                if (updateRecipeDetail <= 0) {
                    throw new UnknownException("未知错误", 300);
                }
            } else if ("8".toString().equals(t.getStepCategory()) || "9".toString().equals(t.getStepCategory())
                    || "13".toString().equals(t.getStepCategory()) || "15".toString().equals(t.getStepCategory())) {

                if (t.getOldstepNo() > Integer.parseInt(t.getStepno())) { // 修改前的工序大于修改后
                    Integer flag = 1;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                } else if (t.getOldstepNo() < Integer.parseInt(t.getStepno())) {
                    Integer flag = 0;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                }
                Integer updateRecipeDetail = dao.updateRecipeDetail(t);
                if (updateRecipeDetail <= 0) {
                    throw new UnknownException("未知错误", 300);
                }
            } else if ("10".toString().equals(t.getStepCategory()) || "11".toString().equals(t.getStepCategory())
                    || "12".toString().equals(t.getStepCategory())) {

                if (t.getOldstepNo() > Integer.parseInt(t.getStepno())) { // 修改前的工序大于修改后
                    Integer flag = 1;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                } else if (t.getOldstepNo() < Integer.parseInt(t.getStepno())) {
                    Integer flag = 0;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                }
                Integer updateRecipeDetail = dao.updateRecipeDetail(t);
                if (updateRecipeDetail <= 0) {
                    throw new UnknownException("未知错误", 300);
                }
            } else if ("14".toString().equals(t.getStepCategory())) {
                if (t.getMaterialName() == null || t.getMaterialName().equals("")) {
                    throw new ParameterNullException("名称不能为空", 200);
                } else if (t.getRecipeId() == null || t.getRecipeId() == 0) {
                    throw new ParameterNullException("配方ID不能为空", 200);
                }
                if (t.getOldstepNo() > Integer.parseInt(t.getStepno())) { // 修改前的工序大于修改后
                    Integer flag = 1;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                } else if (t.getOldstepNo() < Integer.parseInt(t.getStepno())) {
                    Integer flag = 0;
                    dao.updateRecipeStepNo(t.getOldstepNo(), Integer.parseInt(t.getStepno()), t.getRecipeId(), flag);
                }
                Integer updateRecipeDetail = dao.updateRecipeDetail(t);
                if (updateRecipeDetail <= 0) {
                    throw new UnknownException("未知错误", 300);
                }
            }
            return 1;
        }
    }

    @Override
    public List<CMesJlMaterialT> findAllJLMaterial() {
        // TODO Auto-generated method stub
        return dao.findAllMaterial();
    }

    @Override
    public List<CMesRecipeDatilT> findRecipeDetailId(CMesRecipeDatilT cMesRecipeDatilT) {
        return dao.findRecipeDetailId(cMesRecipeDatilT);
    }

    /**
     * 复制配方，生成新版
     */
	@Override
	public CMesRecipeDatilT copyRecipe(CMesRecipeDatilT recipe) {
		Integer recipeId = recipe.getRecipeId();
		Integer recipeDetailId = recipe.getId();
		Map<String, Object> totalRecipeMap = dao.getTotalRecipe(recipe);
		if(totalRecipeMap == null) {
			//新增时获取总配方
			totalRecipeMap = dao.getTotalRecipe2(recipe);
		}
		if(totalRecipeMap == null) return recipe;
		String totalRecipeName = totalRecipeMap.get("TOTAL_RECIPE_NAME").toString().split("_")[0];
		String lastRecipeName = dao.getLastRecipeName(totalRecipeMap);
		if(!lastRecipeName.matches("(.*)_(.*)版")) {
			totalRecipeMap.put("TOTAL_RECIPE_NAME", totalRecipeName + "_2版");
		}else {
			Integer version = Integer.parseInt(lastRecipeName.substring(lastRecipeName.indexOf("_") + 1, lastRecipeName.indexOf("版")));
			version ++;
			totalRecipeMap.put("TOTAL_RECIPE_NAME", totalRecipeName + "_" + version + "版");
		}
		List<Map<String, Object>> recipeList = dao.findRecipeByTotalId(totalRecipeMap);
		// 插入总配方表
		dao.insertTotalRecipe(totalRecipeMap);
		Object newTotalId = totalRecipeMap.get("id");
		for (Map<String, Object> recipeMap : recipeList) {
			List<Map<String, Object>> recipeDetailList = dao.findRecipeDetailListByRecipeId(recipeMap);
			Integer oldRecipeId = Integer.parseInt(recipeMap.get("ID").toString());
			recipeMap.put("TOTAL_ID", newTotalId);
			dao.insertRecipeCopy(recipeMap);
			Integer newRecipeId = Integer.parseInt(recipeMap.get("id").toString());
			if(recipeId != null && oldRecipeId.compareTo(recipeId) == 0) {
				recipe.setRecipeId(newRecipeId);
			}
			for (Map<String, Object> recipeDetailMap : recipeDetailList) {
				Integer oldRecipeDetailId = Integer.parseInt(recipeDetailMap.get("ID").toString());
				recipeDetailMap.put("RECIPE_ID", newRecipeId);
				dao.insertRecipeDetailCopy(recipeDetailMap);
				Integer newRecipeDetailId = Integer.parseInt(recipeDetailMap.get("id").toString());
				if(recipeDetailId !=null && oldRecipeDetailId.compareTo(recipeDetailId) == 0) {
					recipe.setId(newRecipeDetailId);
				}
			}
		}
		// 更新工单总配方id
		totalRecipeMap.put("workorderId", recipe.getWorkorderId());
		dao.updateWorkorderRecipe(totalRecipeMap);
		return recipe;
	}
}
