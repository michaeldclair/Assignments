package ssa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ssa.Major;

public class MajorMethods{
	
	public static mainline mainline = new mainline();

	
	public static void insertMajor(String aDescription, int aReqSat) {
		System.out.println("Inserting new Major");
		try {
			SessionFactory factory = mainline.getFactory();
			Session session = factory.getCurrentSession();
			Major newMajor = new Major(aDescription, aReqSat);

			session.beginTransaction();
			session.save(newMajor);
			session.getTransaction().commit();

		}catch(Exception ex){ex.printStackTrace();}
		finally {}
	}
	
//	public static void update() {
//		SessionFactory factory = mainline.getFactory();
//		try {
//
//			Session session = factory.getCurrentSession();
//			int majorId=11;
//			session.beginTransaction();
//			Major major = session.get(Major.class, majorId);
//			major.setReq_sat(1500);
//			session.getTransaction().commit();
//			
//			session = factory.getCurrentSession();
//			session.beginTransaction();
//			session.createQuery("update Major set req_sat = 900 where id = 10").executeUpdate();
//			session.getTransaction().commit();
//
//			
//		}catch(Exception ex){ex.printStackTrace();}
//		finally {}
//	}
//	
	public static void updateMajorDescription(String oldName, String newName) {
		System.out.println("Updating Major Description");
		SessionFactory factory = mainline.getFactory();
		try {
			
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("update Major set description = '" + newName + "' where description = '" + oldName +"'").executeUpdate();
			session.getTransaction().commit();

		}catch(Exception ex){ex.printStackTrace();}
		finally {}
	}
	
	public static void deleteMajor(String aMajorDescription) {
		System.out.println("Deleting Major");
		SessionFactory factory = mainline.getFactory();
		try {

			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Major where description = '" + aMajorDescription + "'").executeUpdate();
			session.getTransaction().commit();
		}catch(Exception ex){ex.printStackTrace();}
		finally {}
	}
	
	public static void displayAllMajors() {
		SessionFactory factory = mainline.getFactory();
		try {

			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Major> majors = session.createQuery("from Major").list();
			System.out.printf("%-4s %-20s %s", "ID", "Description", "Required SAT");
			System.out.println("");
			System.out.println("--------------------------------------------");
			for (Major major: majors) {
				display(major);
			}
			session.getTransaction().commit();
		}catch(Exception ex){ex.printStackTrace();}
		finally {}
	}
	
	public static void selectById(int aMajorId) {
		SessionFactory factory = mainline.getFactory();
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Major> majors = session.createQuery("from Major where id = " + aMajorId).list();
			System.out.printf("%-4s %-20s %s", "ID", "Description", "Required SAT");
			System.out.println("");
			System.out.println("--------------------------------------------");
			for (Major major: majors) {
				display(major);
			}
			session.getTransaction().commit();
		}catch(Exception ex){ex.printStackTrace();}
		finally {}
	}

	public static void display(Major major) {
		System.out.printf("%-4d %-20s %d", major.getId(),  major.getDescription(),  major.getReq_sat());
		System.out.println("");
	}
	

}

