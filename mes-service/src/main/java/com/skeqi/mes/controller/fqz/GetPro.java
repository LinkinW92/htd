package com.skeqi.mes.controller.fqz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.util.ToolUtils;

public  class GetPro {

	public static Connection getConnection(){
		Connection conn = null;
		String url	= "jdbc:sqlserver://192.168.2.229:1433;DatabaseName=BC1";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, "sa","skq");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return conn;
	}

	//返回所有产品
	public static List<CMesProductionT> getpro() throws Exception{
		Connection connection = getConnection();
		Statement sta = connection.createStatement();
		ResultSet rs = sta.executeQuery("select * from C_MES_PRODUCTION_T");
		List<CMesProductionT> list = new ArrayList<>();
		while(rs.next()){
			CMesProductionT t = new CMesProductionT();
			t.setId(Integer.parseInt(rs.getString(1)));
			t.setProductionName(rs.getString(2));
			t.setProductionType(rs.getString(3));
			t.setProductionTrademark(rs.getString(4));
			t.setProductionSeries(rs.getString(5));
			t.setProductionVr(rs.getString(6));
			t.setProductionDiscription(rs.getString(7));
			t.setStationName(rs.getString(8));
			t.setProductionEt(rs.getString(9));
			t.setProductionGt(rs.getString(10));
			t.setProductionSte(rs.getString(11));
			if(rs.getString(12)==null){
				t.setProductionPrintId(null);
			}else{
				t.setProductionPrintId(Integer.parseInt(rs.getString(12)));
			}
			if(rs.getString(13)==null){
				t.setProductionSystemId(null);
			}else{
				t.setProductionSystemId(Integer.parseInt(rs.getString(13)));
			}
			if(rs.getString(14)==null){
				t.setProductionGroupId(Integer.parseInt(rs.getString(14)));
			}else{
				t.setProductionGroupId(null);
			}
			t.setProductionGroupName(rs.getString(15));
			list.add(t);
		}
		return list;
	}

	//根据id查询数据
	public static CMesProductionT findByid(Integer id) throws Exception{
		Connection connection = getConnection();
		Statement sta = connection.createStatement();
		String sql = "select * from C_MES_PRODUCTION_T where id ="+id+"";
		ResultSet rs = sta.executeQuery(sql);
		CMesProductionT t = new CMesProductionT();
		while(rs.next()){
/*		t.setId(Integer.parseInt(rs.getString(1)));*/
		t.setProductionName(rs.getString(2));
		t.setProductionType(rs.getString(3));
		t.setProductionTrademark(rs.getString(4));
		t.setProductionSeries(rs.getString(5));
		t.setProductionVr(rs.getString(6));
		t.setProductionDiscription(rs.getString(7));
		t.setStationName(rs.getString(8));
		t.setProductionEt(rs.getString(9));
		t.setProductionGt(rs.getString(10));
		t.setProductionSte(rs.getString(11));
		if(rs.getString(12)==null){
			t.setProductionPrintId(null);
		}else{
			t.setProductionPrintId(Integer.parseInt(rs.getString(12)));
		}
		if(rs.getString(13)==null){
			t.setProductionSystemId(null);
		}else{
			t.setProductionSystemId(Integer.parseInt(rs.getString(13)));
		}
		if(rs.getString(14)==null){
			t.setProductionGroupId(null);
		}else{
			t.setProductionGroupId(Integer.parseInt(rs.getString(14)));
		}
		if(rs.getString(15)==null){
			t.setGroupNumber(null);
		}else{
			t.setGroupNumber(Integer.parseInt(rs.getString(15)));
		}
		}
		return t;
	}

	//返回所有物料
	public static List<CMesMaterialT> findAll() throws Exception{
		List<CMesMaterialT> list = new ArrayList<>();
		Connection connection = getConnection();
		Statement sta = connection.createStatement();
		String sql = "select * from C_MES_MATERIAL_T";
		ResultSet rs = sta.executeQuery(sql);
		while(rs.next()){
			CMesMaterialT c = new CMesMaterialT();
			c.setId(Integer.parseInt(rs.getString(1)));
			c.setGraphNumber(rs.getString(2));
			c.setMaterialNo(rs.getString(3));
			c.setMaterialName(rs.getString(4));
			if(rs.getString(5)==null){
				c.setMaterialTypeId(null);
			}else{
				c.setMaterialTypeId(Integer.parseInt(rs.getString(5)));
			}
			c.setMaterialEdition(rs.getString(6));
			if(rs.getString(7)==null){
				c.setAssemblyTypeId(null);
			}else{
				c.setAssemblyTypeId(Integer.parseInt(rs.getString(7)));
			}
			c.setMaterialSeriesName(rs.getString(8));
			c.setPrintLable(rs.getString(9));
			c.setProductionCommodityNumber(rs.getString(10));
			c.setProductionLicence(rs.getString(11));
			c.setProctionSecurityCode(rs.getString(12));
			c.setSamplingType(rs.getString(13));
			c.setTransferMaterial(rs.getString(14));
			c.setBoxedNumber(rs.getString(15));
			if(rs.getString(16)==null){
				c.setBatchNumber(null);
			}else{
				c.setBatchNumber(Integer.parseInt(rs.getString(16)));
			}
			c.setMaterialVr(rs.getString(17));
			if(rs.getString(18)==null){
				c.setStandardNumber(null);
			}else{
				c.setStandardNumber(Integer.parseInt(rs.getString(18)));
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			c.setDt(sdf.parse(rs.getString(19)));
			list.add(c);
		}
		return list;
	}


}
