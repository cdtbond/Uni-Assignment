package Menu;
import java.sql.SQLException;

import Data.HashMaps;
import Exceptions.ABNLengthIncorrectException;
import Exceptions.ABNValuesIncorrectException;
import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IdAlreadyUsedException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidNamingException;
import Objects.Project.Company;
import Objects.Project.Owner;
import Objects.Project.Project;
import Objects.Student.Preferences;
import Objects.Student.Student;
import Objects.Student.StudentInfo;
import dataStorage.HandleFile;
import dataStorage.HandleSerialization;

public class HardCodedEntries extends Menu {
	public void buildFromScratch(HandleFile hf, HandleSerialization hs) {
		//create an empty set of Hashmaps
		HashMaps hm = HashMaps.getSingleton();
		try {
		 Integer max = 5;
		 
		 Company c1 = new Company(1,"Trolly Ltd","93906092521","www.TrollyLtd.com.au","27 Parkes Road, Melbourne");
		 Company c2 = new Company(2,"Tisk Tisk","51901921060","www.Tisk.com.au","40 Hart Street, Parkville");
		 Company c3 = new Company(3,"Elevator King","99482033040","www.ElevatorK.com.au","50 Quayside Vista, Parkes");
		 Company c4 = new Company(4,"Program No1","86263161397","www.ProgramNo1.com.au","23 Yarra Street, Clunes");
		 Company c5 = new Company(5,"Retro Game Shop","11442168264","www.RetroGameShop.com.au","65 Cecil Street, Macquarie Park");
		 Company c6 = new Company(6,"wrens&wrens","12186027250","www.wrens&wrens.com.au","9 Hay St, West Ryde");
		 Company c7 = new Company(7,"services to art","34971720936","www.servicesToArt.com.au","12 Ulm St, Ermington");
		 Company c8 = new Company(8,"broadcast key","06231380528","www.broadcastkey.com.au","308 Mary St, Richmond");
		 Company c9 = new Company(9,"codixy","27913825155","www.codixy.com.au","21 Mackey St, Surry Hills");
		 Company c10 = new Company(10,"Tv Tools","39927809024","www.tvtools.com.au","60 Abingdon St, Woolloongabba");
		 Company c11 = new Company(11,"blurring code","20680360892","www.blurringcode.com.au","13 Bellevue St, North Parramatta");
		 Company c12 = new Company(12,"Cubing","60001091070","www.cubing.com.au","49 Waminda Av, Campbelltown");
		 Company c13 = new Company(13,"jamally high","01574383809","www.jamally high.com.au","5 Solander St, Ruse");
		 Company c14 = new Company(14,"Harris Agents","73557906487","www.harrisagents.com.au","19 Ruse St, Harris Park");
		 Company c15 = new Company(15,"Phenom Codey","31920247876","www.phenomcodey.com.au","303 Mount Annan Dr, Mt Annan");
		 Company c16 = new Company(16,"Botonix","80554167724","www.Botonix.com.au","8 Johnston St, Thomastown");
		 Company c17 = new Company(17,"Geny Code","41764104456","www.GenyCode.com.au","8 Peppermint Gr, Cairnlea");
		 Company c18 = new Company(18,"Circy Circ","76103776654","www.circycirc.com.au","27 Garnet Way, Taylors Hill");
		 Company c19 = new Company(19,"Solid Gate","47493404323","www.solidgate.com.au","26 Muddy La, Myrniong");
		 Company c20 = new Company(20,"Brick by Brick","39822540380","www.brickbybrick.com.au","853 Morrisons La, Korobeit");
		
		 Owner own1 = new Owner(1,"Devon Smith","Solution Architect","Devon.Smith@cubing.com.au","Pr1","C12");
		 Owner own2 = new Owner(2,"Terry Lu","HR Specialist","Terry.Lu@Tisk.com.au","Pr2","C2");
		 Owner own3 = new Owner(3,"Jonathon McCoy","Head of Marketing","Jonathon.McCoy@harrisagents.com.au","Pr5","C14");
		 Owner own4 = new Owner(4,"Michelle Jackman","CIO","Michelle.Jackman@solidgate.com.au","Pr6","C19");
		 Owner own5 = new Owner(5,"Christine Moses","Network Engineer","Christine.Moses@ProgramNo1.com.au","Pr3","C4");
		 Owner own6 = new Owner(6,"Trixy Johnson","Angel Investor","Trixy.Johnson@ElevatorK.com.au","Pr15","C3");
		 Owner own7 = new Owner(7,"Obella Christopher","Head of IT","Obella.Christopher@circycirc.com.au","Pr4","C18");
		 Owner own8 = new Owner(8,"Tina Arthur","Gaming Developer","Tina.Arthur@Botonix.com.au","Pr20","C16");
		 Owner own9 = new Owner(9,"Abel Tron","General Manager of Security","Abel.Tron@GenyCode.com.au","Pr16","C17");
		 Owner own10 = new Owner(10,"Jeremy Steer","Company Soothsayer","Jeremy.Steer@codixy.com.au","Pr9","C9");
		 Owner own11 = new Owner(11,"Michael Johns","Network Architect","Michael.Johns@wrens&wrens.com.au","Pr17","C6");
		 Owner own12 = new Owner(12,"Reginald Revesby","Security Architect","Reginald.Revesby@blurringcode.com.au","Pr8","C11");
		 Owner own13 = new Owner(13,"Bruce Wang","Security Engineer","Bruce.Wang@tvtools.com.au","Pr14","C10");
		 Owner own14 = new Owner(14,"Andrew Chan","Security Assessor","Andrew.Chan@TrollyLtd.com.au","Pr19","C1");
		 Owner own15 = new Owner(15,"Jillian Murphy","Project Manager","Jillian.Murphy@servicesToArt.com.au","Pr10","C7");
		 Owner own16 = new Owner(16,"Marion O'Connor","Web Developer","Marion.O'Connor@brickbybrick.com.au","Pr11","C20");
		 Owner own17 = new Owner(17,"William Smythe","Automation Engineer","William.Smythe@RetroGameShop.com.au","Pr18","C5");
		 Owner own18 = new Owner(18,"Paddy O'Rourke","Java Developer","Paddy.O'Rourke@broadcastkey.com.au","Pr12","C8");
		 Owner own19 = new Owner(19,"Everly Tran","CEO","Everly.Tran@jamally high.com.au","Pr7","C13");
		 Owner own20 = new Owner(20,"Kristian Thompson","Venture Capitalist","Kristian.Thompson@phenomcodey.com.au","Pr13","C15");

		 Preferences pref1 = new Preferences(1 ,4,18,7,5);
		 Preferences pref2 = new Preferences(2 ,8,4,14,20);
		 Preferences pref3 = new Preferences(3 ,14,19,6,12);
		 Preferences pref4 = new Preferences(4 ,2,10,13,12);
		 Preferences pref5 = new Preferences(5 ,15,6,4,5);
		 Preferences pref6 = new Preferences(6 ,14,5,18,19);
		 Preferences pref7 = new Preferences(7 ,1,9,14,11);
		 Preferences pref8 = new Preferences(8 ,8,4,11,10);
		 Preferences pref9 = new Preferences(9 ,18,10,3,9);
		 Preferences pref10 = new Preferences(10 ,5,10,6,9);
		 Preferences pref11 = new Preferences(11 ,12,4,11,15);
		 Preferences pref12 = new Preferences(12,15,12,10,9);
		 Preferences pref13 = new Preferences(13 ,16,20,8,11);
		 Preferences pref14 = new Preferences(14 ,13,20,9,1);
		 Preferences pref15 = new Preferences(15 ,14,9,4,16);
		 Preferences pref16 = new Preferences(16,5,6,13,2);
		 Preferences pref17 = new Preferences(17,20,16,19,5);
		 Preferences pref18 = new Preferences(18,19,12,4,5);
		 Preferences pref19 = new Preferences(19,7,5,2,18);
		 Preferences pref20 = new Preferences(20,17,14,3,8);

		 Student stud1 = new Student(1,2,1,2,3);
		 Student stud2 = new Student(2,0,2,3,3);
		 Student stud3 = new Student(3,3,2,1,3);
		 Student stud4 = new Student(4,2,3,1,4);
		 Student stud5 = new Student(5,3,4,0,3);
		 Student stud6 = new Student(6,3,3,2,3);
		 Student stud7 = new Student(7,2,2,3,1);
		 Student stud8 = new Student(8,0,2,1,2);
		 Student stud9 = new Student(9,1,3,4,1);
		 Student stud10 = new Student(10,2,0,3,4);
		 Student stud11 = new Student(11,0,3,2,0);
		 Student stud12 = new Student(12,0,3,3,4);
		 Student stud13 = new Student(13,0,2,4,4);
		 Student stud14 = new Student(14,4,1,1,2);
		 Student stud15 = new Student(15,1,3,2,2);
		 Student stud16 = new Student(16,0,4,4,1);
		 Student stud17 = new Student(17,2,0,4,3);
		 Student stud18 = new Student(18,0,3,4,2);
		 Student stud19 = new Student(19,2,2,1,2);
		 Student stud20 = new Student(20,4,4,4,1);

		 StudentInfo studInfo1 = new StudentInfo(1,2,1,2,3,'C',"S4","S14"); 
		 StudentInfo studInfo2 = new StudentInfo(2,0,2,3,3,'A'); 
		 StudentInfo studInfo3 = new StudentInfo(3,3,2,1,3,'A',"S12","S1");
		 StudentInfo studInfo4 = new StudentInfo(4,2,3,1,4,'C');
		 StudentInfo studInfo5 = new StudentInfo(5,3,4,0,3,'A',"S12","S11");
		 StudentInfo studInfo6 = new StudentInfo(6,3,3,2,3,'C');
		 StudentInfo studInfo7 = new StudentInfo(7,2,2,3,1,'A',"S18");
		 StudentInfo studInfo8 = new StudentInfo(8,0,2,1,2,'D');
		 StudentInfo studInfo9 = new StudentInfo(9,1,3,4,1,'A');
		 StudentInfo studInfo10 = new StudentInfo(10,2,0,3,4,'A');
		 StudentInfo studInfo11 = new StudentInfo(11,0,3,2,0,'C');
		 StudentInfo studInfo12 = new StudentInfo(12,0,3,3,4,'C');
		 StudentInfo studInfo13 = new StudentInfo(13,0,2,4,4,'A');
		 StudentInfo studInfo14 = new StudentInfo(14,4,1,1,2,'B');
		 StudentInfo studInfo15 = new StudentInfo(15,1,3,2,2,'B');
		 StudentInfo studInfo16 = new StudentInfo(16,0,4,4,1,'D'); 
		 StudentInfo studInfo17 = new StudentInfo(17,2,0,4,3,'D');
		 StudentInfo studInfo18 = new StudentInfo(18,0,3,4,2,'B');
		 StudentInfo studInfo19 = new StudentInfo(19,2,2,1,2,'D');
		 StudentInfo studInfo20 = new StudentInfo(20,4,4,4,1,'B');

		 
		 Project Pr1 = new Project(1,"Customer Relationship Management (CRM)","CRM to be conducted by team of 4","Own6",1,2,3,4);
		 Project Pr2 = new Project(2,"Microservices refactoring","Microservices replatform to be conducted by team of 4","Own18",4,1,3,2);
		 Project Pr3 = new Project(3,"Migration to Azure Cloud","Cloud Migration to be conducted by team of 4","Own10",2,3,1,4);
		 Project Pr4 = new Project(4,"Improving DevOps ToolKit","DevOps Tooling assessment to be conducted by team of 4","Own11",1,4,2,3);
		 Project Pr5 = new Project(5,"Moving to CICD","Build Pipeline to be implemented by team of 4","Own13",4,2,1,3);
		 Project Pr6 = new Project(6,"Inventory system uplift","Inventory System Upgrade to be conducted by team of 4","Own16",2,4,3,1);
		 Project Pr7 = new Project(7,"Benefits management software implementation","Benefits Management software to be implemented by team of 4","Own15",1,3,4,2);
		 Project Pr8 = new Project(8,"Project Tracking tool","Project tracking to be implemented by team of 4","Own20",4,3,2,1);
		 Project Pr9 = new Project(9,"Disk Quota tooling","Disk Quota tool to be implemented by team of 4","Own7",2,1,4,3);
		 Project Pr10 = new Project(10,"Project Collaboration tool","Collaboration platform to be implemented by team of 4","Own17",1,2,3,4);
		 Project Pr11 = new Project(11,"Training System Implementation","Employee Training system to be implemented by team of 4","Own4",4,1,3,2);
		 Project Pr12 = new Project(12,"Assessment of Cloud Pricing","Cloud Pricing Assessment to be conducted by team of 4","Own8",2,3,1,4);
		 Project Pr13 = new Project(13,"Fitout Network Architecture","New Office Network Architecture to be conducted by team of 4","Own12",1,4,2,3);
		 Project Pr14 = new Project(14,"Penetration Testing","Pen Test to be conducted by team of 4","Own9",4,2,1,3);
		 Project Pr15 = new Project(15,"Cost Savings - Network","Network cost saving assessment to be conducted by team of 4","Own3",2,4,3,1);
		 Project Pr16 = new Project(16,"Assessment of Security Posture","Security assessment to be conducted by team of 4","Own2",1,3,4,2);
		 Project Pr17 = new Project(17,"Line Automation","Factory Line automation to be conducted by team of 4","Own14",4,3,2,1);
		 Project Pr18 = new Project(18,"Clerk processing automation","Process Automation to be conducted by team of 4","Own5",2,4,3,1);
		 Project Pr19 = new Project(19,"Implement new HR Firing System","HR System to be implemented by team of 4","Own1",1,3,4,2);
		 Project Pr20 = new Project(20,"Phenomenal - A game inspired by work","Company-inspired game to be built by team of 4","Own19",4,3,2,1);

		hm.addCompany(c1); 
		hm.addCompany(c2); 
		hm.addCompany(c3); 
		hm.addCompany(c4); 
		hm.addCompany(c5); 
		hm.addCompany(c6); 
		hm.addCompany(c7); 
		hm.addCompany(c8); 
		hm.addCompany(c9); 
		hm.addCompany(c10); 
		hm.addCompany(c11); 
		hm.addCompany(c12); 
		hm.addCompany(c13); 
		hm.addCompany(c14); 
		hm.addCompany(c15); 
		hm.addCompany(c16); 
		hm.addCompany(c17); 
		hm.addCompany(c18); 
		hm.addCompany(c19); 
		hm.addCompany(c20); 

		hm.addOwner(own1);
		hm.addOwner(own2);
		hm.addOwner(own3);
		hm.addOwner(own4);
		hm.addOwner(own5);
		hm.addOwner(own6);
		hm.addOwner(own7);
		hm.addOwner(own8);
		hm.addOwner(own9);
		hm.addOwner(own10);
		hm.addOwner(own11);
		hm.addOwner(own12);
		hm.addOwner(own13);
		hm.addOwner(own14);
		hm.addOwner(own15);
		hm.addOwner(own16);
		hm.addOwner(own17);
		hm.addOwner(own18);
		hm.addOwner(own19);
		hm.addOwner(own20);
		
		
		//student
		hm.addStudents(stud1);
		hm.addStudents(stud2);
		hm.addStudents(stud3);
		hm.addStudents(stud4);
		hm.addStudents(stud5);
		hm.addStudents(stud6);
		hm.addStudents(stud7);
		hm.addStudents(stud8);
		hm.addStudents(stud9);
		hm.addStudents(stud10);
		hm.addStudents(stud11);
		hm.addStudents(stud12);
		hm.addStudents(stud13);
		hm.addStudents(stud14);
		hm.addStudents(stud15);
		hm.addStudents(stud16);
		hm.addStudents(stud17);
		hm.addStudents(stud18);
		hm.addStudents(stud19);
		hm.addStudents(stud20);
		
		
		//studentinfo
		hm.addStudInfo(studInfo1);
		hm.addStudInfo(studInfo2);
		hm.addStudInfo(studInfo3);
		hm.addStudInfo(studInfo4);
		hm.addStudInfo(studInfo5);
		hm.addStudInfo(studInfo6);
		hm.addStudInfo(studInfo7);
		hm.addStudInfo(studInfo8);
		hm.addStudInfo(studInfo9);
		hm.addStudInfo(studInfo10);
		hm.addStudInfo(studInfo11);
		hm.addStudInfo(studInfo12);
		hm.addStudInfo(studInfo13);
		hm.addStudInfo(studInfo14);
		hm.addStudInfo(studInfo15);
		hm.addStudInfo(studInfo16);
		hm.addStudInfo(studInfo17);
		hm.addStudInfo(studInfo18);
		hm.addStudInfo(studInfo19);
		hm.addStudInfo(studInfo20);
		
		//project
		hm.addProject(Pr1);
		hm.addProject(Pr2);
		hm.addProject(Pr3);
		hm.addProject(Pr4);
		hm.addProject(Pr5);
		hm.addProject(Pr6);
		hm.addProject(Pr7);
		hm.addProject(Pr8);
		hm.addProject(Pr9);
		hm.addProject(Pr10);
		hm.addProject(Pr11);
		hm.addProject(Pr12);
		hm.addProject(Pr13);
		hm.addProject(Pr14);
		hm.addProject(Pr15);
		hm.addProject(Pr16);
		hm.addProject(Pr17);
		hm.addProject(Pr18);
		hm.addProject(Pr19);
		hm.addProject(Pr20);
		 
		
		hm.addPreferences(pref1);
		hm.addPreferences(pref2);
		hm.addPreferences(pref3);
		hm.addPreferences(pref4);
		hm.addPreferences(pref5);
		hm.addPreferences(pref6);
		hm.addPreferences(pref7);
		hm.addPreferences(pref8);
		hm.addPreferences(pref9);
		hm.addPreferences(pref10);
		hm.addPreferences(pref11);
		hm.addPreferences(pref12);
		hm.addPreferences(pref13);
		hm.addPreferences(pref14);
		hm.addPreferences(pref15);
		hm.addPreferences(pref16);
		hm.addPreferences(pref17);
		hm.addPreferences(pref18);
		hm.addPreferences(pref19);
		hm.addPreferences(pref20);
		
		}
		catch (IncorrectRangeException e) {
			
		} catch (IncorrectPersonalityTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectGradeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectRankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IdAlreadyUsedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicatePreferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IDOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ABNValuesIncorrectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ABNLengthIncorrectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainMenu menu = new MainMenu();
		menu.mainMenu(hm, hf, hs);     
	}	
}
