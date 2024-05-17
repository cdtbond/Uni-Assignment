package SharedValues;

public class Count {
	private int studentCount = 0;
	private int projectsCount = 0;
	
	private static Count _instance;
	private Count() {}
	public static synchronized Count getSingleton() {
		if(_instance == null) {
			_instance = new Count();
		}
		return _instance;
	}
	public Integer getStudentCount() {
		return studentCount;
	}
	public Integer getProjectCount() {
		return projectsCount;
	}
	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
	public void setProjectCount(int projectsCount) {
		this.projectsCount = projectsCount;
	}
}
