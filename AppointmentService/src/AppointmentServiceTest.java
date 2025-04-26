import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class AppointmentServiceTest {
	AppointmentService service;
	Appointment appointment;
	private Date futureDate;
	
	@BeforeEach
	void setup() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 2); // Creates calendar and future date
		futureDate = cal.getTime(); // sets date
		
		service = new AppointmentService();
		appointment = new Appointment("123", futureDate, "Some description");
	}
	
	@Test
	void testAddAppointment() {
		service.addAppointment(appointment);
	}
	
	@Test
	void testAddDuplicateAppointmentID() {
		service.addAppointment(appointment);
		// Try to add appointment with same ID
		assertThrows(IllegalArgumentException.class, ()-> {
			service.addAppointment(new Appointment("123", futureDate, "Description"));
		});
	}
	
	@Test
	void testDeleteAppointment() {
		service.addAppointment(appointment);
		service.deleteAppointment(appointment.getAppointmentID());
	}
	
	@Test
	void testDeleteAppointmentNotFound() {
		service.addAppointment(appointment);
	    assertThrows(IllegalArgumentException.class, () -> {
	        service.deleteAppointment("000");
	    });
	}
}
