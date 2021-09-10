package com.skeqi.mes.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skeqi.mes.pojo.project.DeviceCollect;
import com.skeqi.mes.pojo.wms.K3ExportNotifydetall;

public class AndonSQLServer {

	public static List<DeviceCollect> findDevice(){

		List<DeviceCollect> list = new ArrayList<DeviceCollect>();

		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String dbURL = "jdbc:sqlserver://10.25.142.202:1433;DatabaseName=SANY";// 数据源
																					// //
																					// ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
		String Name = "andonUN";
		String Pwd = "cape123456";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(dbURL, Name, Pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//关闭事务自动提交功能
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "select * from DEVICE_COLLECT_DATA_TO_ANDON";

		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DeviceCollect d = new DeviceCollect();
				d.setDeviceName(rs.getString("DEVICE_NAME"));
				d.setDeviceNumber(rs.getString("DEVICE_NUMBER"));
				d.setLastUpdatetime(rs.getString("LAST_UPDATETIME"));
				d.setAlarmState(rs.getInt("ALARM_STATE"));
				list.add(d);
			}
			//提交事务
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}



}
