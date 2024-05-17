package Tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Data.HashMaps;
import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Exceptions.InvalidMemberException;
import Exceptions.NoLeaderException;
import Exceptions.PersonalityImbalanceException;
import Exceptions.RepeatedMemberException;
import Exceptions.StudentConflictException;
import Objects.Project.Project;
import Objects.Student.StudentInfo;
import Objects.Student.StudentInfoWithPrefs;
import SharedValues.StandardDeviationValues;
import Objects.Project.Team;

class HashMapsTest {
	HashMaps hm; 
	StandardDeviationValues sd;
	@BeforeEach
	void setUp() throws Exception { 
		hm = HashMaps.getSingleton();
		sd = StandardDeviationValues.getSingleton();
		hm.getTeams().clear();
	}
	
	@Test
	void testInvalidMember() throws IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException {
		assertThrows(InvalidMemberException.class,
		()->{
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team1 = new Team(proj,sif1,sif2,sif3,sif4);
		
		
		hm.addTeam(team1);
		
		proj = new Project("Pr2","Test Project","Only a test","Own2",4,3,2,1);
		StudentInfo si5 = new StudentInfo(5,0,0,0,0,'A');
		StudentInfoWithPrefs sif5 = new StudentInfoWithPrefs(si5,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si6 = new StudentInfo(6,0,0,0,0,'B');
		StudentInfoWithPrefs sif6 = new StudentInfoWithPrefs(si6,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si7 = new StudentInfo(7,0,0,0,0,'C');
		StudentInfoWithPrefs sif7 = new StudentInfoWithPrefs(si7,"Pr1","Pr2","Pr3","Pr4");
		
		//repeatedly add student 1
		Team team2 = new Team(proj,sif1,sif5,sif6,sif7);
		hm.addTeam(team2);
		
		});
		
	}

	@Test
	void testInvalidMemberNegative() throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, InvalidMemberException, IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(5,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team1 = new Team(proj,sif1,sif2,sif3,sif4);
		
		hm.addTeam(team1);
		
		proj = new Project("Pr2","Test Project","Only a test","Own2",4,3,2,1);
		StudentInfo si5 = new StudentInfo(5,0,0,0,0,'A');
		StudentInfoWithPrefs sif5 = new StudentInfoWithPrefs(si5,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si6 = new StudentInfo(6,0,0,0,0,'B');
		StudentInfoWithPrefs sif6 = new StudentInfoWithPrefs(si6,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si7 = new StudentInfo(7,0,0,0,0,'C');
		StudentInfoWithPrefs sif7 = new StudentInfoWithPrefs(si7,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si8 = new StudentInfo(8,0,0,0,0,'C');
		StudentInfoWithPrefs sif8 = new StudentInfoWithPrefs(si8,"Pr1","Pr2","Pr3","Pr4");
		
		//repeatedly add student 1
		Team team2 = new Team(proj,sif5,sif6,sif7,sif8);
		try {
			hm.addTeam(team2);
			assertEquals(0,0);
		} catch (InvalidMemberException e) {
			fail("This exception should not have been thrown");
		}
	}
	

	@Test
	void testComputeSkillCompetency() throws InvalidMemberException, StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IncorrectRankException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team1 = new Team(proj,sif1,sif2,sif3,sif4);
		
		hm.addTeam(team1);
		
		proj = new Project("Pr2","Test Project","Only a test","Own2",4,3,2,1);
		StudentInfo si5 = new StudentInfo(5,0,0,0,0,'A');
		StudentInfoWithPrefs sif5 = new StudentInfoWithPrefs(si5,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si6 = new StudentInfo(6,0,0,0,0,'B');
		StudentInfoWithPrefs sif6 = new StudentInfoWithPrefs(si6,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si7 = new StudentInfo(7,0,0,0,0,'C');
		StudentInfoWithPrefs sif7 = new StudentInfoWithPrefs(si7,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si8 = new StudentInfo(8,0,0,0,0,'C');
		StudentInfoWithPrefs sif8 = new StudentInfoWithPrefs(si8,"Pr1","Pr2","Pr3","Pr4");
		
		//repeatedly add student 1
		Team team2 = new Team(proj,sif5,sif6,sif7,sif8);
		hm.addTeam(team2);
		hm.computeSdSkillCompetency();
		
		//Evaluates to zero
		
		assertEquals(0.0,sd.getSdSkillCompetency());

	}
	
	@Test
	void testComputeSkillCompetencyNegative() throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, InvalidMemberException, IncorrectRankException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team1 = new Team(proj,sif1,sif2,sif3,sif4);
		

		hm.addTeam(team1);
		
		proj = new Project("Pr2","Test Project","Only a test","Own2",4,3,2,1);
		StudentInfo si5 = new StudentInfo(5,4,4,4,4,'A');
		StudentInfoWithPrefs sif5 = new StudentInfoWithPrefs(si5,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si6 = new StudentInfo(6,4,4,4,4,'B');
		StudentInfoWithPrefs sif6 = new StudentInfoWithPrefs(si6,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si7 = new StudentInfo(7,4,4,4,4,'C');
		StudentInfoWithPrefs sif7 = new StudentInfoWithPrefs(si7,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si8 = new StudentInfo(8,4,4,4,4,'C');
		StudentInfoWithPrefs sif8 = new StudentInfoWithPrefs(si8,"Pr1","Pr2","Pr3","Pr4");
		
		Team team2 = new Team(proj,sif5,sif6,sif7,sif8);
		hm.addTeam(team2);
		hm.computeSdSkillCompetency();
		
		//16 vs 0
		//Evaluates to eight
		
		assertEquals(8.0,sd.getSdSkillCompetency());
	}

	@Test
	void testComputeSDGettingFirstNSecondPref() throws InvalidMemberException, StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IncorrectRankException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team1 = new Team(proj,sif1,sif2,sif3,sif4);
		

		hm.addTeam(team1);
		
		proj = new Project("Pr10","Test Project","Only a test","Own2",4,3,2,1);
		StudentInfo si5 = new StudentInfo(5,4,4,4,4,'A');
		StudentInfoWithPrefs sif5 = new StudentInfoWithPrefs(si5,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si6 = new StudentInfo(6,4,4,4,4,'B');
		StudentInfoWithPrefs sif6 = new StudentInfoWithPrefs(si6,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si7 = new StudentInfo(7,4,4,4,4,'C');
		StudentInfoWithPrefs sif7 = new StudentInfoWithPrefs(si7,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si8 = new StudentInfo(8,4,4,4,4,'C');
		StudentInfoWithPrefs sif8 = new StudentInfoWithPrefs(si8,"Pr1","Pr2","Pr3","Pr4");
		
		Team team2 = new Team(proj,sif5,sif6,sif7,sif8);
		hm.addTeam(team2);
		hm.computeSDGettingFirstNSecondPref();

		//100% vs 0%
		//Evaluates to 50%
		assertEquals(0.50,sd.getSdGettingFirstNSecondPref());
	}
	
	@Test
	void testComputeSDGettingFirstNSecondPrefNegative() throws IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, InvalidMemberException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team1 = new Team(proj,sif1,sif2,sif3,sif4);
		
		hm.addTeam(team1);
		
		proj = new Project("Pr2","Test Project","Only a test","Own2",4,3,2,1);
		StudentInfo si5 = new StudentInfo(5,4,4,4,4,'A');
		StudentInfoWithPrefs sif5 = new StudentInfoWithPrefs(si5,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si6 = new StudentInfo(6,4,4,4,4,'B');
		StudentInfoWithPrefs sif6 = new StudentInfoWithPrefs(si6,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si7 = new StudentInfo(7,4,4,4,4,'C');
		StudentInfoWithPrefs sif7 = new StudentInfoWithPrefs(si7,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si8 = new StudentInfo(8,4,4,4,4,'C');
		StudentInfoWithPrefs sif8 = new StudentInfoWithPrefs(si8,"Pr1","Pr2","Pr3","Pr4");
		
		
		Team team2 = new Team(proj,sif5,sif6,sif7,sif8);
		hm.addTeam(team2);
		hm.computeSDGettingFirstNSecondPref();
		
		//100% vs 100%
		//Evaluates to 0%
		
		assertEquals(0.0,sd.getSdGettingFirstNSecondPref());
	}

	@Test
	void testComputeSDSkillShortfall() throws IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, InvalidMemberException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,4,4,4,4,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,4,4,4,4,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,4,4,4,4,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,4,4,4,4,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team1 = new Team(proj,sif1,sif2,sif3,sif4);
		

		hm.addTeam(team1);
		
		proj = new Project("Pr2","Test Project","Only a test","Own2",4,3,2,1);
		StudentInfo si5 = new StudentInfo(5,4,4,4,4,'A');
		StudentInfoWithPrefs sif5 = new StudentInfoWithPrefs(si5,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si6 = new StudentInfo(6,4,4,4,4,'B');
		StudentInfoWithPrefs sif6 = new StudentInfoWithPrefs(si6,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si7 = new StudentInfo(7,4,4,4,4,'C');
		StudentInfoWithPrefs sif7 = new StudentInfoWithPrefs(si7,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si8 = new StudentInfo(8,4,4,4,4,'C');
		StudentInfoWithPrefs sif8 = new StudentInfoWithPrefs(si8,"Pr1","Pr2","Pr3","Pr4");
		
		
		Team team2 = new Team(proj,sif5,sif6,sif7,sif8);
		hm.addTeam(team2);
		hm.computeSDSkillShortfall();
		
		//100 vs 100
		//Evaluates to 0
		
		assertEquals(0.0,sd.getSdShortfall());
	}

	@Test
	void testComputeSDSkillShortfallNegative() throws IncorrectRankException, IncorrectRangeException, IncorrectPersonalityTypeException, IncorrectGradeException, StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, InvalidMemberException, NumberFormatException, IDOutOfRangeException {
		Project proj = new Project("Pr1","Test Project","Only a test","Own1",4,3,2,1);
		StudentInfo si1 = new StudentInfo(1,0,0,0,0,'A');
		StudentInfoWithPrefs sif1 = new StudentInfoWithPrefs(si1,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si2 = new StudentInfo(2,0,0,0,0,'B');
		StudentInfoWithPrefs sif2 = new StudentInfoWithPrefs(si2,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si3 = new StudentInfo(3,0,0,0,0,'C');
		StudentInfoWithPrefs sif3 = new StudentInfoWithPrefs(si3,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si4 = new StudentInfo(4,0,0,0,0,'D');
		StudentInfoWithPrefs sif4 = new StudentInfoWithPrefs(si4,"Pr1","Pr2","Pr3","Pr4");
		Team team1 = new Team(proj,sif1,sif2,sif3,sif4);
		
		
		hm.addTeam(team1);
		
		proj = new Project("Pr2","Test Project","Only a test","Own2",4,3,2,1);
		StudentInfo si5 = new StudentInfo(5,4,3,2,1,'A');
		StudentInfoWithPrefs sif5 = new StudentInfoWithPrefs(si5,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si6 = new StudentInfo(6,4,3,2,1,'B');
		StudentInfoWithPrefs sif6 = new StudentInfoWithPrefs(si6,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si7 = new StudentInfo(7,4,3,2,1,'C');
		StudentInfoWithPrefs sif7 = new StudentInfoWithPrefs(si7,"Pr1","Pr2","Pr3","Pr4");
		StudentInfo si8 = new StudentInfo(8,4,3,2,1,'C');
		StudentInfoWithPrefs sif8 = new StudentInfoWithPrefs(si8,"Pr1","Pr2","Pr3","Pr4");
		
		Team team2 = new Team(proj,sif5,sif6,sif7,sif8);
		hm.addTeam(team2);
		hm.computeSDSkillShortfall();
		
		//10 vs 0
		//Evaluates to 5
	
		assertEquals(5.0,sd.getSdShortfall());
	}

}
