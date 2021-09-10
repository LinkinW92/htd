package com.skeqi.mes.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {

	public static String jdbc_driverClassName = "";
	public static String jdbc_url = "";
	public static String username = "";
	public static String password = "";

	/**
	 * 连接
	 *
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(jdbc_driverClassName);
			con = DriverManager.getConnection(jdbc_url,username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭
	 *
	 * @param con
	 * @param stat
	 */
	public static void close(Connection connection, Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
