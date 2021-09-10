package com.skeqi.mes.service.qh;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesReportColumn;
import com.skeqi.mes.pojo.CMesTableReportT;

import org.springframework.http.ResponseEntity;

public interface CMesReportTableService {
	// 查询数据报表的表
	public List<CMesTableReportT> findAllReport();

	// 查询此表下面的列
	public List<CMesReportColumn> findColumnById(Integer tableReportId);

	// 修改此表展示的列
	public Integer updateStatus(Integer tableReportId, String tableColumnsName, Integer showFlag);

	// 根据id获取表名
	public String getTableNameById(Integer tableReportId);

	// 查询报表内容
	public List<Map<String, Object>> findDataReport(Map<String, String> map) throws ServicesException;

	ResponseEntity<byte[]> exportExcel(Map<String, String> map) throws ServicesException;
}
