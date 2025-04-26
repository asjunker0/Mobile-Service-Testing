import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Calendar;

public class AppointmentTest {
	
	private Date futureDate;
	
	@BeforeEach
	void setup() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 2); // Creates calendar and future date
		futureDate = cal.getTime(); // sets date
	}
	
	// Appointment creation tests
	
	@Test
	void testValidAppointmentCreation() {
		Appointment appointment = new Appointment("123", futureDate, "Some description");
		assertEquals("123", appointment.getAppointmentID());
		assertEquals(futureDate, appointment.getDate());
		assertEquals("Some description", appointment.getDescription());
	}
	
	@Test
	void testAppointmentNullID() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Appointment(null, futureDate, "Some description");
	    });
	}
	
	@Test
	void testAppointmentNullDate() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Appointment("123", null, "Some description");
	    });
	}
	
	@Test
	void testAppointmentNullDescription() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Appointment("123", futureDate, null);
	    });
	}
	
	@Test
	void testAppointmentIDTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("12345678901", futureDate, "Some description");
		});
	}
	
	@Test
	void testAppointmentPastDate() {
		// Calendar and date creation
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1); // 1 day in the past
		Date pastDate = cal.getTime();
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("123", pastDate, "Some description");
		});
	}
	
	@Test
	void testAppointmentDescriptionTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("123", futureDate, "This description has over 50 characters and is not valid");
		});
	}
	
	// Setter tests
	@Test
	void testSetDate() {
		// Calendar and date creation
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 3); // 3 days into future
		Date newDate = cal.getTime();
		
		Appointment appointment = new Appointment("123", futureDate, "Some description");
		appointment.setDate(newDate);
		assertEquals(newDate, appointment.getDate());
	}
	
	@Test
	void testSetDescription() {
		Appointment appointment = new Appointment("123", futureDate, "Some description");
		appointment.setDescription("New description");
		assertEquals("New description", appointment.getDescription());
	}
	
	// Null Setter tests
	@Test
	void testSetNullDate() {
		assertThrows(IllegalArgumentException.class, () ->{
			Appointment appointment = new Appointment("123", futureDate, "Some description");
			appointment.setDate(null);
		});
	}
	
	@Test
	void testSetNullDescription() {
		assertThrows(IllegalArgumentException.class, () ->{
			Appointment appointment = new Appointment("123", futureDate, "Some description");
			appointment.setDescription(null);
		});
	}
	
	
}
