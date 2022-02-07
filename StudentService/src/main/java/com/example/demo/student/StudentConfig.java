package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	//Config Class
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return new CommandLineRunner() {
		
		@Override
		public void run(String... args) throws Exception {
			//save to database
			Student ilias = new Student("ilias", "ilias@gmail.com", LocalDate.of(1982, Month.NOVEMBER, 2));
			Student evi = new Student("evi", "evi@gmail.com", LocalDate.of(1986, Month.AUGUST, 25));
			repository.saveAll(List.of(ilias, evi));
			//retrieve from database
//			List<Student> listOfStudents1 = new ArrayList<>();
//			for(Student student1: repository.findAll()) {
//				Student student = new Student(student1.getId(), student1.getName(), student1.getEmail(), student1.getDob()); 
//				listOfStudents1.add(student);
//			}
//			repository.saveAll(listOfStudents1);
		}
	   };
	}
	
}
