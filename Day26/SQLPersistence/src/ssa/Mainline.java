package ssa;

import java.sql.*;
import java.util.*;

public class Mainline {

	public static void main(String[] args) throws SQLException {
		
		try {
			
		Students students = new Students();
		students.delete(290);
			
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
		Student tomJones = new Student(290, "Tom", "Jones", 1300, 2.0);
		students.insert(tomJones);
		System.out.println(students.getById(290));
		
		System.out.println("******UPDATING NEW STUDENT*********");
		//Changes and then updates that new Student
		tomJones.setLastName("Bones");
		tomJones.setSat(1600);
		tomJones.setGpa(4.0);
		students.update(tomJones); 
		System.out.println(students.getById(290));
		
		System.out.println("******SELECTING GPAS BETWEEN 2.00 AND 2.99*********");
		//Selects by GPA after user enters minimum and maximum limits
		ArrayList<Student> gpaCheck = students.getByGPA(2.00,2.99);
		for (Student student: gpaCheck) {
			System.out.println(student);
		}
		
		Majors majors = new Majors();
		ArrayList<Major> allMajors = majors.getAllMajors();
		for (Major major: allMajors) {
			System.out.println(major);
		
		}
		
		Major Astronomy = new Major(9, "Astronomy");
		majors.insertMajor(Astronomy);
//		majors.assignMajor(tomJones, Astronomy);
		}catch(Exception ex){
			ex.printStackTrace();
}
	}

}
