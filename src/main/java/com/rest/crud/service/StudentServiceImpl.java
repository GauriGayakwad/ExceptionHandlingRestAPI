package com.rest.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rest.crud.exception.ResourceNotFoundException;
import com.rest.crud.model.Student;
import com.rest.crud.repository.StudentRepository;
@Service
public class StudentServiceImpl  implements StudentService{

	//@Autowired
	StudentRepository sr;
	
	public StudentServiceImpl(StudentRepository sr) {
		this.sr=sr;
	}
	
	
	public void saveData(Student s) {
		
		System.out.println("In Service:"+ s.getName());
		sr.save(s);
	}

	@Override
	public List<Student> getAlldata() {
		
		List<Student> list = (List<Student>) sr.findAll();

		return list;
	}


	@Override
	public void deleteByRollno(Integer rollno) {
		
		sr.deleteById(rollno);//delete data from database
		
	}


	@Override
	public Student getByRollno(Integer rollno) {
	
		//Student st= sr.findById(rollno).get();//return single data from db
		//return st;
		Optional<Student> stu = sr.findById(rollno);
		if(stu.isPresent()) {
			return stu.get();
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found.....!! : "+rollno);
		}
	}


	@Override
	public void update(int rollno, Student s) {
		Optional<Student> existing = sr.findById(rollno);
		if(existing.isPresent())
		{
			    existing.get().setName(s.getName());
				existing.get().setEmail(s.getEmail());
				existing.get().setContact(s.getContact());
				existing.get().setAddress(s.getAddress());
				existing.get().setUsername(s.getUsername());
				existing.get().setPassword(s.getPassword());
				sr.save(existing.get());
				
		}else {
			throw new ResourceNotFoundException("Resource Not Found....!!: "+rollno);
		}
//		existing.setName(s.getName());
//		existing.setEmail(s.getEmail());
//		existing.setContact(s.getContact());
//		existing.setAddress(s.getAddress());
//		existing.setUsername(s.getUsername());
//		existing.setPassword(s.getPassword());
		
		//sr.save(existing);  //update data in database
		
	}


	@Override
	public Student loginCheck(String username, String Password) {
		Student st = sr.findByUsernameAndPassword(username, Password);
		return st;
	}


	@Override
	public List<Student> pagingAndSorting(int pageNo, int pageSize,String sortBy) {
		Pageable pageable =PageRequest.of(pageNo, pageSize,Sort.by(Sort.Direction.DESC,sortBy));
		List<Student> list = sr.findAll(pageable).getContent();
		return list;
	}


	@Override
	public void updateName(int rollno, String name) {
		sr.updateName(rollno, name);
		
	}



}
