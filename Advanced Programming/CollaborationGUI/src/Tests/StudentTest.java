package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectRangeException;
import Objects.Student.Student;
import Objects.Supporting.Skills;

class StudentTest {
	Student s1;
	
	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	void testAddPrefix() throws NumberFormatException, IncorrectRangeException, IDOutOfRangeException {
		s1 = new Student(1, 2, 4, 4, 4);
		assertEquals(s1.getID(),"S1");
	}


	@Test
	void testStudentStringSkills() throws IncorrectRangeException {
		s1 = new Student("S1",new Skills(2, 4, 4, 4));
		assertEquals(s1.getID(),"S1");
	}

	@Test
	void testStudentStringStringStringStringString() throws NumberFormatException, IncorrectRangeException {
		s1 = new Student("S1", "P-2", "A-4", "N-4", "W-4");
		assertEquals(s1.getID(),"S1");
	}


	@Test
	void testSetAverageGrade() throws NumberFormatException, IncorrectRangeException, IDOutOfRangeException {
		s1 = new Student(1, 2, 4, 4, 4);
		s1.setAverageGrade(1,1, 1, 1);
		assertEquals(1.0,s1.getAverageGrade(),0.1);
	}

	@Test
	void testGetSkills() throws NumberFormatException, IncorrectRangeException, IDOutOfRangeException {
		s1 = new Student(1, 2, 4, 4, 4);
		assertEquals(s1.getSkills().getAnSkill(),4.0,0.1);
	}

	@Test
	void testGetAnSkill() throws NumberFormatException, IncorrectRangeException, IDOutOfRangeException {
		s1 = new Student(1, 2, 4, 4, 4);
		assertEquals(s1.getAnSkill(),4.0,0.1);
	}

	@Test
	void testGetNetSkill() throws NumberFormatException, IncorrectRangeException, IDOutOfRangeException {
		s1 = new Student(1, 2, 4, 4, 4);
		assertEquals(s1.getNetSkill(),4.0,0.1);
	}

	@Test
	void testGetProgSkill() throws NumberFormatException, IncorrectRangeException, IDOutOfRangeException {
		s1 = new Student(1, 2, 4, 4, 4);
		assertEquals(s1.getProgSkill(),2.0,0.1);
	}

	@Test
	void testGetWebSkill() throws NumberFormatException, IncorrectRangeException, IDOutOfRangeException {
		s1 = new Student(1, 2, 4, 4, 4);
		assertEquals(s1.getWebSkill(),4.0,0.1);
	}

}
