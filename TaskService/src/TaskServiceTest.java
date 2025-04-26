import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {
	TaskService service;
	Task task;
	
	@BeforeEach
	void setup() {
		service = new TaskService();
		task = new Task("123", "Name", "Description");
	}
	
	@Test
	void testAddTask() {
		service.addTask(task);
	}
	
	@Test
	void testAddDuplicateTaskID() {
		service.addTask(task);
		// Try to add task with same ID
		assertThrows(IllegalArgumentException.class, ()-> {
			service.addTask(new Task("123", "Name", "Description"));
		});
	}
	
	@Test
	void testDeleteTask() {
		service.addTask(task);
		service.deleteTask("123");
	}
	
	@Test
	void testDeleteTaskNotFound() {
		service.addTask(task);
		assertThrows(IllegalArgumentException.class, () -> {
			service.deleteTask("000");
		});
	}
	
	@Test
	void testUpdateName() {
		service.addTask(task);
		service.updateName("123", "New Name");
		assertEquals("New Name", task.getName());
	}
	
	@Test
	void testUpdateDescription() {
		service.addTask(task);
		service.updateDescription("123", "New Description");
		assertEquals("New Description", task.getDescription());
	}
}
