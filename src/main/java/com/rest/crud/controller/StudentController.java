package com.rest.crud.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.crud.model.Student;
import com.rest.crud.service.StudentService;

@RestController
public class StudentController {

	StudentService si;
	
	public StudentController(StudentService si) {
		this.si=si;
	}
	
	@PostMapping("/save")
	public Student saveData(@RequestBody Student s)
	{
		System.out.println("In controller:"+s.getName());
		si.saveData(s);
		return s;
	}
	@GetMapping("/getAllData")
	public List<Student> getAllData()
	{
		List<Student> list = si.getAlldata();// in-built method
		return list;
	}
	@DeleteMapping("/deleteByRollno/{rollno}")
	public String deleteData(@PathVariable int rollno) {
		si.deleteByRollno(rollno);
		return "Record deleted....!!"+rollno;
	}
	
	@GetMapping("/getSingleData/{rollno}")
	public Student getSingleData(@PathVariable int rollno) {
		 Student s = si.getByRollno(rollno);
		return s;
	}
	
	@PutMapping("/update/{rollno}")
	public Student update(@PathVariable int rollno,@RequestBody Student s)
	{
		si.update(rollno, s);
		return s;
	}
	
	@GetMapping("/login/{username}/{password}")
	public Student login(@PathVariable String username,@PathVariable String password)
	{
		Student st = si.loginCheck(username, password);
		return st;
	}
	
	@GetMapping("/pagingAndSorting/{pageNo}/{pageSize}/{sortBy}")
	public List<Student> pagingAndSorting(@PathVariable int pageNo,@PathVariable int pageSize,@PathVariable String sortBy)
	{
		List<Student> list = si.pagingAndSorting(pageNo, pageSize,sortBy);
		return list;
	}
	
	@PatchMapping("/updateName/{rollno}")
	public String updateName(@PathVariable int rollno,@RequestBody Student s)
	{
		si.updateName(rollno, s.getName());
		return "Record updated.....!!"+rollno;
	}
}
