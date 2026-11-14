package com.rest.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.rest.crud.model.Student;

public interface StudentService {
		
		public void saveData(Student s);
		
		public  List<Student> getAlldata();
		
		public void deleteByRollno(Integer rollno);
		
		public Student getByRollno(Integer rollno);

		public void update(int rollno, Student s);
		
		public Student loginCheck(String username,String Password);
		
		public List<Student> pagingAndSorting(int pageNo, int pageSize,String sortBy);

		public void updateName(int rollno, String name);
}
