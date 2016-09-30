package com.ssa.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssa.entity.Student;
import com.ssa.service.IStudentService;

@CrossOrigin
@Controller
@RequestMapping("/")
public class StudentController {
	
	@Autowired
	private IStudentService studentService;
	
	@RequestMapping(value= "/student", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> student = studentService.getAllStudents();
        

        return new ResponseEntity<List<Student>>(student, HttpStatus.OK);
    }
	
	@RequestMapping(value= "/student/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
	
	@RequestMapping(value= "/student/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int studentId) {


        studentService.deleteStudent(studentId);
    }
	
	@RequestMapping("/about")
  public ModelAndView about(HttpServletRequest request, ModelAndView mv) {
      mv.setViewName("about");
      return mv;
  }
	
	@RequestMapping("/help")
  public ModelAndView help(HttpServletRequest request, ModelAndView mv) {
		HashMap<String, String> helpMessages = new HashMap<String, String>();
		helpMessages.put("1", "First Message");
		helpMessages.put("2", "Second Message");
		helpMessages.put("3", "Third Message");
		helpMessages.put("4", "Fourth Message");
		helpMessages.put("5", "Fifth Message");
		String str = request.getParameter("id");
	    mv.addObject("desc", helpMessages.get(str));
	    mv.addObject("id", str);
		
      mv.setViewName("help");
      return mv;
  }

}
