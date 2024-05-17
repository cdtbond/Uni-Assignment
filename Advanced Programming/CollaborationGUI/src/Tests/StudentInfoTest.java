package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.RepeatedMemberException;
import Objects.Student.Student;
import Objects.Student.StudentInfo;
import Objects.Supporting.PersonalityType;

class StudentInfoTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testStudentInfoStudentCharStringString() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfo stud = new StudentInfo(new Student(1, 2, 4, 4, 4),'A',"S2","S3");
		assertEquals("S1",stud.getID());
	}



	@Test
	void testDifficultStudentsToString() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfo stud = new StudentInfo(new Student(1, 2, 4, 4, 4),'A',"S2","S3");
		assertEquals("S2 S3 ",stud.difficultStudentsToString());
	}

	@Test
	void testIncorrectPersonalityTypeException() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		assertThrows(IncorrectPersonalityTypeException.class,
		()->{
		StudentInfo stud = new StudentInfo(new Student(1, 2, 4, 4, 4),'F',"S2","S3");
		});
	}
	
	@Test
	void testSetPersonality() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfo stud = new StudentInfo(new Student(1, 2, 4, 4, 4),'A',"S2","S3");
		stud.setPersonality(new PersonalityType('B'));
		assertEquals('B',stud.getPersonality().getPersonalityId());
	}

	@Test
	void testCompareDifficultStudents() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfo stud = new StudentInfo(new Student(1, 2, 4, 4, 4),'A',"S2","S3");
		assertTrue(stud.compareDifficultStudents("S2"));
	}

	@Test
	void testSetDifficultStudents() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfo stud = new StudentInfo(new Student(1, 2, 4, 4, 4),'A',"S2");
		ArrayList<String> diff = new ArrayList<String>();
		diff.add("S5");
		stud.setDifficultStudents(diff);
		assertTrue(stud.compareDifficultStudents("S5"));;
	}

	@Test
	void testIsLeader() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfo stud = new StudentInfo(new Student(1, 2, 4, 4, 4),'A',"S2");
		assertTrue(stud.isLeader());;
	}

	@Test
	void testIsSocializer() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfo stud = new StudentInfo(new Student(1, 2, 4, 4, 4),'B',"S2");
		assertTrue(stud.isSocializer());
	}

	@Test
	void testIsThinker() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfo stud = new StudentInfo(new Student(1, 2, 4, 4, 4),'C',"S2");
		assertTrue(stud.isThinker());
	}

	@Test
	void testIsSupporter() throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfo stud = new StudentInfo(new Student(1, 2, 4, 4, 4),'D',"S2");
		assertTrue(stud.isSupporter());
	}

}
