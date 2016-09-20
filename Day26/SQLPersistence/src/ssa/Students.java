package ssa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.sql.*;


public class Students extends Student {
	
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
	public Students() throws Exception{
		makeConnection();
	}
	
	private static void makeConnection() throws SQLException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("demonstration.properties"));
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");
		String dbUrl = prop.getProperty("dburl");
		db = new SQLdb(dbUrl, user, pass);
	}
	
	public Student getById(int studentId) throws SQLException{
		List<Student> students = select("Select * from student where id = " +  studentId);
		if(students.isEmpty()) 
			return null;
		return students.get(0);
	}
	
	public ArrayList<Student> getAll () throws SQLException {
		return select("Select * from student");
	}
	public ArrayList<Student> getAllOrdered (String columnName, String ascOrDesc) throws SQLException {
		return select("Select * from student order by " + columnName + " " + ascOrDesc);
	}
	public ArrayList<Student> getByGPA(double minGpa, double maxGpa) throws SQLException {
		return select("Select * from student where gpa > " + minGpa + " and gpa < " + maxGpa);
	}
	public ArrayList<Student> select(String sql) {
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			ResultSet rs = db.executeSQLQuery(sql);
			while (rs.next()) {
				Student student = loadStudent(rs);
				students.add(student);
			}
			return students;
		} catch (Exception ex) {
			ex.printStackTrace(); }
		return null;
	}
	private Student loadStudent(ResultSet rs) {
		Student student = new Student();
		try {
			student.setId(rs.getInt("id"));
			student.setFirstName(rs.getString("first_name"));
			student.setLastName(rs.getString("last_name"));
			student.setGpa(rs.getDouble("gpa"));
			student.setSat(rs.getInt("sat"));
			return student;
		} catch (SQLException ex) { 
			ex.printStackTrace(); }
		return null;
	}
	
	public void delete(int studentId) throws SQLException{
		try {
		ResultSet rs = db.executeSQLQuery("Select * from student_class_relationship where student_id = " +  studentId);
		if (rs.next()) {
			System.out.println("Student must be removed from classes before he can be deleted");
}
		else{
			String sql = ("Delete from student where id = " +  studentId);
			db.executeSQLUpdate(sql); 
		}
		}
		catch (SQLException ex) { 
			ex.printStackTrace(); }
	}
	public void insert(Student student) throws SQLException{
		String sql = ("Insert student (id, first_name, last_name, sat, gpa) values (" + student.getId() + ", '" + student.getFirstName() + "', '" + student.getLastName() + "', " + student.getSat() + ", " + student.getGpa() + ")");
		db.executeSQLUpdate(sql);
	}
	public void update(Student student) throws SQLException{
		String sql = ("Update student set first_name = '" + student.getFirstName() + "', last_name = '" + student.getLastName() + "', sat = " + student.getSat() + ", gpa = " + student.getGpa() + "where id = " + student.getId());
		db.executeSQLUpdate(sql);
	}
	public void enrollForClass(Student student, int classId) throws SQLException{
		String sql = ("Insert student_class_relationship (student_id, class_id) values (" + student.getId() + ", " + classId + ")");
		db.executeSQLUpdate(sql);
	}
	public void leaveClass(Student student, int classId) throws SQLException{ 
		String sql = ("Delete from student_class_relationship where student_id = " + student.getId() + " and class_id = " + classId);
		db.executeSQLUpdate(sql);
	}
	}
