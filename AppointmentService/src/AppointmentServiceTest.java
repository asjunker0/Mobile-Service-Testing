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

    // Add appointment tests

	
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

    // Delete appointment tests
	
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

    // Update appointment tests

    @Test
    void testUpdateAppointmentDescription(){
        service.addAppointment(appointment);

        String newDescription = "New Description";
        service.updateAppointment(appointment.getAppointmentID(), appointment.getDate(), newDescription);

        assertEquals(newDescription, appointment.getDescription());
        // Dates should be the same
        assertEquals(futureDate, appointment.getDate());
    }

    @Test
    void testUpdateAppointmentDate(){
        service.addAppointment(appointment);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 10);
        Date newDate = cal.getTime();

        service.updateAppointment(appointment.getAppointmentID(), newDate, null);
        assertEquals(newDate, appointment.getDate());
        // Description should be the same
        assertEquals("Some description", appointment.getDescription());
    }

    @Test
    void testUpdateAppointmentDateAndDescription(){
        service.addAppointment(appointment);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        Date newDate = cal.getTime();

        String newDescription = "Changed both fields";
        service.updateAppointment(appointment.getAppointmentID(), newDate, newDescription);

        assertEquals(newDate, appointment.getDate());
        assertEquals(newDescription, appointment.getDescription());
    }

    @Test
    void testUpdateAppointmentNotFound(){
        service.addAppointment(appointment);

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAppointment("000", futureDate, "Does not matter");
        });
    }

    @Test
    void testUpdateAppointmentNothingToUpdate() {
        service.addAppointment(appointment);

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAppointment(appointment.getAppointmentID(), null, null);
        });
    }
}
