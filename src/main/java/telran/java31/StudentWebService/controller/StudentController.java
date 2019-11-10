package telran.java31.StudentWebService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java31.StudentWebService.dto.ScoreDto;
import telran.java31.StudentWebService.dto.StudentDto;
import telran.java31.StudentWebService.dto.StudentResponseDto;
import telran.java31.StudentWebService.dto.StudentUpdateDto;
import telran.java31.StudentWebService.service.StudentService;

@RestController
public class StudentController {
	
@Autowired	
StudentService studentService;


@PostMapping("/student")
public boolean addStudent(@RequestBody StudentDto studentDto){

		return studentService.addStudent(studentDto);
}

@DeleteMapping("/student/{id}")
public StudentResponseDto removeStudent(@PathVariable int id){
	return studentService.deleteStudent( id);
}

@GetMapping("/student/{id}")
public StudentResponseDto findStudent(@PathVariable int id){
	return studentService.findStudent(id);
}

@PutMapping("/student/{id}")
public StudentDto editStudent(@PathVariable int id,@RequestBody StudentUpdateDto
		studentUpdateDto){
	return studentService.editStudent(id, studentUpdateDto);
}

@PutMapping("/score/student/{id}")
public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto){
	return studentService.addScore(id, scoreDto);
}
@GetMapping("/students/name/{name}")
public List<StudentResponseDto > findStudentByName(@PathVariable String name)
{
	return studentService.findStudentByName(name);
}
@GetMapping("/students/names")
public long countStudentByNames(@RequestBody List<String> names) {
	return studentService.countByName(names);
}
@GetMapping("/student/score/{exam}/min/{score}")
public List<StudentResponseDto> findStudentByExamScore(@PathVariable String exam,@PathVariable int score){
	return studentService.findSudentByExam(exam, score);
}
}
