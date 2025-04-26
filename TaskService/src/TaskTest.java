import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskTest {
	
	@Test
	void testValidTaskCreation() {
		Task task = new Task("123", "Write test", "Write tests for assignment");
		assertEquals("123", task.getTaskID());
		assertEquals("Write test", task.getName());
		assertEquals("Write tests for assignment", task.getDescription());
	}
	
	// setter behavior tests
	@Test
	void testSetName() {
		Task task = new Task("123", "Old Name", "Description");
		task.setName("New Name");
		assertEquals("New Name", task.getName());
	}
	
	@Test
	void testSetDescription() {
		Task task = new Task("123", "Name", "Old Description");
		task.setDescription("New Description");
		assertEquals("New Description", task.getDescription());
	}
	
	// Negative tests
	@Test 
	void testTaskIDTooLong() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Task("12345678901", "Name", "Description");
		});
	}
	
	@Test 
	void testNameTooLong() { 
		assertThrows(IllegalArgumentException.class, () ->{
			new Task("123", "Very long invalid name", "Description");
		});
	}
	@Test void testDescriptionTooLong() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Task("123", "Name", "This description has over 50 characters and is not valid");
		});
	}
	@Test void testTaskIDNull() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Task(null, "Name", "Description");
		});
	}
	
	// Negative set tests
	@Test void testSetNameTooLong() { 
		assertThrows(IllegalArgumentException.class, () ->{
			Task task = new Task("123", "Name", "Description");
			task.setName("Very long invalid name");
		});
	}
	@Test void testSetDescriptionTooLong() {
		assertThrows(IllegalArgumentException.class, () ->{
			Task task = new Task("123", "Name", "Description");
			task.setDescription("This description has over 50 characters and is not valid");
		});
	}
	@Test void testSetNameNull() {
		assertThrows(IllegalArgumentException.class, () ->{
			Task task = new Task("123", "Name", "Description");
			task.setName(null);
		});
	}
	@Test void testSetDescriptionNull() {
		assertThrows(IllegalArgumentException.class, () ->{
			Task task = new Task("123", "Name", "Description");
			task.setDescription(null);
		});
	}
}
