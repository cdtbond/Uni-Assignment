package Tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Exceptions.NoLeaderException;
import Exceptions.PersonalityImbalanceException;
import Exceptions.RepeatedMemberException;
import Exceptions.StudentConflictException;
import Objects.Project.Project;
import Objects.Project.Team;
import Objects.Student.StudentInfo;
import Objects.Student.StudentInfoWithPrefs;

class TeamTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testComputeAverageSkillCompetency() throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IncorrectRankException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team = new Team(proj,sif1,sif2,sif3,sif4);
		team.computeAverageSkillCompetency();
		assertEquals(team.getAverageSkillCompetency(),0.0);
		
	}
	
	@Test
	void testComputeAverageSkillCompetencyNegative() throws IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,4,4,4,4,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,4,4,4,4,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team = new Team(proj,sif1,sif2,sif3,sif4);
		team.computeAverageSkillCompetency();
		assertEquals(8.0,team.getAverageSkillCompetency());
	}
	
	
	@Test
	void testComputeSkillShortfall() throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,4,3,2,1,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,4,3,2,1,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,4,3,2,1,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,4,3,2,1,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team = new Team(proj,sif1,sif2,sif3,sif4);
		assertEquals(0.0,team.getSkillShortfall());
	}
	@Test
	void testComputeSkillShortfallNegative() throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team = new Team(proj,sif1,sif2,sif3,sif4);
		assertEquals(10.0,team.getSkillShortfall());
	}

	@Test 
	void testRepeatedMember() throws IncorrectRankException, IncorrectRangeException, StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, IncorrectPersonalityTypeException, IncorrectGradeException {
		assertThrows(RepeatedMemberException.class,
		()->{
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		Team team = new Team(proj,sif1,sif2,sif3,sif1);
		});
	}
	
	@Test 
	void testRepeatedMemberNegative() throws IncorrectRankException, IncorrectRangeException, StudentConflictException, PersonalityImbalanceException, NoLeaderException, IncorrectPersonalityTypeException, IncorrectGradeException, NumberFormatException, IDOutOfRangeException {
		
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'C');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		try {
			Team team = new Team(proj,sif1,sif2,sif3,sif4);
			assertEquals(0,0);
		} catch (RepeatedMemberException e) {
			fail("This exception should not have been thrown");
		}
		
	}
	
	//Expect that the exception is thrown for a clash
	@Test 
	void testPersonalityClashCheck() throws IncorrectRankException, IncorrectRangeException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, IncorrectPersonalityTypeException, IncorrectGradeException {
		assertThrows(StudentConflictException.class,
		()->{
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A',"S2");
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'C');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team = new Team(proj,sif1,sif2,sif3,sif4);
		});
		
		
	}
	
	//Expect that the exception is not thrown
	@Test
	void testPersonalityClashCheckNegative() throws IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A',"S5","S6");
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B',"S5","S6");
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C',"S5");
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'C',"S5");
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		try {
			Team team = new Team(proj,sif1,sif2,sif3,sif4);
			assertEquals(0,0);
		} catch (StudentConflictException e) {
			// TODO Auto-generated catch block
			fail("This exception should not have been thrown");
		}
	}
	

	@Test
	void testPersonalityImbalance() {
		assertThrows(PersonalityImbalanceException.class,
		()->{
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'A');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'A');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'A');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team = new Team(proj,sif1,sif2,sif3,sif4);
		});
	}

	@Test
	void testPersonalityImbalanceNegative() throws IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, StudentConflictException, RepeatedMemberException, NoLeaderException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A',"S5","S6");
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B',"S5","S6");
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C',"S5");
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D',"S5");
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		try {
			Team team = new Team(proj,sif1,sif2,sif3,sif4);
			assertEquals(0,0);
		} catch (PersonalityImbalanceException e) {
			fail("This exception should not have been thrown");
		}
	}
	
	@Test
	void testNoLeader() {
		assertThrows(NoLeaderException.class,
		()->{
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'B');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'C');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'D');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'B');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team = new Team(proj,sif1,sif2,sif3,sif4);
		});
	}

	@Test
	void testNoLeaderNegative() throws IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, StudentConflictException, RepeatedMemberException, PersonalityImbalanceException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A',"S5","S6");
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B',"S5","S6");
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C',"S5");
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D',"S5");
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		try {
			Team team = new Team(proj,sif1,sif2,sif3,sif4);
			assertEquals(0,0);
		} catch (NoLeaderException e) {
			fail("This exception should not have been thrown");
		}
	}
	



	@Test
	void testComputeFirstAndSecondPrefs() throws IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,4,4,4,4,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,4,4,4,4,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr2","Pr1","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr2","Pr10","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr2","Pr10","Pr3","Pr4");
		Team team = new Team(proj,sif1,sif2,sif3,sif4);
		
		assertEquals(0.5,team.getFirstAndSecondPrefsPercent());
	}
	@Test
	void testComputeFirstAndSecondPrefsNegative() throws IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,4,4,4,4,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr10","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,4,4,4,4,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr10","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr10","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr10","Pr2","Pr3","Pr4");
		Team team = new Team(proj,sif1,sif2,sif3,sif4);
		team.computeAverageSkillCompetency();
		assertEquals(0,team.getFirstAndSecondPrefsPercent());
	}
	
}
