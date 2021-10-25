package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public List<Student> findAll();
	
	@Query(value = "SELECT s FROM Student s WHERE email = ?1")
	Optional<Student> findStudentByEmail(String email);
}
