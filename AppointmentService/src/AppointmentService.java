import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
	// Creates list of appointments
	private List<Appointment> appointments = new ArrayList<>();
	
	// Add an appointment to list
	public void addAppointment(Appointment appointment) {
		// Verifies that ID is unique
		for (Appointment a : appointments) {
			if (a.getAppointmentID().equals(appointment.getAppointmentID())) {
				throw new IllegalArgumentException("Appointment ID already exists");
			}
		}
		appointments.add(appointment);
	}

	// Delete a appointment from list
	public void deleteAppointment(String appointmentID) {
		boolean removed = appointments.removeIf(a -> a.getAppointmentID().equals(appointmentID));
		if (!removed) {
			throw new IllegalArgumentException("Appointment not found");
		}
	}
}
