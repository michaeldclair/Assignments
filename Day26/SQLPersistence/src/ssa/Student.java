package ssa;

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private int sat;
	private double gpa;
	private Major major;
	
	public Student(){};
	
	public Student(String aFirstName, String aLastName, int aSat, double aGpa) {
		this.firstName = aFirstName;
		this.lastName = aLastName;
		this.sat = aSat;
		this.gpa = aGpa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSat() {
		return sat;
	}

	public void setSat(int sat) {
		this.sat = sat;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public void display(Student student) {
		System.out.println(student.getId() + " " + student.getFirstName() + " " + student.getLastName() + "      " + student.getGpa() + " " + student.getSat());
	}
	
	@Override
	public String toString() {
		String msg = String.format("%3d %-20s %4.2f %4d",
				this.id,
				this.firstName + " " + this.lastName,
				this.gpa,
				this.sat);
		return msg;
	}
	
	
}
