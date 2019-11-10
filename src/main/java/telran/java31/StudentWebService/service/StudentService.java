package telran.java31.StudentWebService.service;

import java.util.List;

import telran.java31.StudentWebService.dto.ScoreDto;
import telran.java31.StudentWebService.dto.StudentDto;
import telran.java31.StudentWebService.dto.StudentResponseDto;
import telran.java31.StudentWebService.dto.StudentUpdateDto;

public interface StudentService {
	
boolean addStudent(StudentDto studentDto);

StudentResponseDto deleteStudent(Integer id);

StudentResponseDto findStudent(Integer id);

StudentDto editStudent(Integer id, StudentUpdateDto studentUpdateDto);
		
boolean addScore(Integer id, ScoreDto scoreDto);

List<StudentResponseDto> findStudentByName(String name);

long countByName(List<String> names);

List<StudentResponseDto> findSudentByExam(String exam,int minScore);
}