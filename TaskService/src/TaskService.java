import java.util.HashMap;
import java.util.Map;

public class TaskService {
	// Creates hash map of tasks
	private final Map<String, Task> tasks = new HashMap<>();
	
	// Add a task to list
	public void addTask(Task task) {
        if (task == null || task.getTaskID() == null) {
            throw new IllegalArgumentException("Task and task ID are required");
        }

        if (tasks.containsKey(task.getTaskID())) {
            throw new IllegalArgumentException("Task ID already exists");
        }

        tasks.put(task.getTaskID(), task);
	}

    // Retrieve a task by ID
    public Task getTask(String taskID) {
        return tasks.get(taskID);
    }
	
	// Delete a task by ID
	public void deleteTask(String taskID) {
        if (tasks.remove(taskID) == null) {
            throw new IllegalArgumentException("Task not found");
        }
	}
	
	// Update name
	public void updateName(String taskID, String newName) {
		Task task = getTask(taskID);
		task.setName(newName);
	}
	
	// Update description
	public void updateDescription(String taskID, String newDescription) {
		Task task = getTask(taskID);
		task.setDescription(newDescription);
	}

}
