package com.rest.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rest.crud.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

	
	public Student findByUsernameAndPassword(String username, String password);
	@Modifying          //to perform update/delete operations(Non-select operation)
	@Transactional      // To commit changes in database
	@Query("update Student set name=:nm where rollno=:rn")
	public void updateName(@Param("rn") int rollno, @Param("nm") String name);
	
}
