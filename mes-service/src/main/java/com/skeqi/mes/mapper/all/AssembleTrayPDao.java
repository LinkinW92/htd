package com.skeqi.mes.mapper.all;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan
public interface AssembleTrayPDao {

	@Select("SELECT ID FROM R_MES_TRACKING_T WHERE SN = #{snBarcode}")
	public Integer queryAssembleTrayBySn(@Param("snBarcode")String snBarcode);

	@Update("UPDATE R_MES_TRACKING_T SET TRAYNUM=#{trayNum} WHERE ID=#{id}")
	public void updateAssembleTray(@Param("trayNum") String trayNum,@Param("id") Integer id);


	@Select("select count(*) from ( select tray.tray_vr from   tripod.c_mes_tray_t tray "
			+ " where  tray.line_id in (SELECT ID  from tripod.c_mes_line_t  "
			+ " where NAME=#{line}) ) tray_  where   regexp_like(#{trayNum},tray_.tray_vr)")
	Integer checkTray(@Param("line")String line, @Param("trayNum")String trayNum);

}
