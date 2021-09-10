package com.skeqi.mes.mapper.all;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.api.GetcurrentempPT;
import com.skeqi.mes.pojo.api.GetcurrentstepPT;
import com.skeqi.mes.pojo.api.UpdatecurrentempPT;
import com.skeqi.mes.pojo.api.UpdatecurrentstepPT;

/**
 *
 * @author yinp
 * @date 2020年1月11日
 *
 */
@Component
@MapperScan
public interface GetcurrentempPDao {

	/**
	 *
	 * @param snBarcode
	 * @param stationEmpName
	 * @param lineEmpName
	 * @return tempSteprecordCount
	 */
	@Select("select count(id) tempSteprecordCount from r_mes_step_t  "
			+ "where sn = #{snBarcode}  "
			+ "and st = #{stationEmpName}  "
			+ "and line_name = #{lineEmpName}")
	public GetcurrentempPT find1(String snBarcode,String stationEmpName,String lineEmpName);

	/**
	 *
	 * @param snBarcode
	 * @param stationEmpName
	 * @param lineEmpName
	 * @return tmepEmpName
	 */
	@Select("select emp tmepEmpName from  r_mes_step_t   "
			+ "where sn = #{snBarcode}  "
			+ "and st = #{stationEmpName}  "
			+ "and line_name = #{lineEmpName}")
	public GetcurrentempPT find2(String snBarcode,String stationEmpName,String lineEmpName);

	//
	//
	//
	//

	/**
	 *
	 * @param serialnumber
	 * @param station
	 * @param line
	 * @return tempSteprecordCount
	 */
	@Select("select count(*) tempSteprecordCount from r_mes_step_t s "
			+ "where s.sn = #{serialnumber} "
			+ "and s.st = #{station} "
			+ "and s.line_name = #{line}")
	public GetcurrentstepPT find10(String serialnumber,String station,String line);

	/**
	 *
	 * @param serialnumber
	 * @param station
	 * @param line
	 * @return step
	 */
	@Select("select s.step_no step from r_mes_step_t s "
			+ "where s.sn = #{serialnumber} "
			+ "and s.st = #{station} "
			+ "and s.line_name = #{line}")
	public GetcurrentstepPT find20(String serialnumber,String station,String line);

	//
	//
	//
	//
	//

	/**
	 *
	 * @param serialnumber
	 * @param stationName
	 * @param line
	 * @return tempSteprecordCount
	 */
	@Select("select count(*) tempSteprecordCount from r_mes_step_t s "
			+ "where s.sn = #{serialnumber} "
			+ "and s.st = #{stationName} "
			+ "and s.line_name = #{line}")
	public UpdatecurrentempPT find100(String serialnumber,String stationName,String line);

	/**
	 *
	 * @param empname
	 * @param serialnumber
	 * @param stationName
	 * @param line
	 */
	@Select("update r_mes_step_t s set s.emp = #{empname} "
			+ "where s.sn = #{serialnumber} "
			+ "and s.st = #{stationName} "
			+ "and s.line_name = #{line}")
	public void update1(String empname,String serialnumber,String stationName,String line);


	//
	//
	//
	//
	//
	@Select("select count(*) tempSteprecordCount from r_mes_step_t s "
			+ "where s.sn = #{serialnumber} "
			+ "and s.st = #{station} "
			+ "and s.line_name = #{line}")
	public UpdatecurrentstepPT find1s(String serialnumber,String station,String line);

	@Update("update r_mes_step_t s set s.step_no = #{step} "
			+ "where s.sn = #{serialnumber} "
			+ "and s.st = #{station} "
			+ "and s.line_name = #{line}")
	public void update1s(String step,String serialnumber,String station,String line);



}
