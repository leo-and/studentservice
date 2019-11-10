package telran.java31.StudentWebService.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java31.StudentWebService.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{
	Stream<Student> findByName(String name);

	long countByNameIn(List<String> names);
@Query("{'?0':{'$gte':?1}}")
Stream<Student> findByExamScore(String exam,int score);

}
