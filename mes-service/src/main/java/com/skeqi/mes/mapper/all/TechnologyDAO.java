package com.skeqi.mes.mapper.all;

import java.util.List;

import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesLabelType;
import com.skeqi.mes.pojo.CMesLineT;

/**
 * 工艺dao
 * @author : FQZ
 * @Package: com.skeqi.mapper.all
 * @date   : 2020年2月24日 下午2:45:47
 */
public interface TechnologyDAO {

	//规则类型列表
	public List<CMesLabelType> findAllLabelType(CMesLabelType c);

	//根据id查询标签类型列表
	public CMesLabelType findLabelTypeByid(Integer id);

	//添加规则类型
	public Integer addLabelType(CMesLabelType c);

	//修改规则类型
	public Integer updateLabelType(CMesLabelType c);

	//删除规则类型
	public Integer delLabelType(Integer id);

	//标签列表
	public List<CMesLabelManagerT> findAllLabelManager(CMesLabelManagerT c);

	//根据id查询标签列表
	public CMesLabelManagerT findLabelManagerByid(Integer id);

	//添加标签
	public Integer addLabelManager(CMesLabelManagerT c);

	//修改标签
	public Integer updateLabelManager(CMesLabelManagerT c);

	//删除标签
	public Integer delLabelManager(Integer id);

	/**
	 * @Title: findAll
	 * @Description: TODO(关联查询)
	 * @return    參數描述
	 * @return List<CMesLineT>  返回类型
	 * @throws
	 */
	public List<CMesLineT> findAll();
}
