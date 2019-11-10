package telran.java31.StudentWebService.configuration;

import org.springframework.context.annotation.Configuration;

import telran.java31.StudentWebService.service.StudentService;
import telran.java31.StudentWebService.service.StudentServiceImpl;

@Configuration
public abstract class StudentConfiguration {

	//@Bean
		public StudentService getStudentService() {
			return new StudentServiceImpl();
		}
}
