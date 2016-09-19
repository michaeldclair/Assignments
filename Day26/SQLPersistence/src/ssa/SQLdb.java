package ssa;

import java.sql.*;

public class SQLdb {
	String url = null;
	String usr = null;
	String pwd = null;
	public Connection conn = null;
	
	public void executeSQLUpdate(String sql) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			stmt.executeUpdate(sql);
		} catch(SQLException ex) { throw ex; }
	}
	public ResultSet executeSQLQuery(String sql) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch(SQLException ex) { throw ex; }
	}	
	

	public SQLdb(String url, String user, String pass) throws SQLException {
		conn = DriverManager.getConnection(url, user, pass);
	}
	
	public void close() throws SQLException {
		if(conn != null) { conn.close(); conn = null; }
	}
}
