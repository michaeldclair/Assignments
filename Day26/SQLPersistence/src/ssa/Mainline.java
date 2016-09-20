package ssa;

import java.sql.*;
import java.util.*;

public class Mainline {

	public static void main(String[] args) throws SQLException {
		
		try {
		Students students = new Students();
			
		//Retrieves and displays a single student by ID
		System.out.println(students.getById(180));
		
		//Retrieves and displays all students
		ArrayList<Student> allStudents = students.getAll();
		for (Student student: allStudents) {
			System.out.println(student);
		}
		System.out.println("*********** STUDENTS ORDERED BY GPA DESC ***************");
		ArrayList<Student> allStudentsOrdered = students.getAllOrdered("gpa", "desc");
		for (Student student: allStudentsOrdered) {
			System.out.println(student);
		}

		System.out.println("******INSERTING NEW STUDENT*********");
		//Inserts new Student
		Student newStudent = new Student("Yvonne", "Yvoffe", 1300, 2.0);
		students.insert(newStudent);

		allStudents = students.getAll();
		for (Student student: allStudents) {
			System.out.println(student);
		}
		
		System.out.println("******UPDATING NEW STUDENT*********");
		//  Creates a Student, identifying the chosen student's ID here rather than in the method parameters
		//  so that the update method works on a Student object.
		Student brian = students.getById(190);
		brian.setLastName("Bigger");
		brian.setSat(1250);
		brian.setGpa(3.6);
		students.update(brian); 
		System.out.println(students.getById(190));
		
		System.out.println("******SELECTING GPAS BETWEEN 2.00 AND 2.99*********");
		//Selects by GPA after user enters minimum and maximum limits
		ArrayList<Student> gpaCheck = students.getByGPA(2.00,2.99);
		for (Student student: gpaCheck) {
			System.out.println(student);
		}
		
		

		System.out.println("**FAILS DELETING**");
		students.enrollForClass(brian, 10101);
		students.delete(brian.getId());
		System.out.println("**SUCCEEDS DELETING**");
		students.leaveClass(brian, 10101);
		students.delete(brian.getId());
		
		allStudents = students.getAll();
		for (Student student: allStudents) {
			System.out.println(student);
		}
		
		// Adds Brian back for more testing
		students.insert(brian);
		
		//Gets all majors
		Majors majors = new Majors();
		ArrayList<Major> allMajors = majors.getAllMajors();
		for (Major major: allMajors) {
			System.out.println(major);
		}
		//Creates new major
		Major Astronomy = new Major("Astronomy");
		majors.insertMajor(Astronomy);
		
		allMajors = majors.getAllMajors();
		for (Major major: allMajors) {
			System.out.println(major);
		}
		
		//Assigns a major to a student
		Major Math = majors.getById(4);
		majors.assignMajor(brian, Math);
		}catch(Exception ex){
			ex.printStackTrace();
}
	}

}
