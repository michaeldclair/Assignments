package ssa;

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private int sat;
	private double gpa;
	private int majorId;
	
	public Student(){};
	
	public Student(int aId, String aFirstName, String aLastName, int aSat, double aGpa, int aMajorId) {
		this.id = aId;
		this.firstName = aFirstName;
		this.lastName = aLastName;
		this.sat = aSat;
		this.gpa = aGpa;
		this.majorId = aMajorId;
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

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	
	
}
