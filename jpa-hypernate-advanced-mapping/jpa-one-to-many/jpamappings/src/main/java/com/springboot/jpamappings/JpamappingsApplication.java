package com.springboot.jpamappings;

import com.springboot.jpamappings.entity.Course;
import com.springboot.jpamappings.entity.Instructor;
import com.springboot.jpamappings.entity.InstructorDetail;
import com.springboot.jpamappings.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.jpamappings.dao.AppDAO;

import java.util.List;

@SpringBootApplication
public class JpamappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpamappingsApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);
//			createInstructorWithCourses(appDAO);
//			findInstructorWithCourses(appDAO);
//			findCoursesForInstructor(appDAO);
//			findInstructorWithCoursesJoinFetch(appDAO);
//			updateInstructor(appDAO);
//			updateCourse(appDAO);
//			deleteCourse(appDAO);
//			createCourseAndReviews(appDAO);
//			retrieveCourseAndReviews(appDAO);
			deleteCourseAndReviews(appDAO);
		};



		


	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId= 11;
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		//get course
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
		System.out.println(tempCourse);
		System.out.println("Reviews : " + tempCourse.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		//create course
		Course tempCourse = new Course("Pacman - how To score 1 million points");
		//add some reviews
		tempCourse.addReview(new Review("Great course..!"));
		tempCourse.addReview(new Review("Good course..!"));
		tempCourse.addReview(new Review("Perfect course..!"));
		//save course
		appDAO.save(tempCourse);
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		appDAO.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		Course tempCourse = appDAO.findCourseById(theId);
		tempCourse.setTitle("Enjoy");
		appDAO.update(tempCourse);
		System.out.println(tempCourse.getTitle());
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		Instructor tempInstructor = appDAO.findInstructorById(id);
		//update the instructor
		tempInstructor.setLastName("Alshourafa");
		appDAO.update(tempInstructor);
		System.out.println(tempInstructor);

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		//find instructor
		int theId = 1;
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("Instructor courses: " + tempInstructor.getCourses());
		System.out.println("InstructorDetail" + tempInstructor.getInstructorDetail());
		System.out.println("Done!");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		//find instructor
		Instructor tempInstructor = appDAO.findInstructorById(id);
		//find courses for that instructor
		List<Course> courses = appDAO.findCourseByInstructorId(id);
		//associate courses to instructor
		tempInstructor.setCourses(courses);
		System.out.println("The associate courses : "+tempInstructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId= 1;
		System.out.println("Finding instructor id:"+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor:"+tempInstructor);
		System.out.println("the associated courses:" +tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor  = new Instructor("Ahmed" , "Yusuf" , "Ahmed@gmail.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.ahmed.com" , "Code");
		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
//		create some courses
		Course tempCourse1= new Course("Air Guitar");
		Course tempCourse2 = new Course("The Pinball Masterclass");
//		add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
//		saving
		System.out.println("Saving ...."+ tempInstructor);
		System.out.println("Courses :" + tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 3;
		appDAO.deleteInstructorDetailById(id);
		System.out.println("done");

	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 1;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println("Temp Instructor Detail : "+ tempInstructorDetail);
		//print associated instructor
		System.out.println("the associated instructor :" + tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("deleteing instructor");
		appDAO.deleteInstructorById(theId);
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+ theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println(tempInstructor.toString());
		System.out.println("Instructor Details : "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO){
		Instructor tempInstructor  = new Instructor("Anas" , "Yusuf" , "anas@gmail.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.anas.com" , "Code");
		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		//save instructor
		System.out.println("Saving Instructor");
		appDAO.save(tempInstructor);
	}

}
