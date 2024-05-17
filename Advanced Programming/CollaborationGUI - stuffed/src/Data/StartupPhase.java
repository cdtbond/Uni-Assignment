package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import Exceptions.IDOutOfRangeException;
import Exceptions.IdAlreadyUsedException;
import Exceptions.InvalidSKeyException;
import Objects.Project.Company;
import Objects.Project.Owner;
import Objects.Project.Project;
import Objects.Student.Preferences;
import Objects.Student.Student;
import Objects.Student.StudentInfo;
import Objects.Supporting.PrefCount;
import Objects.Supporting.PrefCountSorter;

public class StartupPhase implements Serializable {
	
	private static final long serialVersionUID = -8316878917403037464L;
	private HashMap<String,Company> companies = new HashMap<String, Company>();
	private HashMap<String,Owner> owners = new HashMap<String, Owner>();
	private HashMap<String,Project> projects = new HashMap<String, Project>();
	private HashMap<String,Student> students = new HashMap<String, Student>();
	private HashMap<String,StudentInfo> studentInfo = new HashMap<String, StudentInfo>();
	private HashMap<String,Preferences> prefs = new HashMap<String,Preferences>();
	private ArrayList<PrefCount> prefCounts = new ArrayList<PrefCount>();
	private int studentCount = 0;
	private int projectsCount = 0;

	private static StartupPhase _instance;
	
	@FunctionalInterface
	interface MyInterface{ 
		double display(ArrayList<Double> numbers);
	}
	
	private StartupPhase() {}
	public static synchronized StartupPhase getSingleton() {
		if(_instance == null) {
			_instance = new StartupPhase();
		}
		return _instance;
	}
	
	
	
	public void setCompanies(HashMap<String,Company> comps) {
		companies.putAll(comps);
	}
	public void setOwners(HashMap<String,Owner> own) {
		owners.putAll(own);
	}
	public void setStudentInfo(HashMap<String,StudentInfo> sInfo) {
		studentInfo.putAll(sInfo);
	}
	public void setProjectInfo(HashMap<String,Project> projs) {
		projects.putAll(projs);
	}
	public void setStudents(HashMap<String,Student> studs) {
		students.putAll(studs);
	}
	public void setPreferences(HashMap<String,Preferences> pref) {
		prefs.putAll(pref);
	}
	public String stringProjects() {
		String outOutput = "";
		for(Map.Entry entry : projects.entrySet()) {
			 outOutput += entry.getValue().toString() + "\n";
		}
		return outOutput;
	}
	   
	   //adds all the Companies into a ArrayList that can be written to a database
	   public ArrayList<Company> getArrayCompanies() {
			   ArrayList<Company> values = new ArrayList<>(companies.values());
			   return values;  
	}
	   //adds all the Projects into a ArrayList that can be written to a database
	   public ArrayList<Project> getArrayProjects() {
			   ArrayList<Project> values = new ArrayList<>(projects.values());
			   return values;  
	}
	   //adds all the Projects into a ArrayList that can be written to a database
	   public ArrayList<Owner> getArrayOwners() {
			   ArrayList<Owner> values = new ArrayList<>(owners.values());
			   return values;  
	}

		public String stringStudents() {
			String outOutput = "";
			for(Map.Entry entry : students.entrySet()) {
				 outOutput += entry.getValue().toString() + "\n";
			}
			return outOutput;
		}
		public String stringCompanies() {
			String outOutput = "";
			for(Map.Entry entry : companies.entrySet()) {
				 outOutput += entry.getValue().toString() + "\n";
			}
			return outOutput;
		}
		public String stringOwners() {
			String outOutput = "";
			for(Map.Entry entry : owners.entrySet()) {
				 outOutput += entry.getValue().toString() + "\n";
			}
			return outOutput;
		}
	 	public String stringStudentInfo() {
			String outOutput = "";
			for(Map.Entry entry : studentInfo.entrySet()) {
				 outOutput += entry.getValue().toString() + "\n";
			}
			return outOutput;
		}
		public String stringStudentPreferences() {
			String outOutput = "";
			for(Map.Entry entry : prefs.entrySet()) {
				 outOutput += entry.getValue().toString() + "\n";
			}
			return outOutput;
		}

		public String stringStudentIds() {
			String outOutput = "";
			Iterator iter = students.entrySet().iterator();
			while (iter.hasNext()) {
		        Map.Entry pair = (Map.Entry)iter.next();
		        System.out.println(pair.getKey());
			}
			return outOutput;
		}
		
		public void addPreferences(Preferences prefs) throws IdAlreadyUsedException {
			prefsDuplicate(prefs.getID());
			this.prefs.put(prefs.getID(), prefs);
			
			addFour(prefs.getProjectPreferences().get(0));
			addThree(prefs.getProjectPreferences().get(1));
			addTwo(prefs.getProjectPreferences().get(2));
			addOne(prefs.getProjectPreferences().get(3));
		}
		public ArrayList<String> returnKeysToRemove(Integer qtyToRemove) {
			
			PrefCountSorter prefCountSorter = new PrefCountSorter(prefCounts);
			ArrayList<PrefCount> sortedPrefCount = prefCountSorter.getSortedPrefCountByValue();
			
			ArrayList<String> projectsToRemove = new ArrayList<String>();
			
			for(int i=0;i<qtyToRemove;i++) {
				projectsToRemove.add(sortedPrefCount.get(i).getID());
			}
			
			return projectsToRemove;
		}
		
		public String stringPrefCount() {
			String outOutput = "";
			for (int i=0;i<prefCounts.size();i++) {
				 outOutput += prefCounts.get(i).toString() + "\n";
			}
			return outOutput;
		}
		

		
		public void addFour(String str) {
			
			for (int i = 0;i<prefCounts.size();i++) {
				if (prefCounts.get(i).getID()==str) {
					Integer value = prefCounts.get(i).getCount();
					value += 4;
					prefCounts.get(i).setCount(value);
				}
			}
		}
		
		public void addThree(String str) {
			for (int i = 0;i<prefCounts.size();i++) {
				if (prefCounts.get(i).getID()==str) {
					Integer value = prefCounts.get(i).getCount();
					value += 3;
					prefCounts.get(i).setCount(value);
				}
			}
		}

		public void addTwo(String str) {
			for (int i = 0;i<prefCounts.size();i++) {
				if (prefCounts.get(i).getID()==str) {
					Integer value = prefCounts.get(i).getCount();
					value += 2;
					prefCounts.get(i).setCount(value);
				}
			};
		}
		
		public void addOne(String str) {
			
			for (int i = 0;i<prefCounts.size();i++) {
				if (prefCounts.get(i).getID()==str) {
					Integer value = prefCounts.get(i).getCount();
					value += 1;
					prefCounts.get(i).setCount(value);
				}
			}
		}
		public void hasKey(String key) throws InvalidSKeyException {
			if (students.containsKey(key)) {	
			}
			else {
				throw new InvalidSKeyException();
			}
				
		}
		
		public Student getStudent(String key) throws InvalidSKeyException {
			hasKey(key);
			return students.get(key);
		}
		
		public void addCompany(Company comp) throws IdAlreadyUsedException {
			companiesDuplicate(comp.getID());
			companies.put(comp.getID(), comp);
		}
		public void addOwner(Owner own) throws IdAlreadyUsedException {
			ownersDuplicate(own.getID());
			owners.put(own.getID(), own);
		}
		public void addStudInfo(StudentInfo sInfo) throws IdAlreadyUsedException {
			prefsDuplicate(sInfo.getID());
			studentInfo.put(sInfo.getID(), sInfo);
		}	
		
		public void addProject(Project proj) throws IdAlreadyUsedException, IDOutOfRangeException {
			projectDuplicate(proj.getID());
			projects.put(proj.getID(), proj);
			PrefCount pc = new PrefCount(proj.getID(),0);
			prefCounts.add(pc);
			projectsCount = projects.size();
		}
		
		public void addStudents(Student proj) throws IdAlreadyUsedException {
			studentsDuplicate(proj.getID());
			students.put(proj.getID(), proj);
			setStudentCount(students.size());
		}
		public void projectDuplicate(String ID) throws IdAlreadyUsedException {
			if (projects.containsKey(ID)) {
			throw new IdAlreadyUsedException();
			}	
	}	
	
	public void companiesDuplicate(String ID) throws IdAlreadyUsedException {
		if (companies.containsKey(ID)) {
			throw new IdAlreadyUsedException();
		}		
}
	
	
	public void ownersDuplicate(String ID) throws IdAlreadyUsedException {
		if (owners.containsKey(ID)) {
			throw new IdAlreadyUsedException();
		}		
}	
	
	
	public void prefsDuplicate(String ID) throws IdAlreadyUsedException {
		if (prefs.containsKey(ID)) {
			throw new IdAlreadyUsedException();
		}		
}

	
	public void studentsDuplicate(String ID) throws IdAlreadyUsedException {
		if (students.containsKey(ID)) {
			throw new IdAlreadyUsedException();
		}		
}

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public Integer getProjectsCount() {
		return projectsCount;
	}

	public void setProjectsCount(Integer projectsCount) {
		this.projectsCount = projectsCount;
	}


	public HashMap<String, Company> getCompanies() {
		return companies;
	}

	public HashMap<String, Owner> getOwners() {
		return owners;
	}


	public HashMap<String, StudentInfo> getStudentInfo() {
		return studentInfo;
	}
	public Map<String, Project> getProjects() {
		return projects;
	}
	public HashMap<String, Preferences> getPrefs() {
		return prefs;
	}
	
}
