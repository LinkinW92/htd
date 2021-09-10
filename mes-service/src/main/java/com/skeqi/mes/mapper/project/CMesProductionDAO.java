package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesProductionT;

//@Component
//@MapperScan
public interface CMesProductionDAO {

//
//	@Select("<script>" +
//			"select * from c_mes_production_t where 1=1"+
//			  "<if test=\"name!='' and name!=null \"> and PRODUCTION_NAME=#{name} </if>"
//			  + "</script>")
	public List<CMesProductionT> findAllPro(@Param("name")String name);

//
//	@Insert("insert into c_mes_production_t(PRODUCTION_NAME,PRODUCTION_TYPE,PRODUCTION_TRADEMARK,PRODUCTION_SERIES,PRODUCTION_VR,"
//			+ "PRODUCTION_DISCRIPTION,PRODUCTION_STE,PATH) "
//			+ "values(#{productionName},#{productionType},#{productionTrademark},#{productionSeries},#{productionVr},#{productionDiscription},"
//			+ "#{productionSte},#{path})")
	public Integer addPro(CMesProductionT pro);


//	@Update("update c_mes_production_t set PRODUCTION_NAME=#{productionName},PRODUCTION_TYPE=#{productionType},PRODUCTION_TRADEMARK=#{productionTrademark},"
//			+ "PRODUCTION_SERIES=#{productionSeries},PRODUCTION_VR=#{productionVr},PRODUCTION_DISCRIPTION=#{productionDiscription},PRODUCTION_STE=#{productionSte},PATH=#{path} where ID=#{id}")
	public Integer updatePro(CMesProductionT pro);


//	@Delete("delete from c_mes_production_t where ID=#{id}")
	public Integer delPro(@Param("id")Integer id);

	//查询当前产品标记是否存在
	public Integer findByProductionType(@Param("name")String name);


	public Integer findByProductionTypeL(@Param("productionType")String productionType,@Param("id") Integer id);

	public Integer addProL(CMesProductionT t);

	public CMesProductionT findByProTypeL(@Param("productionType")String productionType);

	public CMesProductionT findProIdAndName(@Param("id")Integer id,@Param("proName")String proName);
}
