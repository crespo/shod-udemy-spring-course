package com.raulcrespo.cruddemo;

import com.raulcrespo.cruddemo.dao.StudentDAO;
import com.raulcrespo.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner -> {
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			getAllStudents(studentDAO);
		};
	}

	private void getAllStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();

		for(Student s : students) {
			System.out.println(s);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student newStudent = new Student("Loo", "Crespo", "Loo@raulcrespo.com");

		System.out.println("Saving new student...");
		studentDAO.save(newStudent);

		int id = newStudent.getId();
		System.out.println("Saved student. Generated ID: " + id);

		System.out.println("Retrieving student by id. ID: " + id);
		Student retrieveStudent = studentDAO.findById(id);

		System.out.println("Retrieve result: " + retrieveStudent);


	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 new student objects...");
		Student newStudent1 = new Student("Foo", "Crespo", "foo@raulcrespo.com");
		Student newStudent2 = new Student("Doo", "Crespo", "doo@raulcrespo.com");
		Student newStudent3 = new Student("Moo", "Crespo", "moo@raulcrespo.com");

		System.out.println("Saving new students...");
		studentDAO.save(newStudent1);
		studentDAO.save(newStudent2);
		studentDAO.save(newStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student newStudent = new Student("Raul", "Crespo", "oi@raulcrespo.com");

		System.out.println("Saving new student...");
		studentDAO.save(newStudent);

		System.out.println("Saved student. Generated ID: " + newStudent.getId());
	}
}
