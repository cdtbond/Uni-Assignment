package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Objects.Student.Student;
import Objects.Student.StudentInfo;
import Objects.Student.StudentInfoWithPrefs;

class StudentInfoWithPrefsTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testStudentInfoWithPrefsStudentInfoStringStringStringString() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfoWithPrefs stud = new StudentInfoWithPrefs(new StudentInfo(new Student(1, 2, 4, 4, 4),'A',"S2","S3"),"Pr1","Pr2","Pr3","Pr4");
		assertEquals("S1",stud.getID());
	}

	@Test
	void testCalculateAverageGrade() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfoWithPrefs stud = new StudentInfoWithPrefs(new StudentInfo(new Student(1, 4, 4, 4, 4),'A',"S2","S3"),"Pr1","Pr2","Pr3","Pr4");
		stud.calculateAverageGrade();
		assertEquals(4.0,stud.getAverageGrade(),0.1);
	}

	@Test
	void testGetPreferenceOne() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfoWithPrefs stud = new StudentInfoWithPrefs(new StudentInfo(new Student(1, 4, 4, 4, 4),'A',"S2","S3"),"Pr1","Pr2","Pr3","Pr4");

		assertEquals("Pr1",stud.getPreferenceOne());
	}

	@Test
	void testGetPreferenceTwo() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfoWithPrefs stud = new StudentInfoWithPrefs(new StudentInfo(new Student(1, 4, 4, 4, 4),'A',"S2","S3"),"Pr1","Pr2","Pr3","Pr4");

		assertEquals("Pr2",stud.getPreferenceTwo());
	}

	@Test
	void testGetPreferenceThree() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfoWithPrefs stud = new StudentInfoWithPrefs(new StudentInfo(new Student(1, 4, 4, 4, 4),'A',"S2","S3"),"Pr1","Pr2","Pr3","Pr4");

		assertEquals("Pr3",stud.getPreferenceThree());
	}

	@Test
	void testGetPreferenceFour() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfoWithPrefs stud = new StudentInfoWithPrefs(new StudentInfo(new Student(1, 4, 4, 4, 4),'A',"S2","S3"),"Pr1","Pr2","Pr3","Pr4");

		assertEquals("Pr4",stud.getPreferenceFour());
	}

	@Test
	void testSetObjectiveValue() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfoWithPrefs stud = new StudentInfoWithPrefs(new StudentInfo(new Student(1, 4, 4, 4, 4),'A',"S2","S3"),"Pr1","Pr2","Pr3","Pr4");
		stud.setObjectiveValue("Pr1", 0.3);
		assertEquals(0.3,stud.getObjectiveValue("Pr1"));
	}

	@Test
	void testSetAverageGradeDouble() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfoWithPrefs stud = new StudentInfoWithPrefs(new StudentInfo(new Student(1, 0, 0, 0, 0),'A',"S2","S3"),"Pr1","Pr2","Pr3","Pr4");
		stud.setAverageGrade(0.2);
		assertEquals(0.2,stud.getAverageGrade(),0.1);
	}

}
