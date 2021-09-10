package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skeqi.mes.pojo.CMesLineT;

public interface CMesProLineDAO {

	// 产线查询
	@Select("<script>" + "select * from c_mes_line_t where 1=1"
			+ "<if test=\"name!='' and name!=null \"> and NAME like ${name} </if>" +
			" 	ORDER BY  REGION  " + "</script>")
	public List<CMesLineT> findAllLine(@Param("name") String name);

	// 添加产线
	@Insert("insert into c_mes_line_t(DT,NAME,DSC,YIELD_NUMBER,REGION,COUNT_TYPE,PRODUCT_MARK,PAIBAN_STATUS,ANDENG_STATUS) "
			+ "values(now(),#{name},#{dsc},#{yieldNumber},#{region},#{countType},#{productMark},#{paibanStatus},#{andengStatus})")
	public Integer addLine(CMesLineT line);

	@Update("update c_mes_line_t set NAME=#{name},DSC=#{dsc},YIELD_NUMBER=#{yieldNumber},"
			+ "REGION=#{region},COUNT_TYPE=#{countType},PRODUCT_MARK=#{productMark}  ,PAIBAN_STATUS=#{paibanStatus}, ANDENG_STATUS=#{andengStatus} where ID=#{id}")
	public Integer updateLine(CMesLineT line);

	@Delete("delete from c_mes_line_t where ID=#{id}")
	public Integer delLine(Integer id);

	@Select("select count(*) from c_mes_line_t   where NAME=#{lineName} ")
	public Integer findByName(@Param("lineName") String lineName);

	// 查询产线是否有这个产品标记
	@Select("select count(*)   from c_mes_line_t where PRODUCT_MARK=#{productionVr}")
	public Integer findProVrByLine(@Param("productionVr") String productionVr);


	// 根据产品编码查询产线
	@Select("select * from c_mes_line_t where PRODUCT_MARK LIKE '%${proType}%'")
	public List<CMesLineT> findproTypeByLine(@Param("proType") String proType);

	// 根据产品编码查询产线
	@Select("select *   from c_mes_line_t where id=#{lineId}")
	public List<CMesLineT> findLineIdByProType(@Param("lineId") Integer lineId);

	// 根据产品编码查询产线
	@Select("select  PRODUCTION_VR   from c_mes_production_t where id=#{productionId}")
	public String findPidByType(@Param("productionId")Integer productionId);
}
