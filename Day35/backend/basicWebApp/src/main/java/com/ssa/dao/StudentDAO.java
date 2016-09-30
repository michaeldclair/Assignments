package com.ssa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.entity.Student;

@Transactional
@Repository
public class StudentDAO implements IStudentDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getAllStudents() {
        String hql = "FROM Student as s ORDER BY s.id";
        return (List<Student>) hibernateTemplate.find(hql);
    }

	@Override
	public Student getStudentById(int studentId) {
		 String hql = "FROM Student where id = " + studentId;
		
	     return (Student) hibernateTemplate.find(hql).get(0);
	}

	@Override
	public boolean addStudent(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStudent(int studentId) {
		Student student = getStudentById(studentId);
	     hibernateTemplate.delete(student);
	}
}
