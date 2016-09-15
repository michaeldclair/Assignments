package ssa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;


public class Enrollment {
	
	public static Connection myConn=null;
	public static PreparedStatement myStmt=null;
	public static ResultSet myRs=null;
	public static ResultSet myRs2=null;
	public static int majorId;
	public static int minimumSat;
	public static int studentSat;
	public static int majorCheck;
	public static String majorDescription;
	public static int requiredSat;
	public static String fullname;
	public static int reportsat;
	public static Double reportgpa;
	public static int reportmajorId;


	

	public static void main(String[] args) throws SQLException {
		
		deleteStudent();
		fetchStudentTable(); System.out.println();
		enrollStudent(200, "Adam", "Zapel", 1200, 3.0);
		enrollStudent(210, "Graham", "Krakir", 500, 2.5);
		enrollStudent(220, "Ella", "Vader", 800, 3.0);
		enrollStudent(230, "Stanley", "Kupp", 1350, 3.3);
		enrollStudent(240, "Lou", "Zar", 950, 3.0);
		fetchStudentTable(); System.out.println();
		assignMajor("Finance",200);
		assignMajor("General Studies",210);
		assignMajor("Accounting",220);
		assignMajor("Engineering",230);
		assignMajor("Education",240);
		fetchStudentTable(); System.out.println();
		fetchScrTable(); System.out.println();
		enrollForClass(200, 10101, 20201, 20202, 20203);
		enrollForClass(210, 10101, 20201, 30101, 40311);
		enrollForClass(220, 10101, 20201, 20202, 60221);
		enrollForClass(230, 10101, 20201, 40311, 40312);
		enrollForClass(240, 10101, 60221, 60222, 60223);

		fetchScrTable(); System.out.println();
		report(220);
	}
	
	
	private static void display() throws SQLException{
		while(myRs.next()){
			String fName = myRs.getString("first_name");
			String lName = myRs.getString("last_name");
			Double gpa = myRs.getDouble("gpa");
			int sat = myRs.getInt("sat");
			int majorId = myRs.getInt("major_id");
			System.out.println(fName + " " + lName + " " + gpa + " " + sat + " MajorId: " + majorId);
		}
	}
	private static void report(int studentId) throws SQLException{
		try {
			makeConnection();
			
			myStmt = myConn.prepareStatement("select * from student where id = ?");
			myStmt.setInt(1,studentId);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
			fullname = myRs.getString("first_name") + " " + myRs.getString("last_name");
			reportsat = myRs.getInt("sat");
			reportgpa = myRs.getDouble("gpa");
			reportmajorId = myRs.getInt("major_id");
			}
			
			myStmt = myConn.prepareStatement("select * from major where id = ?");
			myStmt.setInt(1,majorId);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
			majorDescription = myRs.getString("description");
			requiredSat = myRs.getInt("req_sat");
			}
			
			
			
			System.out.println("Education System - Enrollment Process");
			System.out.println("=====================================");
			System.out.println();
			System.out.println("Enrolled " + fullname + " as a new student.");
			System.out.println(fullname + " has an SAT score of " + reportsat);
			System.out.println("Assigned " + fullname + " to the major " + majorDescription + " which requires a required SAT score of " + requiredSat);
			System.out.println("Enrolled " + fullname + " in the following four classes:");
	
			myStmt = myConn.prepareStatement("select class_id from student_class_relationship where student_id = ?");
			myStmt.setInt(1,studentId);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
				myStmt = myConn.prepareStatement("select subject from class where id = ?");
				myStmt.setInt(1,myRs.getInt("class_id"));
				myRs2=myStmt.executeQuery();
				while (myRs2.next()) {
					System.out.println(myRs2.getString("subject"));
				}
			}
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			if(myConn!=null)
				myConn.close();
			if(myStmt!=null)
				myStmt.close();
			if(myRs!=null)
				myRs.close();
		}
	}
		
	
	
	
	private static void fetchStudentTable() throws SQLException{
		
		try {
			makeConnection();
			myStmt = myConn.prepareStatement("select * from student where id > ?");
			myStmt.setInt(1,0);
			myRs=myStmt.executeQuery();
			display();
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			if(myConn!=null)
				myConn.close();
			if(myStmt!=null)
				myStmt.close();
			if(myRs!=null)
				myRs.close();
		}
	}
	private static void fetchScrTable() throws SQLException{
		
		try {
			makeConnection();
			myStmt = myConn.prepareStatement("select * from student_class_relationship where id > ?");
			myStmt.setInt(1,0);
			myRs=myStmt.executeQuery();	
			while (myRs.next()){
			System.out.println(" Student Id: " + myRs.getInt("student_id") + " Class ID: " + myRs.getInt("class_id"));
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			if(myConn!=null)
				myConn.close();
			if(myStmt!=null)
				myStmt.close();
			if(myRs!=null)
				myRs.close();
		}
	}
	
	
	private static void makeConnection() throws SQLException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("enrollment.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dbUrl = prop.getProperty("dburl");
		myConn = DriverManager.getConnection(dbUrl, user, password);
	}
	
	private static void deleteStudent() throws SQLException {
		try {
			makeConnection();
			myStmt = myConn.prepareStatement("delete from student_class_relationship where student_id > ?");
			myStmt.setInt(1,190);
			myStmt.executeUpdate();
			myStmt = myConn.prepareStatement("delete from student where id > ? ");
			myStmt.setInt(1,190);
			myStmt.executeUpdate();

			System.out.println();
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			close();
	}
}
	
	private static void assignMajor(int majorId, int studentId) throws SQLException {
		try {
			makeConnection();
			myStmt = myConn.prepareStatement("select req_sat from major where id = ?");
			myStmt.setInt(1,majorId);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				minimumSat = myRs.getInt("req_sat");
			}
			
			myStmt = myConn.prepareStatement("select sat from student where id = ?");
			myStmt.setInt(1,studentId);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				studentSat = myRs.getInt("sat");
			}
			if (studentSat >= minimumSat) {
				myStmt = myConn.prepareStatement("update student set major_id = ? where id = ?");
				myStmt.setInt(1,majorId);
				myStmt.setInt(2,studentId);
				myStmt.executeUpdate();
			}
			else {
				System.out.println("Error: Student SAT does not meet minimum SAT for this major");
			}
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			close();
	}
	}
	
	private static void assignMajor(String majorName, int studentId) throws SQLException {
		try {
			makeConnection();
			// Gets the major_id based on the major's description
			myStmt = myConn.prepareStatement("select id from major where description = ?");
			myStmt.setString(1,majorName);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
			majorId = myRs.getInt("id");
			}
			assignMajor(majorId, studentId);
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	
	private static void enrollStudent(int id, String firstname, String lastname, int sat, double gpa) throws SQLException {
		try {
			makeConnection();
			myStmt = myConn.prepareStatement("insert into student (id, first_name, last_name, sat, gpa, major_id) values (?, ?, ?, ?, ?, ?)");
			myStmt.setInt(1,id);
			myStmt.setString(2,firstname);
			myStmt.setString(3,lastname);
			myStmt.setInt(4,sat);	
			myStmt.setDouble(5,gpa);
			myStmt.setNull(6,java.sql.Types.INTEGER);
			myStmt.executeUpdate();
			System.out.println();
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	private static void enrollForClass(int studentId, int classId1, int classId2, int classId3, int classId4) throws SQLException {
		try {
			makeConnection();
//			myStmt = myConn.prepareStatement("select major_id from student where id = ?");
//			myStmt.setInt(1,studentId);
//			myRs = myStmt.executeQuery();
//			while (myRs.next()) {
//				majorCheck = myRs.getInt("major_id");
//			}
				
			myStmt = myConn.prepareStatement("insert into student_class_relationship (student_id, class_id) values (?, ?)");
			myStmt.setInt(1,studentId);
			myStmt.setInt(2,classId1);
			myStmt.executeUpdate();
			myStmt.setInt(1,studentId);
			myStmt.setInt(2,classId2);
			myStmt.executeUpdate();
			myStmt.setInt(1,studentId);
			myStmt.setInt(2,classId3);
			myStmt.executeUpdate();
			myStmt.setInt(1,studentId);
			myStmt.setInt(2,classId4);
			myStmt.executeUpdate();
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			close();
	}
	}
	
	public static void close() throws SQLException{
		if(myConn!=null)
			myConn.close();
		if(myStmt!=null)
			myStmt.close();
		if(myRs!=null)
			myRs.close();
	}
}