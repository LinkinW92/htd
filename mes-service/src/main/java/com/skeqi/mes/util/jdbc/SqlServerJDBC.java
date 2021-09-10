package com.skeqi.mes.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skeqi.mes.pojo.wms.K3ExportNotifydetall;

public class SqlServerJDBC {

	public static String jdbc_driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String jdbc_url = "jdbc:sqlserver://192.168.7.8:1433;DatabaseName=DataCenter";
	public static String username = "sa";
	public static String password = "kingdee";

	/**
	 * 连接
	 *
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		DbUtil.jdbc_driverClassName = jdbc_driverClassName;
		DbUtil.jdbc_url = jdbc_url;
		DbUtil.username = username;
		DbUtil.password = password;
		Connection connection = DbUtil.getConnection();
		return connection;
	}

	/**
	 * 关闭
	 *
	 * @param con
	 * @param stat
	 */
	public static void close(Connection connection, Statement stmt) {
		DbUtil.close(connection, stmt);
	}


	public static List<K3ExportNotifydetall> select(){

		List<K3ExportNotifydetall> list = new ArrayList<K3ExportNotifydetall>();

		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String dbURL = "jdbc:sqlserver://192.168.7.8:1433;DatabaseName=DataCenter";// 数据源
																					// //
																					// ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
		String Name = "sa";
		String Pwd = "kingdee";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(dbURL, Name, Pwd);
			System.out.println("连接数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接失败");
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

		String sql = "select * from SYS_CONFIG";

		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.print("fkey:" + rs.getString("fkey") + "\tfvalue:" + rs.getString("fvalue") + "\tfremark:"
						+ rs.getString("fremark"));
				System.out.println();
//				sql = "update SYS_CONFIG set fremark='" + (rs.getString("fremark") + 1) + "' where fremark='"
//						+ rs.getString("fremark") + "'";
//				stmt.executeUpdate(sql);
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
