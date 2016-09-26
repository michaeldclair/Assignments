package ssa;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ssa.MajorMethods;



public class mainline {
	
	public static SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Major.class)
			.buildSessionFactory();

	public static void main(String[] args) {
		
		MajorMethods majormethod = new MajorMethods();
		
		majormethod.insertMajor("Astronomy", 1450);
		majormethod.updateMajorDescription("Education", "Education Studies");
		majormethod.deleteMajor("General Studies");
		majormethod.displayAllMajors();
		majormethod.selectById(3);
		
		factory.close();	
	}
	
	public static SessionFactory getFactory () {
		return factory;
	}
}
