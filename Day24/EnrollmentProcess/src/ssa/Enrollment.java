package ssa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.sql.*;


public class Enrollment extends Student {
	
	public static Connection myConn=null;
	public static PreparedStatement myStmt=null;
	public static ResultSet myRs=null;
	public static ResultSet myRs2=null;
	public static ResultSet myRs3=null;
	public static int majorId;
	public static int minimumSat;
	public static int studentSat;
	public static int majorCheck;
	public static String majorDescription;
	public static int requiredSat;
	public static String fullname;
	public static int reportsat;
	public static int classId1;
	public static int classId2;
	public static int classId3;
	public static int classId4;
	public static boolean classId1isMajor = false;
	public static boolean classId2isMajor = false;
	public static boolean classId3isMajor = false;
	public static boolean classId4isMajor = false;
	public static Double reportgpa;
	public static int reportmajorId;
	public static ArrayList<Student> students= new ArrayList<Student>();
	public static String reportString;

	

	public static void main(String[] args) throws SQLException {


		students.add(new Student(200, "Adam", "Zapel", 1200, 3.0, 3));
		students.add(new Student(210, "Graham", "Krakir", 500, 2.5, 7));
		students.add(new Student(220, "Ella", "Vader", 800, 3.0, 2));
		students.add(new Student(230, "Stanley", "Kupp", 1350, 3.3, 5));
		students.add(new Student(240, "Lou", "Zar", 950, 3.0, 6));
		
		
		//Deletes all students/student-class relationships for student IDs over 190 
		deleteStudent();
		
		//Inserts entire array of students
		fetchStudentTable(); System.out.println();
		enrollStudent();
		
		//Inserts students one at a time
		enrollStudent(250, "Tommy", "Test", 1300, 2.0);
		enrollStudent(260, "Katie", "Kaplan", 1300, 2.0);
		fetchStudentTable(); System.out.println();
		
		//Assigns majors for entire array of students
		assignMajor();
		
		//Assigns majors one at a time based either on major description or ID
		assignMajor(250, "Math");
		assignMajor(260, 1);
		fetchStudentTable(); System.out.println();
		
		
		//Enrolls students in classes
		fetchScrTable(); System.out.println();
		
		//Failure for not meeting minimum number of major classes
		enrollForClass(200, "Education", 221, "Education", 222, "Education", 223, "Education", 251);
		//Success
		enrollForClass(200, "English", 101, "Math", 201, "Math", 202, "Math", 204);
		
		
		
//		enrollForClass(210, 10101, 20201, 30101, 40311);
//		enrollForClass(230, 10101, 20201, 40311, 40312);
//		enrollForClass(240, 10101, 60221, 60222, 60223);
		fetchScrTable(); System.out.println();
		
		//Displays report for an individual student 
		report(200);
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
			
			
			int i=0;
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
				i++;
				myStmt = myConn.prepareStatement("select * from class where id = ?");
				myStmt.setInt(1,myRs.getInt("class_id"));
				myRs2=myStmt.executeQuery();
				while (myRs2.next()) {
					System.out.print(i + ". " + myRs2.getString("subject") + " " + myRs2.getInt("section"));

				myStmt = myConn.prepareStatement("select major_id from major_class_relationship where class_id = ?");
				myStmt.setInt(1, myRs.getInt("class_id"));
				myRs3 = myStmt.executeQuery();
				reportString = " elective (not required for major)";
				while (myRs3.next()){
					if (myRs3.getInt("major_id") == reportmajorId) {
						reportString = " required for major";}
					}
				System.out.println(reportString);
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
	
	private static void assignMajor() throws SQLException {
		try {
			makeConnection();
			for (Student student: students) {
			myStmt = myConn.prepareStatement("select req_sat from major where id = ?");
			myStmt.setInt(1,student.getMajorId());
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				minimumSat = myRs.getInt("req_sat");
			}
			
			myStmt = myConn.prepareStatement("select sat from student where id = ?");
			myStmt.setInt(1,student.getId());
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				studentSat = myRs.getInt("sat");
			}
			if (studentSat >= minimumSat) {
				myStmt = myConn.prepareStatement("update student set major_id = ? where id = ?");
				myStmt.setInt(1,student.getMajorId());
				myStmt.setInt(2,student.getId());
				myStmt.executeUpdate();
			}
			else {
				System.out.println("Error: Student SAT does not meet minimum SAT for this major");
			}
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			close();
	}
	}
	
	private static void assignMajor(int studentId, int majorId) throws SQLException {
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
	
	private static void assignMajor(int studentId, String majorName) throws SQLException {
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
	
	
	private static void enrollStudent() throws SQLException {
		try {
			makeConnection();
			
			for (Student student: students) {
			myStmt = myConn.prepareStatement("insert into student (id, first_name, last_name, sat, gpa, major_id) values (?, ?, ?, ?, ?, ?)");
			myStmt.setInt(1,student.getId());
			myStmt.setString(2,student.getFirstName());
			myStmt.setString(3,student.getLastName());
			myStmt.setInt(4,student.getSat());	
			myStmt.setDouble(5,student.getGpa());
			myStmt.setNull(6,java.sql.Types.INTEGER);
			myStmt.executeUpdate();
			System.out.println();
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	private static void enrollForClass(int studentId, String subject1, int class1, String subject2, int class2, String subject3, int class3, String subject4, int class4) throws SQLException {
		int majorCounter = 0;
		try {
			makeConnection();
			myStmt = myConn.prepareStatement("select major_id from student where id = ?");
			myStmt.setInt(1,studentId);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				majorCheck = myRs.getInt("major_id");
			}
			myStmt = myConn.prepareStatement("select * from major_class_relationship where class_id = (select id from class where subject = ? and section = ?)");
			myStmt.setString(1,subject1);
			myStmt.setInt(2,class1);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				classId1 = myRs.getInt("class_id");
				if (myRs.getInt("major_id") == majorCheck) {
					majorCounter++;
				}
			}
			myStmt = myConn.prepareStatement("select * from major_class_relationship where class_id = (select id from class where subject = ? and section = ?)");
			myStmt.setString(1,subject2);
			myStmt.setInt(2,class2);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				classId2 = myRs.getInt("class_id");
				if (myRs.getInt("major_id") == majorCheck) {
					majorCounter++;
				}
			}
			myStmt = myConn.prepareStatement("select * from major_class_relationship where class_id = (select id from class where subject = ? and section = ?)");
			myStmt.setString(1,subject3);
			myStmt.setInt(2,class3);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				classId3 = myRs.getInt("class_id");
				if (myRs.getInt("major_id") == majorCheck) {
					majorCounter++;
				}
			}
			myStmt = myConn.prepareStatement("select * from major_class_relationship where class_id = (select id from class where subject = ? and section = ?)");
			myStmt.setString(1,subject4);
			myStmt.setInt(2,class4);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				classId4 = myRs.getInt("class_id");
				if (myRs.getInt("major_id") == majorCheck) {
					majorCounter++;
				}
			}
			
			if (majorCounter >= 2) {	
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
			}
			else {
				System.out.println("Error: Need at least 2 of these 4 classes to be in this student's major");
			}
			
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