package com.skeqi.mes.mapper.qh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesReportColumn;
import com.skeqi.mes.pojo.CMesTableReportT;

public interface CMesReportTableDAO {

	// 查询数据报表的表
	public List<CMesTableReportT> findAllReport(@Param("tableReportId") Integer tableReportId);

	// 查询此表下面的列
	public List<CMesReportColumn> findColumnById(@Param("tableReportId") Integer tableReportId);

	// 修改此表展示的列
	public Integer updateStatus(@Param("tableReportId") Integer tableReportId,
			@Param("tableColumnsName") String tableColumnsName, @Param("showFlag") Integer showFlag);

	// 查询报表内容
	public List<Map<String, Object>> findDataReport(@Param("tableName") String tableName,
			@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("sn") String sn,
			@Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

	public String getTableNameById(@Param("tableReportId") Integer tableReportId);
}
