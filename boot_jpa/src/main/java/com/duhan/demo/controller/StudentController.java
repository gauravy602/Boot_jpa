package com.duhan.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.duhan.demo.dao.StudentRepo;
import com.duhan.demo.model.CheckStatus;
import com.duhan.demo.model.Student;
import com.duhan.demo.model.Student_Response;


@RestController
public class StudentController {
	
	@Autowired
	StudentRepo Stud_repo;
	private int count;
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
    @PostMapping("/student")
	public Map<String, Object> addStudent(Student s) {
    	Student_Response res = new Student_Response();
    	Map<String,Object> out =new HashMap<String,Object>();
    	  // return res.Return_WrongInput(s);
    	  boolean check = res.checkInput(s);
    	  if(!check) {
    		  out =  res.Return_WrongInput(s);
    		  return out;
    	  }
    	CheckStatus c = new CheckStatus();
    	count = (int)Stud_repo.count();
    	count++;
    	s.setSid(count);
    	boolean b = c.check(s);
    	if(b) {
    		Stud_repo.save(s);
    	}
    	else {
    		s.setWife_name("");
    		s.setWife_age("");
    		Stud_repo.save(s);
    	}
    	
        out  = res.getResponse(s);
		return out;
	}
     
    @DeleteMapping("/student/{aid}")
    public String deleteAlien(@PathVariable int aid) {
    	Student s = Stud_repo.getOne(aid);
    	Stud_repo.delete(s);
    	return "deleted";
    } 
    
    @RequestMapping("/students")
    public List<Student> getAliens() {
//    	HiberApi result = new HiberApi();
//    	return result.getResponse();
           return Stud_repo.findAll(); 
    }
    
    @RequestMapping("/alien/{aid}")
	public java.util.Optional<Student> getAlien(@PathVariable("aid") int aid) {
    	
		return Stud_repo.findById(aid);
	}   
}

	

