
public class Task {
	// Attributes
		private final String taskID;
		private String name;
		private String description;
		
		// Constructor
		public Task(String taskID,String name, String description){
			// Constraints
			if (taskID == null || taskID.length() > 10) {
				throw new IllegalArgumentException("Invalid task ID, cannot be null or greater than 10 characters");
			}
			if (name == null || name.length() > 20) {
				throw new IllegalArgumentException("Invalid name, cannot be null or greater than 20 characters");
			}
			if (description == null || description.length() > 50) {
				throw new IllegalArgumentException("Invalid description, cannot be null or greater than 30 characters");
			}
			
			this.taskID = taskID;
			this.name = name;
			this.description = description;
		}
		
		// Only getter, its unable to be edited
		public String getTaskID() {
			return taskID;
		}
		
		// Getters and setters
		// name
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			if(name == null || name.length() > 20) {
				throw new IllegalArgumentException("Invalid name, cannot be null or greater than 10 characters");
			}
			this.name = name;
		}
		
		// description
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			if(description == null || description.length() > 50) {
				throw new IllegalArgumentException("Invalid description, cannot be null or greater than 50 characters");
			}
			this.description = description;
		}
}
