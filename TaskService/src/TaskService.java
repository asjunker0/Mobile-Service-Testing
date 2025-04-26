import java.util.ArrayList;
import java.util.List;

public class TaskService {
	// Creates list of tasks
	private List<Task> tasks = new ArrayList<>();
	
	// Add a task to list
	public void addTask(Task task) {
		// Verifies that ID is unique
		for (Task t : tasks) {
			if (t.getTaskID().equals(task.getTaskID())) {
				throw new IllegalArgumentException("Task ID already exists");
			}
		}
		tasks.add(task);
	}
	
	// Delete a task by ID
	public void deleteTask(String taskID) {
		boolean removed = tasks.removeIf(c -> c.getTaskID().equals(taskID));
		if (!removed) {
			throw new IllegalArgumentException("Task not found");
		}
	}
	
	// Update name
	public void updateName(String taskID, String newName) {
		Task task = findTaskByID(taskID);
		task.setName(newName);
	}
	
	// Update description
	public void updateDescription(String taskID, String newDescription) {
		Task task = findTaskByID(taskID);
		task.setDescription(newDescription);
	}
	
	// Helper function to find task by ID
	private Task findTaskByID(String taskID) {
		for (Task t : tasks) {
			if(t.getTaskID().equals(taskID)) {
				return t;
			}
		}
		throw new IllegalArgumentException("Task ID not found");
	}
}
