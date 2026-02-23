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
    void testAddNullAppointment() {
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(null));
    }

    @Test
    void testAddDuplicateAppointmentID() {
        service.addAppointment(appointment);
        // Try to add appointment with same ID
        assertThrows(IllegalArgumentException.class, ()-> service.addAppointment(new Appointment("123", futureDate, "Description")));
    }


    @Test
    void testGetAppointment() {
        service.addAppointment(appointment);
        Appointment found = service.getAppointment(appointment.getAppointmentID());
        assertNotNull(found);
        assertEquals(appointment.getAppointmentID(), found.getAppointmentID());
    }

    @Test
    void testGetAppointmentNotFoundReturnsNull() {
        assertNull(service.getAppointment("000"));
    }


    @Test
    void testUpdateAppointmentDateAndDescription() {
        service.addAppointment(appointment);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        Date updatedDate = cal.getTime();

        service.updateAppointment(appointment.getAppointmentID(), updatedDate, "Updated description");
        Appointment updated = service.getAppointment(appointment.getAppointmentID());
        assertEquals(updatedDate, updated.getDate());
        assertEquals("Updated description", updated.getDescription());
    }

    @Test
    void testUpdateAppointmentNotFound() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        Date updatedDate = cal.getTime();

        assertThrows(IllegalArgumentException.class, () -> service.updateAppointment("000", updatedDate, "Updated description"));
    }

    @Test
    void testUpdateAppointmentNothingToUpdate() {
        service.addAppointment(appointment);
        assertThrows(IllegalArgumentException.class, () -> service.updateAppointment(appointment.getAppointmentID(), null, "   "));
    }

    @Test
    void testUpdateAppointmentMissingID() {
        assertThrows(IllegalArgumentException.class, () -> service.updateAppointment(" ", futureDate, "Updated description"));
    }

    @Test
    void testDeleteAppointment() {
        service.addAppointment(appointment);
        service.deleteAppointment(appointment.getAppointmentID());
    }

    @Test
    void testDeleteAppointmentNotFound() {
        service.addAppointment(appointment);
        assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("000"));
    }
}