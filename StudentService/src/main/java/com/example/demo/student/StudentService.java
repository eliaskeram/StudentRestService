package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@ComponentScan("com.example.student.StudentRepository")
public class StudentService {
	
	private final Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository studentRepository;	
	
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	public void addNewStudent(Student newStudent) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(newStudent.getEmail());
		
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("email of user has already taken!");
		}
		
		studentRepository.save(newStudent);
		//studentRepository.saveAndFlush(newStudent); save to database
		
		logger.info(newStudent.toString());
	}
	
	@Transactional
	public void updateStudent(Long studentID, String name, String email) {
		Student student = studentRepository.findById(studentID)
				                   .orElseThrow(() -> new IllegalStateException("Student with ID: " + studentID + " does not exist!"));
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student>  studentOptional = studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("email of user has already taken!");
			}
			student.setEmail(email);
		}
	}
	
	public void deleteStudent(Long studentID) {
		boolean existsID = studentRepository.existsById(studentID);
		if(!existsID) {
			throw new IllegalStateException("Student with ID: " + studentID + " does not exist!");
		}
		studentRepository.deleteById(studentID);
	}	
}
