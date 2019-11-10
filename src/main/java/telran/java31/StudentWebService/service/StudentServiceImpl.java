package telran.java31.StudentWebService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java31.StudentWebService.dao.StudentRepository;
import telran.java31.StudentWebService.dto.ScoreDto;
import telran.java31.StudentWebService.dto.StudentDto;
import telran.java31.StudentWebService.dto.StudentNotFoundException;
import telran.java31.StudentWebService.dto.StudentResponseDto;
import telran.java31.StudentWebService.dto.StudentUpdateDto;
import telran.java31.StudentWebService.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
@Autowired
	StudentRepository studentRepository;
	
	@Override
	public boolean addStudent(StudentDto studentDto) {
	if(!studentRepository.existsById(studentDto.getId()))	{
		Student student = new Student(studentDto.getId(),
				studentDto.getName(),studentDto.getPassword());
		
		studentRepository.save(student);
		return true;
	}else {
		
		return false;
	}
	}
	@Override
	public StudentResponseDto deleteStudent(Integer id) {
	Student student = studentRepository.findById(id)
			.orElseThrow(() -> new StudentNotFoundException(id));
	studentRepository.delete(student);
		return  studentToStudentResponseDto(student);
		
	}

	@Override
	public StudentResponseDto findStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id)); 
		
			return studentToStudentResponseDto(student);
	}

		
	
	private StudentResponseDto studentToStudentResponseDto(Student student) {
		
		return new StudentResponseDto(student.getId(),student.getName(),student.getScores());
	}

	@Override
	public StudentDto editStudent(Integer id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
		
		if (studentUpdateDto.getName() != null) {
			student.setName(studentUpdateDto.getName());
		}
		if (studentUpdateDto.getPassword() != null) {
			student.setPassword(studentUpdateDto.getPassword());
		}
		studentRepository.save(student);
		return studentToStudentDto(student);
		
	}

	

	private StudentDto studentToStudentDto(Student student) {
		return StudentDto.builder()
				.id(student.getId())
				.name(student.getName())
				.password(student.getPassword())
				.build();
	}
	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
		
		boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore()); 
	studentRepository.save(student);
	return res;
		
		
	}
	@Override
	public List<StudentResponseDto> findStudentByName(String name) {
		
		return studentRepository.findByName(name)
				.map(this::studentToStudentResponseDto)
				.collect(Collectors.toList());
	}
	@Override
	public long countByName(List<String> names) {
		
		return studentRepository.countByNameIn(names);
	}
	@Override
	public List<StudentResponseDto> findSudentByExam(String exam, int minScore) {
	
		return studentRepository.findByExamScore("scores."+exam, minScore)
				.map(this::studentToStudentResponseDto)
				.collect(Collectors.toList());
	}

}
