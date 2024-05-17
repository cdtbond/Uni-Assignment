package Objects;

public class ProjectObjectiveFunction {

	
		private String projectId;
		private double objectiveFunction;
		
		public ProjectObjectiveFunction(String projectId,double objectiveFunction) {
			this.projectId = projectId;
			this.objectiveFunction = objectiveFunction;
		}
		
		
		public String getProjectId() {
			return projectId;
		}
		
		public double getObjectiveFunction() {
			return objectiveFunction;
		}
		
		public boolean smallerThanExistingObjectiveFunction(double newObjectiveFunction) {
			return newObjectiveFunction < objectiveFunction;
		}
		
		public boolean projectEquals(String projectId) {
			return this.projectId.equals(projectId);
		}
}
