package ssa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.sql.*;


public class Majors extends Student {
	
	public static SQLdb db = null;
	public static int majorId;
	public static int minimumSat;
	public static int studentSat;
	public static int majorCheck;
	public static String majorDescription ;
	public static int requiredSat;
	public static ArrayList<Student> students= new ArrayList<Student>();

	

	public static void main(String[] args) throws SQLException {
		

	}
	public Majors() throws Exception{
		makeConnection();
	}
	
	public void assignMajor(Student student, Major major) throws SQLException{
		String sql = ("Update student set first_name = '" + student.getFirstName() + "', last_name = '" + student.getLastName() + "', sat = " + student.getSat() + ", gpa = " + student.getGpa() + ", major_id = " + major.getId() + "where id = " + student.getId());
		db.executeSQLUpdate(sql);
	}	
	
	private static void makeConnection() throws SQLException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("demonstration.properties"));
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");
		String dbUrl = prop.getProperty("dburl");
		db = new SQLdb(dbUrl, user, pass);
	}
	public ArrayList<Major> getAllMajors () throws SQLException {
		return select("Select * from major");
	}
	public ArrayList<Major> select(String sql) {
		ArrayList<Major> majors = new ArrayList<Major>();
		try {
			ResultSet rs = db.executeSQLQuery(sql);
			while (rs.next()) {
				Major major = loadMajor(rs);
				majors.add(major);
			}
			return majors;
		} catch (Exception ex) {
			ex.printStackTrace(); }
		return null;
	}
	private Major loadMajor(ResultSet rs) {
		Major major = new Major();
		try {
			major.setId(rs.getInt("id"));
			major.setDescription(rs.getString("description"));
			return major;
		} catch (SQLException ex) { 
			ex.printStackTrace(); }
		return null;
	}
	
	public void deleteMajor(int majorId) throws SQLException{
		String sql = ("Delete from major where id = " +  majorId);
		db.executeSQLUpdate(sql);
	}
	public void insertMajor(Major major) throws SQLException{
		String sql = ("Insert major (id, description, req_sat) values (" + major.getId() + ", '" + major.getDescription() +"', " + "0)");
		System.out.println(sql);
		db.executeSQLUpdate(sql);
	}

	
	
}
