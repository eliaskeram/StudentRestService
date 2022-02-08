package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
	//Controller Class
	@Autowired
	StudentService studentService;
	
	@GetMapping
	public List<Student> getStudents(){
		return studentService.getStudents();
	}
	
	@PostMapping
	public void registerStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	@PutMapping(path = "{studentID}")
	public void updateStudent(@PathVariable("studentID") Long studentID, 
			                  @RequestParam(required = false) String name,
			                  @RequestParam(required = false) String email) {
		studentService.updateStudent(studentID, name, email);
	}
	
	@DeleteMapping(path ="{studentID}")
	public void deleteStudent(@PathVariable("studentID") Long studentID) {
		studentService.deleteStudent(studentID);
	}
}
